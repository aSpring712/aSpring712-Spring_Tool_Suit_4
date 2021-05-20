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

@RequestMapping("/member/*") // member�� Ÿ�� ���� ���� �� ��������!
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	// ȸ������
	@GetMapping("join")
	public void join() {
		
	}

	@PostMapping("join")
	@ResponseBody // success.jsp�� �ƴ϶� ����� ���� "success"�� �״�� �����ҰŶ�
	public String join(@RequestBody MemberDTO member) {
		int cnt = mService.idCheck(member.getId());
		if(cnt != 0) return "fail"; // �̹� �����ϴ� id���(javascript���� �ߺ��˻� ó���� ������ ���� �ʱ� ������)
		mService.join(member);
		return "success";
	}
	
	// id �ߺ��˻�
	@GetMapping("idCheck")
	@ResponseBody // .jsp�� �ƴ϶� ����� ���ڸ� �״�� �����ҰŶ�
	public int idCheck(String id) {
		return mService.idCheck(id);
	}
	
	// �α���
	@GetMapping("login")
	public void login() { // login.jsp�� �̵���
		
	}
	
	// �α���
	@PostMapping("login")
	@ResponseBody
	public String login(String id, String pass, HttpServletRequest request) { // �׳� 1. HttpSession ssesion
		MemberDTO member = mService.loginCheck(id);
		String result = "";
		
		if(member == null) {
			result = "no";
		} else if (member.getPass().equals(pass)){
			HttpSession session = request.getSession(); // 2. ���� ���� �ص� ��
			session.setAttribute("sMember", member); // session�� member ����
			result = "success";
		} else {
			result = "passError";
		}
		
		return result;
	}
	
	// �α׾ƿ�
	@GetMapping
	public String logout(HttpSession session) {
		session.invalidate(); // ���� ����
		return "member/login"; // login.jsp�� �̵�
	}
}
