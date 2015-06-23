package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Flowdetailmanager {
	
		//
		
		private    String   flowcode ;
		//
		
		private    String   servicecode ;
		//
		
		private    Integer   floworder ;
		//
		
		private    String   processflag ;
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
	
    
	public String getServicecode() {
		return servicecode;
	}
	
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	
    
	public Integer getFloworder() {
		return floworder;
	}
	
	public void setFloworder(Integer floworder) {
		this.floworder = floworder;
	}
	
    
	public String getProcessflag() {
		return processflag;
	}
	
	public void setProcessflag(String processflag) {
		this.processflag = processflag;
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
