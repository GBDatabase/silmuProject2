package com.mysite.bbs.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.bbs.question.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	private final AnswerRepository answerRepository;

	//특정 질문에 대한 답변 등록 처리
	public void create(Question question, String content) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answerRepository.save(answer);
	}
}
