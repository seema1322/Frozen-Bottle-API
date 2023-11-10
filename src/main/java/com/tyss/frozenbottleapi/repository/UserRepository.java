package com.tyss.frozenbottleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.frozenbottleapi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByRole(String role);

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

}
