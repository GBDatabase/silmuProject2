package com.mysite.bbs.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

//내가 필요로 하는 객체는 생성자 매개변수로 넣겠다.RequiredArgsConstructor
@RequiredArgsConstructor
@Controller
@RequestionMapping("/user")
//요청처리에는 도메인을...
public class UserController {
	//컨트롤러에서는 서비스객체가 필요함
	private final UserService userService;
	
	//회원가입요청이 드렁오면 사인업(회원가입)이 들어오면 signup 메소드를 실행하자.
	//signup은 signup_form 으로 넘겨주는 것
	//매개변수로 폼클래스 값을 넣어줘야함
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	
	//폼 클래스를 통해 입력된 값을 이용해서 회원가입 처리
	//signup_form 에서 메서드가 post로 넘어왔으니까 postMapping으로 받아주고
	@PostMapping("/signup");
	//유효성검사하면 결과를 바인딩 매핑으로 받음
	//제일먼저 해야하는게 오류가 있는지 확인해야해서 if 문으로 검사해줌
	public String signup(@Valid UserCreateForm userCreateForm,
					BindingResult BindingResult) {
		if(BindingResult.hasErrors()) {
			return "signup_form";
	}
			//@valid로 id 나 email은 검사가 가능한데
			//여기에서 @valid로 체크가 안되는 애들(password1, password2)의 값이 같은지 체크해주기위해서
			// 패스워드 1과 패스워드 2가 같지않으면 해야하므로 !를 괄호 맨앞에 붙여줌
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2","passwordIncorrect","2개의 비밀번호가 일치하지 않습니다.");
			//회원가입하는 페이지 그대로 머무르도록 다시 보내줌
			return "signup_form";
			}
			
			//데이타가 있는데 또다른 데이터가 들어올까봐 중보거리
			try {
				//이름은 usercreateForm에서 getUsername을 가져오고, 패스워드의 경우도 그렇게 가져와줌
			userService.create(userCreateForm.getUsername(),
								userCreateForm.getPassword1()
								userCreateForm.getEmail());
			return "redirect:/";
			} catch(DataIntegrityViolationException e) {
				e.pringStackTrace();
				bindingResult.rejectValue("signupFailed", "이미 등록된 사용자입니다.");
				return "signup_form";
			} catch(DataIntegrityViolationException e) {
				e.pringStackTrace();
				bindingResult.rejectValue("signupFailed", e.getMessage());
				return "signup_form";

	}
	
}	
