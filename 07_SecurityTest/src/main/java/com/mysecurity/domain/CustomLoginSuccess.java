package com.mysecurity.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccess implements AuthenticationSuccessHandler { // �α��� ���� �� ó���Ǵ� �κ�

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException { // ������ �����Ǹ� Authentication�� �����ϰ� ��
		String encPwd = passwordEncoder.encode(request.getParameter("password")); // ������� �Է��� password�� ��ȣȭ
		System.out.println("password : " + encPwd);
		
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority // ���⿡ ��Ƽ� 
				-> roleNames.add(authority.getAuthority())); // add ��Ŵ
		
		System.out.println("roleNames : " + roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/myapp07/admin");
			return; // ������
		} else if(roleNames.contains("ROLE_MANAGER" )) {
			response.sendRedirect("/myapp07/manager");
			return;
		}
		response.sendRedirect("/myapp07"); // �߸� �� ����� root�� �ѱ�
	} 
	
}
