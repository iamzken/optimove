package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Transmanager;

public interface ITransmanagerService {
	
	public List<Transmanager> searchTransmanager(Map searchMap);
	
	public List<Transmanager> getTransmanager(String transcode);
	
	public List<Transmanager> searchAll();
	
	public void insert(Transmanager transmanager);
	
	public void update(Transmanager transmanager);
	
	public void delete(String id);

}
