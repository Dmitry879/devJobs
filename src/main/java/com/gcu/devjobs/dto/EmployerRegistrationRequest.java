package com.gcu.devjobs.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployerRegistrationRequest {
	
	@NotBlank(message = "Username is required.")
	@Size(min = 5, max = 45, message = "Username must be between 5 and 45 characters.")
	private String username;
	
	@NotBlank(message = "Password is required.")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters long.")
	private String password;
	
	@NotBlank(message = "Please confirm your password.")
	private String confirmPassword;
	
	@NotBlank(message = "First name is required.")
	@Size(max = 45, message = "Name cannot exceed 45 characters.")
	private String name;
	
	@NotBlank(message = "Phone number is required.")
	@Pattern(regexp = "^\\+?[0-9()\\-\\s]{10,20}$",
	         message = "Please enter a valid phone number.")
	private String phone;
	
	@NotBlank(message = "Email address is required.")
	@Email(message = "Please, enter a valid email address.")
	@Size(max = 45, message = "Email address cannot exceed 45 characters.")
	private String email;
	
	@AssertTrue(message = "Passwords do not match.")
	public boolean isPasswordMatch() {
		return password != null && password.equals(confirmPassword);
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
