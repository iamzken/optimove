package com.topcheer.model;

import javax.validation.constraints.Size;



public class UserGroup {
	
		//�û�Ա����
		@Size(max=8,message="��󳤶�8")
		private    String   workId ;
		//��ɫ��ʶ
		@Size(max=20,message="��󳤶�20")
		private    String   grpId ;
		//����Ա��
		@Size(max=8,message="��󳤶�8")
		private    String   operatorCode ;
		//������
		
		private    Integer   cretaedBy ;
		//��������
		
		private    String   creationDate ;
		//����޸���
		
		private    Integer   lastUpdatedBy ;
		//����޸�����
		
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
