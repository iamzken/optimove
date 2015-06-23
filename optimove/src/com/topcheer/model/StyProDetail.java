package com.topcheer.model;

import javax.validation.constraints.Size;

public class StyProDetail {

	@Size(max = 20, message = "最大长度20")
	private String prodId;

	@Size(max = 20, message = "最大长度20")
	private String nodeNo;

	@Size(max = 20, message = "最大长度20")
	private String nodeType;

	@Size(max = 20, message = "最大长度20")
	private String nodeOperation;

	@Size(max = 20, message = "最大长度20")
	private String groupId;

	@Size(max = 20, message = "最大长度20")
	private String branchId;

	@Size(max = 20, message = "最大长度20")
	private String deptId;

	@Size(max = 20, message = "最大长度20")
	private String userId;

	@Size(max = 20, message = "最大长度20")
	private String proId;
	
	@Size(max = 20, message = "最大长度20")
	private String nodeFrom;
	
	@Size(max = 20, message = "最大长度20")
	private String nodeTo;

	@Size(max = 20, message = "最大长度20")
	private String code;
	
	private String source;
	
	private String[] tempIds;

	private String[] tempBranchIds;

	private String[] tempDeptIds;

	private String[] tempNodeNos;

	private String[] tempNodeTypes;

	private String[] tempNodeOperations;
	
	private String[] tempNodeFroms;
	
	private String[] tempNodeTos;
	
	private String[] tempCodes;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeOperation() {
		return nodeOperation;
	}

	public void setNodeOperation(String nodeOperation) {
		this.nodeOperation = nodeOperation;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getNodeFrom() {
		return nodeFrom;
	}

	public void setNodeFrom(String nodeFrom) {
		this.nodeFrom = nodeFrom;
	}

	public String getNodeTo() {
		return nodeTo;
	}

	public void setNodeTo(String nodeTo) {
		this.nodeTo = nodeTo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String[] getTempIds() {
		return tempIds;
	}

	public void setTempIds(String[] tempIds) {
		this.tempIds = tempIds;
	}

	public String[] getTempBranchIds() {
		return tempBranchIds;
	}

	public void setTempBranchIds(String[] tempBranchIds) {
		this.tempBranchIds = tempBranchIds;
	}

	public String[] getTempDeptIds() {
		return tempDeptIds;
	}

	public void setTempDeptIds(String[] tempDeptIds) {
		this.tempDeptIds = tempDeptIds;
	}

	public String[] getTempNodeNos() {
		return tempNodeNos;
	}

	public void setTempNodeNos(String[] tempNodeNos) {
		this.tempNodeNos = tempNodeNos;
	}

	public String[] getTempNodeTypes() {
		return tempNodeTypes;
	}

	public void setTempNodeTypes(String[] tempNodeTypes) {
		this.tempNodeTypes = tempNodeTypes;
	}

	public String[] getTempNodeOperations() {
		return tempNodeOperations;
	}

	public void setTempNodeOperations(String[] tempNodeOperations) {
		this.tempNodeOperations = tempNodeOperations;
	}

	public String[] getTempNodeFroms() {
		return tempNodeFroms;
	}

	public void setTempNodeFroms(String[] tempNodeFroms) {
		this.tempNodeFroms = tempNodeFroms;
	}

	public String[] getTempNodeTos() {
		return tempNodeTos;
	}

	public void setTempNodeTos(String[] tempNodeTos) {
		this.tempNodeTos = tempNodeTos;
	}

	public String[] getTempCodes() {
		return tempCodes;
	}

	public void setTempCodes(String[] tempCodes) {
		this.tempCodes = tempCodes;
	}
}