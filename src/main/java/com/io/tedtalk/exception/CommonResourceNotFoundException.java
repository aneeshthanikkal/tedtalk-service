package com.io.tedtalk.exception;

import org.springframework.http.HttpStatus;

public class CommonResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6175801603735087430L;

	private final String message;
	
	private final transient Object[] args;

	public CommonResourceNotFoundException(String message) {
		super(message);
		this.message = message;
		this.args = null;
	}

	public HttpStatus getErrorCode() {
		return HttpStatus.NOT_FOUND;
	}

	public String getMessage() {
		return message;
	}

	public Object[] getArgs() {
		return args;
	}
	
}
