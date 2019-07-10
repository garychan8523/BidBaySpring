package com.fdmgroup.BidbaySpring.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.BideventDaoImpl;
import com.fdmgroup.BidbaySpring.dal.BidrecordDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

public class HomeServiceTest {

	@Mock
	private BideventDaoImpl bideventDao;
	@Mock
	private BidrecordDaoImpl bidrecordDao;
	
	@InjectMocks
	private HomeService homeService = new HomeService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_getLatestBideventWithPrice_with_no_bidevent_returned_bideventDao_methods_should_be_called() {
		// Arrange
		List<Bidevent> emptyBideventList = new ArrayList<Bidevent>();
		
		// Stub
		when(bideventDao.listBidevent()).thenReturn(emptyBideventList);
		
		// Act
		TreeMap<Bidevent, Integer> resultMap = homeService.getLatestBideventWithPrice();
		
		// Assert or Verify
		assertEquals(resultMap, new TreeMap<Bidevent, Integer>(Bidevent.sortByCreateTimeDesc));
	}
	
	@Test
	public void when_getLatestBideventWithPrice_with_bidevent_and_record_returned_bideventDao_methods_should_be_called() {
		// Arrange
		List<Bidevent> emptyBideventList = new ArrayList<Bidevent>();
		Bidevent mockBidevent = mock(Bidevent.class);
		emptyBideventList.add(mockBidevent);
		
		List<Bidrecord> mockBidrecordList = new ArrayList<Bidrecord>();
		Bidrecord mockBidrecord = mock(Bidrecord.class);
		mockBidrecordList.add(mockBidrecord);
		
		// Stub
		when(bideventDao.listBidevent()).thenReturn(emptyBideventList);
		when(bidrecordDao.getBidrecordByEvent(mockBidevent)).thenReturn(mockBidrecordList);
		
		// Act
		homeService.getLatestBideventWithPrice();
		
		// Assert or Verify
		verify(mockBidrecordList).get(0).getPrice();
	}
	
	@Test
	public void when_getLatestBideventWithPrice_with_bidevent_and_no_record_returned_bideventDao_methods_should_be_called() {
		// Arrange		
		List<Bidevent> mockBideventList = mock(ArrayList.class);
		Bidevent mockBidevent = mock(Bidevent.class);
		List<Bidrecord> mockBidrecordList = mock(ArrayList.class);
		
		// Stub
		when(bideventDao.listBidevent()).thenReturn(mockBideventList);
		when(bidrecordDao.getBidrecordByEvent(mockBidevent)).thenReturn(mockBidrecordList);
		
		// Act
		homeService.getLatestBideventWithPrice();
		
		// Assert or Verify
		verify(mockBidevent).getFromPrice();
	}
	
	
}
