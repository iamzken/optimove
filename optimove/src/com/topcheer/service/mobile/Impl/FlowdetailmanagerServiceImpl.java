package com.topcheer.service.mobile.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.mobile.FlowdetailmanagerMapper;
import com.topcheer.model.mobile.Flowdetailmanager;
import com.topcheer.service.mobile.IFlowdetailmanagerService;

@Service("flowdetailmanagerService")
public class FlowdetailmanagerServiceImpl implements IFlowdetailmanagerService{
	
	@Autowired
	private FlowdetailmanagerMapper flowdetailmanagerMapper;

	public void delete(String id) {
		flowdetailmanagerMapper.delete(id);
		
	}

	public List<Flowdetailmanager> getFlowdetailmanager(String flowcode) {
		return flowdetailmanagerMapper.getFlowdetailmanager(flowcode);
	}

	public void insert(Flowdetailmanager flowdetailmanager) {
		 flowdetailmanagerMapper.insert(flowdetailmanager);
	}

	public List<Flowdetailmanager> searchAll() {
		return flowdetailmanagerMapper.searchAll();
	}

	public List<Flowdetailmanager> searchFlowdetailmanager(Map searchMap) {
		return flowdetailmanagerMapper.searchFlowdetailmanager(searchMap);
	}

	public void update(Flowdetailmanager Flowdetailmanager) {
		flowdetailmanagerMapper.update(Flowdetailmanager);
	}

	public FlowdetailmanagerMapper getFlowdetailmanagerMapper() {
		return flowdetailmanagerMapper;
	}

	public void setFlowdetailmanagerMapper(FlowdetailmanagerMapper FlowdetailmanagerMapper) {
		this.flowdetailmanagerMapper = FlowdetailmanagerMapper;
	}
	
	
	

}
