package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Businesstypemanager;

public interface IBusinesstypemanagerService {
	
	public List<Businesstypemanager> searchBusinesstypemanager(Map searchMap);
	
	public List<Businesstypemanager> getBusinesstypemanager(String typecode);
	
	public List<Businesstypemanager> searchAll();
	
	public void insert(Businesstypemanager businesstypemanager);
	
	public void update(Businesstypemanager businesstypemanager);
	
	public void delete(String id);

}
