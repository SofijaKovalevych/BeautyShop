package com.beautyshop.service;

import com.beautyshop.entity.Category;

public interface CategoryService {
	
	void saveCategory(Category category);
	
	Category findByOne(int categoryId);
	
	Category findByName(String name);
	
	void deleteCategory(int categoryId);

}
