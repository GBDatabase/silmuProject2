package com.exapmle.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Member {
	
	@Value("홍길동")
	private String name;
	
	@Value("도사")
	private String nickname;
	
	@Autowired
	private Transportation trans;
	
	@Qualifier("transWalk");

	public Member() {
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public Member(String name, String nickname, Transportation trans) {
		this.name = name;
		this.nickname = nickname;
		this.trans = trans;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public void setTrans(Transportation trans) {
		this.trans = trans;
	}
	
	public void move(){
		trans.move();
	}
	
	
	
}


