package com.mycomm.models;

import java.util.HashMap;
import java.util.List;

import com.mycomm.vo.PersonVO;

public interface PersonDAO  {
	// 추가
	public void dao_insert(String mid, PersonVO person);
	// 전체보기
	public List<PersonVO> dao_list(String mid, HashMap<String, String> hm);
	// 상세보기
	public PersonVO dao_view(String mid, String id);
	// 수정
	public void dao_update(String mid, PersonVO person);
	// 삭제
	public void dao_delete(String mid, String id);
	// 개수
	public int dao_count(String mid, HashMap<String, String> hm);
}
