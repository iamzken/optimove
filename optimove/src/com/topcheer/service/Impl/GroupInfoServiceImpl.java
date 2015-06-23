package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.GroupInfoMapper;
import com.topcheer.model.GroupInfo;
import com.topcheer.service.IGroupInfoService;

@Service("groupInfoService")
public class GroupInfoServiceImpl implements IGroupInfoService{
	
	@Autowired
	private GroupInfoMapper groupInfoMapper;

	public void delete(String id) {
		groupInfoMapper.delete(id);
		
	}

	public List<GroupInfo> getGroupInfo(String grpId) {
		return groupInfoMapper.getGroupInfo(grpId);
	}

	public void insert(GroupInfo groupInfo) {
		 groupInfoMapper.insert(groupInfo);
	}

	public List<GroupInfo> searchAll() {
		return groupInfoMapper.searchAll();
	}

	public List<GroupInfo> searchGroupInfo(Map searchMap) {
		return groupInfoMapper.searchGroupInfo(searchMap);
	}

	public void update(GroupInfo GroupInfo) {
		groupInfoMapper.update(GroupInfo);
	}

	public GroupInfoMapper getGroupInfoMapper() {
		return groupInfoMapper;
	}

	public void setGroupInfoMapper(GroupInfoMapper GroupInfoMapper) {
		this.groupInfoMapper = GroupInfoMapper;
	}
	
	
	

}
