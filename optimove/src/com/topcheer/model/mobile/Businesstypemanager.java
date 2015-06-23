package com.topcheer.model.mobile;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Businesstypemanager {
	
		//
		
		private    String   typecode ;
		//
		
		private    String   typename ;
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
		//
		
		private    String   logourl ;
    
    
	public String getTypecode() {
		return typecode;
	}
	
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	
    
	public String getTypename() {
		return typename;
	}
	
	public void setTypename(String typename) {
		this.typename = typename;
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
	
    
	public String getLogourl() {
		return logourl;
	}
	
	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}
	
	
}
