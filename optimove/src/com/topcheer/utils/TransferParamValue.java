package com.topcheer.utils;

/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：TransferParamValue.java
 * 
 * 描  述：
 * 
 * [变更履历]
 * Version   变更日         部署              作者           变更内容
 * -----------------------------------------------------------------------------
 * V1.00     Sep 27, 2014   topcheer   liuhs      create
 * -----------------------------------------------------------------------------
 */
public class TransferParamValue {
	/**
	 * 获取审核状态转义
	 * 
	 * @param currencyType
	 * @return
	 */
	public String getApprStatus(String ApprStatus) {

		// 如果为空则直接返回""
		if (null == ApprStatus) {
			return "";
		}

		// 过滤左右空格
		ApprStatus = ApprStatus.trim();

		if ("0".equals(ApprStatus)) {
			return "待审核";
		} else if ("1".equals(ApprStatus)) {
			return "审核通过";
		} else if ("2".equals(ApprStatus)) {
			return "审核不通过";
		} else {
			return "";
		}
	}

}
