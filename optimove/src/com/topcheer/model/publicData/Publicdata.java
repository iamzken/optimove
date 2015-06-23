package com.topcheer.model.publicData;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Publicdata {
	
		//
		
		private    String   publicdataid ;
		//
		
		private    String   publicdataname ;
		//
		
		private    String   publicdatasource ;
		//
		
		private    String   publicdatatype ;
		//
		
		private    String   remarks ;
		//
		
		private    String   publicdatafile ;
    
    
	public String getPublicdataid() {
		return publicdataid;
	}
	
	public void setPublicdataid(String publicdataid) {
		this.publicdataid = publicdataid;
	}
	
    
	public String getPublicdataname() {
		return publicdataname;
	}
	
	public void setPublicdataname(String publicdataname) {
		this.publicdataname = publicdataname;
	}
	
    
	public String getPublicdatasource() {
		return publicdatasource;
	}
	
	public void setPublicdatasource(String publicdatasource) {
		this.publicdatasource = publicdatasource;
	}
	
    
	public String getPublicdatatype() {
		return publicdatatype;
	}
	
	public void setPublicdatatype(String publicdatatype) {
		this.publicdatatype = publicdatatype;
	}
	
    
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    
	public String getPublicdatafile() {
		return publicdatafile;
	}
	
	public void setPublicdatafile(String publicdatafile) {
		this.publicdatafile = publicdatafile;
	}
	
	
}
