package com.fdmgroup.BidbaySpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.dal.UserDaoImpl;
import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.utilities.PasswordUtility;

@Component
public class SignupService {

	@Autowired
	private UserDaoImpl userDao;

	public boolean isUserNameTaken(String username) {
		User targetUser = userDao.getUser(username);
		if (targetUser != null) {
			return true;
		} else {
			return false;
		}
	}

	public void registerUser(String username, String password, String email) {
		try {
			password = PasswordUtility.getSaltedHash(password);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			userDao.addUser(username, password, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
