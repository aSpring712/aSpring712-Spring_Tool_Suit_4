package com.mycom.myapp06;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myboard.dto.CommentDTO;
import com.myboard.model.CommentService;

@RequestMapping("/reply/*") // reply를 타고 넘어오는 모든 것은 이 Controller와 매핑하겠다
@RestController // @Controller + @ResponseBody
public class CommentController {
	
	@Autowired // -> type으로 찾아감 /  Service로 할 시에는 이름 다르게 지정해줘야 함
	private CommentService cservice; // serviceImpl(실행될 때 생성됨)로 구현될 때 실행되면 늦음 -> 트랜잭션을 처리할 때는 구현 이전 단계에서 처리되어져야 함
	
	// 댓글추가
	@PostMapping("commentInsert")
	public String insert(@RequestBody CommentDTO comment) { // json 형태로 받아야 해서 RequestBody
		cservice.insert(comment);
		return "success"; // .jsp가 아닌 문자 자체를 반환 -> ResponseBody
	}
	
	// 댓글 리스트
	@GetMapping("commentList")
	public List<CommentDTO> list(int bnum) {
		List<CommentDTO> clist = cservice.getList(bnum);
		return clist;
	}
}
