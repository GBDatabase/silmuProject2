package com.example.demo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class Config {

		public Member member1() {
			Member member1 = new Member();
			member1.setName("홍길동");
			member1.setNickname("도사");
			member1.setTrans(new TransportationWalk());
			return member1;
		}
		
		
		@Bean(name = "hello")
		public Member member2() {
			return new Member("전우치","도사",new TransportationBus());
		}
		
		@Bean
		public TransportationTrain transportation() {
			return new TransportationTrain();
		}
		
}
