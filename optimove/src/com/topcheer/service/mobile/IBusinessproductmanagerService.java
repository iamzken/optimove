package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Businessproductmanager;

public interface IBusinessproductmanagerService {
	
	public List<Businessproductmanager> searchBusinessproductmanager(Map searchMap);
	
	public List<Businessproductmanager> getBusinessproductmanager(String productcode);
	
	public List<Businessproductmanager> searchAll();
	
	public void insert(Businessproductmanager businessproductmanager);
	
	public void update(Businessproductmanager businessproductmanager);
	
	public void delete(String id);

}
