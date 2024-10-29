package com.mysite.bbs.answer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.bbs.question.Question;
import com.mysite.bbs.question.QuestionService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	//답변을 하기 위해서는 질문이 있어야 함.
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	//답변을 create 요청처리
	@PostMapping("/create/id")
	public String createAnswer(@PathVariable("id") int id, @RequestParam("content") String content,Model model){
		Question question = questionService.getQuestion(id);
		
		// 답변을 저장하는 코드
		answerService.create(question, content);
		return String.format("redirect:/question/detail/%s", id);
	
	}
}
