package com.tyss.frozenbottleapi.exceptions;

public class IdNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Id not found";

	
	public IdNotFoundException() {
		
	}

	public IdNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
