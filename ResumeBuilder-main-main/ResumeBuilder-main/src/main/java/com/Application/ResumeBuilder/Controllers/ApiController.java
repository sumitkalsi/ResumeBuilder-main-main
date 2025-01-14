package com.Application.ResumeBuilder.Controllers;



import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.Application.ResumeBuilder.Models.ResumeInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ApiController {
	@Autowired
	private JsonFormat json;
	@Autowired
	private FileToBase64 f;
	@Autowired 
	private RestTemplate restTemplate;

	public static void traverseJson(JsonNode node, HashMap<String, String> fieldMap) {
        if (node.isObject()) {
            node.fieldNames().forEachRemaining(fieldName -> {
                JsonNode childNode = node.path(fieldName);
                if (childNode.isValueNode()) {
                    // Add to the HashMap if it's a value node
                    fieldMap.put(fieldName, childNode.asText());
                }
                // Recursive call for nested objects or arrays
                traverseJson(childNode, fieldMap);
            });
        } else if (node.isArray()) {
            for (JsonNode arrayItem : node) {
                traverseJson(arrayItem, fieldMap);  // Recursive call for array items
            }
        } else if (node.isValueNode()) {
            // Leaf node, this can only occur in an array element directly
            fieldMap.put("arrayValue", node.asText());
        }
    }
	
	@PostMapping(value="/resume/api/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ResponseEntity<String> api(@PathVariable Long id , @RequestParam("file") MultipartFile file) {
		  String targetUrl = "http://192.168.1.101:8080/RChilliParser9/Rchilli/parseResumeBinary";
		  
		
				json.setFiledata(f.toBase64(file));
				
				json.setFilename(file.getName());
				
					ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,json, String.class);
					
					
					
					ObjectMapper map = new ObjectMapper();
				
					
					try {
						JsonNode 	node = map.readTree(response.getBody());
						HashMap<String, String> map1 = new HashMap<>();
						traverseJson(node,map1);
						
						map1.entrySet().removeIf(entry -> entry.getValue().isEmpty());
						System.out.println(map1);
						   System.out.println("===============================================================================================");
    
					
					} catch (JsonProcessingException e) {
					
					
					}
					   HttpHeaders headers = new HttpHeaders();
					  		        
					   headers.setLocation(URI.create("/resume/resumeBuilder/"+id));
					
			        return new ResponseEntity<String>(response.getBody(), headers ,HttpStatus.FOUND);
			      
			
		 
	}
	
	

	
	
}
