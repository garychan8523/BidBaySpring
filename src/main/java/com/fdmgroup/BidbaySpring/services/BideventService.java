package com.fdmgroup.BidbaySpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.dal.BideventDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;

@Component
public class BideventService {
	
	@Autowired
	private BideventDaoImpl bideventDao;
	
	public void createBidevent(Bidevent bidevent) {
		bideventDao.addBidevent(bidevent);
	}
	
	public Bidevent getBideventById(int eventid) {
		return bideventDao.getBidevent(eventid);
	}

}
