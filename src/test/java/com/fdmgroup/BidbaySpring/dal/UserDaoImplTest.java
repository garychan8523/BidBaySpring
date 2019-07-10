package com.fdmgroup.BidbaySpring.dal;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.BidbaySpring.entities.User;
import com.fdmgroup.BidbaySpring.dal.UserDaoImpl;

public class UserDaoImplTest {
	
	@Mock
	private EntityManagerFactory mockEmf;
	
	@InjectMocks
	private UserDaoImpl userDao = new UserDaoImpl();
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void given_user_object_when_addUser_then_persist_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		userDao.addUser(mockUser);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockUser);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_id_when_getUser_then_find_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
				
		// Act
		userDao.getUser(mockUser.getUserid());

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);
		
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).find(User.class, mockUser.getUserid());
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_username_when_getUser_then_find_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);
		 
		@SuppressWarnings("unchecked")
		TypedQuery<User> mockQuery = Mockito.mock(TypedQuery.class);
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u where u.username = :username", User.class)).thenReturn(mockQuery);
		when(mockQuery.setParameter("username", "bob")).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);
		
		// Act
		userDao.getUser("bob");

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockQuery);
		
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT u FROM User u where u.username = :username", User.class);
		inOrder.verify(mockQuery).setParameter("username", "bob");
		inOrder.verify(mockQuery).getSingleResult();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_userid_when_removeUser_then_remove_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(User.class, mockUser.getUserid())).thenReturn(mockUser);
				
		// Act
		userDao.removeUser(mockUser.getUserid());

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);
				
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEm).find(User.class, mockUser.getUserid());
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).remove(mockUser);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_username_when_removeUser_then_remove_related_procedures_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Query mockQuery = Mockito.mock(Query.class);
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("delete from User u where u.username= '" + "dummy" + "'")).thenReturn(mockQuery);
		when(mockQuery.executeUpdate()).thenReturn(1);
		
		//when(mockEm.find(User.class, mockUser.getUserid())).thenReturn(mockUser);
				
		// Act
		userDao.removeUser("dummy");

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockQuery);
						
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).createQuery("delete from User u where u.username= '" + "dummy" + "'");
		inOrder.verify(mockQuery).executeUpdate();
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_user_object_when_updateUser_then_updateUser_should_be_called() throws Exception {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(User.class, mockUser.getUserid())).thenReturn(mockUser);
				
		// Act
		userDao.updateUser(mockUser);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockUser);
		
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEm).find(User.class, mockUser.getUserid());
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockUser).setUsername(mockUser.getUsername());
		inOrder.verify(mockUser).setHash_password(mockUser.getHash_password());
		inOrder.verify(mockUser).setEmail(mockUser.getEmail());
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	
	@Test
	public void when_listUsers_if_resultList_is_not_null_then_listUsers_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		@SuppressWarnings("unchecked")
		TypedQuery<User> mockQuery = Mockito.mock(TypedQuery.class);
		ArrayList<User> resultList = new ArrayList<User>();
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT u from User u", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(resultList);
				
		// Act
		userDao.listUsers();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);
				
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT u from User u", User.class);
		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}
	
	@Test
	public void when_listUsers_if_resultList_is_null_then_listUsers_should_be_called() throws IOException {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		@SuppressWarnings("unchecked")
		TypedQuery<User> mockQuery = Mockito.mock(TypedQuery.class);
		
		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT u from User u", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);
				
		// Act
		userDao.listUsers();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);
				
		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT u from User u", User.class);
		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}
}
