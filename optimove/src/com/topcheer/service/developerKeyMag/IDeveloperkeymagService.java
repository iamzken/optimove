package com.topcheer.service.developerKeyMag;

import java.util.List;
import java.util.Map;

import com.topcheer.model.developerKeyMag.Developerkeymag;

public interface IDeveloperkeymagService {
	
	public List<Developerkeymag> searchDeveloperkeymag(Map searchMap);
	
	public List<Developerkeymag> getDeveloperkeymag(String keyid);
	
	public List<Developerkeymag> searchAll();
	
	public void insert(Developerkeymag developerkeymag);
	
	public void update(Developerkeymag developerkeymag);
	
	public void delete(String id);

}
