package com.mysecurity.dto;

import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {
	private String userid;
	private String userpw;
	private String username;
	private boolean enabled;
	private String regDate;
	private String updateDate;
	private List<AuthDTO> authList; // 한 회원은 권한을 여러 개를 가지므로 List형으로 (관리자 - user 기능, manager 기능, admin 기능 가짐)
}
