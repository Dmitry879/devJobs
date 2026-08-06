package com.gcu.devjobs.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Applicant;
import com.gcu.devjobs.entities.Employer;
import com.gcu.devjobs.repositories.AccountRepository;
import com.gcu.devjobs.repositories.ApplicantRepository;
import com.gcu.devjobs.repositories.EmployerRepository;

@Controller
public class ProfileController {
	
	private final AccountRepository accountRepository;
	private final ApplicantRepository applicantRepository;
	private final EmployerRepository employerRepository;
	
	public ProfileController(AccountRepository accountRepository,
							 ApplicantRepository applicantRepository,
							 EmployerRepository employerRepository) {
		this.accountRepository = accountRepository;
		this.applicantRepository = applicantRepository;
		this.employerRepository = employerRepository;
	}
	
	@GetMapping("/profile")
	public String showProfile(Model model, Authentication authentication) {
		
		Account account = accountRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new RuntimeException("Logged-in user not found."));
		
		String role = account.getRole().name();
		
		if (role.equals("APPLICANT")) {
			Applicant applicant = applicantRepository.findByAccount(account)
					.orElseThrow(() -> new RuntimeException("Applicant profile not found."));
			model.addAttribute("applicant", applicant);
			return "applicantProfile";
		}
		
		if (role.equals("EMPLOYER")) {
			Employer employer = employerRepository.findByAccount(account)
					.orElseThrow(() -> new RuntimeException("Employer profile not found."));
			model.addAttribute("employer", employer);
			return "employerProfile";
		}
		
		return "redirect:/";
	}

}
