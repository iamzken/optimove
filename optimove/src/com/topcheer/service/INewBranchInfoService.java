package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.NewBranchInfo;

public interface INewBranchInfoService {
	
	public List<NewBranchInfo> searchNewBranchInfo(Map searchMap);
	
	public List<NewBranchInfo> getNewBranchInfo(String branchCode);
	
	public List<NewBranchInfo> searchAll();
	
	public void insert(NewBranchInfo newBranchInfo);
	
	public void update(NewBranchInfo newBranchInfo);
	
	public void delete(String id);

}
