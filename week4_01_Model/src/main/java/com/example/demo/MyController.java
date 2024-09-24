package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "Model & View";
	}
	
	@RequestMapping("/test1")
	public String test1(Model model) {
		model.addAttribute("name","홍길동");
		return "test1";
	}
	
	
	@RequestMapping("/mv")
	public ModelAndView test2() {
		//ModelAndView : 데이터와 뷰를 동시에 설정 가능
		ModelAndView mv = new ModelAndView();
		
		List<String> list = new ArrayList<>();
		list.add("test1");
		list.add("test2");
		list.add("test3");
	
		mv.addObject("lists",list);
		mv.addObject("ObjectTest","테스트입니다");
		mv.addObject("name","홍길동");
		mv.setViewName("sub/myView");
		
		return mv;
	}
	
}
	
	
	
	