package com.mysite.bbs.question;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository qService;

    @GetMapping("/question/index")
    public String root() {
        return "index";
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
    public String list(Model model) {
        List<Question> qList = qService.getList();
        model.addAttribute("qList", qList);
        return "question_list";

    }
    
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id,
    		Model model) { 
    	Question question = qService.getQuestion(id);
    	model.addAttribute("question",question);
    	return "question_detail";
    }
    
}