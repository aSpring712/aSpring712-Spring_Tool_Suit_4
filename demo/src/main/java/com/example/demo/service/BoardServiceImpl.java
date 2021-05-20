package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardMapper;
import com.example.demo.vo.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService { 
	@Autowired // service에서 dao를 알 수 있게
	private BoardMapper bMapper;

	@Override
	public List<BoardDTO> list(HashMap<String, Object> hm) {
		return bMapper.list(hm); // bMapper의 list를 부름
	}

	@Override
	public int getCount(HashMap<String, Object> hm) {
		return bMapper.getCount(hm);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO view(int num) {
		bMapper.updateHitcount(num);
		return bMapper.view(num);
	}

	@Override
	public void insert(BoardDTO board) {
		bMapper.insert(board);
		
	}

	@Override
	public void delete(int num) {
		bMapper.delete(num);
		
	}

	@Override
	public void update(BoardDTO board) {
		bMapper.update(board);	
	}
}
