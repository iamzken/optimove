package com.topcheer.dao;

import java.util.List;
import java.util.Map;


import com.topcheer.model.GroupInfo;


public interface GroupInfoMapper {
	
	public List<GroupInfo> searchGroupInfo(Map searchMap);
	
	public List<GroupInfo> searchAll();
	
	public List<GroupInfo> getGroupInfo(String grpId);
	
	public void insert(GroupInfo groupInfo);
	
	public void update(GroupInfo groupInfo);
	
	public void delete(String grpId);
	
}
