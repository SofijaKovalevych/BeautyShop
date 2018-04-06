package com.beautyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beautyshop.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	Item findByName(String name); 
	
	Item findByCountryId(int countryId);
	
	Item findByCategoryId(int categoryId);
	
	Item findByBrandId(int brandId);
	
}
