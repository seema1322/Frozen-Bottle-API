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
import com.tyss.frozenbottleapi.entity.Menu;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu menu) {
		return menuService.saveMenu(menu);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Menu>> getMenuById(@PathVariable int id) {
		return menuService.getMenuById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteMenuById(@PathVariable int id) {
		return menuService.deleteMenuById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Menu>>> getAllMenu() {
		return menuService.getAllMenu();
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Menu>> updateMenu(@PathVariable int id,
			@RequestBody List<FoodProduct> foodProducts) {
		return menuService.updateMenu(id, foodProducts);
	}
}
