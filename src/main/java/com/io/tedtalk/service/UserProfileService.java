package com.io.tedtalk.service;

import org.springframework.stereotype.Service;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.exception.CommonResourceNotFoundException;
import com.io.tedtalk.model.TedTalk;
import com.io.tedtalk.model.UserProfile;
import com.io.tedtalk.repository.TedTalkRepository;
import com.io.tedtalk.repository.UserProfileRepository;
import com.io.tedtalk.security.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

	private final UserProfileRepository userProfileRepository;
	private final TedTalkRepository tedTalkRepository;

	private final JwtUtils jwtUtils;

	public void addFavouriteTedTalk(String tedtalkId) {
		UserProfile userProfile = userProfileRepository.findByUsername(findUserName())
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.USER_PROFILE_NOT_FOUND));
		
		TedTalk tedTalk = tedTalkRepository.findById(tedtalkId)
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		
		userProfile.getFavouriteTedTalks().add(tedTalk);
		userProfileRepository.save(userProfile);
	}

	public void deleteFavouriteTedTalk(String tedtalkId) {
		UserProfile userProfile = userProfileRepository.findByUsername(findUserName())
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		
		TedTalk tedTalk = tedTalkRepository.findById(tedtalkId)
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		
		userProfile.getFavouriteTedTalks().remove(tedTalk);
		userProfileRepository.save(userProfile);

	}
	
	private String findUserName() {
		return jwtUtils.getUserNameFromJwtToken(jwtUtils.getAccessToken());
	}

}
