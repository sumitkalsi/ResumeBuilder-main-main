package com.Application.ResumeBuilder.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



@Component
public class FileToBase64 {
	
	

    public String toBase64(MultipartFile file) throws IOException {
    	   byte[] fileBytes = Files.readAllBytes(((File) file).toPath());
    	   return Base64.getEncoder().encodeToString(fileBytes);
    }
    }

