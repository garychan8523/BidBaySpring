package com.fdmgroup.BidbaySpring.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.BidrecordDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

public class BidrecordServiceTest {

	@Mock
	private BidrecordDaoImpl bidrecordDao;
	
	@InjectMocks
	private BidrecordService bidrecordService = new BidrecordService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_bidevent_when_getBidrecordListByEvent_getBidrecordByEvent_should_be_called() {
		// Arrange
		Bidevent mockBidevent = mock(Bidevent.class);
		
		// Act
		bidrecordService.getBidrecordListByEvent(mockBidevent);
		
		// Assert or Verify
		verify(bidrecordDao).getBidrecordByEvent(mockBidevent);
	}
	
	@Test
	public void given_bidrecord_when_addBidrecord_addBidrecord_should_be_called() {
		// Arrange
		Bidrecord mockBidrecord = mock(Bidrecord.class);
		
		// Act
		bidrecordService.addBidrecord(mockBidrecord);
		
		// Assert or Verify
		verify(bidrecordDao).addBidrecord(mockBidrecord);
	}
	
}
