package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Businesstypemanager;


public interface BusinesstypemanagerMapper {
	
	public List<Businesstypemanager> searchBusinesstypemanager(Map searchMap);
	
	public List<Businesstypemanager> searchAll();
	
	public List<Businesstypemanager> getBusinesstypemanager(String typecode);
	
	public void insert(Businesstypemanager businesstypemanager);
	
	public void update(Businesstypemanager businesstypemanager);
	
	public void delete(String typecode);
	
}
