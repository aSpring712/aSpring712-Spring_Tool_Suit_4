package com.myguest.model;

import java.util.HashMap;
import java.util.List;

public interface GuestService {
	// 추가
	public void guestInsert(GuestDTO guest);
	// 전체보기
	public List<GuestDTO> guestList(HashMap<String, String> hm);
	// 상세보기
	public GuestDTO findByNum(int num);
	// 수정
	public void guestUpdate(GuestDTO guest);
	// 삭제
	public void guestDelete(int num);
	// 개수
	public int guestCount(HashMap<String, String> hm);
}