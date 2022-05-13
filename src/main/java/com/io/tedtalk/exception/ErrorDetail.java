package com.io.tedtalk.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Aneesh Thannikkal
 *
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode()
public class ErrorDetail {

	private Integer status;

	private String message;

}
