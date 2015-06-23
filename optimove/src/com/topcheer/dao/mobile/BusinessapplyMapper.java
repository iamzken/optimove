package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Businessapply;


public interface BusinessapplyMapper {
	
	public List<Businessapply> searchBusinessapply(Map searchMap);
	
	public List<Businessapply> searchAll();
	
	public List<Businessapply> getBusinessapply(String businessid);
	
	public void insert(Businessapply businessapply);
	
	public void update(Businessapply businessapply);
	
	public void delete(String businessid);
	
}
