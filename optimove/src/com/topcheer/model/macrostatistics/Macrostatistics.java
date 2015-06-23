package com.topcheer.model.macrostatistics;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Macrostatistics {
	
		//
		
		private    String   macrostatisticsid ;
		//
		
		private    String   macrostatisticsname ;
		//
		
		private    String   macrodatasource ;
		//
		
		private    String   datatype ;
		//
		
		private    String   remarks ;
		//
		
		private    String   macrodatafile ;
    
    
	public String getMacrostatisticsid() {
		return macrostatisticsid;
	}
	
	public void setMacrostatisticsid(String macrostatisticsid) {
		this.macrostatisticsid = macrostatisticsid;
	}
	
    
	public String getMacrostatisticsname() {
		return macrostatisticsname;
	}
	
	public void setMacrostatisticsname(String macrostatisticsname) {
		this.macrostatisticsname = macrostatisticsname;
	}
	
    
	public String getMacrodatasource() {
		return macrodatasource;
	}
	
	public void setMacrodatasource(String macrodatasource) {
		this.macrodatasource = macrodatasource;
	}
	
    
	public String getDatatype() {
		return datatype;
	}
	
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
    
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
    
	public String getMacrodatafile() {
		return macrodatafile;
	}
	
	public void setMacrodatafile(String macrodatafile) {
		this.macrodatafile = macrodatafile;
	}
	
	
}
