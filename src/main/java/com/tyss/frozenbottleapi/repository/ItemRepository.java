package com.tyss.frozenbottleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tyss.frozenbottleapi.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query("select i.items from FoodOrder i  where i.id=?1")
	List<Item> findItem(int orderid);

}
