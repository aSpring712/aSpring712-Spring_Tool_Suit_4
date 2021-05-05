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
	
	// 개수
	public int countAll() {
		return dao.dao_countAll("allCount");
	}
	
	@Override
	public void insert(PersonVO person) {
		dao.dao_insert("insertPerson", person);
	}

	@Override
	public List<PersonVO> list(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonVO view(String id) { // viewPerson
		return dao.dao_view("viewPerson", id);
	}

	@Override
	public void update(PersonVO person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return 0;
	}

}
