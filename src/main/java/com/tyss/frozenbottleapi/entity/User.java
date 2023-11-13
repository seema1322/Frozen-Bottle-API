package com.tyss.frozenbottleapi.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private UserRole role;

	@OneToOne(mappedBy = "user")
	private Menu menu;

	@OneToMany(mappedBy = "user")
	List<Branch> branches;

	@OneToMany(mappedBy = "user")
	List<FoodOrder> orders;
	
	@OneToMany(mappedBy = "user")
	List<Coupon> coupons;
}
