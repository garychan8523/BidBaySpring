package com.fdmgroup.BidbaySpring.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.services.UserService;

public class LoginControllerTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private LoginController loginController;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_authenticate_with_logout_not_equals_to_null_then_session_should_be_removed() throws IOException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		loginController.authenticate(mockSession, null, "true", null, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession);
		inOrder.verify(mockSession).removeAttribute("username");
	}
	
	@Test
	public void when_authenticate_with_logout_not_equals_to_null_and_frompage_not_equals_to_null_then_redirect_should_be_returned() throws IOException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		assertEquals(loginController.authenticate(mockSession, "", "true", null, mockModel), "redirect:");
	}
	
	@Test
	public void when_authenticate_with_logout_not_equals_to_null_and_frompage_equals_to_null_then_msg_should_be_set() throws IOException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		loginController.authenticate(mockSession, null, "true", null, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel);
		inOrder.verify(mockModel).addAttribute("msg", "Logged out");
		inOrder.verify(mockModel).addAttribute("fromuri", null);
	}
	
	@Test
	public void when_authenticate_with_logout_not_equals_to_null_and_frompage_equals_to_null_then_redirect_should_be_retuened() throws IOException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		assertEquals(loginController.authenticate(mockSession, null, "true", null, mockModel), "redirect");
	}
	
	@Test
	public void when_authenticate_with_logout_equals_to_null_then_authenticate_should_be_called() throws Exception {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		User mockUser = mock(User.class);
		
		// Stub
		when(userService.authenticate(null, null)).thenReturn(null);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		loginController.authenticate(mockSession, null, null, mockUser, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockUser, mockSession, mockModel, userService);
		inOrder.verify(mockUser).getUsername();
		inOrder.verify(mockUser).getHash_password();
		inOrder.verify(userService).authenticate(null, null);
		inOrder.verify(mockModel).addAttribute("msg", "User not found");
		inOrder.verify(mockModel).addAttribute("fromuri", null);
	}
	
	@Test
	public void when_authenticate_with_logout_equals_to_null_then_redirect_should_be_returned() throws Exception {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		User mockUser = mock(User.class);
		
		// Stub
		when(userService.authenticate(null, null)).thenReturn(null);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		assertEquals(loginController.authenticate(mockSession, null, null, mockUser, mockModel), "redirect");
	}
	
	@Test
	public void when_authenticate_with_logout_equals_to_null_then_if_authUser_exists_set_session_should_be_called() throws Exception {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		User mockUser = mock(User.class);
		
		// Stub
		when(userService.authenticate(null, null)).thenReturn(mockUser);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		loginController.authenticate(mockSession, null, null, mockUser, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockUser, mockSession, mockModel, userService);
		inOrder.verify(mockUser).getUsername();
		inOrder.verify(mockUser).getHash_password();
		inOrder.verify(userService).authenticate(null, null);
		inOrder.verify(mockSession).setAttribute("username", null);
	}
	
	@Test
	public void when_authenticate_with_logout_equals_to_null_then_if_authUser_exists_and_frompage_not_null_redirect_should_be_returned() throws Exception {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		User mockUser = mock(User.class);
		
		// Stub
		when(userService.authenticate(null, null)).thenReturn(mockUser);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		assertEquals(loginController.authenticate(mockSession, "", null, mockUser, mockModel), "redirect:");
	}
	
	@Test
	public void when_authenticate_with_logout_equals_to_null_then_if_authUser_exists_and_frompage_is_null_msg_should_be_set() throws Exception {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		User mockUser = mock(User.class);
		
		// Stub
		when(userService.authenticate(null, null)).thenReturn(mockUser);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		loginController.authenticate(mockSession, null, null, mockUser, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockUser, mockSession, mockModel, userService);
		inOrder.verify(mockUser).getUsername();
		inOrder.verify(mockUser).getHash_password();
		inOrder.verify(userService).authenticate(null, null);
		inOrder.verify(mockSession).setAttribute("username", null);
		inOrder.verify(mockModel).addAttribute("msg", "Welcome " + null);
		inOrder.verify(mockModel).addAttribute("fromuri", null);
	}
	
	@Test
	public void when_authenticate_with_logout_equals_to_null_then_if_authUser_exists_and_frompage_is_null_redirect_should_be_returned() throws Exception {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		User mockUser = mock(User.class);
		
		// Stub
		when(userService.authenticate(null, null)).thenReturn(mockUser);
		
		// Act
		// (HttpSession, String frompage, String logout, User loginFormUser, Model model)
		assertEquals(loginController.authenticate(mockSession, null, null, mockUser, mockModel), "redirect");
	}

}
