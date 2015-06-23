package com.topcheer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topcheer.model.GroupInfo;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.StyPro;
import com.topcheer.model.StyProDetail;
import com.topcheer.model.User;

public interface StyProMapper {

	public List<StyPro> searchStyPro(Map searchMap);

	public List<StyPro> searchAll();

	public StyPro getStyPro(String proId);

	public void insert(StyPro styPro);
	
	public void insertStyProDetail(StyProDetail styProDetail);

	public void update(StyPro styPro);
	
	public void updateStyProDetail(StyProDetail styProDetail);

	public void delete(String proId);
	
	public void publish(StyPro styPro);
	
	public List<StyProDetail> searchStyProDetail(StyPro styPro);
	
	/**
	 * 
	 * ���ݽڵ����ͻ�ȡ��ʼ���߽����ڵ���Ϣ
	 * @param proId
	 * @param nodeType
	 * @return
	 */
	public List<StyProDetail> searchDetailByNode(@Param("proId") String proId,@Param("nodeType") String nodeType);
	
	/**
	 * ��ȡ��һ���ڵ���Ϣ ��ʱ�����Ƕ�ڵ�
	 * 
	 * @param proId
	 * @param prodId
	 * @return
	 */
	public List<StyProDetail> getNexeNode(@Param("proId") String proId,@Param("prodId") String prodId);
	
	public List<StyProDetail>  getCurrentNode(@Param("prodId") String prodId);
}