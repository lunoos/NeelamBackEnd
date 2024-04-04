package com.neelam.ecom.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "porducts")
public class Product {
	@Id
	private String _id;
	private String name;
	private BigDecimal price;
	private Long countInStock;
	private Short rating;
	private Integer numReviews;
	private String image;
	private String brand;
	private String category;
	private String description;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getCountInStock() {
		return countInStock;
	}
	public void setCountInStock(Long countInStock) {
		this.countInStock = countInStock;
	}
	public Short getRating() {
		return rating;
	}
	public void setRating(Short rating) {
		this.rating = rating;
	}
	public Integer getNumReviews() {
		return numReviews;
	}
	public void setNumReviews(Integer numReviews) {
		this.numReviews = numReviews;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Product [_id=" + _id + ", name=" + name + ", price=" + price + ", countInStock=" + countInStock
				+ ", rating=" + rating + ", numReviews=" + numReviews + ", image=" + image + ", brand=" + brand
				+ ", category=" + category + ", description=" + description + "]";
	}
	
	
	
}
