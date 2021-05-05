package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("test.go")
	public String test() { // String은 View(jsp)를 의미
		
		return "result"; // result.jsp를 의미 -> result.jsp로 감
	}
	
	@RequestMapping("test22.go")
	public String test2() { // String은 View(jsp)를 의미
		
		return "result2"; // result.jsp를 의미 -> result.jsp로 감
	}
}
