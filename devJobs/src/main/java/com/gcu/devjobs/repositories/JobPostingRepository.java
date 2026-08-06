package com.gcu.devjobs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gcu.devjobs.entities.Employer;
import com.gcu.devjobs.entities.JobPosting;

public interface JobPostingRepository extends CrudRepository<JobPosting, Integer> {
	
	List<JobPosting> findByEmployer(Employer employer);

}
