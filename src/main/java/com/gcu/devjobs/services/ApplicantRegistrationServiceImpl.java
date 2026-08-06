package com.gcu.devjobs.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.devjobs.dto.ApplicantRegistrationRequest;
import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Applicant;
import com.gcu.devjobs.enums.Role;
import com.gcu.devjobs.repositories.AccountRepository;
import com.gcu.devjobs.repositories.ApplicantRepository;

@Service
public class ApplicantRegistrationServiceImpl implements ApplicantRegistrationService {

	private final ApplicantRepository applicantRepository;
	private final AccountRepository accountRepository;
	private final PasswordEncoder encoder;
	
	public ApplicantRegistrationServiceImpl(ApplicantRepository applicantRepository,
											AccountRepository accountRepository,
										    PasswordEncoder encoder) {
		this.applicantRepository = applicantRepository;
		this.accountRepository = accountRepository;
		this.encoder = encoder;
		
	}
	
	@Override
	public boolean registerApplicant(ApplicantRegistrationRequest request) {
		
		if (accountRepository.findByUsername(request.getUsername()).isPresent()) {
			return false;
		}
		
		var account = new Account();
		account.setUsername(request.getUsername());
		account.setPassword(encoder.encode(request.getPassword()));
		account.setRole(Role.APPLICANT);
		
		accountRepository.save(account);
		
		var applicant = new Applicant();
		applicant.setFirstName(request.getFirstName());
		applicant.setLastName(request.getLastName());
		applicant.setDateOfBirth(request.getDateOfBirth());
		applicant.setPhone(request.getPhone());
		applicant.setEmail(request.getEmail());
		applicant.setQualDescript(request.getQualDescription());
		
		applicant.setAccount(account);
		
		applicantRepository.save(applicant);
		
		return true;
	}
}
