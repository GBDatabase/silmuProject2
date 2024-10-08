package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer>{ //어떤 정보를 핸들링 할 것인가(T→question, ID 타입엔 겍체형태가와야해서 Integer)

	
}
