package com.myguest.model;

import java.util.HashMap;
import java.util.List;

public interface GuestDAO {
	// 추가
	public void dao_guestInsert(GuestDTO guest);
	// 전체보기
	public List<GuestDTO> dao_guestList(HashMap<String, String> hm);
	// 상세보기
	public GuestDTO dao_findByNum(int num);
	// 수정
	public void dao_guestUpdate(GuestDTO guest);
	// 삭제
	public void dao_guestDelete(int num);
	// 개수
	public int dao_guestCount(HashMap<String, String> hm);
}
