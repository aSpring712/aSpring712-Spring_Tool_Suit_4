package com.person.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.person.model.PersonDAOImpl;
import com.person.model.PersonDTO;

public class PersonInsert extends AbstractController {
	private PersonDAOImpl dao;
	
	//setter
	public void setDao(PersonDAOImpl dao) { // springapp-servlet.xml
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse arg1) throws Exception { // doPost 라고 생각하면됨
		// 5개에 대한 값 받아오기 -> DI : 예전 서블릿에서는 dao getInstance로 해서 싱글톤으로 만들고 받아와야 했었는데 spring에서는 .xml에서 처리
		PersonDTO person = new PersonDTO();
		person.setGender(req.getParameter("gender"));
		person.setId(req.getParameter("id"));
		person.setJob(req.getParameter("job"));
		person.setName(req.getParameter("name"));
		person.setPassword(req.getParameter("password"));
		dao.personInsert(person); // personInsert 실행
		
		// 값 입력 후 list로 이동 -> ModelAndView 객체를 만들어서 컨트롤러를 불러줘야 함
		return new ModelAndView("redirect:personList.sp"); // 정보 가져가서 연결 끊어야 함 -> sendRedirect 방식필요(spring 기본 : 연결 유지-forward이므로) 
		// 넘길 때 두가지 방식 있음
		// reDirect, sendRedirect(관계 끊고 새로 연결), Dispatcher(관계 가지고 값을 넘김)
	}
}
