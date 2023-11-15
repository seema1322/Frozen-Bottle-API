package com.tyss.frozenbottleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyss.frozenbottleapi.entity.FoodProduct;

public interface FoodProductRepository extends JpaRepository<FoodProduct, Integer> {

	List<FoodProduct> findByAvailability(String availability);

	@Query("SELECT f FROM FoodProduct f WHERE f.menu_id=?1")
	List<FoodProduct> findByMenuId(int menuId);

}
