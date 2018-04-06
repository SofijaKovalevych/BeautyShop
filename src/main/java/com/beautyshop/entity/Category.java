package com.beautyshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category extends Base{

	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
	private List <Item> items = new ArrayList<>();
}
