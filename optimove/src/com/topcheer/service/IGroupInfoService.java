package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.GroupInfo;

public interface IGroupInfoService {
	
	public List<GroupInfo> searchGroupInfo(Map searchMap);
	
	public List<GroupInfo> getGroupInfo(String grpId);
	
	public List<GroupInfo> searchAll();
	
	public void insert(GroupInfo groupInfo);
	
	public void update(GroupInfo groupInfo);
	
	public void delete(String id);

}
