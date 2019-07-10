package com.fdmgroup.BidbaySpring.client;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.BidbaySpring.dal.BideventDaoImpl;
import com.fdmgroup.BidbaySpring.dal.BidrecordDaoImpl;
import com.fdmgroup.BidbaySpring.dal.CategoryDaoImpl;
import com.fdmgroup.BidbaySpring.dal.UserDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;
import com.fdmgroup.BidbaySpring.entities.Category;
import com.fdmgroup.BidbaySpring.entities.User;

public class Initialize 
{
    public static void main( String[] args ) throws Exception
    {
    	
    	//BasicConfigurator.configure();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bidbay_user_config");
		UserDaoImpl userService = new UserDaoImpl();
		CategoryDaoImpl categoryService = new CategoryDaoImpl();
		BideventDaoImpl bideventService = new BideventDaoImpl();
		BidrecordDaoImpl bidrecordService = new BidrecordDaoImpl();
		
		userService.setEmf(emf);
		categoryService.setEmf(emf);
		bideventService.setEmf(emf);
		bidrecordService.setEmf(emf);
		
		User user;
		Category category;
		Bidevent bidevent;
		Bidrecord bidrecord;
		
//		bidevent = bideventService.getBidevent(1);
//		List<Bidrecord> bidrecordList = bidrecordService.getBidrecordByEvent(bidevent);
//		System.out.println(bidrecordList);

//		
//		System.out.println(bidevent.getItemname());
		categoryService.initalizeCategories();
//		categoryService.addCategory("others");
		
//		User newUser = new User();
//		newUser.setUsername("bob");
//		newUser.setHash_password("456");
//		newUser.setEmail("bob@bob.com");
//		userService.addUser(newUser);
		
		userService.addUser("bob", "123", "bob@bob.com");
		userService.addUser("jane", "456", "jane@bob.com");
		userService.addUser("john", "789", "john@bob.com");
//		
//		userService.removeUser("john");
//		
		user = userService.getUser("bob");
		category = categoryService.getCategory(1);
		bidevent = new Bidevent(user, "bulb", category, 13, 5, "this is some description", "30/03/2019");
		bideventService.addBidevent(bidevent);
		
		bidevent = new Bidevent(user, "great books", category, 12, 0, "come buy great books", "30/04/2021");
		bideventService.addBidevent(bidevent);

		user = userService.getUser("jane");
		bidevent = bideventService.getBidevent(1);
		bidrecord = new Bidrecord(bidevent, 20, user);
		bidrecordService.addBidrecord(bidrecord);
		bidrecord = new Bidrecord(bidevent, 27, user);
		bidrecordService.addBidrecord(bidrecord);
		bidrecord = new Bidrecord(bidevent, 29, user);
		bidrecordService.addBidrecord(bidrecord);
		bidrecord = new Bidrecord(bidevent, 48, user);
		bidrecordService.addBidrecord(bidrecord);
//		
//
//		List<User> listUser = userService.listUsers();
//		for(User u : listUser) {
//			System.out.println(u.getUsername());
//		}
		
//		String username = "bob";
//		String password = "123";
//		User authUser = userService.authenticate(username, password);
//		if(authUser != null) {
//			System.out.println("Logged in: " + authUser.getUsername());
//		} else {
//			System.out.println("Failed login attempt: " + username);
//		}
		
		
//		User user = userService.getUser("bob");
//		user.setUsername("john");
//		userService.updateUser(user);
		
//		System.out.println(userService.getUser(1).getUsername());
//		System.out.println(userService.getUser("john").getUsername());
		
		
		
		
//		userService.removeUser("bob");

//    	UsersDAO userDAO = new UsersDAO();
//    	
//    	// phase 2
//    	for(User user : userDAO.listUsers()) {
//    		System.out.println(user.getUsername());
//    	}
//    	
//    	
//    	// phase 3
//    	User user = userDAO.getUser("bob");
//    	System.out.println(user.getUsername());
    	
    	// phase 4
    	// userDAO.removeUser("456");
    	
    	// phase 5
//    	User newUser = new User();
//    	newUser.setUser_id("3");
//    	newUser.setUsername("jane");
//    	newUser.setPassword("4567");
//    	newUser.setFirstname("jane");
//    	newUser.setLastname("doe");
//    	newUser.setEmail("jane@bob.com");
//    	newUser.setRole("user");
//    	newUser.setStatus("active");
//    	
//    	userDAO.addUser(newUser);
    	
    	// phase 6
//    	User updateUser = new User();
//    	updateUser.setUser_id("1");
//    	updateUser.setUsername("bob");
//    	updateUser.setPassword("1234");
//    	updateUser.setFirstname("bob");
//    	updateUser.setLastname("doe");
//    	updateUser.setEmail("bob@bob.com");
//    	updateUser.setRole("user");
//    	updateUser.setStatus("active");
//    	
//    	userDAO.updateUser(updateUser);
    }
}
