package com.mysecurity.dto;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User { // security가 가지고 있는 User 객체를 상속받음
	private MemberDTO member;
	// 생성자 필요 (MemberDTO를 Spring이 Test하는 User 객체로 변환해주기!)
	public CustomUser(MemberDTO vo) {
		super(vo.getUserid(), 
			  vo.getUserpw(), 
			  vo.getAuthList().stream()
			  .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
			  .collect(Collectors.toList())
			);
		this.member = vo;
	};
}
