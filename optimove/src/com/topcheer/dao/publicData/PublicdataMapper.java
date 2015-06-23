package com.topcheer.dao.publicData;

import java.util.List;
import java.util.Map;


import com.topcheer.model.publicData.Publicdata;


public interface PublicdataMapper {
	
	public List<Publicdata> searchPublicdata(Map searchMap);
	
	public List<Publicdata> searchAll();
	
	public List<Publicdata> getPublicdata(String publicdataid);
	
	public void insert(Publicdata publicdata);
	
	public void update(Publicdata publicdata);
	
	public void delete(String publicdataid);
	
}
