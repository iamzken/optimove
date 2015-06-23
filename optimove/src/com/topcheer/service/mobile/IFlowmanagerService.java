package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Flowmanager;

public interface IFlowmanagerService {
	
	public List<Flowmanager> searchFlowmanager(Map searchMap);
	
	public List<Flowmanager> getFlowmanager(String flowcode);
	
	public List<Flowmanager> searchAll();
	
	public void insert(Flowmanager flowmanager);
	
	public void update(Flowmanager flowmanager);
	
	public void delete(String id);

}
