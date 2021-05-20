package com.example.demo2.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="tbl_user") // table 명
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY : AI(auto increment) 적용
	private Long userId; // 보통 id값 자동생성 시 Long형으로 만들기
	private String userName;
	private String nickName;
	private String address;
	@OneToMany(mappedBy="user") // user를 참조해서 쓴다
	private List<Order> orders; // 1명이 짜장면, 짬뽕, 탕수육 등.. 여러개 주문
}
