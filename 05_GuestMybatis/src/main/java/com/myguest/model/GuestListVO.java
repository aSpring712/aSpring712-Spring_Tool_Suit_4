package com.myguest.model;

import java.util.List;

import lombok.Data;

@Data // @Getter만 있으면 됨
public class GuestListVO {
	private int count;
	private List<GuestDTO> list;
	
	public GuestListVO(int count, List<GuestDTO> list) { // 생성자
		this.count = count;
		this.list = list;
	}
}
