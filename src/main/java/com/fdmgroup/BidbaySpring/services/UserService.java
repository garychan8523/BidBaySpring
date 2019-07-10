package com.fdmgroup.BidbaySpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.dal.UserDaoImpl;
import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.utilities.PasswordUtility;

@Component
public class UserService {
	
	@Autowired
	private UserDaoImpl userDao;
	
	public User getUserByUsername(String username) {
		return userDao.getUser(username);
	}
	
	public User authenticate(String username, String password) throws Exception {
		User user = userDao.getUser(username);
		if (user != null) {
			if (PasswordUtility.check(password, user.getHash_password())) {
				return user;
			}
		}
		return null;
	}
	
}
