package com.tyss.frozenbottleapi.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.frozenbottleapi.entity.FoodOrder;
import com.tyss.frozenbottleapi.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {
	
	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	public FoodOrder addFoodOrder(FoodOrder foodOrder) {
		return foodOrderRepository.save(foodOrder);
	}
	
	public FoodOrder updateFoodOrder(int id, String status) {
		Optional<FoodOrder> optional = foodOrderRepository.findById(id);
		if(optional.isPresent()) {
			//Getting the FoodOrder object from Optional
			FoodOrder foodOrder = optional.get();
			foodOrder.setStatus(status);
			foodOrder.setOrderDeliveredTime(LocalDateTime.now());
			return foodOrderRepository.save(foodOrder);
		}else {
			return null;
		}
	}
	
	public FoodOrder getFoodOrderById(int id) {
		Optional<FoodOrder> optional = foodOrderRepository.findById(id);
		if(optional.isPresent()) {
			//Getting the FoodOrder object from Optional
			FoodOrder foodOrder = optional.get();
			return foodOrder;
		}else {
			return null;
		}
	}
	
	public List<FoodOrder> getFoodOrderByStatus(String status) {
		return foodOrderRepository.findByStatus(status);
	}
	
	public List<FoodOrder> geAllFoodOrders(){
		return foodOrderRepository.findAll();
	}
	
	public List<FoodOrder> getFoodOrderByUserId(int userId){
		return foodOrderRepository.findFoodOrderByUserId(userId);
	}
}
