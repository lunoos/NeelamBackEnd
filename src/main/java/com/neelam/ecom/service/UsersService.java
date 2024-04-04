package com.neelam.ecom.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neelam.ecom.JwtTokenFilter;
import com.neelam.ecom.dto.UserSignIn;
import com.neelam.ecom.entity.User;
import com.neelam.ecom.repository.UserRepo;

@Service
public class UsersService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	public List<User> getUserById(String email) {
		System.out.println("Inside UsersService.getUserById() method");
		return userRepo.findByEmail(email);
	}
	
	public User authenticateService(UserSignIn userSignIn) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = authenticateUser(userSignIn.getUserId(), userSignIn.getPassword());	
		if (!Objects.isNull(user)) {
			Map<String,Object> claims = new HashMap<String,Object>();
	        claims.put(userSignIn.getUserId(), userSignIn.getPassword()); 
	        claims.put("roles", new String[]{"USER"}); 
	        user.setToken(jwtTokenFilter.generateToken(claims));
	        return user;
		}
		throw new RuntimeException("User authentication failed");
	}
	
	public User authenticateUser(String email, String password) {
		List<User> list = userRepo.findByEmail(email);
		if (list.size() > 0) {
			User user = list.get(0);
			if (user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public User registerUser(User user) {
		return userRepo.save(user);
	}
}
