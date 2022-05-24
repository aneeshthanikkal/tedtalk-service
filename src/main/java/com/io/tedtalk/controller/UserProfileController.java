package com.io.tedtalk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class UserProfileController {

	private final UserProfileService userProfileService;

	@PostMapping(path = "favouriteTedtalk/{tedtalkId}", produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<String> addFavouriteTedTalk(@PathVariable(name = "tedtalkId") String tedtalkId) {
		userProfileService.addFavouriteTedTalk(tedtalkId);
		return new ResponseEntity<>(TedTalkConstants.TEDTALK_ADDED_TO_FAVOURITES, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "favouriteTedtalk/{tedtalkId}", produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<String> deleteFavouriteTedTalk(@PathVariable(name = "tedtalkId") String tedtalkId) {
		userProfileService.deleteFavouriteTedTalk(tedtalkId);
		return new ResponseEntity<>(TedTalkConstants.TEDTALK_REMOVED_FROM_FAVOURITES, HttpStatus.NO_CONTENT);
	}

	
}
