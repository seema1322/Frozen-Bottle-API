package com.tyss.frozenbottleapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.frozenbottleapi.entity.User;
import com.tyss.frozenbottleapi.entity.UserRole;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByRole(UserRole role);

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

}
