package com.beautyshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beautyshop.entity.Category;

public interface CategoryService {
	
	void saveCategory(Category category);
	
	Category findByOne(int categoryId);
	
	Category findByName(String name);
	
	void deleteCategory(int categoryId);

	Page<Category> findPage(Pageable pageable);

}
