package com.beautyshop.service;

import com.beautyshop.entity.Brand;

public interface BrandService {

	void saveBrand(Brand brand);
	
	Brand findByOne(int brandId);
	
	Brand findByName(String name);
	
	void deleteBrand(int brandId);
}
