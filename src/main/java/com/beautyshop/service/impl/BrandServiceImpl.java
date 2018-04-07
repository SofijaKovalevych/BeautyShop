package com.beautyshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beautyshop.entity.Brand;
import com.beautyshop.repository.BrandRepository;
import com.beautyshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	private BrandRepository brandRepository;
	
	@Autowired 
	public  BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository; 
	}
	
	
	@Override
	public void saveBrand(Brand brand) {
		brandRepository.save(brand); 
		
	}

	@Override
	public Brand findByOne(int brandId) {
		return brandRepository.findOne(brandId);
	}

	@Override
	public Brand findByName(String name) {
		return brandRepository.findByName(name);
	}

	@Override
	public void deleteBrand(int brandId) {
		brandRepository.delete(brandId);
		
	}

	@Override
	public Page<Brand> findPage(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}


	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}
}
