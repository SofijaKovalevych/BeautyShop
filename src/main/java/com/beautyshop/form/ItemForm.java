package com.beautyshop.form;

import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.web.multipart.MultipartFile;

import com.beautyshop.entity.Brand;
import com.beautyshop.entity.Category;
import com.beautyshop.entity.Country;
import com.beautyshop.entity.Item;

public class ItemForm {
	
	private Integer id;
	
	private String name;
	
	private String price;
	
	private String description;
	
	private MultipartFile file;
	
	private int brandId;

	private int categoryId;
	
	private int countryId;
	
	private Brand brand;
	
	private Category category;
	
	private Country country;
	
	private byte[] img;

	public ItemForm() {
	}

	public ItemForm(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice().toString();
		this.description = item.getDescription();
		this.img = item.getImg();
		this.brand = item.getBrand();
		this.category = item.getCategory();
		this.country = item.getCountry();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
}
