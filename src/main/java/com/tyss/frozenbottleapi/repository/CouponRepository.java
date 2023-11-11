package com.tyss.frozenbottleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.frozenbottleapi.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	
	Coupon findByCouponCode(String couponCode);
	
}
