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
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

@Component
public class BidrecordDaoImpl implements BidrecordDao {

	@Autowired
	private EntityManagerFactory emf;

	public BidrecordDaoImpl() {
		super();
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void addBidrecord(Bidrecord bidrecord) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(bidrecord);
		et.commit();
		em.close();
	}

	public List<Bidrecord> getAllBidrecord() {
		EntityManager em = getEntityManager();
		
		TypedQuery<Bidrecord> query = em.createQuery("SELECT b from Bidrecord b", Bidrecord.class);
		List<Bidrecord> resultList = (ArrayList<Bidrecord>) query.getResultList();

		em.close();
		
		return resultList;
	}

	public List<Bidrecord> getBidrecordByEvent(Bidevent bidevent) {

		EntityManager em = getEntityManager();
		TypedQuery<Bidrecord> query = em.createQuery(
				"SELECT b from Bidrecord b where eventid=" + bidevent.getEventid() + " order by price desc",
				Bidrecord.class);

		ArrayList<Bidrecord> resultList = (ArrayList<Bidrecord>) query.getResultList();

		em.close();

		if (resultList != null) {
			return resultList;
		} else {
			return null;
		}
	}

}
