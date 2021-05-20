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

public class CustomLoginSuccess implements AuthenticationSuccessHandler { // 로그인 성공 시 처리되는 부분

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException { // 권한이 인정되면 Authentication을 참조하게 됨
		String encPwd = passwordEncoder.encode(request.getParameter("password")); // 사람들이 입력한 password를 암호화
		System.out.println("password : " + encPwd);
		
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority // 여기에 담아서 
				-> roleNames.add(authority.getAuthority())); // add 시킴
		
		System.out.println("roleNames : " + roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/myapp07/admin");
			return; // 끝내고
		} else if(roleNames.contains("ROLE_MANAGER" )) {
			response.sendRedirect("/myapp07/manager");
			return;
		}
		response.sendRedirect("/myapp07"); // 잘못 온 사람은 root로 넘김
	} 
	
}
