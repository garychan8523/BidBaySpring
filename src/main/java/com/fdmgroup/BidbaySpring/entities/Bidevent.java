package com.fdmgroup.BidbaySpring.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "bidbay_bidevent")
public class Bidevent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventid;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@Column(nullable = false)
	private String itemname;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	@Column(nullable = false)
	private int fromPrice = 0;

	@Column(nullable = false)
	private int quantity = 0;

	private String description;

	@Column(name = "fromTime", nullable = false, updatable = false, insertable = false, columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
	private String fromTime;

	private String toTime;

	public Bidevent() {

	}

	public Bidevent(User user, String itemname, Category category, int fromPrice, int quantity, String description,
			String toTime) {
		super();
		this.user = user;
		this.itemname = itemname;
		this.category = category;
		this.fromPrice = fromPrice;
		this.quantity = quantity;
		this.description = description;
		this.toTime = toTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getFromPrice() {
		return fromPrice;
	}

	public void setFromPrice(int fromPrice) {
		this.fromPrice = fromPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public int getEventid() {
		return eventid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Comparator<Bidevent> sortByCreateTimeDesc = new Comparator<Bidevent>() {

		public int compare(Bidevent b1, Bidevent b2) {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date b1date = null;
			Date b2date = null;
			try {
				b1date = format.parse(b1.getFromTime());
				b2date = format.parse(b2.getFromTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return b2date.compareTo(b1date);
		}
	};

}
