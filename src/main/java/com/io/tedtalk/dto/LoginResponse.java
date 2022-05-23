package com.io.tedtalk.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginResponse {

	private String token;

	private String type = "Bearer";
	
	public LoginResponse(String token) {
		this.token = token;
	}

	
}
