package com.example.demo3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import com.example.demo3.model.Board;
import com.example.demo3.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// root page로 이동 -> home.jsp
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	// insert Form으로 이동 
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
	// insert 해서 DB에 값 넣기
	@PostMapping("insert")
	public String insert(Board board) {
		boardService.save(board);
		return "redirect:list";
	}
	
	// 전체보기
//	@GetMapping("list")
//	public String list(Model model) {
//		model.addAttribute("lists", boardService.list());
//		model.addAttribute("count", boardService.count());
//		return "list";
//	}
	
	// 전체보기 페이징 
	@GetMapping("list")
	public String list(Model model, @PageableDefault(size = 5, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) { // 한 화면에 5개씩 보이게하고, num을 기준으로, 내림차순 정렬
		Page<Board> lists = boardService.findAll(pageable); // 반환형 -> Page
		model.addAttribute("lists", lists);
		model.addAttribute("count", boardService.count());
		return "list";
	}
	
	// 글 상세보기
	@GetMapping("view/{num}")
	public String view(@PathVariable Long num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "view";
	}
	
	// 글 삭제
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable Long num) {
		boardService.delete(num);
		return "success";
	}
	
	// 글 수정 폼으로 이동
	@GetMapping("update/{num}")
	public String update(@PathVariable Long num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "updateForm";
	}
	
	// 수정(db 값 변경)
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody Board board) {
		boardService.update(board);
		return "success";
	}
}
