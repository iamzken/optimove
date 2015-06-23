package com.topcheer.dao.deptMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.deptMgr.Department;


public interface DepartmentMapper {
	
	public List<Department> searchDepartment(Map searchMap);
	
	public List<Department> searchAll();
	
	public List<Department> getDepartment(String departmentcode);
	
	public void insert(Department department);
	
	public void update(Department department);
	
	public void delete(String departmentcode);
	
}
