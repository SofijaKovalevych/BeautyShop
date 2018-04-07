package com.beautyshop.dto;

import java.math.BigDecimal;

import org.apache.tomcat.util.codec.binary.Base64;

import com.beautyshop.entity.Item;

public class ItemDto {
	
	private int id;
	
	private String name;
	
	private String price;

	private String img;

	public ItemDto() {
	}	

	public ItemDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice().toString();
		this.img = new String(Base64.encodeBase64(item.getImg()));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
