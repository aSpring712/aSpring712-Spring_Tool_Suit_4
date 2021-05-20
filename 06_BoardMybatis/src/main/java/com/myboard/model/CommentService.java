package com.myboard.model;

import java.util.List;

import com.myboard.dto.CommentDTO;

public interface CommentService {
	// 추가
	public void insert(CommentDTO comment);
	// 전체보기
	public List<CommentDTO> getList(int bnum);
	// 댓글 삭제
	public void delete(int cnum);
}
