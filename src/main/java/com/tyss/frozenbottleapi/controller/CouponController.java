package com.tyss.frozenbottleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.frozenbottleapi.entity.Coupon;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Coupon>> addCoupon(@RequestBody Coupon coupon){
		return couponService.addCoupon(coupon);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Coupon>>> getAllCoupons(){
		return couponService.getAllCoupons();
	}
	
	@PutMapping("/{id}/{isUsed}")
	public ResponseEntity<ResponseStructure<Coupon>> updateIfUsed(@PathVariable int id, @PathVariable boolean isUsed){
		return couponService.updateIfUsed(id, isUsed);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Coupon>> getCouponById(@PathVariable int id){
		return couponService.getCouponById(id);
	}
	
	@GetMapping("/code/{couponCode}")
	public ResponseEntity<ResponseStructure<Coupon>> getCouponByCode(@PathVariable String couponCode){
		return couponService.getCouponByCode(couponCode);
	}
}
