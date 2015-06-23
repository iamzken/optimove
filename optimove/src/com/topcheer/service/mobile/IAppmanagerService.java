package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Appmanager;

public interface IAppmanagerService {
	
	public List<Appmanager> searchAppmanager(Map searchMap);
	
	public List<Appmanager> getAppmanager(String appcode);
	
	public List<Appmanager> searchAll();
	
	public void insert(Appmanager appmanager);
	
	public void update(Appmanager appmanager);
	
	public void delete(String id);

}
