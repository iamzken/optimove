package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.FlowmanagerMapper;
import com.topcheer.model.mobile.Flowmanager;
import com.topcheer.service.mobile.IFlowmanagerService;

@Service("flowmanagerService")
public class FlowmanagerServiceImpl implements IFlowmanagerService{
	
	@Autowired
	private FlowmanagerMapper flowmanagerMapper;

	public void delete(String id) {
		flowmanagerMapper.delete(id);
		
	}

	public List<Flowmanager> getFlowmanager(String flowcode) {
		return flowmanagerMapper.getFlowmanager(flowcode);
	}

	public void insert(Flowmanager flowmanager) {
		 flowmanagerMapper.insert(flowmanager);
	}

	public List<Flowmanager> searchAll() {
		return flowmanagerMapper.searchAll();
	}

	public List<Flowmanager> searchFlowmanager(Map searchMap) {
		return flowmanagerMapper.searchFlowmanager(searchMap);
	}

	public void update(Flowmanager Flowmanager) {
		flowmanagerMapper.update(Flowmanager);
	}

	public FlowmanagerMapper getFlowmanagerMapper() {
		return flowmanagerMapper;
	}

	public void setFlowmanagerMapper(FlowmanagerMapper FlowmanagerMapper) {
		this.flowmanagerMapper = FlowmanagerMapper;
	}
	
	
	

}
