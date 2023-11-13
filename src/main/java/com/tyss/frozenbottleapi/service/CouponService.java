package com.tyss.frozenbottleapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.CouponDao;
import com.tyss.frozenbottleapi.entity.Coupon;
import com.tyss.frozenbottleapi.exceptions.IdNotFoundException;
import com.tyss.frozenbottleapi.exceptions.NoRecordsFoundException;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@Service
public class CouponService {

	@Autowired
	private CouponDao couponDao;
	
	public ResponseEntity<ResponseStructure<Coupon>> addCoupon(Coupon coupon){
		coupon.setExpiryDate(LocalDate.now().plusDays(20));
		coupon.setSetToCustomer(false);
		coupon.setUsed(false);
		couponDao.addCoupon(coupon);
		
		ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data saved");
		responseStructure.setData(coupon);
		
		return new ResponseEntity<ResponseStructure<Coupon>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Coupon>> updateIfUsed(int id, boolean isUsed){
		Coupon coupon = couponDao.getCouponById(id);
		if(coupon!=null) {
			couponDao.updateIfUsed(id, isUsed);
			
			ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Data updated");
			responseStructure.setData(coupon);
			
			return new ResponseEntity<ResponseStructure<Coupon>>(responseStructure, HttpStatus.OK);
		}
		throw new IdNotFoundException("The entered coupon id["+id+"] is not present to update");
	}
	
	public ResponseEntity<ResponseStructure<Coupon>> getCouponById(int id){
		Coupon coupon = couponDao.getCouponById(id);
		if(coupon!=null) {
			ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Coupon found");
			responseStructure.setData(coupon);
			
			return new ResponseEntity<ResponseStructure<Coupon>>(responseStructure, HttpStatus.FOUND);
		}
		throw new IdNotFoundException("The entered coupon id["+id+"] is not present");
	}
	
	public ResponseEntity<ResponseStructure<List<Coupon>>> getAllCoupons(){
		List<Coupon> coupons = couponDao.getAllCoupons();
		if(!coupons.isEmpty()) {
			ResponseStructure<List<Coupon>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Coupons found");
			responseStructure.setData(coupons);
			
			return new ResponseEntity<ResponseStructure<List<Coupon>>>(responseStructure, HttpStatus.FOUND);
		}
		throw new NoRecordsFoundException("No coupons are present");
	}
	
	public ResponseEntity<ResponseStructure<Coupon>> getCouponByCode(String couponCode){
		Coupon coupon = couponDao.getCouponByCode(couponCode);
		if(coupon!=null) {
			ResponseStructure<Coupon> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Coupon with the code["+couponCode+"] found");
			responseStructure.setData(coupon);
			
			return new ResponseEntity<ResponseStructure<Coupon>>(responseStructure, HttpStatus.FOUND);
		}
		throw new IdNotFoundException("Coupon with the code["+couponCode+"] is not present");
	}
}
