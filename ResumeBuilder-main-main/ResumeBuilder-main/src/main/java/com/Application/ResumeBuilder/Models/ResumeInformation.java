package com.Application.ResumeBuilder.Models;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ResumeInformation {

	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	
	@ManyToOne
	@Nonnull
	private User user;
	
	
	@OneToOne(mappedBy ="resume" ,cascade=CascadeType.ALL )
	private Template template;
//	personal information============================================
	
	


	private String name =" ";
	
	private String email =" ";
	
	private String contactNumber =" ";
	
	private String address =" ";
	
	
	@OneToMany(mappedBy = "resume" ,cascade=CascadeType.ALL )
	
	private  List<Link> links;
	

//education==========================================================

	@OneToMany(mappedBy = "resume" ,cascade=CascadeType.ALL )
	private List<Education> education;
	
	
//	workexperience===============================================
	
	
	@OneToMany(mappedBy = "resume" ,cascade=CascadeType.ALL )
    private List<WorkExperience> workExperience ; 
	
	@OneToOne(mappedBy = "resume" ,cascade=CascadeType.ALL )
	private Skills skills;
	
   
	
//	Getters and Setters===============================================
	
	
	
	public List<Education> getEducation() {	
		return education;
	}


	public void setEducation(List<Education> education) {
		this.education = education;
	}


	public List<WorkExperience> getWorkExperience() {
		return workExperience;
	}


	public void setWorkExperience(List<WorkExperience> workExperience) {
		this.workExperience = workExperience;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Template getTemplate() {
		return template;
	}


	public void setTemplate(Template template) {
		this.template = template;
	}


	public Skills getSkills() {
		return skills;
	}


	public void setSkills(Skills skills) {
		this.skills = skills;
	}
	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	


	public List<Link> getLinks() {
		return links;
	}


	public void setLinks(List<Link> links) {
		this.links = links;
	}
    public int getLinksSize() {
        return links.size();
    }
    public int getWorkExperienceSize() {
        return workExperience.size();
    }
    public int getEducationSize() {
        return education.size();
    }
	
	@Override
	public String toString() {
		return "ResumeInformation [id=" + id + ", user=" + user + ", template=" + template + ", name=" + name
				+ ", email=" + email + ", contactNumber=" + contactNumber + ", address=" + address + ", links=" + links
				+ ", education=" + education + ", workExperience=" + workExperience + ", skills=" + skills + "]";
	}

	
}
