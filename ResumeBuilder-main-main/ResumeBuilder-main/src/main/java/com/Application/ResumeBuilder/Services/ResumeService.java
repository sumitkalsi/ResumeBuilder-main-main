package com.Application.ResumeBuilder.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Application.ResumeBuilder.Models.ResumeInformation;
import com.Application.ResumeBuilder.Repositories.ResumeInformationRepository;

@Service
public class ResumeService {

	private final ResumeInformationRepository resumeRepo;
	
	public ResumeService(ResumeInformationRepository resumeRepo) {
		this.resumeRepo=resumeRepo;
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
    public Optional<ResumeInformation> getResumeById(Long id) {
        return resumeRepo.findById(id);
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
            throw new RuntimeException("Resume with ID " + id + " not found");
        }
    }
	
	
	
}
