package com.gcu.devjobs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.devjobs.entities.Account;
import com.gcu.devjobs.entities.Applicant;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Optional<Account> findByUsername(String username);
	
	boolean existsByUsername(String username);

}
