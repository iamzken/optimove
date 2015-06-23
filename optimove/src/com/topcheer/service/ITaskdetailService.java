package com.topcheer.service;

import java.util.List;
import java.util.Map;

import com.topcheer.model.Taskdetail;

public interface ITaskdetailService {
	
	public List<Taskdetail> searchTaskdetail(Map searchMap);
	
	public List<Taskdetail> getTaskdetail(String taskid);
	
	public List<Taskdetail> searchAll();
	
	public void insert(Taskdetail taskdetail);
	
	public void update(Taskdetail taskdetail);
	
	public void delete(String id);
	
	//��ѯԤ�㷽����������
	public List<Taskdetail> searchBudgetSchemeTaskdetail(Map searchMap);
	//��ѯԤ����������
	public List<Taskdetail> searchBudgetTaskdetail(Map searchMap);
}
