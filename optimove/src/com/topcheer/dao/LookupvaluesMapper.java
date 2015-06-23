package com.topcheer.dao;

import java.util.Map;
import java.util.List;
import com.topcheer.model.Lookupvalues;


public interface LookupvaluesMapper {
	public List<Lookupvalues> searchLookvalues(Map search);
	public List<Lookupvalues> searchAll();
	public List<Lookupvalues> getLookvalues(String lookupCode);	
	public List<Lookupvalues> getLookvaluesBylookup(Map search);
	public void insert(Lookupvalues lookupvalues);
	public void update(Lookupvalues lookupvalues);
	//public void delete(String lookupCode);
	public void delete(Lookupvalues lookupvalues);

}
