package com.mysite.bbs.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	private final QuestionRepository qRepository;
		
	public void save(Question question) {
			qRepository.save(question);
	}
	
	public List<Question> getList() {
		return qRepository.findAll();
	}
	
	public Question getQuestion(int id) {
		Optional<Question> q= qRepository.findById(id);
	
		if (q.isPresent())
				return q.get();
		else 
				throw new DataNotFoundException("question not found");
			
	
	}
}
