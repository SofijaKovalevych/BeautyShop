package com.beautyshop.dto;

public class CategoryFilter {
	
	private String search;

	public CategoryFilter(String search) {
		this.search = search;
	}
	
	public CategoryFilter() {
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	

}
