package com.example.demo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.model.Comment;

@RequestMapping("/reply/*")
@RestController
public class CommentController {
	@Autowired
	private CommentService cService;
	
	// 댓글 추가
	@PostMapping("commentInsert")
	public String insert(@RequestBody Comment comment) {
		cService.insert(comment);
		return "success";
	}
	
	// 댓글 리스트
	@GetMapping("commentList")
	public List<Comment> list(Long bnum) {
		List<Comment> cList = cService.getList(bnum);
		return cList;
	}

	// 댓글 삭제
	@DeleteMapping("del/{cnum}")
	@ResponseBody
	public int fdel(@PathVariable Long cnum) {
		cService.delete(cnum);
		return cnum;
	}
	
}
