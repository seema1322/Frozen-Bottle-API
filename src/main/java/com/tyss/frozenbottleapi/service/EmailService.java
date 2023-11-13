package com.tyss.frozenbottleapi.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.CouponDao;
import com.tyss.frozenbottleapi.dao.UserDao;
import com.tyss.frozenbottleapi.entity.Coupon;
import com.tyss.frozenbottleapi.entity.User;
import com.tyss.frozenbottleapi.exceptions.CouponNotValidException;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	@Autowired
	private CouponDao couponDao;
	@Autowired
	private UserDao userDao;

	public void sendMail(String toEmail, String subject, String message) throws MessagingException {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		String userName = "hrmstest1322";
		String password = "hwpc pyso fwun pian";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		Message mailMessage = new MimeMessage(session);

		mailMessage.setFrom(new InternetAddress("hrmstest1322@gmail.com"));
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		Transport.send(mailMessage);
		
	}

	public ResponseEntity<ResponseStructure<String>> sendCouponToCustomerMail(int customerId, int couponId) throws MessagingException {
		User customer = userDao.findUserById(customerId);
		Coupon coupon = couponDao.getCouponById(couponId);

		if (coupon.isSetToCustomer()) {
			throw new CouponNotValidException("Coupon is already set to a customer");
		} else {
			String toEmail = customer.getEmail();
			String subject = "Yay! You received a coupon from FrozenBottle";
			String message = "For your recent purchase from our store you have received a coupon\n" + "Coupon code: "
					+ coupon.getCouponCode() + "\nCoupon can be used for purchases above or equal to Rs."
					+ coupon.getValidForPrice() + "/-" + "\nUse the coupon before " + coupon.getExpiryDate();
			sendMail(toEmail, subject, message);
			coupon.setUser(customer);
			coupon.setSetToCustomer(true);
			couponDao.updateIfMailSent(coupon);
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Mail has been sent");
			responseStructure.setData("Coupon code[" + coupon.getCouponCode()
					+ "] has been sent through the mail to customer " + customer.getName());

			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		}
	}
}
