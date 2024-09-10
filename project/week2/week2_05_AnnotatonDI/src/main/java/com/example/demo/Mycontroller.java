package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exapmle.demo.bean.Member;
import com.exapmle.demo.bean.Transportation;


@Controller
public class Mycontroller {
	@Autowired
	Member member1;
	@Autowired
	Member member2;
	@Autowired
	@Qualifier("transTrain")
	Transportation trans;
	
	@RequestMapping("/")
	
	public @ResponseBody String root() {
		member1.move();
		member1.setTrans(trans); //바꾸기 
		
		//같은 객체인지 물어보기
		if (member1 == member2)
			System.out.println("동일한 객체");
		else
		System.out.println("동일한 객체");
	
		return("어노테이션 사용하기");
	
	}
}
