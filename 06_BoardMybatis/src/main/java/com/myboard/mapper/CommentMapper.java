package com.myboard.mapper;

import java.util.List;

import com.myboard.dto.CommentDTO;

public interface CommentMapper {
	// �߰�
	public void insert(CommentDTO comment);
	// ��ü����
	public List<CommentDTO> getList(int bnum);
}