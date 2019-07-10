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

import com.fdmgroup.BidbaySpring.entities.Category;

public class CategoryDaoImplTest {
	
	@Mock
	private EntityManagerFactory mockEmf;
	
	@InjectMocks
	private CategoryDaoImpl categoryDao = new CategoryDaoImpl();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_initalizeCategories_then_persist_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		categoryDao.initalizeCategories();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		//inOrder.verify(mockEm).persist(mockCategory);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void given_category_when_addCategory_then_persist_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		Category mockCategory = mock(Category.class);
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		categoryDao.addCategory(mockCategory);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockCategory);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void given_categoryid_when_getCategory_then_find_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		int categoryid = 1;
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		categoryDao.getCategory(categoryid);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).find(Category.class, categoryid);
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void when_listCategories_then_typedquery_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Category> mockQuery = Mockito.mock(TypedQuery.class);
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT c from Category c order by c.categoryid", Category.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(categoryList);
				
		// Act
		categoryDao.listCategories();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT c from Category c order by c.categoryid", Category.class);
//		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void when_listCategories_if_resultList_is_null_then_null_should_be_returned() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);
		
		@SuppressWarnings("unchecked")
		TypedQuery<Category> mockQuery = Mockito.mock(TypedQuery.class);
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT c from Category c order by c.categoryid", Category.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);
				
		// Act
		assertEquals(categoryDao.listCategories(), null);
	}

}
