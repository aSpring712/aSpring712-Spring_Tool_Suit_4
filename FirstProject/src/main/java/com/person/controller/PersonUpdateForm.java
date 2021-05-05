package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;
import com.person.model.PersonDTO;

public class PersonUpdateForm extends AbstractController {
	private PersonDAOImpl dao;
	
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		String id = req.getParameter("id"); // 넘어오는 id값 받아오기
		ModelAndView mv = new ModelAndView();
		PersonDTO person = dao.personView(id); // 이용해서 수정할 user의 데이터를 받아와야 함
		mv.addObject("person", person);
		mv.setViewName("WEB-INF/jsp/personUpdate.jsp"); 
		return mv;
	}

}
