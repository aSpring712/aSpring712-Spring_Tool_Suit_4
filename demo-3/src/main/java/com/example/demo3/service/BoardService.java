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
	
	// 상세보기
	@Transactional
	public Board view(Long num) {
		Optional<Board> board = boardRepository.findById(num); 
		return board.get();
	}
	
	// 삭제하기
	@Transactional
	public void delete(Long num) {
		boardRepository.deleteById(num);
	}
	
	// 수정하기
	@Transactional
	public void update(Board board) {
		Optional<Board> brd = boardRepository.findById(board.getNum());
		Board b = brd.get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
	}
	
	// 글 개수
	@Transactional
	public Long count() {
		return boardRepository.count();
	}
	
	// 조회수 증가
	@Transactional
	public void updateHitcnt(Long num) {
		Optional<Board> brd = boardRepository.findById(num);
		Board b = brd.get();
		Long cnt = b.getHitcount()+1;
		b.setHitcount(cnt);
	}
}
