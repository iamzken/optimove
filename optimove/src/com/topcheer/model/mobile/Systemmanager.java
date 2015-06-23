package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Systemmanager {
	
		//
		
		private    String   syscode ;
		//
		
		private    String   sysname ;
		//
		
		private    String   sysdescription ;
		//
		
		private    String   accesstype ;
		//
		
		private    String   sysip ;
		//
		
		private    String   sysport ;
		//
		
		private    String   sysuser ;
		//
		
		private    String   syspwd ;
		//
		
		private    String   operator ;
		//
		
		private    String   operatororg ;
		//
		
		private    String   updatedate ;
		//
		
		private    String   updatetime ;
    
    
	public String getSyscode() {
		return syscode;
	}
	
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}
	
    
	public String getSysname() {
		return sysname;
	}
	
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	
    
	public String getSysdescription() {
		return sysdescription;
	}
	
	public void setSysdescription(String sysdescription) {
		this.sysdescription = sysdescription;
	}
	
    
	public String getAccesstype() {
		return accesstype;
	}
	
	public void setAccesstype(String accesstype) {
		this.accesstype = accesstype;
	}
	
    
	public String getSysip() {
		return sysip;
	}
	
	public void setSysip(String sysip) {
		this.sysip = sysip;
	}
	
    
	public String getSysport() {
		return sysport;
	}
	
	public void setSysport(String sysport) {
		this.sysport = sysport;
	}
	
    
	public String getSysuser() {
		return sysuser;
	}
	
	public void setSysuser(String sysuser) {
		this.sysuser = sysuser;
	}
	
    
	public String getSyspwd() {
		return syspwd;
	}
	
	public void setSyspwd(String syspwd) {
		this.syspwd = syspwd;
	}
	
    
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
    
	public String getOperatororg() {
		return operatororg;
	}
	
	public void setOperatororg(String operatororg) {
		this.operatororg = operatororg;
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
