package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Servicemanager;

public interface IServicemanagerService {
	
	public List<Servicemanager> searchServicemanager(Map searchMap);
	
	public List<Servicemanager> getServicemanager(String servicecode);
	
	public List<Servicemanager> searchAll();
	
	public void insert(Servicemanager servicemanager);
	
	public void update(Servicemanager servicemanager);
	
	public void delete(String id);

}
