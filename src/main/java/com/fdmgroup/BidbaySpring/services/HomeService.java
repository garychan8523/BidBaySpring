package com.fdmgroup.BidbaySpring.services;

import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.BidbaySpring.dal.BideventDaoImpl;
import com.fdmgroup.BidbaySpring.dal.BidrecordDaoImpl;
import com.fdmgroup.BidbaySpring.entities.Bidevent;
import com.fdmgroup.BidbaySpring.entities.Bidrecord;

@Component
public class HomeService {

	@Autowired
	private BideventDaoImpl bideventDao;
	@Autowired
	private BidrecordDaoImpl bidrecordDao;

	public TreeMap<Bidevent, Integer> getLatestBideventWithPrice() {

		TreeMap<Bidevent, Integer> latestBidEventLatestPrice = new TreeMap<Bidevent, Integer>(Bidevent.sortByCreateTimeDesc);

		List<Bidevent> bideventList = bideventDao.listBidevent();
		
		for (Bidevent bidevent : bideventList) {
			List<Bidrecord> allBidrecord = bidrecordDao.getBidrecordByEvent(bidevent);
			
			Integer latestbid = 0;
			if (allBidrecord.size() > 0) {
				latestbid = allBidrecord.get(0).getPrice();
			} else {
				latestbid = bidevent.getFromPrice();
			}
			latestBidEventLatestPrice.put(bidevent, latestbid);
		}
		
		return latestBidEventLatestPrice;
	}
}
