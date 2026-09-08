package com.gcu.devjobs.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Employer;
import com.gcu.devjobs.entities.JobPosting;
import com.gcu.devjobs.repositories.AccountRepository;
import com.gcu.devjobs.repositories.EmployerRepository;
import com.gcu.devjobs.services.JobPostingService;

@Controller
public class EmployerJobsController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployerJobsController.class);
	
	private final JobPostingService service;
	private final AccountRepository accountRepository;
	private final EmployerRepository employerRepository;
	
	public EmployerJobsController(JobPostingService service,
								AccountRepository accountRepository,
								EmployerRepository employerRepository) {
		this.service = service;
		this.accountRepository = accountRepository;
		this.employerRepository = employerRepository;
	}
	
	@GetMapping("/employerJobs/create")
	public String createJobPosting(Model model) {
		model.addAttribute("posting", new JobPosting());
		
		return "createJob";
	}
	
	@PostMapping("/employerJobs/create")
	public String createJobPosting(@ModelAttribute JobPosting posting, Authentication authentication) {
		
		logger.debug("Job posting creation attempt by user '{}'", authentication.getName());
		
		Account account = accountRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> {
					logger.error("Job posting creation failed: account not found for username '{}'", authentication.getName());
					return new RuntimeException("Logged-in user profile not found.");
		});
					
		
		Employer employer = employerRepository.findByAccount(account)
				.orElseThrow(() -> {
					logger.error("Job posting creation failed: no employer profile linked to account '{}'", account.getUsername());
					return new RuntimeException("Employer profile not found.");
				});
		
		service.save(posting, employer);
		
		logger.info("Employer '{}' created new job posting: '{}'", account.getUsername(), posting.getPosition());
		
		return "redirect:/employerJobs";
	}
	
	@GetMapping("/employerJobs")
	public String showEmployerJobs(Model model, Authentication authentication) {
		Employer employer = getCurrentEmployer(authentication);
		List<JobPosting> emplJobs = service.findByEmployer(employer);
		
		model.addAttribute("employerJobs", emplJobs);
		
		return "employerJobs";
	}
	
	@GetMapping("/employerJobs/edit/{id}")
	public String editJobPosting(@PathVariable int id, Model model) {
		Optional<JobPosting> posting = service.findById(id);
		
		if (posting.isEmpty()) {
			return "redirect:/employerJobs";
		}
		
		model.addAttribute("posting", posting.get());
		
		return "editJob";
	}
	
	@PostMapping("/employerJobs/edit/{id}")
	public String updateJobPosting(@PathVariable int id,
								   @ModelAttribute JobPosting posting,
								   Authentication authentication) {
		Employer employer = getCurrentEmployer(authentication);
		service.update(id, posting, employer);
		
		return "redirect:/employerJobs";
	}
	
	@GetMapping("/employerJobs/delete/{id}")
	public String deleteJobPosting(@PathVariable int id, Authentication authentication) {
		Employer employer = getCurrentEmployer(authentication);
		
		logger.warn("Employer '{}' is deleting job posting id={}", authentication.getName());
		
		service.deleteById(id, employer);
		
		return "redirect:/employerJobs";
	}
	
	private Employer getCurrentEmployer(Authentication authentication) {
		Account account = accountRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new RuntimeException("Logged-in user not found."));
		
		return employerRepository.findByAccount(account)
				.orElseThrow(() -> new RuntimeException("Employer profile not found."));
	}
}
