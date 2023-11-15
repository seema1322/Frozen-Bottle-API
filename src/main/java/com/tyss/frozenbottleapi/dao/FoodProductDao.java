package com.tyss.frozenbottleapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.tyss.frozenbottleapi.entity.FoodProduct;

import com.tyss.frozenbottleapi.repository.FoodProductRepository;

@Repository
public class FoodProductDao {
	@Autowired
	private FoodProductRepository foodProductRepository;

	public FoodProduct saveFoodProduct(FoodProduct foodProduct) {
		return foodProductRepository.save(foodProduct);
	}

	public FoodProduct updatefoodProduct(FoodProduct foodProduct) {
		return foodProductRepository.save(foodProduct);
	}

	public FoodProduct findFoodProductById(int id) {
		Optional<FoodProduct> optional = foodProductRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void deleteFoodProduct(int id) {
		foodProductRepository.deleteById(id);
	}

	public List<FoodProduct> findFoodProductByAvailability(String availability) {
		return foodProductRepository.findByAvailability(availability);
	}

	public List<FoodProduct> findFoodProductByMenuId(int id) {
		return foodProductRepository.findByMenuId(id);
	}
}
