package com.person.model;

import java.util.List;

public interface PersonDAO {
	// 추가
	public void personInsert(PersonDTO person);
	// 전체보기
	public List<PersonDTO> personList();
	// 상세보기
	public PersonDTO personView(String id);
	// 수정하기
	public void personUpdate(PersonDTO person);
	// 삭제하기
	public void personDelete(String id);
}
