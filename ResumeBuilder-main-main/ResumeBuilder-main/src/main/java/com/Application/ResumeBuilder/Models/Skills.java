package com.Application.ResumeBuilder.Models;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;

@Entity
public class Skills {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@OneToOne
private ResumeInformation resume;



private String languages = " " ;



private String libraries_frameworks = " " ;


private String tools  =" ";











// Getters and setters





public ResumeInformation getResume() {
	return resume;
}

public void setResume(ResumeInformation resume) {
	this.resume = resume;
}

public Skills() {
	super();
	

}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getLanguages() {
	return languages;
}

public void setLanguages(String languages) {
	this.languages = languages;
}

public String getLibraries_frameworks() {
	return libraries_frameworks;
}

public void setLibraries_frameworks(String libraries_frameworks) {
	this.libraries_frameworks = libraries_frameworks;
}

public String getTools() {
	return tools;
}

public void setTools(String tools) {
	this.tools = tools;
}

public Skills(Long id, ResumeInformation resume, String languages, String libraries_frameworks, String tools) {
	super();
	this.id = id;
	this.resume = resume;
	this.languages = languages;
	this.libraries_frameworks = libraries_frameworks;
	this.tools = tools;
}

@Override
public String toString() {
	return "Skills [id=" + id + ", resume=" + resume + ", languages=" + languages + ", libraries_frameworks="
			+ libraries_frameworks + ", tools=" + tools + "]";
}


}
