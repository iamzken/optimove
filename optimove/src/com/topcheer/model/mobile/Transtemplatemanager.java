package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Transtemplatemanager {
	
		//
		
		private    String   templatecode ;
		//
		
		private    String   templatename ;
		//
		
		private    String   templateobject ;
		//
		
		private    String   linkurl ;
		//
		
		private    String   usechannel ;
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
	
    
	public String getTemplateobject() {
		return templateobject;
	}
	
	public void setTemplateobject(String templateobject) {
		this.templateobject = templateobject;
	}
	
    
	public String getLinkurl() {
		return linkurl;
	}
	
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	
    
	public String getUsechannel() {
		return usechannel;
	}
	
	public void setUsechannel(String usechannel) {
		this.usechannel = usechannel;
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
