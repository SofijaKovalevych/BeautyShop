package com.beautyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beautyshop.entity.ShopingCart;

public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer>{

}
