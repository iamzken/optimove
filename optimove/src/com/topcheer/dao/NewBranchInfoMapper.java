package com.topcheer.dao;

import java.util.List;
import java.util.Map;


import com.topcheer.model.NewBranchInfo;


public interface NewBranchInfoMapper {
	
	public List<NewBranchInfo> searchNewBranchInfo(Map searchMap);
	
	public List<NewBranchInfo> searchAll();
	
	public List<NewBranchInfo> getNewBranchInfo(String branchCode);
	
	public void insert(NewBranchInfo newBranchInfo);
	
	public void update(NewBranchInfo newBranchInfo);
	
	public void delete(String branchCode);
	
}
