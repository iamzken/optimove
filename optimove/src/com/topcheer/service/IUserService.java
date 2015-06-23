package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.GroupInfo;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.User;

public interface IUserService {
	
	public List<User> searchUser(Map searchMap);
	
	public User getUser(String workId);
	
	public List<User> searchAll();
	
	public void insert(User user);
	
	public void update(User user);
	
	public void delete(String workId);
	
	public NewBranchInfo getBranchInfo(String userBankCode);
	
	public List<User> getLeftUserInfo(User user);
	
	public List<GroupInfo> getLeftGroupInfo(User user);

}