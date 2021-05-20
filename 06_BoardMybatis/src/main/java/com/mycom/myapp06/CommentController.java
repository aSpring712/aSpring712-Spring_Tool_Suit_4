package com.mycom.myapp06;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myboard.dto.CommentDTO;
import com.myboard.dto.MemberDTO;
import com.myboard.model.CommentService;

@RequestMapping("/reply/*") // reply�� Ÿ�� �Ѿ���� ��� ���� �� Controller�� �����ϰڴ�
@RestController // @Controller + @ResponseBody
public class CommentController {
	
	@Autowired // -> type���� ã�ư� /  Service�� �� �ÿ��� �̸� �ٸ��� ��������� ��
	private CommentService cservice; // serviceImpl(����� �� ������)�� ������ �� ����Ǹ� ���� -> Ʈ������� ó���� ���� ���� ���� �ܰ迡�� ó���Ǿ����� ��
	
	// ����߰�
	@PostMapping("commentInsert")
	public String insert(@RequestBody CommentDTO comment, HttpSession session) { // json ���·� �޾ƾ� �ؼ� RequestBody
		String id = ((MemberDTO)session.getAttribute("sMember")).getId(); // session�� ������ MemberDTO ��ü sMember�� id�� �޾ƿ��� 
		comment.setUserid(id);
		cservice.insert(comment);
		return "success"; // .jsp�� �ƴ� ���� ��ü�� ��ȯ -> ResponseBody
	}
	
	// ��� ����Ʈ
	@GetMapping("commentList")
	public List<CommentDTO> list(int bnum) {
		List<CommentDTO> clist = cservice.getList(bnum);
		return clist;
	}
	
	// ��� ����
	@DeleteMapping("del/{cnum}")
	@ResponseBody
	public int fdel(@PathVariable int cnum) {
		cservice.delete(cnum);
		return cnum;
	}
}
