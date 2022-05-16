package com.io.tedtalk.exception;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;

public class CommonResourceNotFoundException extends RuntimeException implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 6175801603735087430L;

	private final String message;
	
	private final transient Object[] args;

	static final Log log = LogFactory.getLog(CommonResourceNotFoundException.class);
	
	public CommonResourceNotFoundException(String message) {
		super(message);
		this.message = message;
		this.args = null;
	}

	public CommonResourceNotFoundException(String message, String arg) {
		super(message);
		this.message = message;
		this.args = new Object[] {arg};
	}
	
	public CommonResourceNotFoundException(String message, Object[] args) {
		super(message);
		this.message = message;
		this.args = args;
	}
	
	public CommonResourceNotFoundException(String message, Object[] args, Exception ex) {
		super(message, ex);
		this.message = message;
		this.args = args;
	}
	
	public CommonResourceNotFoundException(String message, Exception ex) {
		super(message, ex);
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
