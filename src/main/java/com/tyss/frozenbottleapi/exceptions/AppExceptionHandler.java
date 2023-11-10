package com.tyss.frozenbottleapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFoundException(UserNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseStructure<String>> dataIntegrityViolationException(
			DataIntegrityViolationException exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData("Please avoid duplicate/null entries");
		ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
				responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
}
