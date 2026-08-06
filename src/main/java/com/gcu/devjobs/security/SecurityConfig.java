package com.gcu.devjobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/", "/login", "/registerApplicant", "/registerEmployer").permitAll()
					.requestMatchers("/*.avif", "/*.jpg", "/*.png", "/*.css", "/*.js").permitAll()
					.requestMatchers("/employerJobs", "/employerJobs/create", "/employerJobs/edit/**", "/employerJobs/delete/**")
					.hasRole("EMPLOYER")
					.anyRequest().authenticated()
					)
					.formLogin(form -> form
							.loginPage("/login")
							.permitAll()
					)
					.logout(logout -> logout
							.logoutUrl("/logout")
							.logoutSuccessUrl("/")
							.permitAll()
					);
		
		return http.build();
	}

}
