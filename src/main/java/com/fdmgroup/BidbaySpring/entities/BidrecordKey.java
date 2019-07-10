package com.fdmgroup.BidbaySpring.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BidrecordKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int eventid;
	private int price;
	
	public BidrecordKey() {
		super();
	}
	
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventid;
		result = prime * result + price;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidrecordKey other = (BidrecordKey) obj;
		if (eventid != other.eventid)
			return false;
		if (price != other.price)
			return false;
		return true;
	}

}
