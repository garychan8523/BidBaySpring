package com.fdmgroup.BidbaySpring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "bidbay_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;

	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String hash_password;
	
	@Column(nullable=false)
	private String email;
	
	@Column(name="memberSince", nullable=false, updatable=false, insertable=false, columnDefinition="DATE DEFAULT CURRENT_TIMESTAMP")
	private String memberSince;
	
	@Column(nullable=false, columnDefinition="number(1)")
	private int isActive = 1;
	
	@Column(nullable=false, columnDefinition="number(1)")
	private int isAdmin = 0;

	public User() {
		super();
	}

	public int getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHash_password() {
		return this.hash_password;
	}

	public void setHash_password(String hash_password) {
		this.hash_password = hash_password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberSince() {
		return memberSince;
	}

	public int getIsActive() {
		return isActive;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + isActive;
		result = prime * result + isAdmin;
		result = prime * result + ((memberSince == null) ? 0 : memberSince.hashCode());
		result = prime * result + userid;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (isActive != other.isActive)
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (memberSince == null) {
			if (other.memberSince != null)
				return false;
		} else if (!memberSince.equals(other.memberSince))
			return false;
		if (userid != other.userid)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

}
