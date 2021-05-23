package com.example.demo3.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo3.model.Board;
import com.example.demo3.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	// 글쓰기
	@Transactional
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	// 글 목록 조회하기
	@Transactional
	public List<Board> list() {
		return boardRepository.findAll();
	}
	
	// 전체보기 페이징
	public Page<Board> findAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	// 상세보기 + 조회수 증가
	@Transactional
	public Board view(Long num) {
		Board board = boardRepository.findById(num).get();
		board.setHitcount(board.getHitcount()+1); // 조회수 증가
		return board;
	}
	
	// 삭제하기
	@Transactional
	public void delete(Long num) {
		boardRepository.deleteById(num);
	}
	
	// 수정하기 -> Dirty checking
	@Transactional // update라는 명령어는 없음 -> Transactional -> DB에 영향 줌 : flush
	public void update(Board board) {
		Board b = boardRepository.findById(board.getNum()).get(); // 1차 캐시에 저장되어있는 값 조회 -> 하나의 b 객체를 가져와서 b.set 해서 값 바꿔주기
		b.setTitle(board.getTitle()); 
		b.setContent(board.getContent());
	}
	
	// 글 개수
	@Transactional
	public Long count() {
		return boardRepository.count();
	}
}
