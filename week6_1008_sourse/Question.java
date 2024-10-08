package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //중복되지않는 값을 하나씩 주겠다
	private int id;
	@Column(length=20)
	private String subject;
	@Column(columnDefinition="TEXT")
	private String content;
//질문이 언제 입력되었는가
	private LocalDateTime createDate;
	@OneToMany(mappedBy = "question", cascade= CascadeType.REMOVE) // 질문한개에 여러개의 답변, 질문이 없애면 답변도 없앤다
	private List<Answer> answerList;

}
