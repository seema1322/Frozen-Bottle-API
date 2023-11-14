package com.tyss.frozenbottleapi.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.CouponDao;
import com.tyss.frozenbottleapi.dao.FoodOrderDao;
import com.tyss.frozenbottleapi.dao.UserDao;
import com.tyss.frozenbottleapi.entity.Coupon;
import com.tyss.frozenbottleapi.entity.FoodOrder;
import com.tyss.frozenbottleapi.entity.Item;
import com.tyss.frozenbottleapi.entity.User;
import com.tyss.frozenbottleapi.exceptions.CouponExpiredException;
import com.tyss.frozenbottleapi.exceptions.CouponNotValidException;
import com.tyss.frozenbottleapi.exceptions.IdNotFoundException;
import com.tyss.frozenbottleapi.exceptions.NoRecordsFoundException;
import com.tyss.frozenbottleapi.repository.CouponRepository;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@Service
public class FoodOrderService {
	
		@Autowired
		private FoodOrderDao foodOrderDao;
		@Autowired
		private CouponRepository couponRepository;
		@Autowired
		private CouponDao couponDao;
		@Autowired
		private UserDao userDao;
		
		public ResponseEntity<ResponseStructure<FoodOrder>> addFoodOrder(int id, FoodOrder foodOrder){
			User customer = userDao.findUserById(id);
			foodOrder.setStatus("Received");
			foodOrder.setCustomerName(foodOrder.getUser().getName());
			foodOrder.setEmail(foodOrder.getUser().getEmail());
			
			double price = 0.0;
			
			List<Item> items = foodOrder.getItems();
			for (Item item : items) {
				price += item.getPrice();
			}
			
			String couponCode = foodOrder.getCouponCode();
			
			if(couponCode != null) {
				Coupon coupon = couponRepository.findByCouponCode(couponCode);
				if(coupon != null) {
					int discount = coupon.getDiscount();
					if(price >= coupon.getValidForPrice()) {
						if(!LocalDate.now().equals(coupon.getExpiryDate())) {
							price -= price * (discount / 100);
							couponDao.updateIfUsed(coupon.getCouponId(), true);
						}
						else
							throw new CouponExpiredException("Coupon expired on "+coupon.getExpiryDate());
					}else {
						throw new CouponNotValidException("Coupon valid only for purchase above or equal to "+coupon.getValidForPrice());
					}
				}else {
					throw new IdNotFoundException("Entered coupon code["+couponCode+"] is not present");
				}
			}
			
			foodOrder.setPrice(price);
			foodOrder.setUser(customer);
			foodOrderDao.addFoodOrder(foodOrder);
			
			ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<FoodOrder>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Data Saved");
			responseStructure.setData(foodOrder);
			
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.CREATED);
		}
		
		public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(int id, String status){
			FoodOrder foodOrder = foodOrderDao.getFoodOrderById(id);
			if(foodOrder != null) {
				foodOrderDao.updateFoodOrder(id, status);
				ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Updated Successfully");
				responseStructure.setData(foodOrder);
				return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.OK);
			}else {
				throw new IdNotFoundException("Entered FoodOrderId["+id+"] is not present to update");
			}
		}
		
		public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(int id){
			FoodOrder foodOrder = foodOrderDao.getFoodOrderById(id);
			if(foodOrder != null) {
				ResponseStructure<FoodOrder> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Food order details with id "+id);
				responseStructure.setData(foodOrder);
				
				return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure, HttpStatus.FOUND);
			}else {
				throw new IdNotFoundException("No Food order present to display with the id "+id);
			}
		}
		
		public ResponseEntity<ResponseStructure<List<FoodOrder>>> getAllFoodOrders() {
			List<FoodOrder> foodOrders = foodOrderDao.geAllFoodOrders();
			if(!foodOrders.isEmpty()) {
				ResponseStructure<List<FoodOrder>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Food orders");
				responseStructure.setData(foodOrders);
				
				return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(responseStructure, HttpStatus.FOUND);
			}else {
				throw new NoRecordsFoundException("No records of food orders were found");
			}
		}
		
		public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrderByStatus(String status){
			List<FoodOrder> foodOrders = foodOrderDao.getFoodOrderByStatus(status);
			if(!foodOrders.isEmpty()) {
				ResponseStructure<List<FoodOrder>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Food orders with status "+status);
				responseStructure.setData(foodOrders);
				
				return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(responseStructure, HttpStatus.FOUND);
			}else {
				throw new IdNotFoundException("No food orders with status "+status+"are present");
			}
		}
		
		public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrderByUserId(int id){
			List<FoodOrder> foodOrders = foodOrderDao.getFoodOrderByUserId(id);
			if(!foodOrders.isEmpty()) {
				ResponseStructure<List<FoodOrder>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Food orders of the user with the Id "+id);
				responseStructure.setData(foodOrders);
				
				return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(responseStructure, HttpStatus.FOUND);
			}else {
				throw new IdNotFoundException("No user present with the id "+id);
			}
		}
		
}
