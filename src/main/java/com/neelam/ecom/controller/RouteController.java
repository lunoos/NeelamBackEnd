package com.neelam.ecom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neelam.ecom.entity.Product;
import com.neelam.ecom.repository.ProductsRepo;
import com.neelam.ecom.service.ProductService;

@RestController
public class RouteController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/product")
	public ResponseEntity<Product> getProductById(@RequestParam String id){
		Optional<Product> optionalProduct = productService.getProductById(id);
		//productService.updateProductById(id);
		 if (optionalProduct.isPresent()) {
			 	Product user = optionalProduct.get();
		        return ResponseEntity.ok(user);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
	}
	
	@GetMapping("/updateProduct")
	public ResponseEntity<String> updateProductById(@RequestParam String id,@RequestParam Integer reqCount){
		return new ResponseEntity<>(productService.updateProductById(id, reqCount),HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.addProduct(product),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct")
	public ResponseEntity<String> deleteProduct(@RequestParam String id){
		productService.deleteProduct(id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
	
	@PostMapping("/updateProductById")
	public ResponseEntity<String> updateProductById(@RequestParam String id,@RequestBody Product product) {
		return new ResponseEntity<>(productService.updateProductById(id,product), HttpStatus.OK);
	}
}

