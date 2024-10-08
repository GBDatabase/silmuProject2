package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.QuestionRepository;

@Controller 	//@Controller + @ResponseBody
public class MyController {
	private QuestionRepository repository;
	// 생성자의 매개변수로 기포지스토리 받기
	public MyController(QuestionRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/")
	public String root(){
		return index;
	}
	
	
	@GetMapping("/insertForm")
	public String insertForm() {
		return "insertFrom";
	}
	
	@GetMapping("/insert")
	public String insert(@RequestParam("subject") String subject,
						@RequestParam("content") String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContetnt(content);
		question.setCreateDate(LocalDateTime.now());
		repository.save(question);
		return "redirect:list"; 		//"/list" 요청을 보냄	
	}
	
	@GetMapping("/list")
	public String list(Model model)
		List<Question> qList = repository.findAll();
		model.addAllAttribute("qList",qList);
		return "question_list";
	
/*	//insert 요청 들어오면 question 테이블에 데이터 넣기
	@GetMapping("/insert")
	public void insert() {
		Question question = new Question();
		question.setSubject("sbb가 무엇인지요?");
		question.setContent("sbb에 대해 알고싶습ㄴ디ㅏ");
		question.setCreateDate(LocalDateTime.now());
		repository.save(question);
	}
*/
	/*
	@GetMapping("/display")
	public List<Question> display() {
		List<Question> q = repository.findAll(); //리포시토리에 있는 것들을 모든걸 읽자
		return q;
	}
}
*/