package com.havendan.challengemelicupon.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
	
	 private HttpStatus httpStatus;
	 
	    private static final long serialVersionUID = -1873750263916403862L;
	 
	    public ServiceException(String message, HttpStatus httpStatus) {
	        super(message);
	        this.httpStatus = httpStatus;
	    }
	 
	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }
	 
	    public void setHttpStatus(HttpStatus httpStatusCode) {
	        this.httpStatus = httpStatusCode;
	    }

}
