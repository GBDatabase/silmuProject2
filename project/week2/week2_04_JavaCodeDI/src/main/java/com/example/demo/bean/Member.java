package com.example.demo.bean;


public class Member {
	private String name;
	private String nickname;
	private Transportation trans;


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


