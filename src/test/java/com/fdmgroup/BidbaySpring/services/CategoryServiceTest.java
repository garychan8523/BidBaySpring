package com.fdmgroup.BidbaySpring.services;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.CategoryDaoImpl;

public class CategoryServiceTest {

	@Mock
	private CategoryDaoImpl categoryDao;
	
	@InjectMocks
	private CategoryService categoryService = new CategoryService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_getAllCategory_listCategories_should_be_called() {
		// Act
		categoryService.getAllCategory();
		
		// Assert or Verify
		verify(categoryDao).listCategories();
	}
	
	@Test
	public void given_categoryid_when_getCategoryById_getCategory_should_be_called() {
		// Act
		categoryService.getCategoryById(1);
		
		// Assert or Verify
		verify(categoryDao).getCategory(1);
	}
	
}
