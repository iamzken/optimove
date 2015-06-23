package com.topcheer.model.nephogramData;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Nephogramdata {
	
		//
		
		private    String   nephogramdataid ;
		//
		
		private    String   nephogrammodelid ;
		//
		
		private    String   nephogramattrid ;
		//
		
		private    String   attrvalue ;
		//
		
		private    String   dataremarks ;
    
    
	public String getNephogramdataid() {
		return nephogramdataid;
	}
	
	public void setNephogramdataid(String nephogramdataid) {
		this.nephogramdataid = nephogramdataid;
	}
	
    
	public String getNephogrammodelid() {
		return nephogrammodelid;
	}
	
	public void setNephogrammodelid(String nephogrammodelid) {
		this.nephogrammodelid = nephogrammodelid;
	}
	
    
	public String getNephogramattrid() {
		return nephogramattrid;
	}
	
	public void setNephogramattrid(String nephogramattrid) {
		this.nephogramattrid = nephogramattrid;
	}
	
    
	public String getAttrvalue() {
		return attrvalue;
	}
	
	public void setAttrvalue(String attrvalue) {
		this.attrvalue = attrvalue;
	}

	public String getDataremarks() {
		return dataremarks;
	}

	public void setDataremarks(String dataremarks) {
		this.dataremarks = dataremarks;
	}
	
    
	
	
}
