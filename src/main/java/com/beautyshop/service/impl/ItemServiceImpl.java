package com.beautyshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beautyshop.entity.Item;
import com.beautyshop.repository.ItemRepository;
import com.beautyshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemRepository;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository =  itemRepository;
		
	}
	
	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
		
	}

	@Override
	public Item findOne(int itemId) {
		return itemRepository.findOne(itemId);
	}

	@Override
	public Item findByName(String name) {
		return itemRepository.findByName(name);
	}


}
