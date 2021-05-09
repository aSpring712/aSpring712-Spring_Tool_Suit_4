package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;
import com.person.model.PersonDTO;

public class PersonUpdate extends AbstractController {
	private PersonDAOImpl dao;
	
	//setter
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, 
			HttpServletResponse arg1) throws Exception {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String job = req.getParameter("job");
		String gender = req.getParameter("gender");
		PersonDTO p = new PersonDTO();
		p.setGender(gender);
		p.setId(id);
		p.setJob(job);
		p.setName(name);
		p.setPassword(password);
		dao.personUpdate(p);
		return new ModelAndView("redirect:personList.sp");
	}
	

}
