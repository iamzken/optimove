package com.topcheer.service.grabData;

import java.util.List;
import java.util.Map;

import com.topcheer.model.grabData.Grabdata;

public interface IGrabdataService {
	
	public List<Grabdata> searchGrabdata(Map searchMap);
	
	public List<Grabdata> getGrabdata(String grabdataid);
	
	public List<Grabdata> searchAll();
	
	public void insert(Grabdata grabdata);
	
	public void update(Grabdata grabdata);
	
	public void delete(String id);

}
