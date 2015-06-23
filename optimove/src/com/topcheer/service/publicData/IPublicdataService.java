package com.topcheer.service.publicData;

import java.util.List;
import java.util.Map;

import com.topcheer.model.publicData.Publicdata;

public interface IPublicdataService {
	
	public List<Publicdata> searchPublicdata(Map searchMap);
	
	public List<Publicdata> getPublicdata(String publicdataid);
	
	public List<Publicdata> searchAll();
	
	public void insert(Publicdata publicdata);
	
	public void update(Publicdata publicdata);
	
	public void delete(String id);

}
