package com.fdmgroup.BidbaySpring.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;
import com.fdmgroup.BidbaySpring.entities.Category;
import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.services.BideventService;
import com.fdmgroup.BidbaySpring.services.BidrecordService;
import com.fdmgroup.BidbaySpring.services.CategoryService;
import com.fdmgroup.BidbaySpring.services.ListingService;
import com.fdmgroup.BidbaySpring.services.UserService;

public class ListingControllerTest {
	
	@Mock
	private ListingService listingService;
	@Mock
	private UserService userService;
	@Mock
	private CategoryService categoryService;
	@Mock
	private BideventService bideventService;
	@Mock
	private BidrecordService bidrecordService;
	
	@InjectMocks
	private ListingController listingController;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_renderListing_bideventService_and_bidrecordService_should_be_called() throws IOException, ParseException {
		// Arrange
		User mockUser = mock(User.class);
		int eid = 1;
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> resultList = new ArrayList<Bidrecord>();
		Bidrecord mockBidrecord = mock(Bidrecord.class);
		resultList.add(mockBidrecord);
		
		// Stub
		when(bideventService.getBideventById(eid)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(resultList);
		
		// Act
		// (User blackFormUser, int eid, Model model)
		listingController.renderListing(mockUser, eid, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockBidevent, mockModel, bideventService, bidrecordService);
		inOrder.verify(bideventService).getBideventById(1);
		inOrder.verify(bidrecordService).getBidrecordListByEvent(mockBidevent);
		inOrder.verify(mockModel).addAttribute("currentbid", resultList.get(0).getPrice());
		inOrder.verify(mockModel).addAttribute("fromurl", "/listing/"+eid);
	}
	
	@Test
	public void when_addListing_with_null_username_then_msg_should_be_set() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn(null);
		
		// Act
		// (HttpSession session, Model model)
		listingController.add(mockSession, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(mockModel).addAttribute("msg", "Please login to create a bid event");
	}
	
	@Test
	public void when_addListing_with_null_username_then_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn(null);
		
		// Act
		// (HttpSession session, Model model)
		assertEquals(listingController.add(mockSession, mockModel), "redirect");
	}
	
	@Test
	public void when_addListing_with_not_null_username_then_msg_should_be_set() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		List<Category> resultList = new ArrayList<Category>();
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(categoryService.getAllCategory()).thenReturn(resultList);
		when(listingService.getTodayDate()).thenReturn("");
		
