package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.GroupRight;

public interface IGroupRightService {
	
	public List<GroupRight> searchGroupRight(Map searchMap);
	
	public List<GroupRight> getGroupRight(String grpId);
	
	public List<GroupRight> searchAll();
	
	public void insert(GroupRight groupRight);
	
	public void update(GroupRight groupRight);
	
	public void delete(String id);

}
