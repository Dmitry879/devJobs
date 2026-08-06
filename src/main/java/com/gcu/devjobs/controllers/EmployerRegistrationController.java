package com.gcu.devjobs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.devjobs.dto.EmployerRegistrationRequest;
import com.gcu.devjobs.services.EmployerRegistrationService;
import jakarta.validation.Valid;


@Controller
public class EmployerRegistrationController {
	
	private final EmployerRegistrationService service;
	
	public EmployerRegistrationController(EmployerRegistrationService service) {
		this.service = service;
	}
	
	@GetMapping("/registerEmployer")
	public String showEmplRegForm(Model model) {
		model.addAttribute("employer", new EmployerRegistrationRequest());
		return "registerEmployer";
	}
	
	@PostMapping("/registerEmployer")
	public String registerEmployer(@Valid @ModelAttribute("employer") EmployerRegistrationRequest request,
								   BindingResult result) {
		if (result.hasErrors()) {
			return "registerEmployer";
		}
		
		service.registerEmployer(request);
		
		return "redirect:/login";
	}

}
