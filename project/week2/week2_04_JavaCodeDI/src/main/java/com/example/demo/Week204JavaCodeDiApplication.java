package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.demo.bean.Config;
import com.example.demo.bean.Member;
import com.example.demo.bean.Transportation;
import com.example.demo.bean.TransportationTrain;

@SpringBootApplication
public class Week204JavaCodeDiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(Week204JavaCodeDiApplication.class, args);
	
	// * #1. IOC 컨테이너 생성 *
	ApplicationContext context = 
			new AnnotationConfigApplicationContext(Config.class);
	
	
	// * #2. member 빈 가져오기 *
	// 가져올때 캐스팅은 잘 안씀
	Member member1 = (Member) context.getBean("member1");
	member1.move();
	
	
	// * #1. member 빈 가져오기 *
	Member member2 = context.getBean("hello",Member.class);
	member2.move();
	
	// * #4. train 가져오기 *
	Transportation trans = context.getBean("transportation",TransportationTrain.class);
	member1.setTrans(trans);
	member2.move();
	

	// * #5. 비교  *
	if (member1 == member2)
		System.out.println("동일한 객체");
	else
		System.out.println("서로 다른 객체");
	
		
	Member member3 = context.getBean("member1", Member.class);
	if (member1 == member3)
		System.out.println("동일한 객체");
	else
		System.out.println("서로 다른 객체");
	
		
	}
}
