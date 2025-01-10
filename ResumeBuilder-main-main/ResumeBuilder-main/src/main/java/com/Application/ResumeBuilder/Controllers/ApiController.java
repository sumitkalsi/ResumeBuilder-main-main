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
import org.springframework.web.servlet.view.RedirectView;

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
		  String targetUrl = "http://192.168.1.101:8080/RChilliParser9/Rchilli/parseResumeBinary";
		  
		   try {
				json.setFiledata(f.toBase64(file));
				json.setFilename(file.getName());
				
					ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,json, String.class);
			        return ResponseEntity.ok(response.getBody());
			      
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad request");
			}
		 
	}
	
	
	
	
}
