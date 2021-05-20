package com.example.demo2.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_order")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private String orderName;
	private String note; // 설명
	private int price;
	@ManyToOne(fetch = FetchType.LAZY) // join을 언제 시킬건지 조건 설정(시기), LAZY -> 지연 전략(실행 시점에 연결 / 시작하자마자X)
	@JoinColumn(name="user_id") // 외래키 잡기 위해 join colum 필요 -> user_id로 잡겠다
	private User user; // 주문자 1명이 여러개 주문 할 수 있음
}
