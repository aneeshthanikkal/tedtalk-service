package com.io.tedtalk.exception;

import org.springframework.http.HttpStatus;

public class CommonBadRequestException extends RuntimeException {

	private static final long serialVersionUID = -721175347370902154L;

	private final String message;
	
	private final transient Object[] args;

	public CommonBadRequestException(String message) {
		super(message);
		this.message = message;
		this.args = null;
	}

	public HttpStatus getErrorCode() {
		return HttpStatus.BAD_REQUEST;
	}

	public String getMessage() {
		return message;
	}

	public Object[] getArgs() {
		return args;
	}
	
}
