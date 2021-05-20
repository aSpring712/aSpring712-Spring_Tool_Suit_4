package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.BoardService;
import com.example.demo.vo.BoardDTO;
import com.example.demo.vo.PageVO;

@Controller // web에서 처리해야 할 데이터를 받고, 이 데이터를 담당할 service를 선택/호출 --처리--> 다음 페이지에서 볼 수 있게 세팅 --> 이동할 페이지 리턴
public class HomeController {
//	@Autowired -> 안 쓸 경우
	private BoardService service;
	
	// 생성자 이용
	public HomeController(BoardService service) {
		this.service = service;
	}
	
//	@GetMapping("/") // root
//	public String index() {
//		return "test";
//	}
//	@GetMapping("list") // ajax 사용(json)
//	public @ResponseBody List<BoardDTO> list() {
//		return service.list();
//	}
	
	// 게시글 목록 전체보기
	@GetMapping({"/", "list"})
	public String list(Model model, String pageNum, 
			@RequestParam(name="field", defaultValue="") String field, 
			@RequestParam(name="word", defaultValue = "") String word) { // ajax를 안썼다면 model에 담고
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5;
		
		hm.put("pageStart", (currentPage-1)*pageSize);
		hm.put("pageSize", pageSize);
		
		List<BoardDTO> lists = service.list(hm);
		int count = service.getCount(hm);
		
		PageVO page = new PageVO(count, currentPage, pageSize);
		
		model.addAttribute("rowNo", count-((currentPage-1)*pageSize));
		
		model.addAttribute("boards", lists);
		model.addAttribute("count", count);
		model.addAttribute("p", page);
		return "list"; // list.jsp에 뿌리기
	}
	
	// 글 상세보기
	@GetMapping("view/{num}")
	public String view(Model model, @PathVariable int num) {
		model.addAttribute("board", service.view(num));
		return "view";
	}
	
	// 글 쓰기 폼
	@GetMapping("insert")
	public void insert() {
		
	}
	
	// 글쓰기 - db에 추가
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		service.insert(board);
		return "redirect:list";
	}
	
	// 글 삭제
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable int num) {
		service.delete(num);
		return "success";
	}
	
	// 수정 폼 이동
	@GetMapping("update/{num}")
	public String update(Model model, @PathVariable int num) {
		BoardDTO board = service.view(num); // 상세보기
		model.addAttribute("board", board);
		return "update";
	}
	
	// 수정
	@PutMapping("update")
	@ResponseBody // json 형태로 글 받아오기
	public String update(@RequestBody BoardDTO board) {
		service.update(board);
		return "success";
	}
}
