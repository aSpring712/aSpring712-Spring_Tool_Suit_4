package com.myboard.model;

import java.util.HashMap;
import java.util.List;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.FileBoardDTO;

public interface BoardDAO {
	// 추가
	public void dao_insert(BoardDTO board);
	// 리스트
	public List<BoardDTO> dao_findAll(HashMap<String, Object> hm);
	// 상세보기
	public BoardDTO dao_findByNum(int num);
	// 수정
	public void dao_update(BoardDTO board);
	// 삭제
	public void dao_delete(int num);
	// 개수
	public int dao_getCount(HashMap<String, Object> hm);
	// 파일 업로드
	public void fileInsert(FileBoardDTO fboard);
	// 파일 리스트 보기
	public List<FileBoardDTO> fileList();
}
