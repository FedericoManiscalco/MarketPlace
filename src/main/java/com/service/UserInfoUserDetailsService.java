package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.config.UserInfoUserDetails;
import com.entity.UserInfo;
import com.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repository.findByEmail(username);

		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}
}
