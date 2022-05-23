package com.io.tedtalk.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.model.User;
import com.io.tedtalk.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(TedTalkConstants.NO_USER_FOUND + username));
		return UserDetailsImpl.build(user);
	}
}