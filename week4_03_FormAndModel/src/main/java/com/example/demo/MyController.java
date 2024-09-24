package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loginResult")
	public String loginResult(@RequestParam("id") String id,
							@RequestParam("password") String pw,
							@RequestParam("name") String name,
							Model model) {
			 model.addAttribute("id",id);
			 model.addAttribute("pw",pw);
			 model.addAttribute("name",name);
		return "inputResult";
	}
}
