package com.havendan.challengemelicupon.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
	@JsonProperty("message")
	private String message; 
	
	@JsonProperty("error")
	private String error;
	
	@JsonProperty("status")
	private int status;
	
//	public ErrorResponse(HttpStatus status,String msg) {
//		this.status = status.value();
//		this.error = status.name();
//		this.message = msg;
//	}
	
	public ErrorResponse(String message, String error,int status ) {
		this.status = status;
		this.error = error;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
