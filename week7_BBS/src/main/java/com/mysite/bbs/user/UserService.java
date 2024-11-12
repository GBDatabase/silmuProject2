package com.mysite.bbs.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	//db핸들링 해야하니까 repository 가져야함
	//직접 repository를 사용
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	//회원등록처리
	//등록해서 등록한 회원정보리턴
	//controller에서 create 불러옴
	//만약에 가져와야할 데이터가 많아지고, 유효성 검색을 해야한다면 이런바익은 좋지않음.
	//이걸 조금 개선하기위해 form class를 만듦
	public SiteUser create(String username,
							String password;
							String email) {
			
			SiteUser user = new SiteUser();
			user.setUsername(username);
			user.setEmail(email);
			
			//sequrityConfig에서 bean을 써서 아래줄을 써서 쓸필요가 없고 가져와야해서 아랫줄 주석처리, 대신 12번째줄 추가함.
			//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			
			user.setPassword(passwordEncoder.encode(password));
			
			userRepository.save(user);
	}
				

}
