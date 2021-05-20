package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.vo.BoardDTO;

// 서비스 : 데이터를 dao를 통해 주고받으면서 비스니스 로직 수행
public interface BoardService {
	public List<BoardDTO> list(HashMap<String, Object> hm);
	
	public int getCount(HashMap<String, Object> hm);
	
	public BoardDTO view(int num);
	
	public void insert(BoardDTO board);
	
	public void delete(int num);
	
	public void update(BoardDTO board);
}
