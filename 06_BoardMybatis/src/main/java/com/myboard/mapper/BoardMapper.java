package com.myboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.FileBoardDTO;

public interface BoardMapper {
	// 추가
	public void insert(BoardDTO board);
	// 리스트
	public List<BoardDTO> findAll(HashMap<String, Object> hm); // xml 값에서는 id값, parameter 값이 1개여야 해서 2개의 값을 전달하기 위해 HashMap 이용(또는 객체 사용)해서 전달
	// 상세보기
	public BoardDTO findByNum(int num);
	// 수정
	public void update(BoardDTO board);
	// 삭제
	public void delete(int num);
	// 개수
	public int getCount(HashMap<String, Object> hm);

	// replyCnt 개수 증가
	public void updateReplyCnt(@Param("bnum") int bnum, @Param("amount") int amount); // xml로 2개 이상의 값 전달 시 HashMap / 객체 / 또는 Param으로 전달
	
	// hitCount 수 증가
	public void updateHitcount(int num);
	
	// 파일 업로드
	public void fileInsert(FileBoardDTO fboard);
	// 파일 리스트 보기
	public List<FileBoardDTO> fileList();
}
