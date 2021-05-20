package com.mysecurity.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysecurity.dto.BoardDTO;
import com.mysecurity.mapper.BoardMapper;

@Repository
public class BoardRepository { // mapper¸¦ ºÎ¸§
	@Autowired
	private BoardMapper boardMapper;
	
	public void insert(BoardDTO board) {
		boardMapper.boardInsert(board);
	}
	
	public List<BoardDTO> list() {
		return boardMapper.list();
	}
	
	public BoardDTO view(int num) {
		return boardMapper.view(num);
	}
}
