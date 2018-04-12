package com.beautyshop.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.beautyshop.dto.CategoryFilter;
import com.beautyshop.entity.Category;
import com.beautyshop.repository.CategoryRepository;
import com.beautyshop.service.CategoryService;
import com.jayway.jsonpath.Criteria;
import javax.persistence.criteria.Predicate;

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

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAllCtegoriesByName(Pageable pageable, CategoryFilter filter) {
		
		return categoryRepository.findAll(getSpecification(filter), pageable);
	}
	
	private Specification<Category> getSpecification(CategoryFilter filter){
		return new Specification<Category>() {
			
			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(filter.getSearch().isEmpty()) return null;
				return cb.like(root.get("name"),  "%" + filter.getSearch() + "%");
			}
			
		};
	}

}
