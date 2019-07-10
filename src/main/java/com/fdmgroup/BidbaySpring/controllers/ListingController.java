package com.fdmgroup.BidbaySpring.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;
import com.fdmgroup.BidbaySpring.entities.Category;
import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.services.BideventService;
import com.fdmgroup.BidbaySpring.services.BidrecordService;
import com.fdmgroup.BidbaySpring.services.CategoryService;
import com.fdmgroup.BidbaySpring.services.ListingService;
import com.fdmgroup.BidbaySpring.services.UserService;

@Controller
public class ListingController {

	@Autowired
	private ListingService listingService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BideventService bideventService;
	@Autowired
	private BidrecordService bidrecordService;
	
	@RequestMapping(value="/listing/{eid}", method=RequestMethod.GET)
	public String renderListing(@ModelAttribute(value = "login_form_blank_user") User blackFormUser, @PathVariable int eid, Model model) throws ParseException {
		
		Bidevent bidevent = bideventService.getBideventById(eid);
		List<Bidrecord> resultList = bidrecordService.getBidrecordListByEvent(bidevent);
		
		String hasend = listingService.isEventEnded(bidevent.getToTime());
		
		model.addAttribute("bidevent", bidevent);
		model.addAttribute("bidrecord", resultList);
		model.addAttribute("hasend", hasend);
		
		if(resultList.size()>0) {
			model.addAttribute("currentbid", resultList.get(0).getPrice());
		}
		
		model.addAttribute("fromurl", "/listing/"+eid);
		return "listing";
	}
	
	@RequestMapping(value="/listing/add", method=RequestMethod.GET)
	public String add(HttpSession session, Model model) throws IOException {
		
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			model.addAttribute("msg", "Please login to create a bid event");
			return "redirect";
		} else {
			List<Category> resultList = categoryService.getAllCategory();
			String todayDate = listingService.getTodayDate();
			
			model.addAttribute("category", resultList);
			model.addAttribute("today", todayDate);
			model.addAttribute("fromurl", "/listing/add");
			return "addevent";
		}
	}
	
	@RequestMapping(value="/listing/addEvent", method=RequestMethod.POST)
	public String addEvent(HttpSession session, 
			@RequestParam("productname") String productname, 
			@RequestParam("quantity") int quantity, 
			@RequestParam("fromprice") int fromprice, 
			@RequestParam("totime") String totimeStr, 
			@RequestParam("category") int categoryid, 
			@RequestParam("description") String description, 
			Model model) throws ParseException {
		
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			model.addAttribute("msg", "Please login to create a bid event");
			return "redirect";
		} else {
			
			Date totimeDate = new SimpleDateFormat("yyyy-MM-dd").parse(totimeStr);
			DateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy");
			String totime = outputformat.format(totimeDate);
			
			User postBy = userService.getUserByUsername(username);
			Category category = categoryService.getCategoryById(categoryid);
			
			Bidevent bidevent = new Bidevent(postBy, productname, category, fromprice, quantity, description, totime);
			bideventService.createBidevent(bidevent);
			
			model.addAttribute("msg", "Bid event added");
			return "redirect";
		}
		
	}
	
	@RequestMapping(value="/listing/addBid", method=RequestMethod.POST)
	public String addBid(HttpSession session, 
			@RequestParam(value="requesturi", required=false) String requesturi,
			@RequestParam("eid") int bid, 
			@RequestParam("bidamount") int price, 
			Model model) throws IOException {
		
//		requesturi = UrlUtility.processListingContextUrl(requesturi);
		
		String username = (String) session.getAttribute("username");
		
		Bidevent bidevent = bideventService.getBideventById(bid);
		
		List<Bidrecord> resultList = bidrecordService.getBidrecordListByEvent(bidevent);
		int currentbid = 0;
		if(resultList.size()>0) {
			currentbid = resultList.get(0).getPrice();
		} else {
			currentbid = bidevent.getFromPrice();
		}
		
		if(price <= currentbid) {
			model.addAttribute("msg", "Bid price lower than or equal to current bid");
			model.addAttribute("fromuri", requesturi);
			return "redirect";
		} else {
			User bidder = userService.getUserByUsername(username);
			Bidrecord bidrecord = new Bidrecord(bidevent, price, bidder);
			bidrecordService.addBidrecord(bidrecord);
			
			if(requesturi != null) {
				return "redirect:" + requesturi;
			}else {
				model.addAttribute("msg", "Bid added");
				model.addAttribute("fromuri", requesturi);
				return "redirect";
			}
		}
	}

}
