package com.myguest.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.myguest.model.GuestDTO;

public interface GuestMapper {
	// 추가
	@Insert("insert into guest(name, content, grade, created, ipaddr) "
			+ "values(#{name}, #{content}, #{grade}, now(), #{ipaddr})")
	public void insert(GuestDTO guest);
	
	@Select("select * from guest")
	public List<GuestDTO> list(HashMap<String, String> hm); // GuestDAOImpl.java의 mapper.list와 이름이 같게 해야함
}
