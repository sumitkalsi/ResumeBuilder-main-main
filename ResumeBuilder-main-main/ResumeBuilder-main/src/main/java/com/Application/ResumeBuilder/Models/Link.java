package com.Application.ResumeBuilder.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Link {

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long id ;
	
	private String link;
	private String url;
	@ManyToOne
	private ResumeInformation resume;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Link(String link, String url) {
		super();
		this.link = link;
		this.url = url;
	}
	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResumeInformation getResume() {
		return resume;
	}
	public void setResume(ResumeInformation resume) {
		this.resume = resume;
	}
	
	
	
	
	
	
	
}
