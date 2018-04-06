package com.beautyshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beautyshop.entity.Category;
import com.beautyshop.repository.CategoryRepository;
import com.beautyshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository){
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public Category findByOne(int categoryId) {
		return categoryRepository.findOne(categoryId);
	}

	@Override
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryRepository.delete(categoryId);
	}

	@Override
	public Page<Category> findPage(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

}
