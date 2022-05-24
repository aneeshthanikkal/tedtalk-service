package com.io.tedtalk.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.model.UserProfile;
import com.io.tedtalk.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserProfileRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfile userProfile = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(TedTalkConstants.NO_USER_FOUND + username));
		return UserDetailsImpl.build(userProfile);
	}
}