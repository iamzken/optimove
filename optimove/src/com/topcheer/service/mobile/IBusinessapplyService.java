package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Businessapply;

public interface IBusinessapplyService {
	
	public List<Businessapply> searchBusinessapply(Map searchMap);
	
	public List<Businessapply> getBusinessapply(String businessid);
	
	public List<Businessapply> searchAll();
	
	public void insert(Businessapply businessapply);
	
	public void update(Businessapply businessapply);
	
	public void delete(String id);

}
