package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.TaskdetailMapper;
import com.topcheer.model.Taskdetail;
import com.topcheer.service.ITaskdetailService;

@Service("taskdetailService")
public class TaskdetailServiceImpl implements ITaskdetailService{
	
	@Autowired
	private TaskdetailMapper taskdetailMapper;

	public void delete(String id) {
		taskdetailMapper.delete(id);
		
	}

	public List<Taskdetail> getTaskdetail(String taskid) {
		return taskdetailMapper.getTaskdetail(taskid);
	}

	public void insert(Taskdetail taskdetail) {
		 taskdetailMapper.insert(taskdetail);
	}

	public List<Taskdetail> searchAll() {
		return taskdetailMapper.searchAll();
	}

	public List<Taskdetail> searchTaskdetail(Map searchMap) {
		return taskdetailMapper.searchTaskdetail(searchMap);
	}

	public void update(Taskdetail Taskdetail) {
		taskdetailMapper.update(Taskdetail);
	}

	public TaskdetailMapper getTaskdetailMapper() {
		return taskdetailMapper;
	}

	public void setTaskdetailMapper(TaskdetailMapper TaskdetailMapper) {
		this.taskdetailMapper = TaskdetailMapper;
	}

	public List<Taskdetail> searchBudgetSchemeTaskdetail(Map searchMap) {
		return taskdetailMapper.searchBudgetSchemeTaskdetail(searchMap);
	}

	public List<Taskdetail> searchBudgetTaskdetail(Map searchMap) {
		return taskdetailMapper.searchBudgetTaskdetail(searchMap);
	}
	
	
	

}
