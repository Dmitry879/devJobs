package com.gcu.devjobs.entities;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_postings")
public class JobPosting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "requirements")
	private String requirements;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@OneToMany(mappedBy = "jobPosting")
	private Set<JobApplication> applications = new LinkedHashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Set<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(Set<JobApplication> applications) {
		this.applications = applications;
	}
}
