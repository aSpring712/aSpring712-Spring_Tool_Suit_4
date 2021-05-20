package com.mycom.myapp07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysecurity.dto.BoardDTO;
import com.mysecurity.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService bservice;
	
	@GetMapping("insert")
	@PreAuthorize("isAuthenticated()") // ������ �̸� �˻��ؼ� ������ ������ �۾���� �̵�, ������ �α��� �������� ���� -> if �Ἥ session�� �ִ��� Ȯ������ �ʾƵ� ��
	public void insert() {
		
	}
	
	@PostMapping("insert")
	@PreAuthorize("isAuthenticated()")
	public String insert(BoardDTO board) {
		bservice.insert(board);
		return "redirect:list"; // -> get list�� �Ѿ
	}
	
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("boards", bservice.list());
		return "board/list";
	}
	
	@GetMapping("view/{num}")
	public String view(@PathVariable int num, Model model) {
		model.addAttribute("board", bservice.view(num));
		return "board/view";
	}
	

}
