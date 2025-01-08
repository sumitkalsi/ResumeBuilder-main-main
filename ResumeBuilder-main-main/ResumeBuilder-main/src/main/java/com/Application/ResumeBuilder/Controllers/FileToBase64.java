package com.Application.ResumeBuilder.Controllers;


import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



@Component
public class FileToBase64 {
	
	

    public String toBase64(MultipartFile file) {
        try {
            // Get the byte array from the MultipartFile
            byte[] fileBytes = file.getBytes();

            // Encode the byte array to Base64
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);

            return base64Encoded;
        } catch (Exception e) {
            throw new RuntimeException("Error while converting file to Base64", e);
        }
    }

}