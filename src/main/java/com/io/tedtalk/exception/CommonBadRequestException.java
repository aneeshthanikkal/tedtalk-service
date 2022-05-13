package com.io.tedtalk.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;

/**
 * 
 * @author Aneesh Thannikkal
 *
 */

public class CommonBadRequestException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -721175347370902154L;

	private final String message;
	
	private final transient Object[] args;

	static final Log log = LogFactory.getLog(CommonBadRequestException.class);
	
	public CommonBadRequestException(String message) {
		super(message);
		this.message = message;
		this.args = null;
	}

	public CommonBadRequestException(String message, String arg) {
		super(message);
		this.message = message;
		this.args = new Object[] {arg};
	}
	
	public CommonBadRequestException(String message, Object[] args) {
		super(message);
		this.message = message;
		this.args = args;
	}
	
	public CommonBadRequestException(String message, Object[] args, Exception ex) {
		super(message, ex);
		this.message = message;
		this.args = args;
	}
	
	public CommonBadRequestException(String message, Exception ex) {
		super(message, ex);
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
