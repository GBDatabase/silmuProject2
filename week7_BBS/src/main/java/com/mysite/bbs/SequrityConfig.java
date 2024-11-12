package com.mysite.bbs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Enablewebsecurity
//스프링 시큐리티는 웹사이트의 콘텐츠가 다른 사이트에 포함되지않도록 하기위해 x-frame-option 헤더의 기본값을 deny로 사용
public class SequrityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSec) {
		//로그인을 하지 않아도 게시물 조회가 가능하도록 허락하는 설정
		httpSec
		 .authoizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequest
				.requestMatchers(new AntPathRequestMatcher("/**"))
				.permitAll());
		// /h2-console/로 시작하는 모든 URL은 CSRF 검증을 하지 않도록 설정
		httpSec
			.csrf((srf)->csrf
					.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/")));
			
			return httpSec.build();
		//웹사이트의 콘텐츠가 다른 사이트에 포함이 가능하도록 설정 변경
			httpSec
				.header((headers)->headers
						.addHeaderWriter(new XFrameOptionsHeaderWriter(
								Xframeheaderwriter.xframepothonmode.sameorigin)));
			return httpSec.build();
	}
	//컨테이너에 만들어두고 필요할때마다 쓰니까 bean을 써서 bean이 붙으면 메소드 형식이지만 이 이름으로 객체가 만들어짐
	//만들어지는 객체의 타입이 PasswordEncoder임 
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
