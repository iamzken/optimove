package com.topcheer.model;

import javax.validation.constraints.Size;



public class UserLoginMgr {
	
		//Ա����
		@Size(max=8,message="��󳤶�8")
		private    String   loginId ;
		//������
		@Size(max=8,message="��󳤶�8")
		private    String   userBankCode ;
		//��½����
		@Size(max=8,message="��󳤶�8")
		private    String   loginInDate ;
		//ע������
		@Size(max=8,message="��󳤶�8")
		private    String   loginOutDate ;
    
    
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
    
	public String getUserBankCode() {
		return userBankCode;
	}
	
	public void setUserBankCode(String userBankCode) {
		this.userBankCode = userBankCode;
	}
	
    
	public String getLoginInDate() {
		return loginInDate;
	}
	
	public void setLoginInDate(String loginInDate) {
		this.loginInDate = loginInDate;
	}
	
    
	public String getLoginOutDate() {
		return loginOutDate;
	}
	
	public void setLoginOutDate(String loginOutDate) {
		this.loginOutDate = loginOutDate;
	}
	
	
}
