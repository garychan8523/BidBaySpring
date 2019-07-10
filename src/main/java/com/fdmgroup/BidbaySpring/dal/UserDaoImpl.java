package com.fdmgroup.BidbaySpring.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.entities.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManagerFactory emf;

	public UserDaoImpl() {
		super();
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void addUser(User user) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(user);
		et.commit();
		em.close();
	}

	public void addUser(String username, String password, String email) throws Exception {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setHash_password(password);
		newUser.setEmail(email);
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(newUser);
		et.commit();
		em.close();
	}

	public User getUser(int userid) {
		User user = new User();
		EntityManager em = getEntityManager();
		try {
			user = em.find(User.class, userid);
		} finally {
			em.close();
		}
		return user;
	}

	public User getUser(String username) {
		User user = null;
		EntityManager em = getEntityManager();
		try {
			user = (User) em.createQuery("SELECT u FROM User u where u.username = :username", User.class)
					.setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}

	public void removeUser(int id) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		User user = em.find(User.class, id);
		try {
			if (user != null) {
				et.begin();
				em.remove(user);
				et.commit();
			}
		} finally {
			em.close();
		}
	}

	public void removeUser(String username) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("delete from User u where u.username= '" + username + "'").executeUpdate();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void updateUser(User user) throws Exception {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		User existingUser = em.find(User.class, user.getUserid());
		try {
			if (existingUser != null) {
				et.begin();
				existingUser.setUsername(user.getUsername());
				existingUser.setHash_password(user.getHash_password());
				existingUser.setEmail(user.getEmail());
				et.commit();
			}
		} finally {
			em.close();
		}
	}

	public List<User> listUsers() {
		EntityManager em = getEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u from User u", User.class);

		ArrayList<User> resultList = (ArrayList<User>) query.getResultList();

		em.close();

		if (resultList != null) {
			return resultList;
		} else {
			return null;
		}
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

}
