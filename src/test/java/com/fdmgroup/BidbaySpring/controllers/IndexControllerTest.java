package com.fdmgroup.BidbaySpring.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.services.HomeService;

public class IndexControllerTest {
	
	@Mock
	private HomeService homeService;
	
	@InjectMocks
	private IndexController indexController;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_renderHome_then_getLatestBideventWithPrice_should_be_called() {
		// Arrange
		TreeMap<Bidevent, Integer> bideventPrice = new TreeMap<Bidevent, Integer>();
		Model mockModel = mock(Model.class);
		
		// Stub
		when(homeService.getLatestBideventWithPrice()).thenReturn(bideventPrice);
		
		// Act
		indexController.renderHome(null, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(homeService, mockModel);
		inOrder.verify(homeService).getLatestBideventWithPrice();
		inOrder.verify(mockModel).addAttribute("bideventPrice", bideventPrice);
	}
	
	@Test
	public void when_renderHome_then_home_should_be_retuened() {
		// Arrange
		TreeMap<Bidevent, Integer> bideventPrice = new TreeMap<Bidevent, Integer>();
		Model mockModel = mock(Model.class);
		
		// Stub
		when(homeService.getLatestBideventWithPrice()).thenReturn(bideventPrice);
		
		// Act
		indexController.renderHome(null, mockModel);
		
		// Assert or Verify
		assertEquals(indexController.renderHome(null, mockModel), "home");
	}
	
}
