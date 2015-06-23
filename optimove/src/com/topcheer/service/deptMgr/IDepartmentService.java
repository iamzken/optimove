package com.topcheer.service.deptMgr;

import java.util.List;
import java.util.Map;

import com.topcheer.model.deptMgr.Department;

public interface IDepartmentService {
	
	public List<Department> searchDepartment(Map searchMap);
	
	public List<Department> getDepartment(String departmentcode);
	
	public List<Department> searchAll();
	
	public void insert(Department department);
	
	public void update(Department department);
	
	public void delete(String id);

}
