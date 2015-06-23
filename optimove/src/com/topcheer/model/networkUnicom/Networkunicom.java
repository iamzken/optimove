package com.topcheer.model.networkUnicom;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Networkunicom {
	
		//
		
		private    String   networkunicomid ;
		//
		
		private    String   ipaddress ;
		//
		
		private    String   port ;
		//
		
		private    String   status ;
		//
		
		private    String   applicationcode ;
		//
		
		private    String   applicationname ;
    
    
	public String getNetworkunicomid() {
		return networkunicomid;
	}
	
	public void setNetworkunicomid(String networkunicomid) {
		this.networkunicomid = networkunicomid;
	}
	
    
	public String getIpaddress() {
		return ipaddress;
	}
	
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
    
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    
	public String getApplicationcode() {
		return applicationcode;
	}
	
	public void setApplicationcode(String applicationcode) {
		this.applicationcode = applicationcode;
	}
	
    
	public String getApplicationname() {
		return applicationname;
	}
	
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	
	
}
