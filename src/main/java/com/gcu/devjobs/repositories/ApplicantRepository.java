package com.gcu.devjobs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Applicant;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
	
	Iterable<Applicant> findAll();
	
	boolean existsByEmail(String email);
	
	Optional<Applicant> findByAccount(Account account);
}
