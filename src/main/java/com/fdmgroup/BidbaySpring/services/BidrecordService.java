package com.fdmgroup.BidbaySpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.dal.BidrecordDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

@Component
public class BidrecordService {
	
	@Autowired
	private BidrecordDaoImpl bidrecordDao;
	
	public List<Bidrecord> getBidrecordListByEvent(Bidevent bidevent) {
		return bidrecordDao.getBidrecordByEvent(bidevent);
	}

	public void addBidrecord(Bidrecord bidrecord) {
		bidrecordDao.addBidrecord(bidrecord);
	}
	
}
