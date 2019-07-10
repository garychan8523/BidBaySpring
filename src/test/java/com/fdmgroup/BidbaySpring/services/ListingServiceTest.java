package com.fdmgroup.BidbaySpring.services;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.BideventDaoImpl;

public class ListingServiceTest {

	@Mock
	private BideventDaoImpl bideventDao;
	
	@InjectMocks
	private ListingService listingService = new ListingService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_a_date_in_future_isEventEnded_should_return_null() throws ParseException {
		// Arrange
		String toTime = "31/12/2199";
		
		// Assert or Verify
		assertEquals(listingService.isEventEnded(toTime), null);
	}
	
	@Test
	public void given_a_date_in_past_isEventEnded_should_return_1() throws ParseException {
		// Arrange
		String toTime = "31/12/1907";
		
		// Assert or Verify
		assertEquals(listingService.isEventEnded(toTime), "1");
	}
	
	@Test
	public void getTodayDate_should_today_date() throws ParseException {
		// Arrange
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// Assert or Verify
		assertEquals(listingService.getTodayDate(), sdf.format(new Date()));
	}
}
