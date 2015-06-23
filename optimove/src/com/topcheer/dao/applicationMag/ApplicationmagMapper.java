package com.topcheer.dao.applicationMag;

import java.util.List;
import java.util.Map;


import com.topcheer.model.applicationMag.Applicationmag;


public interface ApplicationmagMapper {
	
	public List<Applicationmag> searchApplicationmag(Map searchMap);
	
	public List<Applicationmag> searchAll();
	
	public List<Applicationmag> getApplicationmag(String applicationid);
	
	public void insert(Applicationmag applicationmag);
	
	public void update(Applicationmag applicationmag);
	
	public void delete(String applicationid);
	
}
