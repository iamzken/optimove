package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.NewBranchInfoMapper;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.service.INewBranchInfoService;

@Service("newBranchInfoService")
public class NewBranchInfoServiceImpl implements INewBranchInfoService{
	
	@Autowired
	private NewBranchInfoMapper newBranchInfoMapper;

	public void delete(String id) {
		newBranchInfoMapper.delete(id);
		
	}

	public List<NewBranchInfo> getNewBranchInfo(String branchCode) {
		return newBranchInfoMapper.getNewBranchInfo(branchCode);
	}

	public void insert(NewBranchInfo newBranchInfo) {
		 newBranchInfoMapper.insert(newBranchInfo);
	}

	public List<NewBranchInfo> searchAll() {
		return newBranchInfoMapper.searchAll();
	}

	public List<NewBranchInfo> searchNewBranchInfo(Map searchMap) {
		return newBranchInfoMapper.searchNewBranchInfo(searchMap);
	}

	public void update(NewBranchInfo NewBranchInfo) {
		newBranchInfoMapper.update(NewBranchInfo);
	}

	public NewBranchInfoMapper getNewBranchInfoMapper() {
		return newBranchInfoMapper;
	}

	public void setNewBranchInfoMapper(NewBranchInfoMapper NewBranchInfoMapper) {
		this.newBranchInfoMapper = NewBranchInfoMapper;
	}
	
	
	

}
