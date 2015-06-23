package com.topcheer.model;

import javax.validation.constraints.Size;



public class GroupRight {
	
		//��ɫID
		@Size(max=20,message="��󳤶�20")
		private    String   grpId ;
		//�˵�����
		@Size(max=12,message="��󳤶�12")
		private    String   menuCode ;
		//������
		
		private    Integer   cretaedBy ;
		//��������
		
		private    String   creationDate ;
		//����޸���
		
		private    Integer   lastUpdatedBy ;
		//����޸�����
		
		private    String   lastUpdateDate ;
		
		
    
    
	public GroupRight() {
			super();
	}

	
	
	public GroupRight(String grpId, String menuCode) {
		super();
		this.grpId = grpId;
		this.menuCode = menuCode;
	}



	public String getGrpId() {
		return grpId;
	}
	
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	
    
	public String getMenuCode() {
		return menuCode;
	}
	
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
