package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.UserGroup;

public interface IUserGroupService {
	
	public List<UserGroup> searchUserGroup(Map searchMap);
	
	public List<UserGroup> getUserGroup(String workId);
	
	public List<UserGroup> searchAll();
	
	public void insert(UserGroup userGroup);
	
	public void update(UserGroup userGroup);
	
	public void delete(String id);

}
