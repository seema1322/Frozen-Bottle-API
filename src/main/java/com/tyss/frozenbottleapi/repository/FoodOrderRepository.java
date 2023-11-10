package com.tyss.frozenbottleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.frozenbottleapi.entity.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer>{

}
