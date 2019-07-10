package com.fdmgroup.BidbaySpring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ListingService {

	public String isEventEnded(String toTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String now = format.format(new Date());
		
		Date totime = format.parse(toTime);
		Date today = format.parse(now);
		
		if(totime.compareTo(today) < 0) {
			return "1";
		} else {
			return null;
		}
	}
	
	public String getTodayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

}
