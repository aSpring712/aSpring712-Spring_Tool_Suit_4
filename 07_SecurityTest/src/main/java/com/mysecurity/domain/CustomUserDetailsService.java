package com.mysecurity.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mysecurity.dto.CustomUser;
import com.mysecurity.dto.MemberDTO;
import com.mysecurity.mapper.MemberMapper;

public class CustomUserDetailsService implements UserDetailsService { // user ��ü�� ������� �� �̹� security�� ����� ��ü�� �������

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // db�� ����ڰ� �Է��� id, pw�� �ִ��� Ȯ������
		System.out.println("loadUserByUsername test"); // spring������ security ����� user�� �����صξ�����, �����Դ� MemberDTO��! -> ��ȯ ����� �� 
		MemberDTO member = memberMapper.read(username); // �Է��� username�� ��ȸ
		return member == null ? null : new CustomUser(member); // �ִ� ���� id�� CustomeUser ��ü�� ����
	} 

}
