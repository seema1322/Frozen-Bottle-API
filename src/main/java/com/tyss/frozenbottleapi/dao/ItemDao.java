package com.tyss.frozenbottleapi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.frozenbottleapi.entity.Item;
import com.tyss.frozenbottleapi.repository.ItemRepository;

@Repository
public class ItemDao {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	public List<Item> findByFoodOrderId(int orderid){
		return itemRepository.findItem(orderid);
	}
}
