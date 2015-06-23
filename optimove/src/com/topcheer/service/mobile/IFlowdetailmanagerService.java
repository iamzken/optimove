package com.topcheer.service.mobile;

import java.util.List;
import java.util.Map;

import com.topcheer.model.mobile.Flowdetailmanager;

public interface IFlowdetailmanagerService {
	
	public List<Flowdetailmanager> searchFlowdetailmanager(Map searchMap);
	
	public List<Flowdetailmanager> getFlowdetailmanager(String flowcode);
	
	public List<Flowdetailmanager> searchAll();
	
	public void insert(Flowdetailmanager flowdetailmanager);
	
	public void update(Flowdetailmanager flowdetailmanager);
	
	public void delete(String id);

}
