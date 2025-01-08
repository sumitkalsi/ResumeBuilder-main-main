package com.Application.ResumeBuilder.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id ;
	
	
	private String userName;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<ResumeInformation> resumes;

	
	
	
	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ResumeInformation> getResumes() {
		return resumes;
	}

	public void setResumes(List<ResumeInformation> resumes) {
		this.resumes = resumes;
	}

	



	
	public User(Long id, String userName, String email, String password,
			List<ResumeInformation> resumes) {
		super();
		this.id = id;
		
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.resumes = resumes;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



}
