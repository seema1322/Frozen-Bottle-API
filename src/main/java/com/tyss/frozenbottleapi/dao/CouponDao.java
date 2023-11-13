package com.tyss.frozenbottleapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.frozenbottleapi.entity.Coupon;
import com.tyss.frozenbottleapi.repository.CouponRepository;

@Repository
public class CouponDao {
	
	@Autowired
	private CouponRepository couponRepository;
	
	public Coupon addCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}
	
	public Coupon updateIfMailSent(int id, boolean isSetToCustomer) {
		Optional<Coupon> optional = couponRepository.findById(id);
		if(optional.isPresent()) {
			Coupon coupon = optional.get();
			coupon.setSetToCustomer(isSetToCustomer);
			
			return couponRepository.save(coupon);
		}
		return null;
	}
	
	public Coupon getCouponByCode(String couponCode) {
		return couponRepository.findByCouponCode(couponCode);
	}
	
	public List<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
	
	public Coupon getCouponById(int id) {
		Optional<Coupon> optional = couponRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Coupon updateIfUsed(int id, boolean isUsed) {
		Optional<Coupon> optional = couponRepository.findById(id);
		if(optional.isPresent()) {
			Coupon coupon = optional.get();
			coupon.setUsed(isUsed);
			
			return couponRepository.save(coupon);
		}
		return null;
	}
}
