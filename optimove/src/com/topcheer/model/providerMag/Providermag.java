package com.topcheer.model.providerMag;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Providermag {
	
		//
		
		private    String   providerid ;
		//
		
		private    String   providername ;
		//
		
		private    String   descriptioncontent ;
		//
		
		private    String   mobile ;
		//
		
		private    String   email ;
		//
		
		private    String   officialwebsite ;
    
    
	public String getProviderid() {
		return providerid;
	}
	
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	
    
	public String getProvidername() {
		return providername;
	}
	
	public void setProvidername(String providername) {
		this.providername = providername;
	}
	
    
	public String getDescriptioncontent() {
		return descriptioncontent;
	}
	
	public void setDescriptioncontent(String descriptioncontent) {
		this.descriptioncontent = descriptioncontent;
	}
	
    
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
    
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    
	public String getOfficialwebsite() {
		return officialwebsite;
	}
	
	public void setOfficialwebsite(String officialwebsite) {
		this.officialwebsite = officialwebsite;
	}
	
	
}
