package com.Application.ResumeBuilder.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	System.out.println(userId);
	
	
	model.addAttribute("user", userService.getUserById(id).orElse(null) );
	
	ResumeInformation resume = new ResumeInformation();
	
	
		userService.addResume(id, resume);
		return "templateSelection";
		
	}
	@GetMapping("/resumeBuilder/{template}/{id}")
	public String resumeBuilder(@PathVariable String template,@PathVariable Long id ) {
		System.out.println(template);
		System.out.println(id);
		return "resumeBuilder";
	}
	
	

	
	
	

}
