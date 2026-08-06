package com.gcu.devjobs.services;

import com.gcu.devjobs.dto.ApplicantRegistrationRequest;
import com.gcu.devjobs.entities.Applicant;

public interface ApplicantRegistrationService {
	
	public boolean registerApplicant(ApplicantRegistrationRequest request);

}
