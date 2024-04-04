package com.neelam.ecom.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neelam.ecom.dto.UserSignIn;
import com.neelam.ecom.entity.User;
import com.neelam.ecom.service.UsersService;


@RestController
public class UserController {
	
	@Autowired
	private UsersService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> list = userService.getUsers();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/userById")
	public ResponseEntity<List<User>> getUserById(@RequestParam String email) {
		List<User> list = userService.getUserById(email);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.registerUser(user),HttpStatus.OK);
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<User> signIn(@RequestBody UserSignIn userSignIn) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		User user = userService.authenticateService(userSignIn);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

}