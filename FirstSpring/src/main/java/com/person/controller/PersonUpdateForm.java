package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;
import com.person.model.PersonDTO;

public class PersonUpdateForm extends AbstractController {
	private PersonDAOImpl dao;

	//setter
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, 
			HttpServletResponse arg1) throws Exception {
		String id = req.getParameter("id");
		ModelAndView mv = new ModelAndView();
		PersonDTO person = dao.personView(id);
		mv.addObject("person", person);
		mv.setViewName("WEB-INF/jsp/personUpdate.jsp");
		return mv;
	}
	
	

}
