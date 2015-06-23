package com.topcheer.dao.specialData;

import java.util.List;
import java.util.Map;


import com.topcheer.model.specialData.Specialdata;


public interface SpecialdataMapper {
	
	public List<Specialdata> searchSpecialdata(Map searchMap);
	
	public List<Specialdata> searchAll();
	
	public List<Specialdata> getSpecialdata(String specialdataid);
	
	public void insert(Specialdata specialdata);
	
	public void update(Specialdata specialdata);
	
	public void delete(String specialdataid);
	
}
