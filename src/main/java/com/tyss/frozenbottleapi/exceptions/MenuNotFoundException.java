package com.tyss.frozenbottleapi.exceptions;

public class MenuNotFoundException extends RuntimeException {
	private String message = "Menu Does Not Exist";

	public MenuNotFoundException() {

	}

	public MenuNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
