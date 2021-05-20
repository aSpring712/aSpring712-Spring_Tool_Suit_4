package com.myboard.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.FileBoardDTO;

@Service // Service��� �� �˷��ֱ�
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAOImpl dao;

	@Override
	public void insert(BoardDTO board) {
		dao.dao_insert(board); // Controller���� ���񽺸� �ҷ��� �������� �԰� ���� DAOImpl�� �̵�
		
	}

	@Override
	public List<BoardDTO> findAll(HashMap<String, Object> hm) {
		return dao.dao_findAll(hm);
	}

	@Override
	public BoardDTO findByNum(int num) {
		return dao.dao_findByNum(num); // Service -> DAO
	}

	@Override
	public void update(BoardDTO board) {
		dao.dao_update(board);
	}

	@Override
	public void delete(int num) {
		dao.dao_delete(num);
	}

	@Override
	public int getCount(HashMap<String, Object> hm) {
		return dao.dao_getCount(hm);
	}

	// ���� ���ε�
	@Override
	public void fileInsert(FileBoardDTO fboard) {
		dao.fileInsert(fboard);
	}

	@Override
	public List<FileBoardDTO> fileList() {
		return dao.fileList();
	}
}
