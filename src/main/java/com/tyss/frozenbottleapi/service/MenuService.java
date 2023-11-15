package com.tyss.frozenbottleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.MenuDao;
import com.tyss.frozenbottleapi.entity.FoodProduct;
import com.tyss.frozenbottleapi.entity.Menu;

import com.tyss.frozenbottleapi.exceptions.MenuNotFoundException;

import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@Service
public class MenuService {
	@Autowired
	private MenuDao menuDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu) {
		Menu savedMenu = menuDao.saveMenu(menu);
		ResponseStructure<Menu> responseStructure = new ResponseStructure<Menu>();
		responseStructure.setData(savedMenu);
		responseStructure.setMessage("menu saved");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
		Menu menu = menuDao.getMenuById(id);
		if (menu != null) {
			ResponseStructure<Menu> responseStructure = new ResponseStructure<Menu>();
			responseStructure.setData(menu);
			responseStructure.setMessage("Menu with id : " + id + " is found");
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new MenuNotFoundException("No Menu Exist With Id " + id);
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteMenuById(int id) {
		Menu menu = menuDao.getMenuById(id);
		if (menu != null) {
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setData(null);
			responseStructure.setMessage("menu has been removed");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		} else {
			throw new MenuNotFoundException("No Menu Exist With Id " + id);
		}
	}

	public ResponseEntity<ResponseStructure<List<Menu>>> getAllMenu() {
		List<Menu> menus = menuDao.getAllMenu();

		if (menus != null) {
			ResponseStructure<List<Menu>> responseStructure = new ResponseStructure<List<Menu>>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("found all menus");
			responseStructure.setData(menus);

			return new ResponseEntity<ResponseStructure<List<Menu>>>(responseStructure, HttpStatus.FOUND);
		}
		throw new MenuNotFoundException("No menus are present in database");
	}

	public ResponseEntity<ResponseStructure<Menu>> updateMenu(int id, List<FoodProduct> foodProducts) {
		Menu menu = menuDao.getMenuById(id);

		if (menu != null) {
			menu.setFoodProducts(foodProducts);
			Menu updatedMenu = menuDao.updateMenu(menu);
			ResponseStructure<Menu> responseStructure = new ResponseStructure<Menu>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Menu updated");
			responseStructure.setData(updatedMenu);
			return new ResponseEntity<ResponseStructure<Menu>>(responseStructure, HttpStatus.OK);
		} else {
			throw new MenuNotFoundException("No Menu Exist With Id " + id);
		}
	}

}
