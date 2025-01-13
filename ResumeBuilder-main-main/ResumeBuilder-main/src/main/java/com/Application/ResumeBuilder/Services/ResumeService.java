package com.Application.ResumeBuilder.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application.ResumeBuilder.Models.Education;
import com.Application.ResumeBuilder.Models.Link;
import com.Application.ResumeBuilder.Models.ResumeInformation;
import com.Application.ResumeBuilder.Models.Skills;
import com.Application.ResumeBuilder.Models.Template;
import com.Application.ResumeBuilder.Models.WorkExperience;
import com.Application.ResumeBuilder.Repositories.ResumeInformationRepository;

@Service
public class ResumeService {

      Long resumeId;
	
	private final ResumeInformationRepository resumeRepo;
	
	public ResumeService(ResumeInformationRepository resumeRepo) {
		this.resumeRepo=resumeRepo;
	}
	
    // Add Links to a Resume
    public ResumeInformation addLinksToResume(Long resumeId, List<Link> links) {
        ResumeInformation resume = resumeRepo.findById(resumeId)
            .orElseThrow(() -> new RuntimeException("Resume not found"));
        
   

        // Set the parent for each link
        for (Link link : links) {
            link.setResume(resume);
        }

        // Add links to the resume and save
        resume.getLinks().addAll(links);
        
        return resumeRepo.save(resume);
    }
	
	
	  // Create or update ResumeInformation
    public ResumeInformation saveResumeInformation(ResumeInformation resumeInformation) {
    
    
        return resumeRepo.save(resumeInformation);
    }

    
    // Get all resumes
    public List<ResumeInformation> getAllResumes() {
        return (List<ResumeInformation>) resumeRepo.findAll();
    }

    // Get a resume by ID
    public List<ResumeInformation> getResumeByUserId(Long id) {
    	List<ResumeInformation>  list = (List<ResumeInformation>) resumeRepo.findByUserId(id).orElse(null);
    	
        return list;
    }
    public ResumeInformation getResumeById(Long id ) {
    	ResumeInformation resume =  resumeRepo.findById(id).orElse(null);
    	return resume;
    }

    // Delete a resume by ID
    public void deleteResumeById(Long id) {
    	resumeRepo.deleteById(id);
    }
    public ResumeInformation updateResumeInformation(Long id, ResumeInformation updatedResume) {
        Optional<ResumeInformation> optionalResume = resumeRepo.findById(id);

        if (optionalResume.isPresent()) {
            ResumeInformation existingResume = optionalResume.get();

            // Update fields
            existingResume.setName(updatedResume.getName());
            existingResume.setEmail(updatedResume.getEmail());
            existingResume.setContactNumber(updatedResume.getContactNumber());
            existingResume.setAddress(updatedResume.getAddress());
            existingResume.setLinks(updatedResume.getLinks());
            existingResume.setEducation(updatedResume.getEducation());
            existingResume.setWorkExperience(updatedResume.getWorkExperience());
            existingResume.setSkills(updatedResume.getSkills());
            existingResume.setTemplate(updatedResume.getTemplate());
            existingResume.setUser(updatedResume.getUser());
            // Add more fields as needed

            // Save updated entity
            return resumeRepo.save(existingResume);
        } else {
           return null;
        }
    }
	
	
	
}
