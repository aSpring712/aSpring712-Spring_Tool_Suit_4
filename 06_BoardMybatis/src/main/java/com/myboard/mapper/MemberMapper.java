package com.myboard.mapper;

import com.myboard.dto.MemberDTO;

public interface MemberMapper {
	// 추가
	public void join(MemberDTO member);
	// id 중복확인 -> boolean 형으로 반한해도 됨
	public int idCheck(String id);
	// 로그인 체크 -> 로그인 할 수 있는지, 회원이 아닌지 등 확인(boolean형으로 반환 가능)
	public MemberDTO loginCheck(String id);
}
