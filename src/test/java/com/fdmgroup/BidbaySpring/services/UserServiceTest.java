package com.fdmgroup.BidbaySpring.services;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.UserDaoImpl;

public class UserServiceTest {
	
	@Mock
	private UserDaoImpl userDao;
	
	@InjectMocks
	private UserService userervice = new UserService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_username_when_getUserByUsername_getUser_should_be_called() {
		// Arrange
		String username = "dummy";
		
		// Act
		userervice.getUserByUsername(username);
		
		// Assert or Verify
		verify(userDao).getUser(username);
	}
	
	@Test
	public void when_authenticate_getUser_should_be_called() throws Exception {
		// Arrange
		String username = "dummy";
		String password = "password";
		
		// Act
		userervice.authenticate(username, password);
		
		// Assert or Verify
		verify(userDao).getUser(username);
	}

}
