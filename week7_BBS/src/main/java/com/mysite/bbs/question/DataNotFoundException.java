package com.mysite.bbs.question;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L; /* 내가만든객체의버전 */
	public DataNotFoundException(String message) {
		super(message); /* notfond났을때 클래스 정의 처리 */
		
	}
}
