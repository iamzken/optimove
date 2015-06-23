package com.topcheer.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



public class GroupInfo {
	
		//角色ID
		@Size(max=20,message="最大长度20")
		private    String   grpId ;
		//角色中文名
		@Size(max=90,message="最大长度90")
		private    String   grpCname ;
		//角色英文名
		@Size(max=20,message="最大长度20")
		private    String   grpEname ;
		//角色级别(0-总行级，1-支行级，3-网点级)
		@Size(max=1,message="最大长度1")
		private    String   grpLevel ;
		//创建人
		
		private    Integer   cretaedBy ;
		//创建日期
		@DateTimeFormat(pattern="yyyyMMdd") 
		private    Date   creationDate ;
		//最后修改人
		
		private    Integer   lastUpdatedBy ;
		//最后修改日期
		
		private    Date   lastUpdateDate ;
		//备注
		@Size(max=60,message="最大长度60")
		private    String   grpRemark ;
    
    
	public String getGrpId() {
		return grpId;
	}
	
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	
    
	public String getGrpCname() {
		return grpCname;
	}
	
	public void setGrpCname(String grpCname) {
		this.grpCname = grpCname;
	}
	
    
	public String getGrpEname() {
		return grpEname;
	}
	
	public void setGrpEname(String grpEname) {
		this.grpEname = grpEname;
	}
	
    
	public String getGrpLevel() {
		return grpLevel;
	}
	
	public void setGrpLevel(String grpLevel) {
		this.grpLevel = grpLevel;
	}
	
    
	public Integer getCretaedBy() {
		return cretaedBy;
	}
	
	public void setCretaedBy(Integer cretaedBy) {
		this.cretaedBy = cretaedBy;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
    
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
    
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
    
	public String getGrpRemark() {
		return grpRemark;
	}
	
	public void setGrpRemark(String grpRemark) {
		this.grpRemark = grpRemark;
	}
	
	
}
