package com.myguest.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.myguest.model.GuestDTO;

public interface GuestMapper {
	// 추가
	@Insert("insert into guest(name, content, grade, created, ipaddr) "
			+ "values(#{name}, #{content}, #{grade}, now(), #{ipaddr})")
	public void insert(GuestDTO guest);
	
	// 전체보기
//	@Select("select * from guest")
	public List<GuestDTO> list(HashMap<String, String> hm); // GuestDAOImpl.java의 mapper.list와 이름이 같게 해야함
	
	// 개수
//	@Select("select count(*) from guest")
	public int count(HashMap<String, String> hm);

	// 상세보기
	@Select("select * from guest where num = #{num}")
	public GuestDTO findByNum(int num);
	
	// 삭제
	@Delete("delete from guest where num = #{num}")
	public void delete(int num);
}