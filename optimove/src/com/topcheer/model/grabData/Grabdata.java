package com.topcheer.model.grabData;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Grabdata {
	
		//
		
		private    String   grabdataid ;
		//
		
		private    String   grabdataname ;
		//
		
		private    String   grabdatasource ;
		//
		
		private    String   grabdatatype ;
		//
		
		private    String   remarks ;
		//
		
		private    String   grabdatafile ;
    
    
	public String getGrabdataid() {
		return grabdataid;
	}
	
	public void setGrabdataid(String grabdataid) {
		this.grabdataid = grabdataid;
	}
	
    
	public String getGrabdataname() {
		return grabdataname;
	}
	
	public void setGrabdataname(String grabdataname) {
		this.grabdataname = grabdataname;
	}
	
    
	public String getGrabdatasource() {
		return grabdatasource;
	}
	
	public void setGrabdatasource(String grabdatasource) {
		this.grabdatasource = grabdatasource;
	}
	
    
	public String getGrabdatatype() {
		return grabdatatype;
	}
	
	public void setGrabdatatype(String grabdatatype) {
		this.grabdatatype = grabdatatype;
	}
	
    
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    
	public String getGrabdatafile() {
		return grabdatafile;
	}
	
	public void setGrabdatafile(String grabdatafile) {
		this.grabdatafile = grabdatafile;
	}
	
	
}
