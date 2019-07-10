package com.fdmgroup.BidbaySpring.dal;

import java.util.List;

import com.fdmgroup.BidbaySpring.entities.User;

public interface UserDao {
	
	public void addUser(User user);
//	public void addUser(String username, String password, String email) throws Exception;

	public User getUser(int userid);
	public User getUser(String username);
	
	public void removeUser(int id);
	public void removeUser(String username);

	public void updateUser(User user) throws Exception;

	public List<User> listUsers();

}
