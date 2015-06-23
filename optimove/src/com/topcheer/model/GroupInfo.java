package com.topcheer.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



public class GroupInfo {
	
		//��ɫID
		@Size(max=20,message="��󳤶�20")
		private    String   grpId ;
		//��ɫ������
		@Size(max=90,message="��󳤶�90")
		private    String   grpCname ;
		//��ɫӢ����
		@Size(max=20,message="��󳤶�20")
		private    String   grpEname ;
		//��ɫ����(0-���м���1-֧�м���3-���㼶)
		@Size(max=1,message="��󳤶�1")
		private    String   grpLevel ;
		//������
		
		private    Integer   cretaedBy ;
		//��������
		@DateTimeFormat(pattern="yyyyMMdd") 
		private    Date   creationDate ;
		//����޸���
		
		private    Integer   lastUpdatedBy ;
		//����޸�����
		
		private    Date   lastUpdateDate ;
		//��ע
		@Size(max=60,message="��󳤶�60")
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
