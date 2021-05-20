package com.example.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo2.model.JpaMember;
import com.example.demo2.service.MemberService;

@Controller
public class HomeController { // Controller이므로 service 만들어야 함
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/") // root page
	public String home() {
		return "home";
	}
	
	@GetMapping("join") // 회원가입 페이지로 이동
	public String join() {
		return "insert";
	}
	
	@PostMapping("join") // 회원가입
	public String join(JpaMember member) { 
		memberService.save(member);
		return "redirect:list";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("lists", memberService.list());
		return "list";
	}
	
	// 상세보기
	@GetMapping("{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberService.detail(id));
		return "view";
	}
	
	@GetMapping("delete/{id}")
	public String delete_get(@PathVariable Long id) {
		memberService.delete(id);
		return "redirect:/list";
	}
	
	// 삭제
	@DeleteMapping("delete/{id}")
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable Long id) {
		memberService.delete(id);
		return new ResponseEntity<>("success", HttpStatus.OK); // 값, 상태정보를 같이 가져감
		// return "success"; // -> 문자만 가져감
	}
	
	// 수정 폼으로 이동
	@GetMapping("update/{id}")
	public String update(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberService.detail(id));
		return "update";
	}
	
	// 수정(DB)
	@PutMapping("update") // 수정 : put mapping
	@ResponseBody // return 값으로 문자 "success" 자체를 넘기기 위해
	public ResponseEntity<String> update(@RequestBody JpaMember member) { // json 형태로 받아오기 위해 @RequestBody
		memberService.update(member);		
		return new ResponseEntity<>("success", HttpStatus.OK); // 값, 상태(OK.. 등) 같이 전달 <> 안에는 "success"를 return 할 것이니까 String 적기
	}
}
