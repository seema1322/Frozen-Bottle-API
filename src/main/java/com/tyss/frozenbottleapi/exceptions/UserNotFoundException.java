package com.tyss.frozenbottleapi.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Request user details are not found";

	
	public UserNotFoundException() {
		
	}

	public UserNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
