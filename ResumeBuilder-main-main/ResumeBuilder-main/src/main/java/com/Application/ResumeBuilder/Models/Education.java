package com.Application.ResumeBuilder.Models;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private ResumeInformation resume;
	
	private String instituteName =" ";
	
    private String location =" ";
    
    private String degreeType =" ";
    
    private String FieldOfStudy =" ";
    @Value("${default.startDate:2000-01-01}")
    private Date  startDate  ;
    @Value("${default.endDate:2000-01-01}")
    private Date endDate;
    
    private String Score =" ";
    
    

	public ResumeInformation getResume() {
		return resume;
	}

	public void setResume(ResumeInformation resume) {
		this.resume = resume;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public String getFieldOfStudy() {
		return FieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		FieldOfStudy = fieldOfStudy;
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

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	

	public Education() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
