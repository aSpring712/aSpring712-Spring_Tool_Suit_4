package com.myguest.model;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myguest.mapper.GuestMapper;

@Repository("dao") // Repository라는걸 알려주기
public class GuestDAOImpl implements GuestDAO {
	
	@Autowired
	public GuestMapper mapper; // 자바 파일(GuestMapper.java 인터페이스로) 만들어야 함

	@Override
	public void dao_guestInsert(GuestDTO guest) {
		mapper.insert(guest);
		
	}

	@Override
	public List<GuestDTO> dao_guestList(HashMap<String, String> hm) {
		return mapper.list(hm); // mapper.java 파일을 말하는데 list가 없어서 오류
	}

	@Override
	public GuestDTO dao_findByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dao_guestUpdate(GuestDTO guest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dao_guestDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int dao_guestCount(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return 0;
	}

}
