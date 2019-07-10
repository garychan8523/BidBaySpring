package com.fdmgroup.BidbaySpring.dal;

import java.util.List;

import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

public interface BidrecordDao {

	public void addBidrecord(Bidrecord bidrecord);
	
	public List<Bidrecord> getAllBidrecord();
	
	public List<Bidrecord> getBidrecordByEvent(Bidevent bidevent);
	
}
