package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week1Application {

	public static void main(String[] args) {
		SpringApplication.run(Week1Application.class, args);
		System.out.printf("%n");
		System.out.println("과목 : 실무프로젝트2");
		System.out.println("학과 : 시스템소프트웨어공학과");
		System.out.println("학번 : 20210646");
		System.out.println("이름 : 문가빈");
	}

}
