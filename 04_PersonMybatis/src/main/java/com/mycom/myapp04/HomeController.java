package com.mycom.myapp04;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.protobuf.Service;
import com.mycomm.models.PersonServiceImpl;
import com.mycomm.vo.PersonVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private PersonServiceImpl service; // PersonService�빐�룄 �맖
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// 추가 폼 이동
	@GetMapping("per_insert")
	public String insert() {
		return "insert";
	}
	
	// 추가 후
	@PostMapping("per_insert")
//	public String insert(@ModelAttribute("per") PersonVO person) { // @ModelAttribute -> default값으로 처리(객체를 자동적으로 담아줌?)되는 것! 그러나 적어주면 view 까지 간다?
		public String insert(@ModelAttribute PersonVO person) {
		service.insert(person);
		return  "redirect:per_list"; // insert 후 per_list 실행 -> personList.jsp로 이동하기 위해
//		return "test"; // 만약 redirect로 넘기지 않고 return "test"; 해서 .jsp로 넘긴다면
		// @ModelAttribute("per") 꼭 써주고 per라는 이름으로 빈 객체에 추가되어서 test.jsp로 전달됨
	}
	
	// 전체보기 -> 검색기능 추가 : field, word 값이 넘어옴
	@GetMapping("per_list")
	public String list(Model model, String field, String word) { // field, word 값 받기
		// mybatis 객체 여러개 전달 불가 -> HashMap에 담아서 전달
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("field", field);
		hm.put("word", word);
//		List<PersonVO> personlist = service.listAll(); 
		List<PersonVO> personlist = service.list(hm);
		int count = service.count(hm);
//		int count = service.countAll();
		model.addAttribute("personlist", personlist);
		model.addAttribute("count", count);
		return "personList";
	}
	
	// 상세보기 & 수정 폼으로 이동
	@GetMapping({"view", "update"}) // 데이터 들고 각 .jsp로 돌아감
	public void update(String id, Model model) { // 넘어오는 id를 받아서 Model 객체에 저장
		PersonVO person = service.view(id); // 상세보기랑 동일함
		model.addAttribute("person", person);
	}
	
//	// 상세보기
//	@GetMapping("view") // 서비스 부르고 다오 부르고 mybitis 부르고 controller 부르고 다시 옴?
//	public String view(Model model, String id) { // model 객체 -> request처럼 전달 가능
//		PersonVO person = service.view(id);
//		model.addAttribute("person", person);
//		return "view";
//	}
	
	// 수정폼으로 이동
	/*@GetMapping("update")
	public String update(String id, Model model) { // Model 객체에 저장
		PersonVO person = service.view(id); // 상세보기랑 동일함
		model.addAttribute("person", person);
		return "update"; // update.jsp로 "person"에 담은 데이터를 들고 이동
	}*/
	
	// 수정
	@PostMapping("update")
	public String update(PersonVO person) {
		service.update(person);
		return "redirect:per_list";
	}
	
	// 삭제
	@GetMapping("delete")
	public String delete(String id) {
		service.delete(id);
		return "redirect:per_list";
	}
}
