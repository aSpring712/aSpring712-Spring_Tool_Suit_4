package com.mycom.myapp06;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myboard.dto.BoardDTO;
import com.myboard.dto.MemberDTO;
import com.myboard.dto.PageVO;
import com.myboard.model.BoardService;


/**
 * Handles requests for the application home page.
 */
@Controller // getter, setter, ������, toString �� �˾Ƽ� �������(lombok)
public class HomeController {
	
	@Autowired // type���� ã�� resource�� name���� ã��
	private BoardService service; // BoardService�� �ص� ��
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		
//		return "redirect:list";
//	}
	
	// insertForm���� �̵�
	@GetMapping("insert") // boardList.jsp���� ��ư Ŭ�� �� insert �������� ��
	public String insert() {
		return "boardInsert"; // boardInsert.jsp�� �̵������ֱ�
	}
	
	// DB�� �� �߰�
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		service.insert(board); // service�� ���ٰ� DAO�� ��
		return "redirect:list"; // �߰� �� list�� �̵�
	}
	
	// �Խñ� ��� ��ü ����
	@GetMapping({"/", "list"})
	public String list(Model model, String pageNum, 
			@RequestParam(name="field", defaultValue="") String field, 
			@RequestParam(name="word", defaultValue="") String word) { // ��ü ����� ������� �� -> model ��ü�� ����
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5;
		
		hm.put("pageStart", (currentPage-1)*pageSize); // ���� ��
		hm.put("pageSize", pageSize);
		
		List<BoardDTO> lists = service.findAll(hm);
		int count = service.getCount(hm);
		
		PageVO page = new PageVO(count, currentPage, pageSize);
		
		// ���� ���ؾ� ��
		model.addAttribute("rowNo", count-((currentPage-1)*pageSize)); // count���� ������ ����ŭ ���ָ� �� (rowNo�� ����ؼ� num ��� �ѷ��ֱ�)
		
		model.addAttribute("lists", lists); // model�� �߰�
		model.addAttribute("count", count); // model�� �߰�
		model.addAttribute("p", page);
		return "boardList";
	}
	
	// �� �󼼺��� -> view/num �̷��� �Ѿ�� (REST ���)
	@GetMapping("view/{num}") // �Ѿ���� num�� �ޱ�
	public String view(Model model, @PathVariable int num) { // ��λ󿡼� num���� �޾ƿ�
		BoardDTO board = service.findByNum(num); // Controller -> Service
		model.addAttribute("board", board);
		return "view";
	}
	
	// �� �����ϱ�
	@DeleteMapping("delete/{num}")
	@ResponseBody // ���� �� success.jsp�� ���� ���� �ƴ϶� "success"��� ���ڸ� �ٽ� ajax�� �����ϰ��� ��
	public String view(@PathVariable int num) {
		service.delete(num);
		return "success"; // success��� ���ڸ� ��� callback �Ǿ����� ajax�� �ٽ� ��
	}
	
	// ���� ������ �̵�
	@GetMapping("update/{num}")
	public String update(Model model, @PathVariable int num) {
		BoardDTO board = service.findByNum(num);
		model.addAttribute("board", board);
		return "update"; // update.jsp�� �̵� -> PathVariable�� ó���� ���� �ʾ����� �󼼺���� ��� ó�� ����
	}
	
	// ����
	@PutMapping("update")  // RequestBody�� ��� json ���·� �޾ƿ�
	@ResponseBody
	public String update(@RequestBody BoardDTO board) { // 3�� -> ��ü �Ѿ�� -> model.addAttribute �ص��ǰ�
		service.update(board);
		return "success"; // ���ڿ� �����ְ���� -> ResponseBody -> �̰ͺ��� Response Entity : ���� + ��Ȳ���� ������ �� ����
	}
	
}
