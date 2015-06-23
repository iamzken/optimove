package com.topcheer.dao.developerKeyMag;

import java.util.List;
import java.util.Map;


import com.topcheer.model.developerKeyMag.Developerkeymag;


public interface DeveloperkeymagMapper {
	
	public List<Developerkeymag> searchDeveloperkeymag(Map searchMap);
	
	public List<Developerkeymag> searchAll();
	
	public List<Developerkeymag> getDeveloperkeymag(String keyid);
	
	public void insert(Developerkeymag developerkeymag);
	
	public void update(Developerkeymag developerkeymag);
	
	public void delete(String keyid);
	
}
