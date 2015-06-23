package com.topcheer.model.applicationMag;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Applicationmag {
	
		//
		
		private    String   applicationid ;
		//
		
		private    String   applicationname ;
		//
		
		private    String   applicationdes ;
		//
		
		private    String   status ;
    
    
	public String getApplicationid() {
		return applicationid;
	}
	
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	
    
	public String getApplicationname() {
		return applicationname;
	}
	
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	
    
	public String getApplicationdes() {
		return applicationdes;
	}
	
	public void setApplicationdes(String applicationdes) {
		this.applicationdes = applicationdes;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
