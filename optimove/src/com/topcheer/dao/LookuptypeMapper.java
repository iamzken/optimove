package com.topcheer.dao;

import java.util.List;
import java.util.Map;

import com.topcheer.model.Lookuptype;

public interface LookuptypeMapper {
	
	public List<Lookuptype> searchLookuptype(Map searchMap);
	public List<Lookuptype> searchAll();
	public List<Lookuptype> getLookuptype(String lookupType);	
	public void insert(Lookuptype lookuptype);
	public void update(Lookuptype lookuptype);
	public void delete(String lookupType);
	


}
