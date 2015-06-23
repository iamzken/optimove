package com.topcheer.model.mapModelInfo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Mapmodelinfo {
	
		//
		
		private    String   modelid ;
		//
		
		private    String   modelcode ;
		//
		
		private    String   modelname ;
		//
		
		private    String   modeltype ;
		//
		
		private    String   modeldatatable ;
		//
		
		private    String   modelcreatetime ;
		//
		
		private    String   modelupdatetime ;
    
	public String getModelid() {
		return modelid;
	}
	
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	
    
	public String getModelcode() {
		return modelcode;
	}
	
	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}
	
    
	public String getModelname() {
		return modelname;
	}
	
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	
    
	public String getModeltype() {
		return modeltype;
	}
	
	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}
	
    
	public String getModeldatatable() {
		return modeldatatable;
	}
	
	public void setModeldatatable(String modeldatatable) {
		this.modeldatatable = modeldatatable;
	}
	
    
	public String getModelcreatetime() {
		return modelcreatetime;
	}
	
	public void setModelcreatetime(String modelcreatetime) {
		this.modelcreatetime = modelcreatetime;
	}
	
    
	public String getModelupdatetime() {
		return modelupdatetime;
	}
	
	public void setModelupdatetime(String modelupdatetime) {
		this.modelupdatetime = modelupdatetime;
	}

}
