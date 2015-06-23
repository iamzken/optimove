package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Businessproductmanager;


public interface BusinessproductmanagerMapper {
	
	public List<Businessproductmanager> searchBusinessproductmanager(Map searchMap);
	
	public List<Businessproductmanager> searchAll();
	
	public List<Businessproductmanager> getBusinessproductmanager(String productcode);
	
	public void insert(Businessproductmanager businessproductmanager);
	
	public void update(Businessproductmanager businessproductmanager);
	
	public void delete(String productcode);
	
}
