package com.beautyshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beautyshop.entity.Country;
import com.beautyshop.repository.CountryRepository;
import com.beautyshop.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	private CountryRepository countryRepository;
	
	@Autowired
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public void saveCountry(Country country) {
		countryRepository.save(country);
		
	}

	@Override
	public Country findByOne(int countryId) {
		return countryRepository.findOne(countryId);
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}

	@Override
	public void deleteCountry(int countryId) {
		countryRepository.delete(countryId);
		
	}

	@Override
	public Page<Country> findPage(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}

}
