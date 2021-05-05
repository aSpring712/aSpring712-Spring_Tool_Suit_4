package com.person2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.person2.model.Person2DAOImpl;
import com.person2.model.PersonVO;

@Controller
public class Person2Controller {
	@Autowired // .xml에 만들어진 것을 자동 주입 -> 전처럼 setter를 만들지 않아도 됨
	public Person2DAOImpl dao;
	
	// 추가 폼으로 이동
	@RequestMapping("personInsert.go") // get방식이므로 이름이 같아도 다르게 접근 가능
	public String insertform() { 
		return "personForm";
	}

	// 추가(db에 추가)
	@RequestMapping(value="personInsert.go", method = RequestMethod.POST) // 기본 get방식인데 post로 넘기려면
//	public ModelAndView insert(PersonVO person) { // ModelAndView 써도 되고
	public String insert(PersonVO person) { // String 써도 됨
		dao.person_insert(person);
		return "redirect:personList.go";
	}
	
	// 전체보기
	@RequestMapping(value="personList.go", method=RequestMethod.GET)
	public ModelAndView list() {
		List<PersonVO> userlist = dao.person_list();
		ModelAndView mv = new ModelAndView();
		mv.addObject("personlist", userlist);
		mv.setViewName("personList");
		return mv;
	}
	
	// 상세보기
	@RequestMapping("personView.go")
	public ModelAndView personView(String id) {
		PersonVO person = dao.person_view(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", person);
		mv.setViewName("personView");
		return mv;
	}
	
	// 수정 Form으로 이동
	@RequestMapping("personUpdate.go")
	public ModelAndView personUpdateForm(String id) {
		PersonVO person = dao.person_view(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", person);
		mv.setViewName("personUpdate");
		return mv;
	}
	
	// 수정하기
	@RequestMapping(value="personUpdate.go", method=RequestMethod.POST)
	public String personUpdate(PersonVO person) {
		dao.person_update(person);
		return "redirect:personList.go";
	}
	
	// 삭제하기
	@RequestMapping("personDelete.go")
	public String delete(String id) {
		dao.person_delete(id);
		return "redirect:personList.go";
	}
}
