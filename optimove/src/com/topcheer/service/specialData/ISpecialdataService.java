package com.topcheer.service.specialData;

import java.util.List;
import java.util.Map;

import com.topcheer.model.specialData.Specialdata;

public interface ISpecialdataService {
	
	public List<Specialdata> searchSpecialdata(Map searchMap);
	
	public List<Specialdata> getSpecialdata(String specialdataid);
	
	public List<Specialdata> searchAll();
	
	public void insert(Specialdata specialdata);
	
	public void update(Specialdata specialdata);
	
	public void delete(String id);

}
