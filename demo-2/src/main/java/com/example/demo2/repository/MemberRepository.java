package com.example.demo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo2.model.JpaMember;

public interface MemberRepository extends JpaRepository<JpaMember, Long> { // T에는 객체 Entity Model 넣고, ID는 객체의 기본키 유형을 넣어줌 -> 처리됨 : 이미 함수가 만들어져 있음
	
}
