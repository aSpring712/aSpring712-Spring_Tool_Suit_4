package com.example.demo1.dto;

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
	private List<AuthDTO> authList;
}
