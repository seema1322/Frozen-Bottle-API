package com.tyss.frozenbottleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.frozenbottleapi.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
