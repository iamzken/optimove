package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Recognitemplatemanager;

public interface IRecognitemplatemanagerService {
	
	public List<Recognitemplatemanager> searchRecognitemplatemanager(Map searchMap);
	
	public List<Recognitemplatemanager> getRecognitemplatemanager(String templatecode);
	
	public List<Recognitemplatemanager> searchAll();
	
	public void insert(Recognitemplatemanager recognitemplatemanager);
	
	public void update(Recognitemplatemanager recognitemplatemanager);
	
	public void delete(String id);

}
