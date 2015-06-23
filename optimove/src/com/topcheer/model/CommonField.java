package com.topcheer.model;

import java.io.Serializable;

public class CommonField implements Serializable {
	private static final long serialVersionUID = -8098015665410937428L;
	
	// 错误代码
	private String errorCode;
	// 错误信息
	private String errorMsg;
	// 错误代码+信息
	private String errMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
