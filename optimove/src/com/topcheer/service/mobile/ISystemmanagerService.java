package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Systemmanager;

public interface ISystemmanagerService {
	
	public List<Systemmanager> searchSystemmanager(Map searchMap);
	
	public List<Systemmanager> getSystemmanager(String syscode);
	
	public List<Systemmanager> searchAll();
	
	public void insert(Systemmanager systemmanager);
	
	public void update(Systemmanager systemmanager);
	
	public void delete(String id);

}
