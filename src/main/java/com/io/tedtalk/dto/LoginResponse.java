package com.io.tedtalk.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 917751056631431767L;


	private String token;

	private String type = "Bearer";
	
	public LoginResponse(String token) {
		this.token = token;
	}

	
}
