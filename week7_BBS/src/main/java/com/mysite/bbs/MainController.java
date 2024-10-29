package com.mysite.bbs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	//루트에 대한 요청처리
	@RequestMapping("/")
	public String root() {
		return "redirect:/question/index";
	}
}
