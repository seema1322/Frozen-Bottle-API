package com.tyss.frozenbottleapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.frozenbottleapi.entity.Menu;
import com.tyss.frozenbottleapi.repository.MenuRepository;

@Repository
public class MenuDao {
	@Autowired
	private MenuRepository menuRepository;

	public Menu saveMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	public Menu getMenuById(int id) {
		Optional<Menu> optional = menuRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void deleteMenuById(int id) {
		menuRepository.deleteById(id);
	}

	public List<Menu> getAllMenu() {
		return menuRepository.findAll();
	}

	public Menu updateMenu(Menu menu) {
		return menuRepository.save(menu);
	}

}
