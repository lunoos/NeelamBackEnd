package com.neelam.ecom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.neelam.ecom.entity.Product;

@Repository
public interface ProductsRepo extends MongoRepository<Product, String> {
		 
}
