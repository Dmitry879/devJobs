package com.gcu.devjobs.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.devjobs.dto.EmployerRegistrationRequest;
import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Employer;
import com.gcu.devjobs.enums.Role;
import com.gcu.devjobs.repositories.AccountRepository;
import com.gcu.devjobs.repositories.EmployerRepository;

@Service
public class EmployerRegistrationServiceImpl implements EmployerRegistrationService {

	private final EmployerRepository employerRepository;
	private final AccountRepository accountRepository;
	private final PasswordEncoder encoder;
	
	public EmployerRegistrationServiceImpl(EmployerRepository employerRepository,
										   AccountRepository accountRepository,	
										   PasswordEncoder encoder) {
		this.employerRepository = employerRepository;
		this.accountRepository = accountRepository;
		this.encoder = encoder;
	}
		
	
	@Override
	public boolean registerEmployer(EmployerRegistrationRequest request) {
		
		if (accountRepository.findByUsername(request.getUsername()).isPresent()) {
			return false;
		}
		
		var account = new Account();
		account.setUsername(request.getUsername());
		account.setPassword(encoder.encode(request.getPassword()));
		account.setRole(Role.EMPLOYER);
		
		accountRepository.save(account);
		
		var employer = new Employer();
		employer.setName(request.getName());
		employer.setPhone(request.getPhone());
		employer.setEmail(request.getEmail());
		
		employer.setAccount(account);
		
		employerRepository.save(employer);
		
		return true;
	}
}
