package com.beautyshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beautyshop.entity.Country;

public interface CountryService {
	
	void saveCountry(Country country);
	
	Country findByOne(int countryId);
	
	Country findByName(String name);
	
	void deleteCountry(int countryId);

	Page<Country> findPage(Pageable pageable);

	List<Country> findAll();

}
