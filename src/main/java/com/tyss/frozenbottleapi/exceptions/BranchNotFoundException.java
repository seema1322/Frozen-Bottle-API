package com.tyss.frozenbottleapi.exceptions;

public class BranchNotFoundException extends RuntimeException {
	private String message = "Branch Does Not Exist";

	public BranchNotFoundException() {

	}

	public BranchNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
