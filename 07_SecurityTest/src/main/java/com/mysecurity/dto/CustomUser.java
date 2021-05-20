package com.mysecurity.dto;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUser extends User { // security�� ������ �ִ� User ��ü�� ��ӹ���
	private MemberDTO member;
	// ������ �ʿ� (MemberDTO�� Spring�� Test�ϴ� User ��ü�� ��ȯ���ֱ�!)
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
