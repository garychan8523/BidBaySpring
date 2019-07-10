package com.fdmgroup.BidbaySpring.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity

@Table(name = "bidbay_bidrecord")
public class Bidrecord {

	@EmbeddedId
	private BidrecordKey bidrecordKey;

	@MapsId("BidrecordKey")
	@JoinColumn (name="eventid")
    @ManyToOne
    private Bidevent bidevent;
	
	@ManyToOne
	@JoinColumn (name="userid")
	private User user;
	
	@Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private String timestamp;

	public Bidrecord() {

	}

	public Bidrecord(Bidevent bidevent, int price, User user) {
		super();
		bidrecordKey = new BidrecordKey();
		bidrecordKey.setEventid(bidevent.getEventid());
		bidrecordKey.setPrice(price);
		this.user = user;
	}

	public Bidevent getBidevent() {
		return bidevent;
	}

	public void setBidevent(Bidevent bidevent) {
		this.bidevent = bidevent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTimestamp() {
		return timestamp;
	}
	
	public int getPrice() {
		return bidrecordKey.getPrice();
	}
	
}
