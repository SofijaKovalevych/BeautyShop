package com.beautyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beautyshop.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	Country findByName(String name);
}
