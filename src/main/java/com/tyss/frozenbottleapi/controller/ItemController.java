package com.tyss.frozenbottleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.frozenbottleapi.entity.Item;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item){
		return itemService.saveItem(item);
	}
	
	@GetMapping("/{orderid}")
	public ResponseEntity<ResponseStructure<List<Item>>> findItemByFoodOrderById(@PathVariable int orderid){
		return itemService.findItemByFoodOrderId(orderid);
	}

}
