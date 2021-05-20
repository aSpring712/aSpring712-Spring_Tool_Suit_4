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
@Controller // getter, setter, 생성자, toString 다 알아서 만들어줌(lombok)
public class HomeController {
	
	@Autowired // type으로 찾고 resource는 name으로 찾음
	private BoardService service; // BoardService로 해도 됨
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		
//		return "redirect:list";
//	}
	
	// insertForm으로 이동
	@GetMapping("insert") // boardList.jsp에서 버튼 클릭 시 insert 이쪽으로 옴
	public String insert() {
		return "boardInsert"; // boardInsert.jsp로 이동시켜주기
	}
	
	// DB에 값 추가
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		service.insert(board); // service로 갔다가 DAO로 감
		return "redirect:list"; // 추가 후 list로 이동
	}
	
	// 게시글 목록 전체 보기
	@GetMapping({"/", "list"})
	public String list(Model model, String pageNum, 
			@RequestParam(name="field", defaultValue="") String field, 
			@RequestParam(name="word", defaultValue="") String word) { // 전체 목록을 보여줘야 함 -> model 객체에 담음
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("field", field);
		hm.put("word", word);
		int currentPage = pageNum == null ? 1 : Integer.parseInt(pageNum);
		int pageSize = 5;
		
		hm.put("pageStart", (currentPage-1)*pageSize); // 시작 행
		hm.put("pageSize", pageSize);
		
		List<BoardDTO> lists = service.findAll(hm);
		int count = service.getCount(hm);
		
		PageVO page = new PageVO(count, currentPage, pageSize);
		
		// 개수 구해야 함
		model.addAttribute("rowNo", count-((currentPage-1)*pageSize)); // count에서 페이지 수만큼 빼주면 됨 (rowNo을 계산해서 num 대신 뿌려주기)
		
		model.addAttribute("lists", lists); // model에 추가
		model.addAttribute("count", count); // model에 추가
		model.addAttribute("p", page);
		return "boardList";
	}
	
	// 글 상세보기 -> view/num 이렇게 넘어옴 (REST 방법)
	@GetMapping("view/{num}") // 넘어오는 num값 받기
	public String view(Model model, @PathVariable int num) { // 경로상에서 num값을 받아옴
		BoardDTO board = service.findByNum(num); // Controller -> Service
		model.addAttribute("board", board);
		return "view";
	}
	
	// 글 삭제하기
	@DeleteMapping("delete/{num}")
	@ResponseBody // 삭제 후 success.jsp로 가는 것이 아니라 "success"라는 글자만 다시 ajax로 전달하고자 함
	public String view(@PathVariable int num) {
		service.delete(num);
		return "success"; // success라는 글자를 들고 callback 되어져서 ajax로 다시 감
	}
	
	// 수정 폼으로 이동
	@GetMapping("update/{num}")
	public String update(Model model, @PathVariable int num) {
		BoardDTO board = service.findByNum(num);
		model.addAttribute("board", board);
		return "update"; // update.jsp로 이동 -> PathVariable로 처리를 하지 않았으면 상세보기와 묶어서 처리 가능
	}
	
	// 수정
	@PutMapping("update")  // RequestBody를 써야 json 형태로 받아옴
	@ResponseBody
	public String update(@RequestBody BoardDTO board) { // 3개 -> 객체 넘어옴 -> model.addAttribute 해도되고
		service.update(board);
		return "success"; // 문자열 돌려주고싶음 -> ResponseBody -> 이것보다 Response Entity : 정보 + 상황까지 돌려줄 수 있음
	}
	
}
