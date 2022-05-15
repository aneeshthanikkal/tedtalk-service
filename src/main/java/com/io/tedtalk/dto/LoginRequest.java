package com.io.tedtalk.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginRequest implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 917751056631431767L;


	private String username;

	private String password;
	
}
