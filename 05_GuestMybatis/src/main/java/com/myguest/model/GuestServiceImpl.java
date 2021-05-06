package com.myguest.model;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("service") // 서비스라는 걸 알려줘야 함 -> 리소스는 name으로 접근
public class GuestServiceImpl implements GuestService {

	@Resource(name="dao")
	private GuestDAOImpl dao;
	
	@Override
	public void guestInsert(GuestDTO guest) {
		dao.dao_guestInsert(guest); // Controller에서 서비스를 불러서 여기로 왔고 이제 DAOImpl로 감
		
	}

	@Override
	public List<GuestDTO> guestList(HashMap<String, String> hm) {
		return dao.dao_guestList(hm);
	}

	@Override
	public GuestDTO findByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guestUpdate(GuestDTO guest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guestDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int guestCount(HashMap<String, String> hm) {
		// TODO Auto-generated method stub
		return 0;
	}

}
