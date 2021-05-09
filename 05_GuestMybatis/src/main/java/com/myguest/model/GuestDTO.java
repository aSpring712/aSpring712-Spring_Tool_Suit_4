package com.myguest.model;

import lombok.Data;

@Data
public class GuestDTO { //lombok(@Data) 사용하면 자동적으로 getter, setter 등등 자동으로 생성됨
	private int num;
	private String name;
	private String content;
	private String grade;
	private String created;
	private String ipaddr;
}
