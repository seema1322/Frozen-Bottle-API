package com.tyss.frozenbottleapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tyss.frozenbottleapi.dao.UserDao;
import com.tyss.frozenbottleapi.entity.User;
<<<<<<< HEAD
import com.tyss.frozenbottleapi.entity.UserRole;
import com.tyss.frozenbottleapi.exceptions.UserNotFoundException;
=======
import com.tyss.frozenbottleapi.exceptions.IdNotFoundException;
>>>>>>> eebbdf12a458d0aaf3b98ab72704b8f9117b2648
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {

		List<User> users = userDao.findUserByRole(UserRole.ADMIN);
		if (users.isEmpty()) {
			throw new UserNotFoundException("Admin for this application is already present");

		} else {
			User userSaved = userDao.saveUser(user);
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Data Saved");
			responseStructure.setData(userSaved);

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.CREATED);

			return responseEntity;
		}

	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int userid, String name) {
		User user = userDao.findUserById(userid);

		if (user != null) {
			user.setName(name);
			User updatedUser = userDao.updateUser(user);
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("User details updated");
			responseStructure.setData(updatedUser);

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);

			return responseEntity;
		} else {
			throw new IdNotFoundException("No user found to update with Id " + userid);
		}
	}

	public ResponseEntity<ResponseStructure<User>> findUserById(int userid) {
		User user = userDao.findUserById(userid);

		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("User details with id " + userid);
			responseStructure.setData(user);

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.FOUND);

			return responseEntity;
		} else {
			throw new IdNotFoundException("No user found with Id " + userid);
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
		List<User> users = userDao.findAllUser();

		if (!users.isEmpty()) {
			ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Below user details are available: ");
			responseStructure.setData(users);

			ResponseEntity<ResponseStructure<List<User>>> responseEntity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.FOUND);

			return responseEntity;
		}
		throw new IdNotFoundException("No users are present in database");
	}

	public ResponseEntity<ResponseStructure<List<User>>> findUserByRole(String role) {
		UserRole role1 = null;
		if (role.equalsIgnoreCase("admin")) {
			role1 = UserRole.ADMIN;
		} else if (role.equalsIgnoreCase("customer")) {
			role1 = UserRole.CUSTOMER;
		} else {
			role1 = UserRole.STAFF;
		}
		List<User> users = userDao.findUserByRole(role1);

		if (!users.isEmpty()) {
			ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Below users are present with role " + role);
			responseStructure.setData(users);

			ResponseEntity<ResponseStructure<List<User>>> responseEntity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.FOUND);

			return responseEntity;
		}
<<<<<<< HEAD
		throw new UserNotFoundException("No users are present with role " + role);
=======
		throw new IdNotFoundException("No users are present with role "+role);
>>>>>>> eebbdf12a458d0aaf3b98ab72704b8f9117b2648
	}

	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password) {
		User user = userDao.findUserByEmailAndPassword(email, password);

		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("User details :");
			responseStructure.setData(user);

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.FOUND);

			return responseEntity;
		} else {
			throw new IdNotFoundException("Invalid Credentials");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(int userid) {
		User user = userDao.findUserById(userid);
		if (user != null) {
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("User has been removed");
			responseStructure.setData(null);

			ResponseEntity<ResponseStructure<String>> responseEntity = new ResponseEntity<ResponseStructure<String>>(
					responseStructure, HttpStatus.OK);

			return responseEntity;
		} else {
			throw new IdNotFoundException("No user found to delete with Id " + userid);
		}
	}

	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {
		User user = userDao.findUserByEmail(email);

		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("User details with email " + email);
			responseStructure.setData(user);

			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.FOUND);

			return responseEntity;
		} else {
			throw new IdNotFoundException("No user found with Id " + email);
		}
	}
}
