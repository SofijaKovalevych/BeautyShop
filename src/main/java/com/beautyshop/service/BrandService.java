package com.beautyshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beautyshop.entity.Brand;

public interface BrandService {

	void saveBrand(Brand brand);
	
	Brand findByOne(int brandId);
	
	Brand findByName(String name);
	
	void deleteBrand(int brandId);

	Page<Brand> findPage(Pageable pageable);
}
