package com.example.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo1.domain.CustomUser;
import com.example.demo1.dto.MemberDTO;
import com.example.demo1.mapper.MemberMapper;

@Service
public class MemberService implements UserDetailsService {
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername test");
		MemberDTO member = memberMapper.read(username);
		return member == null ? null : new CustomUser(member); // 없다면 null 반환, 있다면 user 객체를 반환해야 함
	}

}
