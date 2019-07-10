package com.fdmgroup.BidbaySpring.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String authenticate(HttpSession session, @RequestParam(value="frompage", required=false) String frompage, @RequestParam(value="logout", required=false) String logout, @ModelAttribute(value = "login_form_blank_user") User loginFormUser, Model model) throws IOException {
		
		if(logout != null) {
			session.removeAttribute("username");
			
			if(frompage != null) {
				return "redirect:" + frompage;
			} else {
				model.addAttribute("msg", "Logged out");
				model.addAttribute("fromuri", frompage);
				return "redirect";
			}
		} else {
			User authUser = null;
			String username = loginFormUser.getUsername();
			String password = loginFormUser.getHash_password();
			
			try {
				authUser = userService.authenticate(username, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(authUser == null) {
				model.addAttribute("msg", "User not found");
				model.addAttribute("fromuri", frompage);
				return "redirect";
			}else {
				
				session.setAttribute("username", authUser.getUsername());
				
				if(frompage != null) {
					return "redirect:" + frompage;
				} else {
					model.addAttribute("msg", "Welcome " + authUser.getUsername());
					model.addAttribute("fromuri", frompage);
					return "redirect";
				}
			}
		}
	}
	
}
