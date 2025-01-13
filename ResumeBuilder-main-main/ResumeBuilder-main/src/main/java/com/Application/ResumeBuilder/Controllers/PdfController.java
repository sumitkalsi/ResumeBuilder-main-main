package com.Application.ResumeBuilder.Controllers;

import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.pdf.ITextRenderer;



@Controller
public class PdfController {
	
	
	@PostMapping("/export")
	public ResponseEntity<String> htmlToPdfConverter(@RequestParam String exportResume) {
		 String styleRegex = "<style>(.*?)</style>";
	        Pattern pattern = Pattern.compile(styleRegex, Pattern.DOTALL);
	        Matcher matcher = pattern.matcher(exportResume);
	        String style = "";
	        if (matcher.find()) {
	        
	            style = matcher.group(1);

	            exportResume = matcher.replaceFirst("");
	        }
	        
		 String htmlContent = "<html> <head> <style>"+style+" </style><title>Sample PDF</title> </head><body>"+ exportResume  +"  </body></html>";
	        String pdfFilePath = "output.pdf";

	        try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(htmlContent);
	            renderer.layout();
	            renderer.createPDF(fos);
	            System.out.println("PDF created successfully at: " + pdfFilePath);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		System.out.println(exportResume +"=============================================================================");
		return ResponseEntity.ok("response");
	}

}
