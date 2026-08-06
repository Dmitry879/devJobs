package com.gcu.devjobs.services;

import com.gcu.devjobs.dto.EmployerRegistrationRequest;
import com.gcu.devjobs.entities.Employer;

public interface EmployerRegistrationService {
	
	public boolean registerEmployer(EmployerRegistrationRequest request);
}
