package com.topcheer.service.applicationMag;

import java.util.List;
import java.util.Map;

import com.topcheer.model.applicationMag.Applicationmag;

public interface IApplicationmagService {
	
	public List<Applicationmag> searchApplicationmag(Map searchMap);
	
	public List<Applicationmag> getApplicationmag(String applicationid);
	
	public List<Applicationmag> searchAll();
	
	public void insert(Applicationmag applicationmag);
	
	public void update(Applicationmag applicationmag);
	
	public void delete(String id);

}
