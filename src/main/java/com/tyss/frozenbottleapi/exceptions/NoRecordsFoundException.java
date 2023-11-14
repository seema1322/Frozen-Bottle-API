package com.tyss.frozenbottleapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NoRecordsFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = "no records found";
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
