package com.beautyshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beautyshop.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	Item findByName(String name); 
	
	Item findByCountryId(int countryId);
	
	Item findByCategoryId(int categoryId);
	
	Item findByBrandId(int brandId);
	
	@Query("SELECT sc.count FROM User u JOIN u.shopingCart sc WHERE u.id=?1")
	Integer findCount(int id);

	@Query("SELECT i FROM Item i JOIN i.shopingCarts sc JOIN sc.users u WHERE u.id=?1")
	List<Item> findAllByUserId(int userId);
	
}
