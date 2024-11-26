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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

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
    
    @Preauthorize("isAuthenticated()")
    @GetMapping("/testData") 
    public String testData(principal principal) {
    	for (int i=1;i<=300; i++)	{
    		Question q = new Question();
    		q.setSubject("테스트 데이터 입니다:[" + i + "]");
    		String subject = String.format("테스트 데이터 입니다 : [%03d]", i);
    		String content = "내용 없음";
    		q.setContent(subject);
    		q.setContent(content);
    		q.setCreateDate(LocalDateTime.now());
    		
    		siteuser siteuser = userservice.getUser(principal.getName());
    		qService.save(q);
    	}
    	return "redirct:/question/list";
    }
    
    @Preauthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @Preauthorize("isAuthenticated()")
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
    
    @Preauthorize("isAuthenticated()")
    @GetMapping("modify/{id}")
	/* question에 있는 author이랑 , principal.getName이 같은지 검사 */
	
    public String questionModify(QuestionForm questionForm,
    							@PathVariable("id") int id,
    							Principal principal) {
    	Question question =qService.getQuestion(id);
    	if(!question.getAuthor().getUsername().equals(principal.getName()))
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
    }
    /* 아이디에 있는 정보를 question form 에다가 저장해서 넘김 */
    
    question.setSubject(question.getSubject());
    question.setSubject(question.getContent());
    
    /*question 등록할때의 폼을 수정할때의 폼으로 쓰는 이유는 , 요약하면 수정폼을 만들지 않고 등록할때의 폼을 쓰는 이유는
    form태그 안에있는 action을 지워서 사용함
    action 속성이 없이 폼을 submit하면 자동으로 현재 url 기준으로 전달됨*/
    return "question_form"
    
    		
    		
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal, //폼클래스를 입력하여 유효성 검증
			@PathVariable("id") int id) {
		
    	if (bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = qService.getQuestion(id); 
 		if (!question.getAuthor().getUsername().equals(principal.getName())) { //글쓴이하고 로그인한 정보의 사용자가 동일해야함
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다."); 
		}
		
		this.qService.modify(question, questionForm.getSubject(), questionForm.getContent()); //입력받는 곳 :questionForm에
		return String.format("redirect:/question/detail/%s", id); //detail페이지에 id를 넘겨서 detail페이지에서 확인가능하게 함
	}    		
	
		
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(@PathVariable("id") int id,
								Principal principal ) { //로그인한 정보 principal로 받음
		Question question = qService.getQuestion(id);
		
		if (!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		qService.delete(question);
		return "redirect:/";
	}
}







