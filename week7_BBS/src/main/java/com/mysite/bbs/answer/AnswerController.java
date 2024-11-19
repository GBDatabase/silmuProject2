package com.mysite.bbs.answer;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.bbs.question.Preauthorize;
import com.mysite.bbs.question.Question;
import com.mysite.bbs.question.QuestionService;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	//답변을 하기 위해서는 질문이 있어야 함.
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@Preauthorize("isAuthenticated()")
	//답변을 create 요청처리
	@PostMapping("/create/{id}")
	public String createAnswer(@PathVariable("id") int id, @Valid AnswerForm answerForm,BindingResult bindingResult,Model model){
		Question question = questionService.getQuestion(id);
		
		//유효성 검사 결과 체크
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
			
		}
		
		
		// 답변을 저장하는 코드
		answerService.create(question, answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	
	}
}
