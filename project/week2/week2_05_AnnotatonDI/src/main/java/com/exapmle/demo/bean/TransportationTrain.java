package com.exapmle.demo.bean;

import org.springframework.stereotype.Component;

@Component("transTrain")
public class TransportationTrain implements Transportation{

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("기차로 이동합니다");
	}

	
}