package com.beautyshop.service;

import com.beautyshop.entity.Country;

public interface CountryService {
	
	void saveCountry(Country country);
	
	Country findByOne(int countryId);
	
	Country findByName(String name);
	
	void deleteCountry(int countryId);

}
