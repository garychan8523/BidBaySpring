package com.fdmgroup.BidbaySpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.dal.CategoryDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Category;

@Component
public class CategoryService {
	
	@Autowired
	private CategoryDaoImpl categoryDao;
	
	public List<Category> getAllCategory(){
		return categoryDao.listCategories();
	}

	public Category getCategoryById(int categoryid) {
		return categoryDao.getCategory(categoryid);
	}
}
