package com.Application.ResumeBuilder.Controllers;





import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.Application.ResumeBuilder.Models.Education;
import com.Application.ResumeBuilder.Models.Link;
import com.Application.ResumeBuilder.Models.ResumeInformation;
import com.Application.ResumeBuilder.Models.Skills;
import com.Application.ResumeBuilder.Models.Template;
import com.Application.ResumeBuilder.Models.User;
import com.Application.ResumeBuilder.Models.WorkExperience;
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
	
	
	User user =userService.getUserById(id).orElse(null);
	
	model.addAttribute("user", user );

    ResumeInformation resume = new ResumeInformation();
 
    
    userService.addResume(id, resume);
	
	
		model.addAttribute("resume",resumeService.getResumeByUserId(id).getLast());
		return "templateSelection";
		
	}
	
//	WORKING FOR RESUMEBUILDER FOR EXISTING RESUME========================================================
	
	@GetMapping("/resumeBuilder/{id}")
	public String edit(@PathVariable Long id , Model model) {
		ResumeInformation resume= resumeService.getResumeById(id);
		model.addAttribute("resume", resume);
		model.addAttribute("template",resume.getTemplate().getTemplatePath());
		
		  model.addAttribute("links",resume.getLinks());
		   model.addAttribute("work",resume.getWorkExperience());
		   model.addAttribute("education",resume.getEducation());
		return"resumeBuilder";
				
	}

//========================================================================================================	
	
	
	
	
//	WORKING for resumebuilder from templateselection======================================================================
	
	@GetMapping("/resumeBuilder/{template}/{id}")
	public String resumeBuilder(@PathVariable String template,@PathVariable Long id,Model model) {
		
		ResumeInformation resume = resumeService.getResumeById(id);
		
	
    
		resume.getTemplate().setTemplatePath(template);
		
	    if (resume.getLinks().isEmpty()) {
	        Link link = new Link();
	        link.setResume(resume);
	        resume.getLinks().add(link);
	    }
	    if (resume.getWorkExperience().isEmpty()) {
	        WorkExperience work = new WorkExperience();
	        work.setResume(resume);
	        resume.getWorkExperience().add(work);
	    }
	    if (resume.getEducation().isEmpty()) {
	        Education ed = new Education();
	        ed.setResume(resume);
	        resume.getEducation().add(ed);
	    }

	    
		resumeService.updateResumeInformation(resume.getId(), resume);
		
		ResumeInformation resumedb= resumeService.getResumeById(id);
		model.addAttribute("resume", resumedb);
		model.addAttribute("template",resumedb.getTemplate().getTemplatePath());
	
//	   model.addAttribute("links",resumedb.getLinks());
//	   model.addAttribute("work",resumedb.getWorkExperience());
//	   model.addAttribute("education",resumedb.getEducation());
	  
		
		return "resumeBuilder";
	       
	       
	}
	
//===================================================================	
	


	
	@GetMapping({"/resumeBuilder/saveResume/user/dashboard","/resumeBuilder/{template}/saveResume/user/dashboard" })
    public String dashboard( Model model )	{
		
		User user =userService.getUserById(userId).orElse(null);
		model.addAttribute("user",user);
		model.addAttribute("resumes",resumeService.getResumeByUserId(user.getId()));
		
		
		return "Dashboard";

	}
	
	@PostMapping({"/resumeBuilder/saveResume/{id}","/resumeBuilder/{template}/saveResume/{id}" })
	public String saveResume( @PathVariable Long id ,@ModelAttribute ResumeInformation resume ){
		System.out.println(resume+"=========================================================================");
		
	      ResumeInformation resumedb = resumeService.getResumeById(id);
//	      resumedb.setAddress(resume.getAddress());
//	      resumedb.setContactNumber(resume.getContactNumber());
//	      resumedb.setEmail(resume.getEmail());
//	      resumedb.setName(resume.getName());
//	   resumedb.getSkills().setLanguages(resume.getSkills().getLanguages());
//	   resumedb.getSkills().setLibraries_frameworks(resume.getSkills().getLibraries_frameworks());
//   resumedb.getSkills().setTools(resume.getSkills().getTools());
  
//   work.stream().forEach(workExperience -> workExperience.setResume(resume));
		
		
	 	 
//	      resumeService.updateResumeInformation(id, resumedb);
//	      
//	   
//	      
   userId = resumedb.getUser().getId();
//	      System.out.println(resumedb.getUser().getId()+"======================================================================");
//
//		
	
		
		return "redirect:user/dashboard";
		
	}
	
	
	

}
