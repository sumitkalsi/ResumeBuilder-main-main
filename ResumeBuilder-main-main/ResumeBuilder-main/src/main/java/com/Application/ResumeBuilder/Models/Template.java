package com.Application.ResumeBuilder.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;

@Entity
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private ResumeInformation resume;
	
	
	private String templatePath = "template1";
	
	public ResumeInformation getResume() {
		return resume;
	}


	public void setResume(ResumeInformation resume) {
		this.resume = resume;
	}


	


	public Template() {
		super();
		
	}


	public Template( String templatePath) {
		super();
		
		
		this.templatePath = templatePath;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public String getTemplatePath() {
		return templatePath;
	}


	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}


	
	
}
