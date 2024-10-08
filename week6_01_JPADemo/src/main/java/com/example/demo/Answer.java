package com.example.demo;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Answer {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)  //중복되지않는 값을 하나씩 주겠다
	private int id;
@Column(columnDefinition="TEXT")
	private String content;
@CreatedDate
	private LocalDate createDate;
@ManyToOne //한개의 질문에 답변이 many하게 달릴수 있다
	private Question question;
}

