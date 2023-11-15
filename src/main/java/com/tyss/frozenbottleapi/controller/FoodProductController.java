package com.tyss.frozenbottleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.frozenbottleapi.entity.FoodProduct;

import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.FoodProductService;

@RestController
@RequestMapping("/foodproduct")
public class FoodProductController {
	@Autowired
	private FoodProductService foodProductService;

	@PostMapping
	public ResponseEntity<ResponseStructure<FoodProduct>> saveFoodProduct(@RequestBody FoodProduct foodProduct) {
		return foodProductService.saveFoodProduct(foodProduct);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<FoodProduct>> updateFoodProduct(@RequestBody FoodProduct foodProduct) {
		return foodProductService.updateFoodProduct(foodProduct);
	}

	@DeleteMapping("/{foodProductId}")
	public ResponseEntity<ResponseStructure<String>> deleteFoodProduct(@PathVariable int foodProductId) {
		return foodProductService.deleteFoodProduct(foodProductId);
	}

	@GetMapping("/{foodProductId}")
	public ResponseEntity<ResponseStructure<FoodProduct>> getFoodProductById(@PathVariable int foodProductId)

	{
		return foodProductService.getFoodProductById(foodProductId);
	}

	@GetMapping("/{availability}")
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getFoodProductByAvailability(
			@PathVariable String availability) {
		return foodProductService.getFoodProductByAvailability(availability);
	}

	@GetMapping("/menu/{menuId}")
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getFoodProductByMenuId(@PathVariable int menuId) {
		return foodProductService.getFoodProductByMenuId(menuId);
	}

}
