package com.gcu.devjobs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.devjobs.dto.ApplicantRegistrationRequest;
import com.gcu.devjobs.services.ApplicantRegistrationService;
import jakarta.validation.Valid;

@Controller
public class ApplicantRegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(JobPostingController.class);
	
	private final ApplicantRegistrationService service;
	
	public ApplicantRegistrationController(ApplicantRegistrationService service) {
		this.service = service;
	}
	
	@GetMapping("/registerApplicant")
	public String showApplRegForm(Model model) {
		model.addAttribute("applicant", new ApplicantRegistrationRequest());
		return "registerApplicant";
	}
	
	@PostMapping("/registerApplicant")
	public String registerApplicant(@Valid @ModelAttribute("applicant") ApplicantRegistrationRequest request,
									BindingResult result) {
		if (result.hasErrors()) {
			
			logger.debug("Applicant registration validation failed: {}", result.getAllErrors());
			
			return "registerApplicant";
		}
		
		service.registerApplicant(request);
		
		logger.info("New applicant registered: {}", request.getUsername());
		
		return "redirect:/login";
		
	}
}
