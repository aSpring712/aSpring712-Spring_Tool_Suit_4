package com.example.demo3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo3.model.Board;
import com.example.demo3.model.Comment;
import com.example.demo3.repository.BoardRepository;
import com.example.demo3.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final BoardRepository BoardRepository;
	private final CommentRepository commentRepository;
	
	// 댓글쓰기
	@Transactional
	public void insert(Comment comment) { // Board의 bnum을 구해와서 rplycnt 증가시켜야 함
		Optional<Board> b = BoardRepository.findById(comment.getBoard().getNum()); // board의 num이 bnum이므로
		// replycnt 증가
		b.get().setReplyCnt(b.get().getReplyCnt()+1);
		commentRepository.commentInsert( // 제공하는 쿼리 말고 직접 만들어서 사용
				comment.getBoard().getNum(), 
				comment.getContent());
		
//		commentRepository.save(comment);
	}
	
	// 댓글 조회
	@Transactional
	public List<Comment> getList(Long bnum) {
		return commentRepository.findByBnum(bnum);
	}
	
	// 댓글 삭제
	@Transactional
	public void delete(Long cnum) {
		// cnum을 이용해서 bnum 구하기
		Optional<Comment> c = commentRepository.findById(cnum);
		Board b = c.get().getBoard();
		b.setReplyCnt(b.getReplyCnt()-1);
		commentRepository.deleteById(cnum);
	}
}
