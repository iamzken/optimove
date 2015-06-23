package com.topcheer.dao.mobile;

import java.util.List;
import java.util.Map;


import com.topcheer.model.mobile.Flowdetailmanager;


public interface FlowdetailmanagerMapper {
	
	public List<Flowdetailmanager> searchFlowdetailmanager(Map searchMap);
	
	public List<Flowdetailmanager> searchAll();
	
	public List<Flowdetailmanager> getFlowdetailmanager(String flowcode);
	
	public void insert(Flowdetailmanager flowdetailmanager);
	
	public void update(Flowdetailmanager flowdetailmanager);
	
	public void delete(String flowcode);
	
}
