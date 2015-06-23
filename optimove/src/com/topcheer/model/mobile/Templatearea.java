package com.topcheer.model.mobile;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Templatearea {
	
		//
		
		private    String   id ;
		//
		
		private    String   templatecode ;
		//
		
		private    String   areacode ;
		//
		
		private    String   areaname ;
		//
		
		private    Integer   areaorder ;
		//
		
		private    String   operator ;
		//
		
		private    String   operatororg ;
		//
		
		private    String   updatedate ;
		//
		
		private    String   updatetime ;
    
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
    
	public String getTemplatecode() {
		return templatecode;
	}
	
	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}
	
    
	public String getAreacode() {
		return areacode;
	}
	
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	
    
	public String getAreaname() {
		return areaname;
	}
	
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	
    
	public Integer getAreaorder() {
		return areaorder;
	}
	
	public void setAreaorder(Integer areaorder) {
		this.areaorder = areaorder;
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
