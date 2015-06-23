package com.topcheer.model;

import java.math.BigDecimal;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



public class User {
	@Size(max=8,message="员工号不能为空，最大长度为8")
	@NotEmpty(message="员工号不能为空，最大长度为8")
	private    String   workId ;
	private    String   userLoginName ;
	private    String   userBankCode ;
	private    String   userDept ;
	private    String   userName ;
	private    String   userPwd ;
	private    String   userLevel ;
	private    String   userRemark ;
	private    String   userStatus ;
	private    String   operatorCode ;
	private    String   telephone ;
	private    BigDecimal   cretaedBy ;
	private    String   creationDate ;
	private    String   lastUpdatedBy ;
	private    String   lastUpdateDate ;
	
	
	
	private    String   groupId ;
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getUserBankCode() {
		return userBankCode;
	}
	public void setUserBankCode(String userBankCode) {
		this.userBankCode = userBankCode;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public BigDecimal getCretaedBy() {
		return cretaedBy;
	}
	public void setCretaedBy(BigDecimal cretaedBy) {
		this.cretaedBy = cretaedBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
