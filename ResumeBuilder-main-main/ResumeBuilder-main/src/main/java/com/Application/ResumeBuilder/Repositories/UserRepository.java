package com.Application.ResumeBuilder.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.Application.ResumeBuilder.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	  @Query("SELECT u FROM User u WHERE u.email = :email" )
	    Optional<User> findByEmail(@Param("email") String email);
	  
}
