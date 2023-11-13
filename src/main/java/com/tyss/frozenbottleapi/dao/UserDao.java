package com.tyss.frozenbottleapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tyss.frozenbottleapi.entity.User;
import com.tyss.frozenbottleapi.entity.UserRole;
import com.tyss.frozenbottleapi.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public User findUserById(int userid) {
		Optional<User> optional = userRepository.findById(userid);
		if (optional.isPresent()) {
			return optional.get();
		} 
		return null;
	}
	
	public List<User> findAllUser(){
		return userRepository.findAll();
	}

	public List<User> findUserByRole(UserRole role){ 
		return userRepository.findByRole(role);
	}
	
	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
