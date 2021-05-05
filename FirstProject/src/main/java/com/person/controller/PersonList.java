package com.person.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;
import com.person.model.PersonDTO;

public class PersonList extends AbstractController {
	private PersonDAOImpl dao;
	
	// setter
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// List를 받아옴
		List<PersonDTO> userlist = dao.personList(); // personlist가 반환됨
		ModelAndView mv = new ModelAndView(); // 데이터를 전송시킬 수 있는 리턴 타입
		mv.addObject("personlist", userlist); // == setAttribute   -> userlist라는 값을 personlist라는 이름으로 들고 
		mv.setViewName("WEB-INF/jsp/personList.jsp"); // .jsp로 간다 -> 어떤 페이지를 보여줄 건지
		// personList.jsp로 가서 뿌려줘야 하는데 -> jstl을 사용하면 된다
		return mv;
	}
}
