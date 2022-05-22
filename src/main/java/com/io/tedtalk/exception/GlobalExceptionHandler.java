package com.io.tedtalk.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;
	
	Locale locale = LocaleContextHolder.getLocale();

	@Autowired
	public GlobalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> genericExceptionHandler(Exception ex) {
		ErrorDetail error = new ErrorDetail();
		if (ex instanceof MissingRequestHeaderException) {
			error.setStatus(HttpStatus.FORBIDDEN.value());
			error.setMessage(ex.getMessage());
			return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
		}
		if (ex instanceof AccessDeniedException) {
			error.setStatus(HttpStatus.FORBIDDEN.value());
			error.setMessage(ex.getMessage());
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		if (ex instanceof InvalidFormatException) {
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage("Cannot parse the input string");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(messageSource.getMessage(ex.getMessage(), null, locale));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CommonBadRequestException.class)
	public ResponseEntity<Object> handleCommonBadRequestException(CommonBadRequestException ex) {
		ErrorDetail error = new ErrorDetail();
		error.setStatus(ex.getErrorCode().value());
		error.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
		return new ResponseEntity<>(error, ex.getErrorCode());
	}
	
	@ExceptionHandler(CommonResourceNotFoundException.class)
	public ResponseEntity<Object> handleCommonResourceNotFoundException(CommonResourceNotFoundException ex) {
		ErrorDetail error = new ErrorDetail();
		error.setStatus(ex.getErrorCode().value());
		error.setMessage(messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale));
		return new ResponseEntity<>(error, ex.getErrorCode());
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorDetail> errorDetailsList = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			ErrorDetail errorDetail = new ErrorDetail();
			errorDetail.setStatus(400);
			errorDetail.setMessage(messageSource.getMessage(error.getDefaultMessage(),  null, locale));
			errorDetailsList.add(errorDetail);
		}
				return new ResponseEntity<>(errorDetailsList, HttpStatus.BAD_REQUEST);
		
	}
}