package com.fdmgroup.BidbaySpring.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.dal.BideventDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;

public class BideventServiceTest {

	@Mock
	private BideventDaoImpl bideventDao;
	
	@InjectMocks
	private BideventService bideventService = new BideventService();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_bidevent_when_createBidevent_addBidevent_should_be_called() {
		// Arrange
		Bidevent mockBidevent = mock(Bidevent.class);
		
		// Act
		bideventService.createBidevent(mockBidevent);
		
		// Assert or Verify
		verify(bideventDao).addBidevent(mockBidevent);
	}
	
	@Test
	public void given_eventid_when_getBideventById_getBidevent_should_be_called() {
		// Act
		bideventService.getBideventById(0);
		
		// Assert or Verify
//		assertThat(bideventService.getBideventById(1), instanceOf(Bidevent.class));
		verify(bideventDao).getBidevent(0);
	}
}
