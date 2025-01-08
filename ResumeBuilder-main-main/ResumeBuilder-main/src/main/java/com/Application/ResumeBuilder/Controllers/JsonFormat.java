package com.Application.ResumeBuilder.Controllers;

import org.springframework.stereotype.Component;

@Component
public class JsonFormat {
	
	private String filedata;
	private String filename;
	private final String userkey="rchilli_datateam";
	private final String version="8.0.0";
	private final String subuserid="sumit";
	public String getFiledata() {
		return filedata;
	}
	public void setFiledata(String filedata) {
		this.filedata = filedata;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUserkey() {
		return userkey;
	}
	
	public String getVersion() {
		return version;
	}

	public String getSubuserid() {
		return subuserid;
	}
	
	public JsonFormat(String filedata, String filename) {
		super();
		this.filedata = filedata;
		this.filename = filename;
	
	}
	public JsonFormat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
