package com.Application.ResumeBuilder.Controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ApiController {

	private final JsonFormat json;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    FileToBase64 f;

	public ApiController(JsonFormat json){
		this.json=json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/resume/api")
	public  ResponseEntity<String> api(@RequestBody MultipartFile file) {
		  String targetUrl = "http://192.168.1.101:8080/RChilliParser9/Rchilli/parseResumeBinary";
		   try {
			json.setFiledata(f.toBase64(file));
			json.setFilename(file.getName());
			
			  
		        ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,json, String.class);
		        return ResponseEntity.ok(response.getBody());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (ResponseEntity<String>) ResponseEntity.badRequest();
		}
		   
		
	}
	
	
	
	
	
}
