package com.beautyshop.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beautyshop.entity.Item;
import com.beautyshop.entity.User;
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
	
	@GetMapping("/")
	public String index(Model model, @CookieValue(defaultValue="0", name="userId") int id, HttpServletResponse response, @PageableDefault Pageable pageable, Principal principal) {
		if(principal!=null) {
			User user = userService.findUserByEmail(principal.getName());
			id = user.getId();
			response.addCookie(new Cookie("userId", String.valueOf(id)));
		}
		model.addAttribute("page", itemService.findPage(pageable));
		Item item = itemService.findOne(1);
		String encodeFileToBase64 = new String(Base64.encodeBase64(item.getImg()));
			model.addAttribute("bag", encodeFileToBase64);
		System.out.println("Image: " + encodeFileToBase64);
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/shopping")
	public String shopping(Model model, @CookieValue(defaultValue = "0", name = "userId") int userId) {
		BigDecimal totalPrice = new BigDecimal(0); 
		List<Item> items = itemService.findAllByUserId(userId);
		for (Item item : items) { 
			totalPrice = totalPrice.add(item.getPrice()); 
		}
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("items", items);
		return "user/shopping";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/buy/{itemId}")
	public String buy(@CookieValue(defaultValue="0", name="userId") int userId, @PathVariable int itemId){
		userService.addToShoppingCart(userId, itemId);
		return "redirect:/";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/del/{itemId}")
	public String remove(@CookieValue(defaultValue = "0", name = "userId") int userId, @PathVariable int itemId) {
		userService.removeToShoppingCart(userId, itemId);
		return "redirect:/shopping";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/iNeedIt")
	public String iNeedIt(@CookieValue(defaultValue = "0", name = "userId") int userId, Principal principal) {
		userService.sendMail("Beauty Shop", principal.getName());
		userService.removeAllToShoppingCart(userId);
		return "user/success";
	}
	
	@GetMapping("/about")
	public String about() {
		return "base/about";
	}
	
	
	
}
