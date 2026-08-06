package com.gcu.devjobs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.gcu.devjobs.entities.JobPosting;
import com.gcu.devjobs.services.JobPostingService;

@Controller
public class JobPostingController {
	
	private final JobPostingService service;
	
	public JobPostingController(JobPostingService service) {
		this.service = service;
	}
	
	@GetMapping("/jobPostings")
	public String showJobPostings(Model model) {
		List<JobPosting> postings = service.findAll();
		model.addAttribute("jobPostings", postings);
		
		return "jobPostings";
	}
	
	@GetMapping("/jobPostings/{id}")
	public String showJobPosting(@PathVariable int id, Model model, Authentication authentication) {
		Optional<JobPosting> posting = service.findById(id);
				
		if (posting.isEmpty()) {
			return "redirect:/jobPostings";
		}
		
		model.addAttribute("posting", posting.get());
		
		return "jobDetails";
	}
}
