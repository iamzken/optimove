package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Recognitemplatemanager {
	
		//
		
		private    String   templatecode ;
		//
		
		private    String   templatename ;
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
    
    
	public String getTemplatecode() {
		return templatecode;
	}
	
	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}
	
    
	public String getTemplatename() {
		return templatename;
	}
	
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
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
