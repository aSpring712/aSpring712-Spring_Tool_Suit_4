package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;
import com.person.model.PersonDTO;

public class PersonView extends AbstractController { // jsp -> controller -> dao로 옴
	private PersonDAOImpl dao;

	// setter
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// ? id 값 달고왔으니까 받기
		String id = req.getParameter("id");
		PersonDTO person = dao.personView(id); // 수행 -> 반환값 : PersonDTO형
		ModelAndView mv = new ModelAndView();
		mv.addObject("person", person);
		mv.setViewName("WEB-INF/jsp/personView.jsp");
		return mv;
	}
	
	
	
}
