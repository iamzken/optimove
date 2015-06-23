package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Flowmanager;


public interface FlowmanagerMapper {
	
	public List<Flowmanager> searchFlowmanager(Map searchMap);
	
	public List<Flowmanager> searchAll();
	
	public List<Flowmanager> getFlowmanager(String flowcode);
	
	public void insert(Flowmanager flowmanager);
	
	public void update(Flowmanager flowmanager);
	
	public void delete(String flowcode);
	
}
