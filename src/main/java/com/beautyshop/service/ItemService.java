package com.beautyshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beautyshop.dto.ItemDto;
import com.beautyshop.entity.Item;
import com.beautyshop.form.ItemForm;

public interface ItemService {
	
	void saveItem(Item item);
	
	Item findOne(int itemId);
	
	Item findByName(String name);
	
	void saveItemFromForm(ItemForm itemForm) throws IOException;
	
	ItemForm findForm(int id);

	void delete(int id);

	Page<ItemDto> findPage(Pageable pageable);

	int findCount(int userId);

	List<Item> findAllByUserId(int userId);
}
