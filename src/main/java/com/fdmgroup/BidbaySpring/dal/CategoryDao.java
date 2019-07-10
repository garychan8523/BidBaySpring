package com.fdmgroup.BidbaySpring.dal;

import java.util.List;

import com.fdmgroup.BidbaySpring.entities.Category;

public interface CategoryDao {
	
	public void initalizeCategories();
	
	public void addCategory(Category category);
	
	public Category getCategory(int categoryid);
	
	public List<Category> listCategories();

}
