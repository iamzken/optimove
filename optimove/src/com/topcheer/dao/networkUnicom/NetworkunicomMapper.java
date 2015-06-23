package com.topcheer.dao.networkUnicom;

import java.util.List;
import java.util.Map;


import com.topcheer.model.networkUnicom.Networkunicom;


public interface NetworkunicomMapper {
	
	public List<Networkunicom> searchNetworkunicom(Map searchMap);
	
	public List<Networkunicom> searchAll();
	
	public List<Networkunicom> getNetworkunicom(String networkunicomid);
	
	public void insert(Networkunicom networkunicom);
	
	public void update(Networkunicom networkunicom);
	
	public void delete(String networkunicomid);
	
}
