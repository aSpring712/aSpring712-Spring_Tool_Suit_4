package com.example.demo3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.model.Board;
import com.example.demo3.model.Comment;
import com.example.demo3.service.CommentService;

@RequestMapping("/reply/*") // reply를 달고오는 건 이쪽으로
@RestController // response + controller
public class CommentController {
	@Autowired
	private CommentService cService;
	
	// 댓글 추가
	@PostMapping("insert/{num}")
	public ResponseEntity<String> insert(@PathVariable Long num, // board의 bnum이지 Comment의 num이 아님 -> board에 들어가야 함
			@RequestBody Comment comment) {
		Board b = new Board(); // board형 객체 만들고
		b.setNum(num); // num값 넣기 -> Board형으로 만들었음!
		
		comment.setBoard(b);
		cService.insert(comment);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	// 댓글 리스트
	@GetMapping("list")
	public List<Comment> list(Long bnum) {
		List<Comment> cList = cService.getList(bnum);
		return cList;
	}

	// 댓글 삭제
	@DeleteMapping("del/{cnum}")
	public String delete(@PathVariable Long cnum) {
		cService.delete(cnum);
		return cnum.toString();
	}
	
}
