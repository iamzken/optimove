package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.BaDeptMapper;
import com.topcheer.dao.NewBranchInfoMapper;
import com.topcheer.model.BaDept;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.service.IBaDeptService;
import com.topcheer.service.INewBranchInfoService;

@Service("baDeptService")
public class BaDeptServiceImpl implements IBaDeptService{
	
	@Autowired
	private BaDeptMapper baDeptMapper;

	public void delete(String id) {
		baDeptMapper.delete(id);
		
	}

	public List<BaDept> getBaDept(String deptCode) {
		return baDeptMapper.getBaDept(deptCode);
	}

	public void insert(BaDept baDept) {
		baDeptMapper.insert(baDept);
	}

	public List<BaDept> searchAll() {
		return baDeptMapper.searchAll();
	}

	public List<BaDept> searchBaDept(Map searchMap) {
		return baDeptMapper.searchBaDept(searchMap);
	}

	public void update(BaDept baDept) {
		baDeptMapper.update(baDept);
	}

	public BaDeptMapper getBaDeptMapper() {
		return baDeptMapper;
	}

	public void setBaDeptMapper(BaDeptMapper baDeptMapper) {
		this.baDeptMapper = baDeptMapper;
	}
}