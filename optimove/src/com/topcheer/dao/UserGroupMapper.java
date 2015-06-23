package com.topcheer.dao;

import java.util.List;
import java.util.Map;


import com.topcheer.model.UserGroup;


public interface UserGroupMapper {
	
	public List<UserGroup> searchUserGroup(Map searchMap);
	
	public List<UserGroup> searchAll();
	
	public List<UserGroup> getUserGroup(String workId);
	
	public void insert(UserGroup userGroup);
	
	public void update(UserGroup userGroup);
	
	public void delete(String workId);
	
}
