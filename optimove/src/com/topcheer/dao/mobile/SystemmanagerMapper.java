package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Systemmanager;


public interface SystemmanagerMapper {
	
	public List<Systemmanager> searchSystemmanager(Map searchMap);
	
	public List<Systemmanager> searchAll();
	
	public List<Systemmanager> getSystemmanager(String syscode);
	
	public void insert(Systemmanager systemmanager);
	
	public void update(Systemmanager systemmanager);
	
	public void delete(String syscode);
	
}
