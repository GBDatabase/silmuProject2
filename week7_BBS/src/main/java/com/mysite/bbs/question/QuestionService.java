package com.mysite.bbs.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	private final QuestionRepository qRepository;
		
	public void save(Question question) {
			qRepository.save(question);
	}
	
//	public List<Question> getList() {
//		return qRepository.findAll();
//	}
	
//  page 기능이 적용된 리스트
	public Page<Question> getList(int page) {
		Pageable pageable=PageRequest.of(page, 10);
		return qRepository.findAll(pageable);
	}
	
	public Question getQuestion(int id) {
		Optional<Question> q= qRepository.findById(id);
	
		if (q.isPresent())
				return q.get();
		else 
				throw new DataNotFoundException("question not found");
			
	
	}
	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		qRepository.save(q);
	}

}
