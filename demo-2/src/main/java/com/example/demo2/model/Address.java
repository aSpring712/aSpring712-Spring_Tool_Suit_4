package com.example.demo2.model;

import javax.persistence.Embeddable;

@Embeddable // Hotel에 포함되므로?
public class Address {
	private String zipcode;
	private String address1;
	private String address2;
}
