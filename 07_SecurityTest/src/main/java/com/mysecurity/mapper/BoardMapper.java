package com.mysecurity.mapper;

import java.util.List;

import com.mysecurity.dto.BoardDTO;

public interface BoardMapper {
	// 추가
	public void boardInsert(BoardDTO board);
	// 전체 글 목록
	public List<BoardDTO> list();
	// 글 상세보기
	public BoardDTO view(int num);
}
