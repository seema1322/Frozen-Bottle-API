package com.tyss.frozenbottleapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

import jakarta.mail.MessagingException;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> notFoundException(IdNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseStructure<String>> dataIntegrityViolationException(DataIntegrityViolationException exception) {
		
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData("Please avoid duplicate/null entries");
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(NoRecordsFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noRecordsFoundException(NoRecordsFoundException exception){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not Found");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(CouponExpiredException.class)
	public ResponseEntity<ResponseStructure<String>> couponExpiredException(CouponExpiredException exception){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.GONE.value());
		responseStructure.setMessage("Coupon expired");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.GONE);
	}
	
	@ExceptionHandler(CouponNotValidException.class)
	public ResponseEntity<ResponseStructure<String>> couponNotValidException(CouponNotValidException exception){
		
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		responseStructure.setMessage("Coupon not valid");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> itemNotFoundException(ItemNotFoundException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Not found");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<ResponseStructure<String>> messagingException(MessagingException exception) {

		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("MessagingException exception");
		responseStructure.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
	}
}
