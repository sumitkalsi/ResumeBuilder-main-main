package com.Application.ResumeBuilder.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ApiController {
	@Autowired
	private JsonFormat json;
	@Autowired
	private FileToBase64 f;
	@Autowired 
	private RestTemplate restTemplate;

	@PostMapping(value="/resume/api" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ResponseEntity<String> api(@RequestBody MultipartFile file) {
		  String targetUrl = "https://rest-mu.rchilli.com/RChilliParser/Rchilli/parseResumeBinary";
		  
		   try {
				json.setFiledata(f.toBase64(file));
				json.setFilename(file.getName());
				System.out.println(json.getFiledata());
				 
					ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,json, String.class);
			        return ResponseEntity.ok(response.getBody());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
			}
	}
	
	
	
	
}
