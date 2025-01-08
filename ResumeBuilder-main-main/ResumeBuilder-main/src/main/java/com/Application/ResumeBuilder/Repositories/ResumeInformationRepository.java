package com.Application.ResumeBuilder.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Application.ResumeBuilder.Models.ResumeInformation;


@Repository
public interface ResumeInformationRepository extends CrudRepository<ResumeInformation	, Long>{

}
