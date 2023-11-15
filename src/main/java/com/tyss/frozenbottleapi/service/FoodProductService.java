package com.tyss.frozenbottleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.FoodProductDao;

import com.tyss.frozenbottleapi.entity.FoodProduct;
import com.tyss.frozenbottleapi.exceptions.FoodProductNotFoundException;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
@Service
public class FoodProductService {
	@Autowired
	private FoodProductDao foodProductDao;

	public ResponseEntity<ResponseStructure<FoodProduct>> saveFoodProduct(FoodProduct foodProduct) {
		FoodProduct foodProductSaved = foodProductDao.saveFoodProduct(foodProduct);
		ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<FoodProduct>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("food product saved");
		responseStructure.setData(foodProductSaved);
		return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<FoodProduct>> updateFoodProduct(FoodProduct foodProduct) {

		int id = foodProduct.getId();
		FoodProduct foodProduct2 = foodProductDao.findFoodProductById(id);
		if (foodProduct2 != null) {
			FoodProduct updatedFoodProduct = foodProductDao.updatefoodProduct(foodProduct);
			ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<FoodProduct>();
			responseStructure.setData(updatedFoodProduct);
			responseStructure.setMessage("food product updated");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.OK);
		} else {
			throw new FoodProductNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteFoodProduct(int id) {
		FoodProduct foodProduct = foodProductDao.findFoodProductById(id);
		if (foodProduct != null) {
			foodProductDao.deleteFoodProduct(id);
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setMessage("Food product has been removed");
			responseStructure.setData(null);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {
			throw new FoodProductNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<FoodProduct>> getFoodProductById(int id) {
		FoodProduct foodProduct = foodProductDao.findFoodProductById(id);
		if (foodProduct != null) {

			ResponseStructure<FoodProduct> responseStructure = new ResponseStructure<FoodProduct>();
			responseStructure.setMessage("Food product with id : " + foodProduct.getId() + " is found");
			responseStructure.setData(foodProduct);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new FoodProductNotFoundException("No food product present with id " + id);
		}
	}

	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getFoodProductByAvailability(String availability) {
		List<FoodProduct> foodProducts = foodProductDao.findFoodProductByAvailability(availability);
		if (foodProducts != null) {
			ResponseStructure<List<FoodProduct>> responseStructure = new ResponseStructure<List<FoodProduct>>();
			responseStructure.setData(foodProducts);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("All food products with status : " + availability + " are found");
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new FoodProductNotFoundException("No food product is having status " + availability);
		}
	}

	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getFoodProductByMenuId(int id) {
		List<FoodProduct> foodProducts = foodProductDao.findFoodProductByMenuId(id);
		if (foodProducts != null) {
			ResponseStructure<List<FoodProduct>> responseStructure = new ResponseStructure<List<FoodProduct>>();
			responseStructure.setData(foodProducts);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("All food products with menu id : " + id + " are found");
			return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new FoodProductNotFoundException("No food product is having menu id " + id);
		}
	}

}
