package com.mycomm.models;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomm.vo.PersonVO;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDAOImpl dao;
	
	// 전체보기
	public List<PersonVO> listAll() {
		return dao.dao_listAll("allPerson");
	}
	
	// 전체 개수
	public int countAll() {
		return dao.dao_countAll("allCount");
	}
	
	// 추가
	@Override
	public void insert(PersonVO person) {
		dao.dao_insert("insertPerson", person);
	}

	// 검색 전체보기
	@Override
	public List<PersonVO> list(HashMap<String, String> hm) {
		return dao.dao_list("listPerson", hm); // 현재 서비스 단 -> dao로 가기
	}

	// 상세보기
	@Override
	public PersonVO view(String id) { // viewPerson
		return dao.dao_view("viewPerson", id);
	}

	// 수정하기
	@Override
	public void update(PersonVO person) {
		dao.dao_update("updatePerson", person); // dao는 손대지 않고 수정 시 service Layer 까지만 손대려고!
	}

	// 삭제하기
	@Override
	public void delete(String id) {
		dao.dao_delete("deletePerson", id);
		
	}

	// 검색 시 count
	@Override
	public int count(HashMap<String, String> hm) {
		return dao.dao_count("countPerson", hm);
	}

}
