package com.topcheer.model.developerKeyMag;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Developerkeymag {
	
		//
		
		private    String   keyid ;
		//
		
		private    String   keyno ;
		//
		
		private    String   servicetype ;
		//
		
		private    String   providerid ;
		//
		
		private    String   providername ;
		//
		
		private    String   keystatus ;
		//
		
		private    String   applicationid ;
		//
		
		private    String   applicationname ;
		//
		
		private    String   status ;
    
    
	public String getKeyid() {
		return keyid;
	}
	
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	
    
	public String getKeyno() {
		return keyno;
	}
	
	public void setKeyno(String keyno) {
		this.keyno = keyno;
	}
	
    
	public String getServicetype() {
		return servicetype;
	}
	
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
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
	
    
	public String getKeystatus() {
		return keystatus;
	}
	
	public void setKeystatus(String keystatus) {
		this.keystatus = keystatus;
	}
	
    
	public String getApplicationid() {
		return applicationid;
	}
	
	public void setApplicationcode(String applicationid) {
		this.applicationid = applicationid;
	}
	
    
	public String getApplicationname() {
		return applicationname;
	}
	
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
