package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Servicemanager;


public interface ServicemanagerMapper {
	
	public List<Servicemanager> searchServicemanager(Map searchMap);
	
	public List<Servicemanager> searchAll();
	
	public List<Servicemanager> getServicemanager(String servicecode);
	
	public void insert(Servicemanager servicemanager);
	
	public void update(Servicemanager servicemanager);
	
	public void delete(String servicecode);
	
}
