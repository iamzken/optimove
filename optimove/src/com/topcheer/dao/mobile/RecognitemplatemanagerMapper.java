package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Recognitemplatemanager;


public interface RecognitemplatemanagerMapper {
	
	public List<Recognitemplatemanager> searchRecognitemplatemanager(Map searchMap);
	
	public List<Recognitemplatemanager> searchAll();
	
	public List<Recognitemplatemanager> getRecognitemplatemanager(String templatecode);
	
	public void insert(Recognitemplatemanager recognitemplatemanager);
	
	public void update(Recognitemplatemanager recognitemplatemanager);
	
	public void delete(String templatecode);
	
}
