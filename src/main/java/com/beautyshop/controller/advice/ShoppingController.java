package com.beautyshop.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.beautyshop.dto.Quantity;
import com.beautyshop.service.ItemService;

@ControllerAdvice
public class ShoppingController {
	
	@Autowired
	private ItemService itemsService;
	
	@ModelAttribute("quantity")
	public Quantity getQuantity(@CookieValue(defaultValue="0", name="userId") int userId){
		int count = itemsService.findCount(userId);
		return new Quantity(count);
	}
}
