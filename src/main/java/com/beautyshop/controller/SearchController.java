package com.beautyshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beautyshop.dto.CategoryFilter;
import com.beautyshop.service.CategoryService;
import com.beautyshop.service.ItemService;
import com.beautyshop.entity.Category;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	private CategoryService categoryService;
	private ItemService itemService;
	
	@Autowired
	public SearchController(CategoryService categoryService, ItemService itemService) {
		this.categoryService = categoryService;
		this.itemService = itemService;
	}
	
	@GetMapping("categoryslist")
	public String showCategorysByFilter(@PageableDefault Pageable pageable, Model model, @RequestParam("search") String search) throws IOException{
		model.addAttribute("name", "Categorys");
		
		CategoryFilter filter = null;
		if(search != null) {
			filter = new CategoryFilter(search);
		}
		
		Page<Category> page = categoryService.findAllCtegoriesByName(pageable, filter);
		model.addAttribute("categorysByFilter", page.getContent());
		
		return "category/categoryslist";
		}

}
