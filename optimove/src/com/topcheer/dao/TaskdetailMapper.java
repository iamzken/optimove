package com.topcheer.dao;

import java.util.List;
import java.util.Map;


import com.topcheer.model.Taskdetail;


public interface TaskdetailMapper {
	
	public List<Taskdetail> searchTaskdetail(Map searchMap);
	
	public List<Taskdetail> searchAll();
	
	public List<Taskdetail> getTaskdetail(String taskid);
	
	public void insert(Taskdetail taskdetail);
	
	public void update(Taskdetail taskdetail);
	
	public void delete(String taskid);
	
	//查询预算方案审批任务
	public List<Taskdetail> searchBudgetSchemeTaskdetail(Map searchMap);
	//查询预算审批任务
	public List<Taskdetail> searchBudgetTaskdetail(Map searchMap);
	
}
