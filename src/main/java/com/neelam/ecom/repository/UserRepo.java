package com.neelam.ecom.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.neelam.ecom.entity.User;

public interface UserRepo extends MongoRepository<User, String> {
	
	public List<User> findByEmail(String email);
}
