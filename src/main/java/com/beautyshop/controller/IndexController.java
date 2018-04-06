package com.beautyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.beautyshop.service.ItemService;
import com.beautyshop.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService; 
	
	
	
	
}
