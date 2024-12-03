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
		Answer answer= answerService.create(question, answerForm.getContent());
		return String.format("redirect:/question/detail/%s#answer_%s",id,answer.getId());
	
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm, 
										@PathVariable("id") Integer id,					
										Principal principal,
										) {
	
		Answer answer = answerService.getAnswer(id);

		if (!answer.getAuthor().getUsername().equals(principal.getName())) { //answer에 getAuthor한게 = principal 객체의 getName한 로그인정보와 같아야함
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다."); //
		}
		
	
		answerForm.setContent(answer.getContent());
		return "answer_form";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm, 
			@PathVariable("id") Integer id,					
			Principal principal.
			BindingResult bindingResult 
			) {
		
		if (bindingResult.hasErrors()) {
			return "answer_form";
		}
		
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.answerService.modify(answer, answerForm.getContent()); //엔서폼에 바인딩된값을 modify해 준 후 , detail페이지로 넘겨주면 됨
		return String.format("redirect:/question/detail/%s#answer_%s",
				answer.getQuestion().getId(), answer.getId()
				answer.getId()mm); //엔서 아이디에 있는 question의 id 값을 가져와야해서 answer.getQuestion().getI() 해줌
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
		Answer answer = this.answerService.getAnswer(id);
		if (!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.answerService.delete(answer);
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String answerVote(Principal principal, @PathVariable("id") int id) {
		Answer answer =answerService.getAnswer(id);
		SiteUser siteUser =userService.getUser(principal.getName());
		answerService.vote(answer, siteUser);
		return String.format("redirect:/question/detail/%s#answer_%s", 
				answer.getQuestion().getId(), 
				answer.getId());
	}
}
