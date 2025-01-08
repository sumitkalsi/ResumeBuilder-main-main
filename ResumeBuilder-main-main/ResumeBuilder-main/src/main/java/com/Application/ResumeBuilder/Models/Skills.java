package com.Application.ResumeBuilder.Models;

import java.util.List;

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



private List<String> languages;

private List<String> libraries_frameworks;

private List<String> tools;











// Getters and setters





public Skills() {
	super();

}

public Skills(Long id, List<String> languages, List<String> libraries_frameworks, List<String> tools) {
	super();
	this.id = id;
	this.languages = languages;
	this.libraries_frameworks = libraries_frameworks;
	this.tools = tools;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public List<String> getLanguages() {
	return languages;
}

public void setLanguages(List<String> languages) {
	this.languages = languages;
}

public List<String> getLibraries_frameworks() {
	return libraries_frameworks;
}

public void setLibraries_frameworks(List<String> libraries_frameworks) {
	this.libraries_frameworks = libraries_frameworks;
}

public List<String> getTools() {
	return tools;
}

public void setTools(List<String> tools) {
	this.tools = tools;
}
}
