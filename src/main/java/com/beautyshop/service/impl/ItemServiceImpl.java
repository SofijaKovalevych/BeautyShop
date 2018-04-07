package com.beautyshop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beautyshop.dto.ItemDto;
import com.beautyshop.entity.Item;
import com.beautyshop.form.ItemForm;
import com.beautyshop.repository.ItemRepository;
import com.beautyshop.service.BrandService;
import com.beautyshop.service.CategoryService;
import com.beautyshop.service.CountryService;
import com.beautyshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	private ItemRepository itemRepository;
	
	private BrandService brandService;
	
	private CategoryService categoryService;
	
	private CountryService countryService;
	
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository, BrandService brandService, CategoryService categoryService,
			CountryService countryService) {
		this.itemRepository = itemRepository;
		this.brandService = brandService;
		this.categoryService = categoryService;
		this.countryService = countryService;
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

	@Override
	public void saveItemFromForm(ItemForm form) throws IOException {
		Item entity = new Item();
		entity.setId(form.getId());
		entity.setName(form.getName());
		entity.setImg(form.getFile().getBytes());
		entity.setDescription(form.getDescription());
		entity.setPrice(new BigDecimal(form.getPrice()));
		entity.setBrand(brandService.findByOne(form.getBrandId()));
		entity.setCountry(countryService.findByOne(form.getCountryId()));
		entity.setCategory(categoryService.findByOne(form.getCategoryId()));
		itemRepository.save(entity);
	}

	@Override
	public ItemForm findForm(int id) {
		Item entity = itemRepository.findOne(id);
		ItemForm form = new ItemForm(entity);
		return form;
	}

	@Override
	public void delete(int id) {
		itemRepository.delete(id);
	}

	@Override
	public Page<ItemDto> findPage(Pageable pageable) {
		return itemRepository.findAll(pageable).map(this::map);
	}
	
	@Override
	public int findCount(int id) {
		Integer count = itemRepository.findCount(id);
		if(count==null)return 0;
		return count;
	}

	@Override
	public List<Item> findAllByUserId(int userId) {
		return itemRepository.findAllByUserId(userId);
	}
	
	private ItemDto map(Item item) {
        return new ItemDto(item);
    }



}
