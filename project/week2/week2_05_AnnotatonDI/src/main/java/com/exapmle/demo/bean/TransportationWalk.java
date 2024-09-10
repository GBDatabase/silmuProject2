package com.exapmle.demo.bean;

import org.springframework.stereotype.Component;

@Component("transWalk")
public class TransportationWalk implements Transportation{

	@Override
	public void move() {
		// TODO Auto-generated method stub
			System.out.println("도보로 이동합니다.");
	}

}
