package com.example.demo2.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Enumerated(EnumType.STRING) // Integer으로 하면 위치값으로 해야하므로 STRING형으로 하는 게 좋음
	private Grade grade; // Grade형
	@Embedded // 포함시켜주기
	private Address address;
	
	@OneToMany(mappedBy = "hotel")
	private List<Review> reviews;
}
