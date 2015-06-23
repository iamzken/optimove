package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.UserMapper;
import com.topcheer.model.GroupInfo;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.User;
import com.topcheer.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMappper;

	public void delete(String workId) {
		userMappper.delete(workId);
		
	}

	public User getUser(String workId) {
		return userMappper.getUser(workId);
	}

	public NewBranchInfo getBranchInfo(String userBankCode) {
		return userMappper.getBranchInfo(userBankCode);
	}

	public void insert(User user) {
		 userMappper.insert(user);
	}

	public List<User> searchAll() {
		return userMappper.searchAll();
	}

	public List<User> searchUser(Map searchMap) {
		return userMappper.searchUser(searchMap);
	}

	public void update(User user) {
		userMappper.update(user);
	}

	public List<User> getLeftUserInfo(User user){
		return userMappper.getLeftUserInfo(user);
	}
	
	public List<GroupInfo> getLeftGroupInfo(User user){
		return userMappper.getLeftGroupInfo(user);
	}
	
	public UserMapper getUserMappper() {
		return userMappper;
	}

	public void setUserMappper(UserMapper userMappper) {
		this.userMappper = userMappper;
	}

}