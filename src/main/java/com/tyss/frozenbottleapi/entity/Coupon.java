package com.tyss.frozenbottleapi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int couponId;
	
	@Column(unique = true, nullable = false)
	private String couponCode;
	private int discount;
	private boolean isSetToCustomer;
	private LocalDate expiryDate;
	private double validForPrice;
	private boolean isUsed;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
}
