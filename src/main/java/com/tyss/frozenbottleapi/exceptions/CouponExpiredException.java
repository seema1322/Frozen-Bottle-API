package com.tyss.frozenbottleapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CouponExpiredException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private String message = "coupon expired";

	@Override
	public String getMessage() {
		return this.message;
	}
}
