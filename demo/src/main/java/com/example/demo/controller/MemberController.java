package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	@GetMapping("join")
	public void join() {
		
	}
	
	@GetMapping("login")
	public void login() {
		
	}
	
	@PostMapping("login")
	public void login(String id, String pwd) {
		
	}
}
