package com.myboard.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myboard.dto.MemberDTO;
import com.myboard.mapper.MemberMapper;

@Service // Service Layer������ �˷���, Repository(DAO���� �˷���), bean(����ڰ� ����� X, ���̺귯������ ����), Component(����ڰ� �����) �� ��ü�� ����� ���� ������ 
public class MemberServiceImpl implements MemberService { // DAO�������� service���� �ٷ� mapper �θ����� ��

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
