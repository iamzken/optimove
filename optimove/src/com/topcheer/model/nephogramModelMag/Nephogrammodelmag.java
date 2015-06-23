package com.topcheer.model.nephogramModelMag;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Nephogrammodelmag {
	
		//
		
		private    String   nephogrammodelid ;
		//
		
		private    String   nephogrammodelname ;
		//
		//±¸×¢ÐÅÏ¢
		private    String   tablemodelname ;
    
    
	public String getNephogrammodelid() {
		return nephogrammodelid;
	}
	
	public void setNephogrammodelid(String nephogrammodelid) {
		this.nephogrammodelid = nephogrammodelid;
	}
	
    
	public String getNephogrammodelname() {
		return nephogrammodelname;
	}
	
	public void setNephogrammodelname(String nephogrammodelname) {
		this.nephogrammodelname = nephogrammodelname;
	}
	
    
	public String getTablemodelname() {
		return tablemodelname;
	}
	
	public void setTablemodelname(String tablemodelname) {
		this.tablemodelname = tablemodelname;
	}
	
	
}
