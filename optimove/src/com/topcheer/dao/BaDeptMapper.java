package com.topcheer.dao;

import java.util.List;
import java.util.Map;


import com.topcheer.model.BaDept;
import com.topcheer.model.NewBranchInfo;


public interface BaDeptMapper {
	
	public List<BaDept> searchBaDept(Map searchMap);
	
	public List<BaDept> searchAll();
	
	public List<BaDept> getBaDept(String deptCode);
	
	public void insert(BaDept baDept);
	
	public void update(BaDept baDept);
	
	public void delete(String deptCode);
	
}
