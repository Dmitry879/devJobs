package com.gcu.devjobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DevJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevJobsApplication.class, args);
	}
}
