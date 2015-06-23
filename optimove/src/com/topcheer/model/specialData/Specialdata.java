package com.topcheer.model.specialData;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Specialdata {
	
		//
		
		private    String   specialdataid ;
		//
		
		private    String   specialdataname ;
		//
		
		private    String   specialdatasource ;
		//
		
		private    String   specialdatatype ;
		//
		
		private    String   remarks ;
		//
		
		private    String   specialdatafile ;
    
    
	public String getSpecialdataid() {
		return specialdataid;
	}
	
	public void setSpecialdataid(String specialdataid) {
		this.specialdataid = specialdataid;
	}
	
    
	public String getSpecialdataname() {
		return specialdataname;
	}
	
	public void setSpecialdataname(String specialdataname) {
		this.specialdataname = specialdataname;
	}
	
    
	public String getSpecialdatasource() {
		return specialdatasource;
	}
	
	public void setSpecialdatasource(String specialdatasource) {
		this.specialdatasource = specialdatasource;
	}
	
    
	public String getSpecialdatatype() {
		return specialdatatype;
	}
	
	public void setSpecialdatatype(String specialdatatype) {
		this.specialdatatype = specialdatatype;
	}
	
    
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    
	public String getSpecialdatafile() {
		return specialdatafile;
	}
	
	public void setSpecialdatafile(String specialdatafile) {
		this.specialdatafile = specialdatafile;
	}
	
	
}
