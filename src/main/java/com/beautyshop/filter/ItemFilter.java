package com.beautyshop.filter;

import java.util.ArrayList;
import java.util.List;

public class ItemFilter {
	
	private List<Integer> brandIds = new ArrayList<>();

	private List<Integer> categoryIds = new ArrayList<>();

	public List<Integer> getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(List<Integer> brandIds) {
		this.brandIds = brandIds;
	}

	public List<Integer> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Integer> categoryIds) {
		this.categoryIds = categoryIds;
	}
	
	
	

}
