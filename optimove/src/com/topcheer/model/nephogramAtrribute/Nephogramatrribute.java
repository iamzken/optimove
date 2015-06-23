package com.topcheer.model.nephogramAtrribute;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Nephogramatrribute {
	
		//
		
		private    String   nephogramattrid ;
		//
		
		private    String   nephogrammodelid ;
		//
		
		private    String   attributecode ;
		//
		
		private    String   attributename ;
		//
		
		private    String   attrtype ;
		//
		
		private    String   remarks ;
    
    
	public String getNephogramattrid() {
		return nephogramattrid;
	}
	
	public void setNephogramattrid(String nephogramattrid) {
		this.nephogramattrid = nephogramattrid;
	}
	
    
	public String getNephogrammodelid() {
		return nephogrammodelid;
	}
	
	public void setNephogrammodelid(String nephogrammodelid) {
		this.nephogrammodelid = nephogrammodelid;
	}
	
    
	public String getAttributecode() {
		return attributecode;
	}
	
	public void setAttributecode(String attributecode) {
		this.attributecode = attributecode;
	}
	
    
	public String getAttributename() {
		return attributename;
	}
	
	public void setAttributename(String attributename) {
		this.attributename = attributename;
	}
	
    
	public String getAttrtype() {
		return attrtype;
	}
	
	public void setAttrtype(String attrtype) {
		this.attrtype = attrtype;
	}
	
    
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
