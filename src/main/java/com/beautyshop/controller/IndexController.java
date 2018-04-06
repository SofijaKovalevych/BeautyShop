package com.beautyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beautyshop.service.ItemService;
import com.beautyshop.service.UserService;

@Controller
public class IndexController {

	private ItemService itemService;
	
	private UserService userService;

	@Autowired
	public IndexController(ItemService itemService, UserService userService) {
		this.itemService = itemService;
		this.userService = userService;
	} 
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String lofin() {
		return "login";
	}
	
	
	
	
}
