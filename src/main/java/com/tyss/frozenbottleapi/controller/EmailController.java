package com.tyss.frozenbottleapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/{customerId}/{couponId}")
	public ResponseEntity<ResponseStructure<String>> sendMail(@PathVariable int customerId, @PathVariable int couponId) throws MessagingException{
		return emailService.sendCouponToCustomerMail(customerId, couponId);
	}
}
