package com.fdmgroup.BidbaySpring.dal;

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

public class BideventDaoImplTest {
	
	@Mock
	private EntityManagerFactory mockEmf;
	
	@InjectMocks
	private BideventDaoImpl bideventDao = new BideventDaoImpl();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_eventid_when_getBidevent_then_find_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		int eventid = 1;

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		bideventDao.getBidevent(eventid);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).find(Bidevent.class, eventid);
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void given_bidevent_object_when_addBidevent_then_persist_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Bidevent mockBidevent = mock(Bidevent.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		bideventDao.addBidevent(mockBidevent);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockBidevent);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void when_listBidevent_then_createquery_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Bidevent> mockQuery = Mockito.mock(TypedQuery.class);
		ArrayList<Bidevent> bidrecordList = new ArrayList<Bidevent>();
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT b from Bidevent b", Bidevent.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(bidrecordList);
				
		// Act
		bideventDao.listBidevent();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT b from Bidevent b", Bidevent.class);
//		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}

}
