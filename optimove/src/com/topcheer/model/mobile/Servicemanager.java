package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Servicemanager {
	
		//
		
		private    String   servicecode ;
		//
		
		private    String   servicecnname ;
		//
		
		private    String   sourcesystem ;
		//
		
		private    String   linkurl ;
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
    
    
	public String getServicecode() {
		return servicecode;
	}
	
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	
    
	public String getServicecnname() {
		return servicecnname;
	}
	
	public void setServicecnname(String servicecnname) {
		this.servicecnname = servicecnname;
	}
	
    
	public String getSourcesystem() {
		return sourcesystem;
	}
	
	public void setSourcesystem(String sourcesystem) {
		this.sourcesystem = sourcesystem;
	}
	
    
	public String getLinkurl() {
		return linkurl;
	}
	
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
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
