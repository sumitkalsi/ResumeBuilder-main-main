package com.Application.ResumeBuilder.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Application.ResumeBuilder.Models.ResumeInformation;



@Repository
public interface ResumeInformationRepository extends CrudRepository<ResumeInformation	, Long>{
	  @Query("SELECT u FROM ResumeInformation u WHERE u.user.id = :userId" )
    Optional<List<ResumeInformation>> findByUserId(@Param("userId") Long id);
	
	
}
