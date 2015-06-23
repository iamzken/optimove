package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Flowmanager {
	
		//
		
		private    String   flowcode ;
		//
		
		private    String   flowname ;
		//
		
		private    String   remark ;
		//
		
		private    String   operator ;
		//
		
		private    String   operatororg ;
		//
		
		private    String   updatedate ;
		//
		
		private    String   updatetime ;
    
    
	public String getFlowcode() {
		return flowcode;
	}
	
	public void setFlowcode(String flowcode) {
		this.flowcode = flowcode;
	}
	
    
	public String getFlowname() {
		return flowname;
	}
	
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}
	
    
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
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
