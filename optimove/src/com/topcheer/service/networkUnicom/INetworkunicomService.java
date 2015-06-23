package com.topcheer.service.networkUnicom;

import java.util.List;
import java.util.Map;

import com.topcheer.model.networkUnicom.Networkunicom;

public interface INetworkunicomService {
	
	public List<Networkunicom> searchNetworkunicom(Map searchMap);
	
	public List<Networkunicom> getNetworkunicom(String networkunicomid);
	
	public List<Networkunicom> searchAll();
	
	public void insert(Networkunicom networkunicom);
	
	public void update(Networkunicom networkunicom);
	
	public void delete(String id);

}
