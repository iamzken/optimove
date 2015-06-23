package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Transtemplatemanager;


public interface TranstemplatemanagerMapper {
	
	public List<Transtemplatemanager> searchTranstemplatemanager(Map searchMap);
	
	public List<Transtemplatemanager> searchAll();
	
	public List<Transtemplatemanager> getTranstemplatemanager(String templatecode);
	
	public void insert(Transtemplatemanager transtemplatemanager);
	
	public void update(Transtemplatemanager transtemplatemanager);
	
	public void delete(String templatecode);
	
}
