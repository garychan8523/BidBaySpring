package com.fdmgroup.BidbaySpring.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.UserDaoImpl;
import com.fdmgroup.BidbaySpring.entities.User;

public class SignupServiceTest {

	@Mock
	private UserDaoImpl userDao;
	
	@InjectMocks
	private SignupService signupService = new SignupService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_username_when_isUserNameTaken_getUser_should_be_called() {
		// Arrange
		String username = "dummy";
		
		// Act
		signupService.isUserNameTaken(username);
		
		// Assert or Verify
		verify(userDao).getUser(username);
	}
	
	@Test
	public void given_username_when_isUserNameTaken_if_targetUser_exists_true_should_be_returned() {
		// Arrange
		String username = "dummy";
		User mockUser = mock(User.class);
		
		// Stub
		when(userDao.getUser(username)).thenReturn(mockUser);
		
		// Assert or Verify
		assertTrue(signupService.isUserNameTaken(username));
	}
	
	@Test
	public void given_username_password_email_when_registerUser_PasswordUtility_addUser_should_be_called() throws Exception {
		// Arrange
		String username = "dummy";
		String password = "password";
		String email = "email";
		
		// Act
		signupService.registerUser(username, password, email);
		
		// Assert or Verify
		verify(userDao).addUser(username, password, email);
	}
	
}
