package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Appmanager;


public interface AppmanagerMapper {
	
	public List<Appmanager> searchAppmanager(Map searchMap);
	
	public List<Appmanager> searchAll();
	
	public List<Appmanager> getAppmanager(String appcode);
	
	public void insert(Appmanager appmanager);
	
	public void update(Appmanager appmanager);
	
	public void delete(String appcode);
	
}
