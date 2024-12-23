package com.mysite.bbs.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.bbs.question.Answer;
import com.mysite.bbs.question.Question;
import com.mysite.bbs.question.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	private final AnswerRepository answerRepository;

	//특정 질문에 대한 답변 등록 처리
	public Answer create(Question question, String content) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answerRepository.save(answer);

		return answer
	}

	public Answer getAnswer(int id) {
		Optional<Answer> answer = this.answerRepository.findById(id);
		
		if (answer.isPresent()) 
			return answer.get();  
		else 
			throw new DataNotFoundException("answer not found");
		}
	
	//답변 수정
	public void modify(Answer answer,String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());
		answerRepository.save(answer);
	}
	
	
	//답변 삭제
	public void delete(Answer answer) {
		answerRepository.delete(answer);
	}
	
	//투표기능
	public void vote(Answer answer, SiteUser siteUser) {
		answer.getVoter().add(siteUser);
		this.answerRepository.save(answer);
	}
	
	
	
}
