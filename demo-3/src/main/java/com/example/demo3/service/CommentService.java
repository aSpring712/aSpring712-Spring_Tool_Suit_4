package com.example.demo3.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo3.model.Comment;
import com.example.demo3.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	
	// 댓글쓰기
	@Transactional
	public void insert(Comment comment) {
		commentRepository.save(comment);
	}
	
	// 댓글 조회
	@Transactional
	public List<Comment> list(Long bnum) {
		return commentRepository.findAllById(bnum);
	}
}
