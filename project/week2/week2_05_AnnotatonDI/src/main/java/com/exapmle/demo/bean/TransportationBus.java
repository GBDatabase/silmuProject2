package com.exapmle.demo.bean;

import org.springframework.stereotype.Component;

@Component("transBus")
public class TransportationBus implements Transportation{

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("버스로 이동합니다");
		
	}




}
