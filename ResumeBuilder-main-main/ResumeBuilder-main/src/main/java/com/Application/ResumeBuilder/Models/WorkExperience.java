package com.Application.ResumeBuilder.Models;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class WorkExperience {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private ResumeInformation resume ;
	
	private String companyName =" ";
	
	private String jobTitle =" ";
	
	private String Location =" ";
	
	 @Value("${default.startDate:2000-01-01}")
	private Date startDate ; 
	 @Value("${default.endDate:2000-01-01}")
	private Date endDate;
	
	private String description =" ";

	public ResumeInformation getResume() {
		return resume;
	}

	public void setResume(ResumeInformation resume) {
		this.resume = resume;
	}

	public WorkExperience() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkExperience(Long id, String companyName, String jobTitle, String location, Date startDate, Date endDate,
			String description) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.jobTitle = jobTitle;
		Location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
