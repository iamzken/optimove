package com.topcheer.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.StyProMapper;
import com.topcheer.dao.UserMapper;
import com.topcheer.model.GroupInfo;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.StyPro;
import com.topcheer.model.StyProDetail;
import com.topcheer.model.User;
import com.topcheer.service.IStyProService;
import com.topcheer.service.IUserService;

@Service("styProService")
public class StyProServiceImpl implements IStyProService {

	@Autowired
	private StyProMapper styProMapper;

	public List<StyProDetail> searchStyProDetail(StyPro styPro) {
		return styProMapper.searchStyProDetail(styPro);
	}

	public void publish(StyPro styPro) {
		styProMapper.publish(styPro);
	}

	public void update(StyPro styPro, StyProDetail styProDetail) {
		StyPro pro = styProMapper.getStyPro(styPro.getId());
		pro.setName(styPro.getName());
		pro.setType(styPro.getType());
		pro.setDescs(styPro.getDescs());
		pro.setCode(styPro.getCode());
		
		styProMapper.update(pro);
		
		// 角色ID或用户ID
		String[] tempIds = styProDetail.getTempIds();
		// 机构ID
		String[] tempBranchIds = styProDetail.getTempBranchIds();
		// 部门ID
		String[] tempDeptIds = styProDetail.getTempDeptIds();
		// 排序
		String[] tempNodeNos = styProDetail.getTempNodeNos();
		// 节点类型
		String[] tempNodeTypes = styProDetail.getTempNodeTypes();
		// 操作权限
		String[] tempNodeOperations = styProDetail.getTempNodeOperations();
		// 本节点
		String[] tempNodeFroms = styProDetail.getTempNodeFroms();
		// 下一节点
		String[] tempNodeTos = styProDetail.getTempNodeTos();
		// 节点标识
		String[] tempCodes = styProDetail.getTempCodes();

		List<StyProDetail> proDetailList = styProMapper.searchStyProDetail(styPro);
		for (int i = 0; i < proDetailList.size(); i++) {
			StyProDetail po = proDetailList.get(i);

//			if (tempDeptIds.length > 0 && !"".equals(tempDeptIds[i])
//					&& tempDeptIds[i] != null) {
//				po.setUserId(tempIds[i]);
//				po.setDeptId(tempDeptIds[i]);
//				po.setBranchId(tempBranchIds[i]);
//			} else {
//				po.setGroupId(tempIds[i]);
//			}

//			po.setProdId(String.valueOf(new Random().nextInt()));
			po.setNodeNo(tempNodeNos[i]);
			po.setNodeType(tempNodeTypes[i]);
//			po.setProId(styPro.getId());
			po.setNodeOperation(tempNodeOperations[i]);

			if ("1".equals(styProDetail.getSource())) {
				po.setNodeFrom(tempNodeFroms[i]);
				po.setNodeTo(tempNodeTos[i]);
				po.setCode(tempCodes[i]);
			}

			styProMapper.updateStyProDetail(po);
		}
	}

	public void insert(StyPro styPro, StyProDetail styProDetail) {
		styPro.setStatus("0");
		styProMapper.insert(styPro);

		// 角色ID或用户ID
		String[] tempIds = styProDetail.getTempIds();
		// 机构ID
		String[] tempBranchIds = styProDetail.getTempBranchIds();
		// 部门ID
		String[] tempDeptIds = styProDetail.getTempDeptIds();
		// 排序
		String[] tempNodeNos = styProDetail.getTempNodeNos();
		// 节点类型
		String[] tempNodeTypes = styProDetail.getTempNodeTypes();
		// 操作权限
		String[] tempNodeOperations = styProDetail.getTempNodeOperations();
		// 本节点
		String[] tempNodeFroms = styProDetail.getTempNodeFroms();
		// 下一节点
		String[] tempNodeTos = styProDetail.getTempNodeTos();
		// 节点标识
		String[] tempCodes = styProDetail.getTempCodes();

		for (int i = 0; i < tempIds.length; i++) {
			StyProDetail po = new StyProDetail();
		
			if (tempDeptIds.length > 0 && !"".equals(tempDeptIds[i])
					&& tempDeptIds[i] != null) {
				if ("task".equals(tempNodeTypes[i])){
					if ("rect2".equals(tempNodeFroms[i])){
						po.setUserId("66666666");
						po.setDeptId("4310");
					} else {
						po.setUserId("88888888");
						po.setDeptId("4311");
					}
				} else {
					po.setUserId(tempIds[i]);
					po.setDeptId(tempDeptIds[i]);
				}
				
				po.setBranchId(tempBranchIds[i]);
			} else {
				po.setGroupId(tempIds[i]);
			}

			po.setProdId(String.valueOf(new Random().nextInt()));
			po.setNodeNo(tempNodeNos[i]);
			po.setNodeType(tempNodeTypes[i]);
			po.setProId(styPro.getId());
			po.setNodeOperation(tempNodeOperations[i]);

			if ("1".equals(styProDetail.getSource())) {
				po.setNodeFrom(tempNodeFroms[i]);
				po.setNodeTo(tempNodeTos[i]);
				po.setCode(tempCodes[i]);
			}

			styProMapper.insertStyProDetail(po);
		}
	}

	public void delete(String proId) {
		styProMapper.delete(proId);
	}

	public StyPro getStyPro(String proId) {
		return styProMapper.getStyPro(proId);
	}

	public List<StyPro> searchAll() {
		return styProMapper.searchAll();
	}

	public List<StyPro> searchStyPro(Map searchMap) {
		return styProMapper.searchStyPro(searchMap);
	}
}