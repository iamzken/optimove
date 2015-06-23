package com.topcheer.model;

import javax.validation.constraints.Size;



public class UserGroup {
	
		//用户员工号
		@Size(max=8,message="最大长度8")
		private    String   workId ;
		//角色标识
		@Size(max=20,message="最大长度20")
		private    String   grpId ;
		//操作员工
		@Size(max=8,message="最大长度8")
		private    String   operatorCode ;
		//创建人
		
		private    Integer   cretaedBy ;
		//创建日期
		
		private    String   creationDate ;
		//最后修改人
		
		private    Integer   lastUpdatedBy ;
		//最后修改日期
		
		private    String   lastUpdateDate ;
    
    
	public String getWorkId() {
		return workId;
	}
	
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
    
	public String getGrpId() {
		return grpId;
	}
	
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	
    
	public String getOperatorCode() {
		return operatorCode;
	}
	
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
    
	public Integer getCretaedBy() {
		return cretaedBy;
	}
	
	public void setCretaedBy(Integer cretaedBy) {
		this.cretaedBy = cretaedBy;
	}
	
    
	
    
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
    
	
	
}
