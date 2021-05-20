package com.myboard.mapper;

import java.util.List;

import com.myboard.dto.CommentDTO;

public interface CommentMapper {
	// 추가
	public void insert(CommentDTO comment);
	// 전체보기
	public List<CommentDTO> getList(int bnum);
	// 삭제
	public int del(int cnum);
	// read
	public CommentDTO read(int cnum);
}
