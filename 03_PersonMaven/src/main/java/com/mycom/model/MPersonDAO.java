package com.mycom.model;

import java.util.List;

public interface MPersonDAO {
	// 추가
	public void per_insert(Person person);
	// 전체보기
	public List<Person> per_list();
	// 상세보기
	public Person per_view(String id);
	// 수정
	public void per_update(Person persone);
	// 삭제
	public void per_delete(String id);
	// 개수
	public int per_count();
}
