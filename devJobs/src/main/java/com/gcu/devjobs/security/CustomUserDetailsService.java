package com.gcu.devjobs.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.devjobs.controllers.JobPostingController;
import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.repositories.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobPostingController.class);
	
	private final AccountRepository repository;
	
	public CustomUserDetailsService(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = repository.findByUsername(username)
				.orElseThrow(() ->
					new UsernameNotFoundException("User not found."));
		
		logger.info("User '{}' authenticated with role {}", username, account.getRole());
		
		return User.builder()
				.username(account.getUsername())
				.password(account.getPassword())
				.roles(account.getRole().name())
				.build();
	}
}
