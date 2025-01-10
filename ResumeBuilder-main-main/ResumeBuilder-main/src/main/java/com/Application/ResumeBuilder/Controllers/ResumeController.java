package com.Application.ResumeBuilder.Controllers;


import java.net.URI;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Application.ResumeBuilder.Models.ResumeInformation;
import com.Application.ResumeBuilder.Models.Skills;
import com.Application.ResumeBuilder.Models.Template;
import com.Application.ResumeBuilder.Models.User;
import com.Application.ResumeBuilder.Repositories.ResumeInformationRepository;
import com.Application.ResumeBuilder.Services.ResumeService;
import com.Application.ResumeBuilder.Services.UserService;

@Controller
@RequestMapping("/resume")
public class ResumeController {
	Long userId;
	
	private final ResumeService resumeService;
	
	private final UserService userService;
	
	public ResumeController(ResumeService resumeService ,UserService userService) {
		this.resumeService= resumeService;
		this.userService = userService;
	}
	
	@GetMapping("/templateSelection/{id}")
	public String templateSelection(@PathVariable Long id , Model model) {
	userId=id;
	
	User user =userService.getUserById(id).orElse(null);
	
	model.addAttribute("user", userService.getUserById(id).orElse(null) );
	
    ResumeInformation resume = new ResumeInformation();
    resume.setUser(user);
	
	
		resumeService.saveResumeInformation(resume);
		return "templateSelection";
		
	}
	

	
	@GetMapping("/resumeBuilder/{template}/{id}")
	public String resumeBuilder(@PathVariable String template,@PathVariable Long id ,Model model) {
		
		List<ResumeInformation>  list = resumeService.getResumeByUserId(id);
		ResumeInformation resume = list.getLast();
		Template temp = new Template();
    	temp.setResume(resume);
    	temp.setTemplatePath(template);
    	Skills skills = new Skills();
    	skills.setResume(resume);
    	
    	resume.setSkills(skills);
		resume.setTemplate(temp);
	       if(!list.contains(resume)){
		resumeService.updateResumeInformation(resume.getId(), resume);
	       }
		model.addAttribute("resume", resume );
	
        model.addAttribute("template", resume.getTemplate() );
		return "resumeBuilder";
	       
	       
	}
	@PostMapping("/resumeBuilder/{template}/{id}")
	public ResponseEntity<ResumeInformation> saveResume(  @PathVariable Long id ,@ModelAttribute ResumeInformation resume ){
	     // Define the redirection URL
        String redirectUrl = "/user/dashboard";
         resume.setUser(userService.getUserById(id).orElse(null));
        // Set the Location header
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));
		resumeService.updateResumeInformation(resume.getId(), resume);
		return new ResponseEntity<>(headers,HttpStatus.OK);
		
	}
	
	

	
	
	

}
