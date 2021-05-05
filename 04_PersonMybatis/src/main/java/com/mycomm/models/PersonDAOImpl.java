package com.mycomm.models;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycomm.vo.PersonVO;

@Repository
public class PersonDAOImpl implements PersonDAO {
	@Autowired
	private SqlSession sqlMapper;
	
	// 전체보기
	public List<PersonVO> dao_listAll(String mid) {
		return sqlMapper.selectList(mid);
	}

	// 전체 개수
	public int dao_countAll(String mid) {
		return sqlMapper.selectOne(mid);
	}
	
	@Override
	public void dao_insert(String mid, PersonVO person) {
		sqlMapper.insert(mid, person);
		
	}

	@Override
	public List<PersonVO> dao_list(String mid, HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return null;
	}

	// 상세보기
	@Override
	public PersonVO dao_view(String mid, String id) {
		return sqlMapper.selectOne(mid, id);
	}

	@Override
	public void dao_update(String mid, PersonVO person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dao_delete(String mid, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dao_count(String mid, HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return 0;
	}

}
