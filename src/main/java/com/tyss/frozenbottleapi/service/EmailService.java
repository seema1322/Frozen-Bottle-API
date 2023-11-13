package com.tyss.frozenbottleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.CouponDao;
import com.tyss.frozenbottleapi.dao.UserDao;
import com.tyss.frozenbottleapi.entity.Coupon;
import com.tyss.frozenbottleapi.entity.User;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private CouponDao couponDao;
	@Autowired
	private UserDao userDao;
	
	public void sendMail(String toEmail, String subject, String messge) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(messge);
		simpleMailMessage.setFrom("hrmstest1322@gmail.com");
		javaMailSender.send(simpleMailMessage);
	}
	
	public ResponseEntity<ResponseStructure<String>> sendCouponToCustomerMail(int customerId, int couponId){
		User customer = userDao.findUserById(customerId);
		Coupon coupon = couponDao.getCouponById(couponId);
		String toEmail = customer.getEmail();
		String subject = "Yay! You received a coupon from FrozenBottle";
		String message = "For your recent purchase from our store you have received a coupon\n"
				+ "Coupon code: "+coupon.getCouponCode()
				+"\nCoupon can be used for purchases above or equal to Rs."+coupon.getValidForPrice()+"/-"
				+"\nUse the coupon before "+coupon.getExpiryDate();
		sendMail(toEmail, subject, message);
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Mail has been sent");
		responseStructure.setData("Coupon code["+coupon.getCouponCode()+"] has been sent through the mail to customer "+customer.getName());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
	}
}
