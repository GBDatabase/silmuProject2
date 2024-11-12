package com.mysite.bbs.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class UserCreateForm {
	//회원가입 시 입력되는 값들만 구성
	//입력값의 유효성 검증
	
	@Data
	//반드시 엔티티의 컬럼명과 동일하게
	//값은 반드시 드러와야하므로
	@NotEmpty(message = "사용자 ID는 필수 항목 입니다.")
	@Size(min =3 , max = 25)
	private String username;
	
	//패스워드 확인도 있음
	//실제엔티티에없는 패스워드가 들어올수도있음
	@NotEmpty(message = "비밀 번호는 필수 항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀 번호 확인은 필수 항목입니다.")
	private String password2;
	
	
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email
	//어노테이션을 주면 입력되는 포멧에 반드시 골뱅이를 줘야함을 명시함
	private String email;
}
