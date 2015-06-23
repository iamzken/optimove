package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.BaDept;

public interface IBaDeptService {
	
	public List<BaDept> searchBaDept(Map searchMap);
	
	public List<BaDept> getBaDept(String deptCode);
	
	public List<BaDept> searchAll();
	
	public void insert(BaDept baDept);
	
	public void update(BaDept baDept);
	
	public void delete(String deptCode);

}
