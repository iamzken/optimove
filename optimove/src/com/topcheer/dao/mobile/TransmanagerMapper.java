package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Transmanager;


public interface TransmanagerMapper {
	
	public List<Transmanager> searchTransmanager(Map searchMap);
	
	public List<Transmanager> searchAll();
	
	public List<Transmanager> getTransmanager(String transcode);
	
	public void insert(Transmanager transmanager);
	
	public void update(Transmanager transmanager);
	
	public void delete(String transcode);
	
}
