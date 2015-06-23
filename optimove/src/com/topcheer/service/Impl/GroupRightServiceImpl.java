package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.GroupRightMapper;
import com.topcheer.model.GroupRight;
import com.topcheer.service.IGroupRightService;

@Service("groupRightService")
public class GroupRightServiceImpl implements IGroupRightService{
	
	@Autowired
	private GroupRightMapper groupRightMapper;

	public void delete(String id) {
		groupRightMapper.delete(id);
		
	}

	public List<GroupRight> getGroupRight(String grpId) {
		return groupRightMapper.getGroupRight(grpId);
	}

	public void insert(GroupRight groupRight) {
		 groupRightMapper.insert(groupRight);
	}

	public List<GroupRight> searchAll() {
		return groupRightMapper.searchAll();
	}

	public List<GroupRight> searchGroupRight(Map searchMap) {
		return groupRightMapper.searchGroupRight(searchMap);
	}

	public void update(GroupRight GroupRight) {
		groupRightMapper.update(GroupRight);
	}

	public GroupRightMapper getGroupRightMapper() {
		return groupRightMapper;
	}

	public void setGroupRightMapper(GroupRightMapper GroupRightMapper) {
		this.groupRightMapper = GroupRightMapper;
	}
	
	
	

}
