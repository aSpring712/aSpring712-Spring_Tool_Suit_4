package com.mycom.myapp03;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycom.model.MPersonDAO;
import com.mycom.model.MPersonDAOImpl;
import com.mycom.model.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired // dao 만들어주기 -> 자동주입 되도록 (Impl에 @Repository 적어서 연결)
	public MPersonDAO dao; // Impl까지 할필요 없음
	
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
	//추가폼
//	@RequestMapping("insert") 또는
//	@GetMapping("insert")
//	public String insert() {
//		return "insert";
//	}
	
	@GetMapping("insert") // insert.jsp, Get 방식으로 넘어올 때
	public void insert() {
		
	}
	
	// 추가
	@PostMapping("insert") // insert.jsp에서 추가 버튼 클릭 시, Post 방식으로 넘어올 때
	public String insert(Person person) {
		dao.per_insert(person); // dao에대한 오류 발생
		return "redirect:list";
	}
	
	// 전체보기
	@GetMapping("list") // home.jsp에서 넘어옴
	public String list(Model model) { // Model 객체에 값을 담아서 가지고 갈 수 있음
		List<Person> personlist = dao.per_list(); // 값을 가지고 가야 함(String은 jsp 이름밖에 전달하지 못함)
		int count = dao.per_count();
		model.addAttribute("count", count); // 개수 저장
		model.addAttribute("personlist", personlist); // list 저장
		return "personList"; // model에 저장된 값들을 들고 personList.jsp로 이동
	}
	
	// 상세보기, 수정폼으로 이동
	@GetMapping({"view", "update"}) // void -> view로 넘어오면 view.jsp, update로 넘어오면 update.jsp를 찾음
	public void view(String id, Model model) {
		Person person = dao.per_view(id);
		model.addAttribute("person", person);
	}
	
//	// 수정폼 이동 -> 바로 위 상세보기와 합침
//	@GetMapping("update")
//	public String update(String id, Model model) {
//		Person person = dao.per_view(id);
//		model.addAttribute("person", person);
//		return "personUpdate";
//	}
	
	// 수정(db)
	@PostMapping("update")
	public String update(Person person) {
		dao.per_update(person);
		return "redirect:list";
	}

	// 삭제
	@GetMapping("delete")
	public String delete(String id) {
		dao.per_delete(id);
		return "redirect:list"; // .jsp로 가지않을 때 redirct
	}
}
