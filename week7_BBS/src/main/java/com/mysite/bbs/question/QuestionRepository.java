package com.mysite.bbs.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> getList();

	Question getQuestion(int id);
	
}
	
