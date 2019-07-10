package com.fdmgroup.BidbaySpring.controllers;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.services.HomeService;

@Controller
public class IndexController {
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String renderHome(@ModelAttribute(value = "login_form_blank_user") User blackFormUser, Model model) {
		TreeMap<Bidevent, Integer> bideventPrice = homeService.getLatestBideventWithPrice();
		model.addAttribute("bideventPrice", bideventPrice);
		model.addAttribute("fromurl", "/");
		return "home";
	}
	
}
