package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.BoardDTO;

@Mapper // Bean 객체를 자동으로 연결시켜 줌
public interface BoardMapper { // repository layer를 빼고 service에서 바로 mapper로. DAO -> Data Access Object : DB의 data에 접근을 위한 객체(DB 접근 위한 로직 , 비즈니스 로직 분리하기 위해 사용) -> DB를 통해 데이터를 조회/수정/삭제 역할
	// 전체 목록 보기
	public List<BoardDTO> list(HashMap<String, Object> hm);
	// 글 개수
	public int getCount(HashMap<String, Object> hm);
	// 상세보기
	public BoardDTO view(int num);
	// 글 쓰기
	public void insert(BoardDTO board);
	// 조회수 증가
	public void updateHitcount(int num);
	// 글 수정
	public void update(BoardDTO board);
	// 글 삭제
	public void delete(int num);
}
