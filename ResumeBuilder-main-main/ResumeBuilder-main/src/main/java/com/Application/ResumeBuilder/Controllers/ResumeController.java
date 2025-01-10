package com.Application.ResumeBuilder.Controllers;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.Application.ResumeBuilder.Models.ResumeInformation;

import com.Application.ResumeBuilder.Models.Template;
import com.Application.ResumeBuilder.Models.User;

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
	System.out.println(user.getId()+"===========================================");
    ResumeInformation resume = new ResumeInformation();
    resume.setUser(user);
	
	
		resumeService.saveResumeInformation(resume);
		model.addAttribute("resume",resumeService.getResumeByUserId(id).getLast());
		return "templateSelection";
		
	}
	
	@GetMapping("/resumeBuilder/{id}")
	public String edit(@PathVariable Long id , Model model) {
		ResumeInformation resume= resumeService.getResumeById(id);
		model.addAttribute("resume", resume);
		model.addAttribute("template",resume.getTemplate().getTemplatePath());
		 model.addAttribute("exportResume", new String());
	
		return"resumeBuilder";
				
	}
	
	
	@GetMapping("/resumeBuilder/{template}/{id}")
	public String resumeBuilder(@PathVariable String template,@PathVariable Long id,Model model) {
		
		ResumeInformation resume = resumeService.getResumeById(id);
		
		Template temp = new Template();
    	temp.setResume(resume);
    	
    	temp.setTemplatePath(template);
    
		resume.setTemplate(temp);
		
	
	       
		resumeService.updateResumeInformation(resume.getId(), resume);
		
		
	       
		model.addAttribute("resume", resume );
	
       
		return "redirect:/resume/resumeBuilder";
	       
	       
	}
	@GetMapping("/resumeBuilder")
	public String resumeBuilder(@ModelAttribute ResumeInformation resume , Model model) {
		model.addAttribute("resume",resume);
		System.out.println(resume.getTemplate().getTemplatePath());
		model.addAttribute("template","template1");
		 model.addAttribute("exportResume", new String());
		return "resumeBuilder";
	}

	
	@GetMapping("/resumeBuilder/saveResume/user/dashboard")
    public String dashboard(@ModelAttribute ResumeInformation resume, Model model )	{
		User user = resume.getUser();
		model.addAttribute("user",user);
		model.addAttribute("resumes",resumeService.getResumeByUserId(user.getId()));
		return "Dashboard";

	}
	
	@PostMapping("/resumeBuilder/saveResume/{id}")
	public String saveResume( @PathVariable Long id ,@ModelAttribute ResumeInformation resume ,Model model){
		
		
		
		
		model.addAttribute("resume",resume);
		resumeService.updateResumeInformation(resume.getId(), resume);
		return "redirect:user/dashboard";
		
	}
	
	
	

}
