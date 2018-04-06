package com.beautyshop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item", indexes = @Index(columnList = "name"))
public class Item extends Base{
	
	@Column(name="price", columnDefinition="DECIMAL(5,2)")
	private BigDecimal price;
	
	@Column(name="description", length=200)
	private String description;
	
	@Column(name="image_path")
	private String imagePath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Country country;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
