package com.restful.app.exception;

import java.util.Date;


public class CustomExceptionResponse {
	
	private Date timestamp;
	private String message;	
	private String details;
	
	public CustomExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	 
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
}
