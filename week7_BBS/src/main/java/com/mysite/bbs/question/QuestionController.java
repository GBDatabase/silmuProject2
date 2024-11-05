package com.mysite.bbs.question;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository qService;

    @GetMapping("/index")
    public String root() {
        //return "index";
        return "redirect:/question/testData";
    }

    @GetMapping("/testData") 
    public String testData() {
    	for (int i=1;i<=300; i++)	{
    		Question q = new Question();
    		q.setSubject("테스트 데이터 입니다:[" + i + "]");
    		String subject = String.format("테스트 데이터 입니다 : [%03d]", i);
    		String content = "내용 없음";
    		q.setContent(subject);
    		q.setContent(content);
    		q.setCreateDate(LocalDateTime.now());
    		qService.save(q);
    	}
    	return "redirct:/question/list";
    }
    
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @GetMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
 BindingResult bindingResult) {
    	//유효성 검사 결과 체크
    	if(bindingResult.hasErrors())
    		return "question_form";
    	
    	
    	//질문을 저장하는 부분
    	qService.create(questionForm.getSubject(),questionFOrm.getContent());
    	
    	
    	// 질문을 저장 후 질문 목록으로 이동
    	
        return "redirect:/question/list";
    }
    
    @GetMapping("/insertForm")
    public String insertForm() {
    	return "insertForm";
    }
    
    @GetMapping("/insert")
    public String insert(@RequestParam("subject") String subject, @RequestParam("content") String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        qService.save(q);
        return "redirect:/question/list";
    }

    @GetMapping("/list")
    public String list(Model model,
    		@RequestParam(value="page", defaultValue = "0") int page) {
    		
    		
        //List<Question> qList = qService.getList();
        
    	Page<Question> paging = qService.getList(page);
    	
    	//model.addAttribute("qList", qList);
    	model.addAttribute("paging", paging);
    	
    	return "question_list";

    }
    
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id,
    		AnswerForm answerForm,
    		Model model) { 
    	Question question = qService.getQuestion(id);
    	model.addAttribute("question",question);
    	return "question_detail";
    }
    
}