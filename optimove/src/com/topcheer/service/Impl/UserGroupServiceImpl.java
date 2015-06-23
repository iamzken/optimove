package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.UserGroupMapper;
import com.topcheer.model.UserGroup;
import com.topcheer.service.IUserGroupService;

@Service("userGroupService")
public class UserGroupServiceImpl implements IUserGroupService{
	
	@Autowired
	private UserGroupMapper userGroupMapper;

	public void delete(String id) {
		userGroupMapper.delete(id);
		
	}

	public List<UserGroup> getUserGroup(String workId) {
		return userGroupMapper.getUserGroup(workId);
	}

	public void insert(UserGroup userGroup) {
		 userGroupMapper.insert(userGroup);
	}

	public List<UserGroup> searchAll() {
		return userGroupMapper.searchAll();
	}

	public List<UserGroup> searchUserGroup(Map searchMap) {
		return userGroupMapper.searchUserGroup(searchMap);
	}

	public void update(UserGroup UserGroup) {
		userGroupMapper.update(UserGroup);
	}

	public UserGroupMapper getUserGroupMapper() {
		return userGroupMapper;
	}

	public void setUserGroupMapper(UserGroupMapper UserGroupMapper) {
		this.userGroupMapper = UserGroupMapper;
	}
	
	
	

}
