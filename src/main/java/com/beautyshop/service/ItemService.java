package com.beautyshop.service;

import com.beautyshop.entity.Item;

public interface ItemService {
	
	void saveItem(Item item);
	
	Item findOne(int itemId);
	
	Item findByName(String name);

}
