package com.topcheer.model.deptMgr;


import org.hibernate.validator.constraints.NotEmpty;

public class Department {
	
		//
		@NotEmpty(message="���ű�Ų���Ϊ�գ���󳤶�Ϊ8")
		private    String   departmentcode ;
		//
		@NotEmpty(message="�������Ʋ���Ϊ�գ���󳤶�Ϊ8")
		private    String   departmentname ;
		//
		
		private    String   deptdescription ;
		//
		
		private    String   remarks ;
		//
		
		private    String   operatorbankcode ;
		//
		
		private    String   operatorcode ;
		//
		
		private    String   updatedate ;
		//
		
		private    String   updatetime ;
    
    
	public String getDepartmentcode() {
		return departmentcode;
	}
	
	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	
    
	public String getDepartmentname() {
		return departmentname;
	}
	
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	
    
	public String getDeptdescription() {
		return deptdescription;
	}
	
	public void setDeptdescription(String deptdescription) {
		this.deptdescription = deptdescription;
	}
	
    
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    
	public String getOperatorbankcode() {
		return operatorbankcode;
	}
	
	public void setOperatorbankcode(String operatorbankcode) {
		this.operatorbankcode = operatorbankcode;
	}
	
    
	public String getOperatorcode() {
		return operatorcode;
	}
	
	public void setOperatorcode(String operatorcode) {
		this.operatorcode = operatorcode;
	}
	
    
	public String getUpdatedate() {
		return updatedate;
	}
	
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
    
	public String getUpdatetime() {
		return updatetime;
	}
	
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
