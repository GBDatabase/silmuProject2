package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class MyController {
	@RequestMapping("/")
	@ResponseBody
	public String root( ) {
		return "Form 데이터를 전달받아 사용하기";
	}
	
	@RequestMapping("/test1")
	public String test1(HttpServletRequest request , Model model) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "test1";
	}
	@RequestMapping("/test2")
	public String test2(@RequestParam("id") String id,
						@RequestParam("name") String name,
						Model model) {
		model.addAttribute("id",id);
		model.addAttribute("name",name);
		return "test2";
	}
	@RequestMapping("/test3")
	public String test3(Member member) {
		//파라미터와 일치하는 빈을 만들어서 사용 => 커맨드 객체
		//view 페이지에서 model을 사용하지 않고도 member  객체 사용 가능
		return "test3";
	}
	
	@RequestMapping("/test4/{id}/{name}")
	public String test4(@PathVariable("id") String id,
						@PathVariable("name") String name,
						Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "test4";
	}
}

	

