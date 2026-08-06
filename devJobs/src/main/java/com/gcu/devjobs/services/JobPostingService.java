package com.gcu.devjobs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gcu.devjobs.controllers.JobPostingController;
import com.gcu.devjobs.entities.Employer;
import com.gcu.devjobs.entities.JobPosting;
import com.gcu.devjobs.repositories.JobPostingRepository;

@Service
public class JobPostingService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobPostingController.class);
	
	private final JobPostingRepository repository;
	
	public JobPostingService(JobPostingRepository repository) {
		this.repository = repository;
	}
	
	public JobPosting save(JobPosting posting, Employer employer) {
		posting.setEmployer(employer);
		return repository.save(posting);
	}
	
	public List<JobPosting> findAll() {
		List<JobPosting>postings = new ArrayList<>();
		repository.findAll().forEach(postings::add);

		return postings;
	}
	
	public Optional<JobPosting> findById(int id) {
		return repository.findById(id);
	}
	
	public List<JobPosting> findByEmployer(Employer employer) {
		return repository.findByEmployer(employer);
	}
	
	public Optional<JobPosting> update(int id, JobPosting posting, Employer employer) {
		return repository.findById(id).map(existing -> {
			if (existing.getEmployer().getId() != employer.getId()) {
				logger.error("Unauthorized update attempt: employer id={} tried to edit posting id={} owned by employer id={}",
							 employer.getId(), id, existing.getEmployer().getId());
				throw new SecurityException("You can edit only your own job postings.");
			}
			posting.setId(id);
			posting.setEmployer(existing.getEmployer());
			
			logger.info("Job posting id ={} updated successfully.", id);
			
			return repository.save(posting);
		});
	}
	
	public boolean deleteById(int id, Employer employer) {
		return repository.findById(id).map(existing -> {
			if (existing.getEmployer().getId() != employer.getId()) {
				throw new SecurityException("You can delete only your own jobs.");
			}
			repository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
