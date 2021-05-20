package com.myboard.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myboard.dto.MemberDTO;
import com.myboard.mapper.MemberMapper;

@Service // Service Layer까지만 알려줌, Repository(DAO까지 알려줌), bean(사용자가 만드는 X, 라이브러리에서 제공), Component(사용자가 만드는) 등 객체를 만드는 것은 동일함 
public class MemberServiceImpl implements MemberService { // DAO하지말고 service에서 바로 mapper 부르도록 함

	@Autowired
	private MemberMapper mMapper;
	
	@Override
	public void join(MemberDTO member) {
		mMapper.join(member);		
	}

	@Override
	public int idCheck(String id) {
		return mMapper.idCheck(id);
	}

	@Override
	public MemberDTO loginCheck(String id) {
		return mMapper.loginCheck(id);
	}

}
