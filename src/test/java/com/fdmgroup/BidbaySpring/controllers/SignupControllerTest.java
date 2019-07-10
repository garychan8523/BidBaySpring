package com.fdmgroup.BidbaySpring.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.BidbaySpring.services.SignupService;

public class SignupControllerTest {
	
	@Mock
	private SignupService signupService;
	
	@InjectMocks
	private SignupController signupController;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void signup_should_return_redirect() throws IOException {
		// Arrange
		Model mockModel = mock(Model.class);
		
		// Assert or Verify
		// (String username, String password, String email, Model model)
		assertEquals(signupController.signup("", "", "", mockModel), "redirect");
		
	}
	
	@Test
	public void when_signup_if_username_taken_then_msg_should_be_set() throws IOException {
		// Arrange
		Model mockModel = mock(Model.class);
		
		// Stub
		when(signupService.isUserNameTaken("")).thenReturn(true);
		
		// Act
		// (String username, String password, String email, Model model)
		signupController.signup("", "", "", mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(signupService);
		inOrder.verify(signupService).isUserNameTaken("");
	}
	
	@Test
	public void when_signup_if_username_not_taken_then_register_and_msg_should_be_set() throws IOException {
		// Arrange
		Model mockModel = mock(Model.class);
		
		// Stub
		when(signupService.isUserNameTaken("")).thenReturn(false);
		
		// Act
		// (String username, String password, String email, Model model)
		signupController.signup("", "", "", mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(signupService, mockModel);
		inOrder.verify(signupService).isUserNameTaken("");
		inOrder.verify(signupService).registerUser("", "", "");
		inOrder.verify(mockModel).addAttribute("msg", "Welcome ");
	}
	
}
