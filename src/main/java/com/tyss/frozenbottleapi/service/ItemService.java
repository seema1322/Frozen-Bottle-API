package com.tyss.frozenbottleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.ItemDao;
import com.tyss.frozenbottleapi.entity.Item;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@Service
public class ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item){
		
		Item itemSaved = itemDao.saveItem(item);
		
		ResponseStructure<Item> responseStructure = new ResponseStructure<Item>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Saved");
		responseStructure.setData(itemSaved);
		
		ResponseEntity<ResponseStructure<Item>> responseEntity= new ResponseEntity<ResponseStructure<Item>>(responseStructure, HttpStatus.OK);
		return responseEntity;
	}
	
public ResponseEntity<ResponseStructure<List<Item>>> findItemByFoodOrderId(int orderid){
		
		List<Item> items= itemDao.findByFoodOrderId(orderid);
		
		ResponseStructure<List<Item>> responseStructure = new ResponseStructure<List<Item>>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Saved");
		responseStructure.setData(items);
		
		ResponseEntity<ResponseStructure<List<Item>>> responseEntity= new ResponseEntity<ResponseStructure<List<Item>>>(responseStructure, HttpStatus.OK);
		return responseEntity;
	}

}
