package com.Application.ResumeBuilder.Controllers;



import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.Application.ResumeBuilder.Models.Education;
import com.Application.ResumeBuilder.Models.Link;
import com.Application.ResumeBuilder.Models.ResumeInformation;
import com.Application.ResumeBuilder.Models.Skills;
import com.Application.ResumeBuilder.Models.Template;
import com.Application.ResumeBuilder.Models.WorkExperience;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApiController {
	@Autowired
	private JsonFormat json;
	@Autowired
	private FileToBase64 f;
	@Autowired 
	private RestTemplate restTemplate;
	
  public static ResumeInformation populateResumeInformationFromMap(Map<String, String> dataMap) {
//	   Create the ResumeInformation object
    ResumeInformation resumeInformation = new ResumeInformation();

	        // Set Personal Information
	        resumeInformation.setName((String) dataMap.getOrDefault("FullName", " "));
	        resumeInformation.setEmail((String) dataMap.getOrDefault("EmailAddress", " "));
	        resumeInformation.setContactNumber((String) dataMap.getOrDefault("OriginalNumber", " "));
	        resumeInformation.setAddress((String) dataMap.getOrDefault("FormattedAddress", " "));

	        // Set Links
	       List<Link>links = new ArrayList<Link>();
	                Link link = new Link();
	                link.setUrl((String) dataMap.getOrDefault("Website", " "));
	                link.setLink((String) dataMap.getOrDefault("Link", " "));
	            links.add(link);
	            resumeInformation.setLinks(links);
          
	            List<WorkExperience> work = new ArrayList<>();
	            WorkExperience workex = new WorkExperience();
	            workex.setCompanyName((String) dataMap.getOrDefault("CurrentEmployer", " "));
	            workex.setCompanyName((String) dataMap.getOrDefault("CurrentEmployer", " "));
	            work.add(workex);
	            
	            List<Education> ed = new ArrayList<>();
	            Education education = new Education();
	            education.setDegreeType((String) dataMap.getOrDefault("DegreeName", " "));
	            ed.add(education);
	            
	            Template temp = new Template();
	            resumeInformation.setTemplate(temp);
	            
	            Skills skills = new Skills();
	            skills.setLibraries_frameworks((String) dataMap.getOrDefault("SkillKeywords", " "));
	            resumeInformation.setLinks(links);
	            resumeInformation.setEducation(ed);
	            resumeInformation.setWorkExperience(work);
                resumeInformation.setSkills(skills);
        return resumeInformation;
    }

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
            fieldMap.put("arrayname", node.asText());
        }
    }
	
	@PostMapping(value="/resume/api/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public String api(@PathVariable Long id , @RequestParam("file") MultipartFile file ,Model model) {
		  String targetUrl = "http://192.168.1.101:8080/RChilliParser9/Rchilli/parseResumeBinary";
		  
		
				json.setFiledata(f.toBase64(file));
				
				json.setFilename(file.getName());
				
					ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,json, String.class);
					
					
					
					ObjectMapper map = new ObjectMapper();
					   ResumeInformation resume = new ResumeInformation();
					
					try {
						JsonNode 	node = map.readTree(response.getBody());
						HashMap<String, String> map1 = new HashMap<>();
						traverseJson(node,map1);
					
						
						map1.entrySet().removeIf(entry -> entry.getValue().isEmpty());
						
						System.out.println(map1.get("DetailResume") +"***********************************************************************************************");
						   System.out.println("===============================================================================================");
						 resume =	 populateResumeInformationFromMap(map1);
						  resume.setId(id);
						   System.out.println(resume);
					
					} catch (JsonProcessingException e) {
					
					
					}
//					   HttpHeaders headers = new HttpHeaders();
					model.addAttribute("resume", resume);
					model.addAttribute("template",resume.getTemplate().getTemplatePath());
					  		        return "resumeBuilder";
//					   headers.setLocation(URI.create("/resume/resumeBuilder/"+id));
					
//			        return new ResponseEntity<String>(response.getBody(), headers ,HttpStatus.FOUND);
			      
			
		 
	}
	
	

	
	
}
