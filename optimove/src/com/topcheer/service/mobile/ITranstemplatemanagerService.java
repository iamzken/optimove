package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Transtemplatemanager;

public interface ITranstemplatemanagerService {
	
	public List<Transtemplatemanager> searchTranstemplatemanager(Map searchMap);
	
	public List<Transtemplatemanager> getTranstemplatemanager(String templatecode);
	
	public List<Transtemplatemanager> searchAll();
	
	public void insert(Transtemplatemanager transtemplatemanager);
	
	public void update(Transtemplatemanager transtemplatemanager);
	
	public void delete(String id);

}
