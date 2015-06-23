package com.topcheer.service.deptMgr.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.deptMgr.DepartmentMapper;
import com.topcheer.model.deptMgr.Department;
import com.topcheer.service.deptMgr.IDepartmentService;

@Service("tbldepartmentService")
public class DepartmentServiceImpl implements IDepartmentService{
	
	@Autowired
	private DepartmentMapper departmentMapper;

	public void delete(String id) {
		departmentMapper.delete(id);
		
	}

	public List<Department> getDepartment(String departmentcode) {
		return departmentMapper.getDepartment(departmentcode);
	}

	public void insert(Department Department) {
		 departmentMapper.insert(Department);
	}

	public List<Department> searchAll() {
		return departmentMapper.searchAll();
	}

	public List<Department> searchDepartment(Map searchMap) {
		return departmentMapper.searchDepartment(searchMap);
	}

	public void update(Department department) {
		departmentMapper.update(department);
	}

	public DepartmentMapper getDepartmentMapper() {
		return departmentMapper;
	}

	public void setDepartmentMapper(DepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}
	
	
	

}
