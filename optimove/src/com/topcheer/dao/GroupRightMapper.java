package com.topcheer.dao;

import java.util.List;
import java.util.Map;


import com.topcheer.model.GroupRight;


public interface GroupRightMapper {
	
	public List<GroupRight> searchGroupRight(Map searchMap);
	
	public List<GroupRight> searchAll();
	
	public List<GroupRight> getGroupRight(String grpId);
	
	public void insert(GroupRight groupRight);
	
	public void update(GroupRight groupRight);
	
	public void delete(String grpId);
	
}
