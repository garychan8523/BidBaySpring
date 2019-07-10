package com.fdmgroup.BidbaySpring.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.entities.Bidevent;

@Component
public class BideventDaoImpl implements BideventDao {

	@Autowired
	private EntityManagerFactory emf;

	public BideventDaoImpl() {
		super();
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public Bidevent getBidevent(int eventid) {
		Bidevent bidevent = new Bidevent();
		EntityManager em = getEntityManager();
		try {
			bidevent = em.find(Bidevent.class, eventid);
		} finally {
			em.close();
		}
		return bidevent;
	}

	public void addBidevent(Bidevent bidevent) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(bidevent);
		et.commit();
		em.close();
	}

	public List<Bidevent> listBidevent() {
		EntityManager em = getEntityManager();
	    
		TypedQuery<Bidevent> query = em.createQuery("SELECT b from Bidevent b", Bidevent.class);
		List<Bidevent> resultList = (ArrayList<Bidevent>) query.getResultList();
		
		em.close();
		
		return resultList;
	}

}
