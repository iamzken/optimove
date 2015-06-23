package com.topcheer.model;

import javax.validation.constraints.Size;



public class NewBranchInfo {
	
		//��֧�д���
		@Size(max=8,message="��󳤶�8")
		private    String   branchCode ;
		//��������(0 ����/1 ֧��/3 ����)
		@Size(max=1,message="��󳤶�1")
		private    String   grade ;
		//�ϼ��д���
		@Size(max=6,message="��󳤶�6")
		private    String   upCode ;
		//����
		@Size(max=120,message="��󳤶�120")
		private    String   name ;
		//��ַ
		@Size(max=60,message="��󳤶�60")
		private    String   address ;
		//�ʱ�
		@Size(max=6,message="��󳤶�6")
		private    String   zipcode ;
		//�绰����
		@Size(max=18,message="��󳤶�18")
		private    String   telephone ;
		//����
		@Size(max=18,message="��󳤶�18")
		private    String   fax ;
		//��Ӫ״̬
		@Size(max=1,message="��󳤶�1")
		private    String   status ;
		//���������������˺�
		@Size(max=19,message="��󳤶�19")
		private    String   account ;
		//��ϵ������
		@Size(max=60,message="��󳤶�60")
		private    String   linkManName ;
		//����
		@Size(max=60,message="��󳤶�60")
		private    String   linkManDept ;
		//ְ��
		@Size(max=60,message="��󳤶�60")
		private    String   linkManPos ;
		//��ϵ�˵绰
		@Size(max=20,message="��󳤶�20")
		private    String   linkManTel ;
		//��ϵ�˴���
		@Size(max=20,message="��󳤶�20")
		private    String   linkManFax ;
		//��ϵ��Email
		@Size(max=40,message="��󳤶�40")
		private    String   linkManEmail ;
		//��������
		@Size(max=8,message="��󳤶�8")
		private    String   createDate ;
		//����Ա��
		@Size(max=8,message="��󳤶�8")
		private    String   operatorCode ;
    
    
	public String getBranchCode() {
		return branchCode;
	}
	
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
    
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
    
	public String getUpCode() {
		return upCode;
	}
	
	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}
	
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
    
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
    
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
    
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
    
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
    
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
    
	public String getLinkManName() {
		return linkManName;
	}
	
	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}
	
    
	public String getLinkManDept() {
		return linkManDept;
	}
	
	public void setLinkManDept(String linkManDept) {
		this.linkManDept = linkManDept;
	}
	
    
	public String getLinkManPos() {
		return linkManPos;
	}
	
	public void setLinkManPos(String linkManPos) {
		this.linkManPos = linkManPos;
	}
	
    
	public String getLinkManTel() {
		return linkManTel;
	}
	
	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}
	
    
	public String getLinkManFax() {
		return linkManFax;
	}
	
	public void setLinkManFax(String linkManFax) {
		this.linkManFax = linkManFax;
	}
	
    
	public String getLinkManEmail() {
		return linkManEmail;
	}
	
	public void setLinkManEmail(String linkManEmail) {
		this.linkManEmail = linkManEmail;
	}
	
    
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
    
	public String getOperatorCode() {
		return operatorCode;
	}
	
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	
}
