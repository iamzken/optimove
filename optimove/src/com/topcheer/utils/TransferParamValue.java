package com.topcheer.utils;

/*
 * ��  �ܣ���˵������Ĺ���
 * 
 * �ļ�����TransferParamValue.java
 * 
 * ��  ����
 * 
 * [�������]
 * Version   �����         ����              ����           �������
 * -----------------------------------------------------------------------------
 * V1.00     Sep 27, 2014   topcheer   liuhs      create
 * -----------------------------------------------------------------------------
 */
public class TransferParamValue {
	/**
	 * ��ȡ���״̬ת��
	 * 
	 * @param currencyType
	 * @return
	 */
	public String getApprStatus(String ApprStatus) {

		// ���Ϊ����ֱ�ӷ���""
		if (null == ApprStatus) {
			return "";
		}

		// �������ҿո�
		ApprStatus = ApprStatus.trim();

		if ("0".equals(ApprStatus)) {
			return "�����";
		} else if ("1".equals(ApprStatus)) {
			return "���ͨ��";
		} else if ("2".equals(ApprStatus)) {
			return "��˲�ͨ��";
		} else {
			return "";
		}
	}

}
