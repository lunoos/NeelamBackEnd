package com.neelam.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neelam.ecom.entity.Product;
import com.neelam.ecom.repository.ProductsRepo;

@Service
public class ProductService {
	@Autowired
	private ProductsRepo productsRepo;
	
	public List<Product> getAllProducts(){
		return productsRepo.findAll();
	}
	
	public Optional<Product> getProductById(String id) {
		return productsRepo.findById(id);
	}
	
	public String updateProductById(String id,Integer countReq){
		String result = null;
		Optional<Product> productOptional = productsRepo.findById(id);
        if (productOptional.isPresent()) {
        	Product product = productOptional.get();
        	if(product.getCountInStock()>=countReq) {
        		product.setCountInStock(product.getCountInStock()-countReq);
        		productsRepo.save(product);
        		result = "Product Successfully updated";
        	}else {
        		result = "Requried Product is more then available";
        	}
        } else {
        	result = "Product selected is not available";
        }
        return result;
	}
	
	public Product addProduct(Product product) {
		return productsRepo.save(product);
	}
	
	public void deleteProduct(String id) {
		productsRepo.deleteById(id);
	}
	
	public String updateProductById(String id,Product product) {
		try {
            productsRepo.deleteById(id);
            productsRepo.save(product);
            return "Product updated successfully";
        } catch (Exception e) {
        	System.out.println(e.getMessage());
			return "Product not found";
		}
	}
}
