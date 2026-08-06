package com.gcu.devjobs.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Employer;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
	
	Iterable<Employer> findAll();
	
	boolean existsByEmail(String Email);
	
	Optional<Employer> findByAccount(Account account);
}
