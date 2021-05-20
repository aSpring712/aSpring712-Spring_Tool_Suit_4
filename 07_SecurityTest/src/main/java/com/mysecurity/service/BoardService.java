package com.mysecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysecurity.dto.BoardDTO;
import com.mysecurity.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired 
	private BoardRepository boardRepository; // repository�� ����ִ°� �����;� ��
	
	public void insert(BoardDTO board) {
		boardRepository.insert(board);
	}
	
	public List<BoardDTO> list() {
		 return boardRepository.list();
	}
	
	public BoardDTO view(int num) {
		return boardRepository.view(num);
	}
}
