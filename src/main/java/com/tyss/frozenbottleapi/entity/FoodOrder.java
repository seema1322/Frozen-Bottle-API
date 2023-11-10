package com.tyss.frozenbottleapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String status;
	private double price;
	
	@CreationTimestamp
	private LocalDateTime orderCreatedTime;
	
	@UpdateTimestamp
	private LocalDateTime orderDeliveredTime;
	
	private String customerName;
	private String email;
	
	@OneToMany
	private List<Item> items;
	
	@ManyToOne
	@JoinColumn
	private User user;

}
