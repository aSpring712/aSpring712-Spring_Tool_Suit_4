package com.mysecurity.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mysecurity.dto.CustomUser;
import com.mysecurity.dto.MemberDTO;
import com.mysecurity.mapper.MemberMapper;

public class CustomUserDetailsService implements UserDetailsService { // user 객체가 만들어질 때 이미 security가 적용된 객체가 만들어짐

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // db에 사용자가 입력한 id, pw가 있는지 확인해줌
		System.out.println("loadUserByUsername test"); // spring에서는 security 적용된 user를 정의해두었지만, 나에게는 MemberDTO임! -> 변환 해줘야 함 
		MemberDTO member = memberMapper.read(username); // 입력한 username을 조회
		return member == null ? null : new CustomUser(member); // 있는 유저 id면 CustomeUser 객체로 만듦
	} 

}
