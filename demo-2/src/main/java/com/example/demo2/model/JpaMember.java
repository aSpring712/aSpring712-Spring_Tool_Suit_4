package com.example.demo2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class JpaMember {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본적으로 autoIncrement가 check됨
	private Long id; // 기본키
	private String name;
	private String password;
	private String email;
	private String memo;
	@Column(name="address") // 테이블에는 address로 들어감
	private String addr; // 객체 이름은 addr
}
