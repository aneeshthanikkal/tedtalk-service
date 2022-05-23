package com.io.tedtalk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.tedtalk.constants.RestURIConstants;
import com.io.tedtalk.dto.LoginRequest;
import com.io.tedtalk.dto.LoginResponse;
import com.io.tedtalk.security.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(RestURIConstants.SECURITY_CONTEXT)
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtUtils jwtUtils;
	
	@PostMapping(RestURIConstants.SIGNIN)
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new LoginResponse(jwt));
	}
}