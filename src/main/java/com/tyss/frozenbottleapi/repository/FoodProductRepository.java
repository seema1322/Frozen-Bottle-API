package com.tyss.frozenbottleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.frozenbottleapi.entity.FoodProduct;

public interface FoodProductRepository extends JpaRepository<FoodProduct, Integer> {

}
