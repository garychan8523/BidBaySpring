package com.fdmgroup.BidbaySpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.BidbaySpring.services.SignupService;

@Controller
public class SignupController {

	@Autowired
	private SignupService signupService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, Model model) {

		if (signupService.isUserNameTaken(username)) {
			model.addAttribute("msg", "Name taken");
		} else {
			signupService.registerUser(username, password, email);
			model.addAttribute("msg", "Welcome " + username);
		}

		return "redirect";
	}
}
