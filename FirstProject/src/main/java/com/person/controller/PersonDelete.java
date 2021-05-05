package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;

public class PersonDelete extends AbstractController { // 연결을 위한 상속 받기
	private PersonDAOImpl dao;
	
	// setter
	public void setDao(PersonDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse arg1) throws Exception { // 구현
		// 삭제할 id 받아오기
		String id = req.getParameter("id");
		// dao의 삭제 함수 호출
		dao.personDelete(id); // 삭제
		// 다시 List로 보냄(달고갈 값은 없음)
		return new ModelAndView("redirect:personList.sp");
	}

}
