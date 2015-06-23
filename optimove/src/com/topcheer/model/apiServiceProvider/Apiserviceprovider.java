package com.topcheer.model.apiServiceProvider;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Apiserviceprovider {
	
		//
		
		private    String   apicode ;
		//
		
		private    String   apiname ;
		//
		
		private    String   apitype ;
		//
		
		private    String   apiversion ;
		//
		
		private    String   operatetime ;
		//
		
		private    String   status ;
		//
		
		private    String   providerid ;
		//
		
		private    String   providername ;
    
    
	public String getApicode() {
		return apicode;
	}
	
	public void setApicode(String apicode) {
		this.apicode = apicode;
	}
	
    
	public String getApiname() {
		return apiname;
	}
	
	public void setApiname(String apiname) {
		this.apiname = apiname;
	}
	
    
	public String getApitype() {
		return apitype;
	}
	
	public void setApitype(String apitype) {
		this.apitype = apitype;
	}
	
    
	public String getApiversion() {
		return apiversion;
	}
	
	public void setApiversion(String apiversion) {
		this.apiversion = apiversion;
	}
	
    
	public String getOperatetime() {
		return operatetime;
	}
	
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    
	public String getProviderid() {
		return providerid;
	}
	
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	
    
	public String getProvidername() {
		return providername;
	}
	
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	
	
}
