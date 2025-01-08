
package com.Application.ResumeBuilder.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Application.ResumeBuilder.Models.ResumeInformation;

import com.Application.ResumeBuilder.Models.User;
import com.Application.ResumeBuilder.Repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository  userRepo;
	
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
		
	}
	
	 // Create a new User====================================
	   
	public User addUser( User user) {
		
		
		return userRepo.save(user);
	}
	
	 

    // Retrieve all Users====================================================
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    // Retrieve a User by ID
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }
    
    
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

  public User addResume(Long id , ResumeInformation resume) {
	  User user = getUserById(id).orElse(null);
	  
	  user.getResumes().add(resume);
	  return userRepo.save(user);
  }

  public User updateUser(Long id , User user) {
	  User updatedUser = getUserById(id).orElse(null);
	  updatedUser.setUserName(user.getUserName());
	  updatedUser.setEmail(user.getEmail());
	  updatedUser.setPassword(user.getPassword());
      return   userRepo.save(updatedUser);
  }
  
  
  
    // Delete a User by ID=========================================================
    public void deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
	

}
