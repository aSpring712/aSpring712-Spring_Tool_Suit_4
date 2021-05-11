package com.myboard.model;

import java.util.HashMap;
import java.util.List;

import com.myboard.dto.BoardDTO;

public interface BoardDAO {
	// �߰�
	public void dao_insert(BoardDTO board);
	// ����Ʈ
	public List<BoardDTO> dao_findAll(HashMap<String, Object> hm);
	// �󼼺���
	public BoardDTO dao_findByNum(int num);
	// ����
	public void dao_update(BoardDTO board);
	// ����
	public void dao_delete(int num);
	// ����
	public int dao_getCount(HashMap<String, Object> hm);
}
