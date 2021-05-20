package com.myboard.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String name;
	private String pass;
	private String addr;
	private String regdate;
}
