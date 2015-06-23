package com.topcheer.dao.grabData;

import java.util.List;
import java.util.Map;


import com.topcheer.model.grabData.Grabdata;


public interface GrabdataMapper {
	
	public List<Grabdata> searchGrabdata(Map searchMap);
	
	public List<Grabdata> searchAll();
	
	public List<Grabdata> getGrabdata(String grabdataid);
	
	public void insert(Grabdata grabdata);
	
	public void update(Grabdata grabdata);
	
	public void delete(String grabdataid);
	
}
