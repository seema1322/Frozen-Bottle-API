package com.tyss.frozenbottleapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CouponNotValidException extends RuntimeException{/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	private String message = "coupon not valid";

	@Override
		public String getMessage() {
			return this.message;
		}
}
