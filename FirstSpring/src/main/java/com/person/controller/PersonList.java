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
	
	//setter
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List<PersonDTO> userlist = dao.personList();
		ModelAndView mv = new ModelAndView();
		mv.addObject("personlist", userlist); //setAttribute랑 같은 기능
		mv.setViewName("WEB-INF/jsp/personList.jsp"); //도달하고자하는 jsp
		return mv;
	}

}
