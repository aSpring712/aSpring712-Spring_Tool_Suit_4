package com.mysecurity.mapper;

import java.util.List;

import com.mysecurity.dto.BoardDTO;

public interface BoardMapper {
	// �߰�
	public void boardInsert(BoardDTO board);
	// ��ü �� ���
	public List<BoardDTO> list();
	// �� �󼼺���
	public BoardDTO view(int num);
}
