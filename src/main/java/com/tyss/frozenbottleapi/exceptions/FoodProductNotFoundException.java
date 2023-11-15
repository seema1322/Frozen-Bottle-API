package com.tyss.frozenbottleapi.exceptions;

public class FoodProductNotFoundException extends RuntimeException {
	private String message = "Food Product Not Exist";

	public FoodProductNotFoundException() {

	}

	public FoodProductNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
		
	}
	

}
