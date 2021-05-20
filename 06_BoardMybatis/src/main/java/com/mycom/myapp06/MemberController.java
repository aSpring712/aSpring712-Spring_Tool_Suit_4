package com.mycom.myapp06;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myboard.dto.MemberDTO;
import com.myboard.model.MemberService;

@RequestMapping("/member/*") // member를 타고 오는 것은 다 이쪽으로!
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	// 회원가입
	@GetMapping("join")
	public void join() {
		
	}

	@PostMapping("join")
	@ResponseBody // success.jsp가 아니라 결과인 문자 "success"를 그대로 전달할거라서
	public String join(@RequestBody MemberDTO member) {
		int cnt = mService.idCheck(member.getId());
		if(cnt != 0) return "fail"; // 이미 존재하는 id라면(javascript에서 중복검사 처리가 완전히 되지 않기 때문에)
		mService.join(member);
		return "success";
	}
	
	// id 중복검사
	@GetMapping("idCheck")
	@ResponseBody // .jsp가 아니라 결과인 숫자를 그대로 전달할거라서
	public int idCheck(String id) {
		return mService.idCheck(id);
	}
	
	// 로그인
	@GetMapping("login")
	public void login() { // login.jsp로 이동함
		
	}
	
	// 로그인
	@PostMapping("login")
	@ResponseBody
	public String login(String id, String pass, HttpServletRequest request) { // 그냥 1. HttpSession ssesion
		MemberDTO member = mService.loginCheck(id);
		String result = "";
		
		if(member == null) {
			result = "no";
		} else if (member.getPass().equals(pass)){
			HttpSession session = request.getSession(); // 2. 여기 생략 해도 됨
			session.setAttribute("sMember", member); // session에 member 저장
			result = "success";
		} else {
			result = "passError";
		}
		
		return result;
	}
	
	// 로그아웃
	@GetMapping
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 끊고
		return "member/login"; // login.jsp로 이동
	}
}