		// Act
		// (HttpSession session, Model model)
		listingController.add(mockSession, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel, categoryService, listingService);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(categoryService).getAllCategory();
		inOrder.verify(listingService).getTodayDate();
		inOrder.verify(mockModel).addAttribute("category", resultList);
		inOrder.verify(mockModel).addAttribute("today", "");
		inOrder.verify(mockModel).addAttribute("fromurl", "/listing/add");
	}
	
	@Test
	public void when_addListing_with_not_null_username_then_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		List<Category> resultList = new ArrayList<Category>();
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(categoryService.getAllCategory()).thenReturn(resultList);
		when(listingService.getTodayDate()).thenReturn("");
		
		// Act
		// (HttpSession session, Model model)
		assertEquals(listingController.add(mockSession, mockModel), "addevent");
	}
	
	@Test
	public void when_addEvent_with_null_username_then_msg_should_be_set() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn(null);
		
		// Act
		// (HttpSession session, String productname, int quantity, int fromprice, String totimeStr, int categoryid, String description, Model model)
		listingController.addEvent(mockSession, "", 1, 0, "", 1, "", mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(mockModel).addAttribute("msg", "Please login to create a bid event");
	}
	
	@Test
	public void when_addEvent_with_null_username_then_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn(null);
		
		// Act
		// (HttpSession session, String productname, int quantity, int fromprice, String totimeStr, int categoryid, String description, Model model)
		assertEquals(listingController.addEvent(mockSession, "", 1, 0, "", 1, "", mockModel), "redirect");
	}
	
	@Test
	public void when_addEvent_with_not_null_username_then_msg_should_be_set() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		
		// Act
		// (HttpSession session, String productname, int quantity, int fromprice, String totimeStr, int categoryid, String description, Model model)
		listingController.addEvent(mockSession, "", 1, 0, "2199-12-31", 1, "", mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel, userService, categoryService);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(userService).getUserByUsername("");
		inOrder.verify(categoryService).getCategoryById(1);
		inOrder.verify(mockModel).addAttribute("msg", "Bid event added");
	}
	
	@Test
	public void when_addEvent_with_not_null_username_then_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		
		// Act
		// (HttpSession session, String productname, int quantity, int fromprice, String totimeStr, int categoryid, String description, Model model)
		assertEquals(listingController.addEvent(mockSession, "", 1, 0, "2199-12-31", 1, "", mockModel), "redirect");
	}
	
	@Test
	public void when_addBid_with_non_empty_resultList_currentbid_should_set_to_result_price() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockResultList = mock(ArrayList.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(bideventService.getBideventById(1)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(mockResultList);
		
		
		// Act
		// (HttpSession session, String requesturi, int bid, int price, Model model)
		listingController.addBid(mockSession, "", 1, 0, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockBidevent, mockModel, mockResultList, userService, bideventService, bidrecordService);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(bideventService).getBideventById(1);
		inOrder.verify(bidrecordService).getBidrecordListByEvent(mockBidevent);
		inOrder.verify(mockBidevent).getFromPrice();
		inOrder.verify(mockModel).addAttribute("msg", "Bid price lower than or equal to current bid");
		inOrder.verify(mockModel).addAttribute("fromuri", "");
	}
	
	@Test
	public void when_addBid_with_non_empty_resultList_currentbid_should_set_to_result_price_and_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockResultList = mock(ArrayList.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(bideventService.getBideventById(1)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(mockResultList);
		
		
		// Act
		// (HttpSession session, String requesturi, int bid, int price, Model model)
		assertEquals(listingController.addBid(mockSession, "", 1, 0, mockModel), "redirect");
	}
	
	@Test
	public void when_addBid_with_price_greater_than_currentbid_then_addBidrecord_should_be_called() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockResultList = mock(ArrayList.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(bideventService.getBideventById(1)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(mockResultList);
		
		
		// Act
		// (HttpSession session, String requesturi, int bid, int price, Model model)
		listingController.addBid(mockSession, "", 1, 10, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel, mockResultList, userService, bideventService, bidrecordService);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(bideventService).getBideventById(1);
		inOrder.verify(bidrecordService).getBidrecordListByEvent(mockBidevent);
	}
	
	@Test
	public void when_addBid_with_price_greater_than_currentbid_and_requesturl_not_null_then_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockResultList = mock(ArrayList.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(bideventService.getBideventById(1)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(mockResultList);
		
		
		// Act
		// (HttpSession session, String requesturi, int bid, int price, Model model)
		assertEquals(listingController.addBid(mockSession, "", 1, 10, mockModel), "redirect:");
	}
	
	@Test
	public void when_addBid_with_price_greater_than_currentbid_and_requesturl_is_null_then_msg_should_be_set() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockResultList = mock(ArrayList.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(bideventService.getBideventById(1)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(mockResultList);
		
		
		// Act
		// (HttpSession session, String requesturi, int bid, int price, Model model)
		listingController.addBid(mockSession, null, 1, 10, mockModel);
		
		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockSession, mockModel, mockResultList, userService, bideventService, bidrecordService);
		inOrder.verify(mockSession).getAttribute("username");
		inOrder.verify(bideventService).getBideventById(1);
		inOrder.verify(bidrecordService).getBidrecordListByEvent(mockBidevent);
		inOrder.verify(mockModel).addAttribute("msg", "Bid added");
		inOrder.verify(mockModel).addAttribute("fromuri", null);
	}
	
	@Test
	public void when_addBid_with_price_greater_than_currentbid_and_requesturl_is_null_then_redirect_should_be_returned() throws IOException, ParseException {
		// Arrange
		HttpSession mockSession = mock(HttpSession.class);
		Model mockModel = mock(Model.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockResultList = mock(ArrayList.class);
		
		// Stub
		when(mockSession.getAttribute("username")).thenReturn("");
		when(bideventService.getBideventById(1)).thenReturn(mockBidevent);
		when(bidrecordService.getBidrecordListByEvent(mockBidevent)).thenReturn(mockResultList);
		
		
		// Act
		// (HttpSession session, String requesturi, int bid, int price, Model model)
		assertEquals(listingController.addBid(mockSession, null, 1, 10, mockModel), "redirect");
	}

}
