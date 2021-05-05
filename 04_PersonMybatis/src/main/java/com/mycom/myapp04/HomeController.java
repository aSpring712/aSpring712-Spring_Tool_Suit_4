package com.mycom.myapp04;

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

import com.google.protobuf.Service;
import com.mycomm.models.PersonServiceImpl;
import com.mycomm.vo.PersonVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private PersonServiceImpl service; // PersonService해도 됨
	
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
	
	// 추가폼
	@GetMapping("per_insert")
	public String insert() {
		return "insert";
	}
	
	// 추가
	@PostMapping("per_insert")
	public String insert(PersonVO person) {
		service.insert(person);
		return null;
	}
	
	// 전체보기
	@GetMapping("per_list")
	public String list(Model model) {
		List<PersonVO> personlist = service.listAll();
		int count = service.countAll();
		model.addAttribute("personlist", personlist);
		model.addAttribute("count", count);
		return "personList";
	}
	
	// 상세보기
	@GetMapping("view")
	public String view(Model model, String id) {
		PersonVO person = service.view(id);
		model.addAttribute("person", person);
		return "view";
	}
}
