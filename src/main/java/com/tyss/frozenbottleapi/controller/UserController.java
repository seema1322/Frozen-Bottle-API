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

import com.tyss.frozenbottleapi.entity.User;
import com.tyss.frozenbottleapi.responsestructure.ResponseStructure;
import com.tyss.frozenbottleapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PutMapping("/{userid}/{name}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int userid, @PathVariable String name){
		return userService.updateUser(userid, name);
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userid) {
		return userService.findUserById(userid);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<ResponseStructure<List<User>>> findUserByRole(@PathVariable String role){
		return userService.findUserByRole(role);
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<ResponseStructure<User>> userLogin(@PathVariable String email, @PathVariable String password){
		return userService.findUserByEmailAndPassword(email, password);
	}
	
	@DeleteMapping("/{userid}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userid){
		return userService.deleteUser(userid);
	}
}
