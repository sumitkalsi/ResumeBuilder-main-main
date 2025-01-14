package com.Application.ResumeBuilder.Controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.Application.ResumeBuilder.Models.User;
import com.Application.ResumeBuilder.Services.ResumeService;
import com.Application.ResumeBuilder.Services.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	private ResumeService resumeService;
	Long userId;
	private UserService userService;

	public UserController(UserService userService ,ResumeService resumeService) {
		this.userService = userService;
		this.resumeService=resumeService;
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user",new User());
		return"login";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user ,Model model){
	  User existingUser = 	userService.getUserByEmail(user.getEmail()).orElse(null);
	  
	  if(	existingUser!=null&&existingUser.getPassword().equals(user.getPassword())) {
		  userId=existingUser.getId();
		  System.out.println(userId);
		  model.addAttribute("user", existingUser);
		  model.addAttribute("resumes",resumeService.getResumeByUserId(userId));
		  return"redirect:/user/dashboard";
		  
	  }else {
		  
		  return "redirect:/user/register";
	  }
		
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	

	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		System.out.println(user.getUserName());
		 userService.addUser(user);
		 userId = userService.getUserByEmail(user.getEmail()).orElse(null).getId();
		 System.out.println(userId);
		 return "Dashboard";
	}
	
	@GetMapping("/dashboard")
     public String dashboard(Model model )	{
		User user =userService.getUserById(userId).orElse(null);
		model.addAttribute("user",user);
		model.addAttribute("resumes",resumeService.getResumeByUserId(user.getId()));
		return "Dashboard";

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
