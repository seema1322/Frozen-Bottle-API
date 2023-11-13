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

import com.tyss.frozenbottleapi.entity.FoodOrder;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.FoodOrderService;

@RestController
@RequestMapping("/foodOrders")
public class FoodOrderController {
	
	@Autowired
	private FoodOrderService foodOrderService;
	
	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> addFoodOrder(@PathVariable int id, @RequestBody FoodOrder foodOrder){
		return foodOrderService.addFoodOrder(id,foodOrder);
	}
	
	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(@PathVariable int id, @PathVariable String status){
		return foodOrderService.updateFoodOrder(id, status);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getAllFoodOrders(){
		return foodOrderService.getAllFoodOrders();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(@PathVariable int id){
		return foodOrderService.getFoodOrderById(id);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrderByStatus(@PathVariable String status){
		return foodOrderService.getFoodOrderByStatus(status);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrderByUserId(@PathVariable int id){
		return foodOrderService.getFoodOrderByUserId(id);
	}
}
