package com.fdmgroup.BidbaySpring.dal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

public class BidrecordDaoImplTest {
	
	@Mock
	private EntityManagerFactory mockEmf;
	
	@InjectMocks
	private BidrecordDaoImpl bidrecordDao = new BidrecordDaoImpl();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_user_object_when_addUser_then_persist_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Bidrecord mockBidrecord = mock(Bidrecord.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		bidrecordDao.addBidrecord(mockBidrecord);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockBidrecord);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void when_getAllBidrecord_then_createquery_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Bidrecord> mockQuery = Mockito.mock(TypedQuery.class);
		ArrayList<Bidrecord> bidrecordList = new ArrayList<Bidrecord>();
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT b from Bidrecord b", Bidrecord.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(bidrecordList);
				
		// Act
		bidrecordDao.getAllBidrecord();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT b from Bidrecord b", Bidrecord.class);
//		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void given_bidevent_when_getBidrecordByEvent_then_createquery_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		Bidevent mockBidevent = mock(Bidevent.class);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Bidrecord> mockQuery = Mockito.mock(TypedQuery.class);
		ArrayList<Bidrecord> bidrecordList = new ArrayList<Bidrecord>();
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT b from Bidrecord b where eventid=0 order by price desc", Bidrecord.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(bidrecordList);
				
		// Act
		bidrecordDao.getBidrecordByEvent(mockBidevent);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT b from Bidrecord b where eventid=0 order by price desc", Bidrecord.class);
//		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void given_bidevent_when_getBidrecordByEvent_if_resultlist_is_null_then_null_should_be_returned() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		
		Bidevent mockBidevent = mock(Bidevent.class);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Bidrecord> mockQuery = Mockito.mock(TypedQuery.class);
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT b from Bidrecord b where eventid=0 order by price desc", Bidrecord.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);
				
		// Act
		assertEquals(bidrecordDao.getBidrecordByEvent(mockBidevent), null);
	}
	

}
