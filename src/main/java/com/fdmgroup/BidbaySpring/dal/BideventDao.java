package com.fdmgroup.BidbaySpring.dal;

import java.util.List;

import com.fdmgroup.BidbaySpring.entities.Bidevent;

public interface BideventDao {

	public Bidevent getBidevent(int eventid);
	
	public void addBidevent(Bidevent bidevent);
	
	public List<Bidevent> listBidevent();
	
}
