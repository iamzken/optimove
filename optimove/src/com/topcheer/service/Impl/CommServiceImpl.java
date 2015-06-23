package com.topcheer.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topcheer.dao.CommMapper;
import com.topcheer.model.Constant;
import com.topcheer.model.GroupInfo;
import com.topcheer.model.Lookupvalues;
import com.topcheer.model.NewBranchInfo;
import com.topcheer.model.User;
import com.topcheer.service.ICommService;
import com.topcheer.service.IGroupInfoService;
import com.topcheer.service.ILookupvaluesService;
import com.topcheer.service.INewBranchInfoService;
import com.topcheer.utils.StringUtil;
@Service("commService")
public class CommServiceImpl implements ICommService{
	private static final Logger logger = Logger.getLogger(CommServiceImpl.class);
	
	public final static String BRANCH_OPTION = "branchOption";
	
	public final static String GROUP_OPTION = "groupOption";
	@Autowired
	private CommMapper commMapper;
	
	
	
	@Autowired
	private ILookupvaluesService lookupvaluesService;
	@Autowired
	private IGroupInfoService groupInfoService;
	@Autowired
	private INewBranchInfoService newBranchInfoService;
	
	
	
	public static boolean isEmptyList(List list){
		boolean result = false;
		if(list==null||list.size()==0){
			result = true;
		}
		return result;
	}
	
	
	public String getCheckAccFlagBySerialNo(String table,String serialNo){
		String result = commMapper.getCheckAccFlagBySerialNo(table, serialNo);
		return result;
	}
	
	public  Object setOperatorName(Object info) throws Exception{
		String operatorCode = BeanUtils.getProperty(info, "operatorCode");
		if(!StringUtil.isNull(operatorCode)){
			List<User> userList = commMapper.getUserInfo(operatorCode);
			if(userList!=null&&userList.size()>0){
				BeanUtils.setProperty(info, "operatorName", userList.get(0).getUserName());
			}else{
				logger.error("该操作用户（"+operatorCode+"）未找到！");
			}
		}
		return info;
	}
	
	
	public List<Constant> searchConstant(String key) {
		logger.info("invoke searchConstant...");
		List<Constant> list = new ArrayList<Constant>();
		if(StringUtil.isNull(key)){
			return list;
		}
		if(key.contains("|")){
			String[] keys = key.split("\\|");
			if(keys.length>2){
				list = getConstants(keys[0], keys[1], keys[2],keys.length==4?keys[3]:null);
			}
		}else{
			if(BRANCH_OPTION.equals(key)){
				list = getNewBranchInfo();
			}if(GROUP_OPTION.equals(key)){
				list = getGroupOption();
			}else{
				List<Lookupvalues> values = lookupvaluesService.getLookvalues(key);
				for(Lookupvalues value : values){
					list.add(new Constant(key, value.getLookupCode(), value.getMeaning()));
				}
			}
		}
		
		return list;
	}
	
	public String  getConstantDisplay(String key,String value) {
		logger.info("invoke getConstantDisplay...");
		String result = value;
		List<Constant> list = searchConstant(key);
		for(Constant c : list){
			if(c.getValue().equals(value)){
				result = c.getDisplay();
			}
		}
		
		return result;
	}
	
	
	
	private List<Constant> getGroupOption(){
		List<Constant> result = new ArrayList<Constant>();
		Map<String,Object> searchMap = new HashMap<String, Object>();
		List<GroupInfo> list = groupInfoService.searchGroupInfo(searchMap);
		for(GroupInfo info : list){
			result.add(new Constant(GROUP_OPTION, info.getGrpId(), info.getGrpCname()));
		}
		return result;
	}
	
	private List<Constant> getNewBranchInfo(){
		List<Constant> result = new ArrayList<Constant>();
		Map<String,Object> searchMap = new HashMap<String, Object>();
		//searchMap.put("Grade", "3");
		List<NewBranchInfo> list = newBranchInfoService.searchNewBranchInfo(searchMap);
		for(NewBranchInfo info : list){
			result.add(new Constant(BRANCH_OPTION, info.getBranchCode(), "("+info.getBranchCode()+")"+info.getName()));
		}
		return result;
	}
	
	public List<Constant> getConstants(String tableName,String value,String display,String searchStr){
		tableName = tableName.split("&")[0];
		String showName = tableName.split("&")[tableName.split("&").length-1];
		List<Constant> result = new ArrayList<Constant>();
		Map searchMap = new HashMap();
		searchMap.put("tableName", tableName);
		if(!StringUtil.isNull(searchStr)){
			String[] strs = searchStr.split(";");
			for(String str : strs){
				String[] tempStr = str.split("\\=");
				if(tempStr.length==2){
					searchMap.put(tempStr[0], tempStr[1]);
				}
				
			}
		}
		List<Map> mapList  = commMapper.searchTableByTableName(searchMap);
		for(Map map : mapList){
			Map tempMap = new HashMap();
			Iterator iterator = map.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next().toString();
				tempMap.put(key.toUpperCase(), map.get(key));
			}
			map = tempMap;	
			Object valueTemp = map.get(value.toUpperCase());
			String valueStr = valueTemp==null?"":valueTemp.toString();
			Object displayTemp = map.get(display.toUpperCase());
			String displayStr = displayTemp==null?"":displayTemp.toString();
			result.add(new Constant(showName, valueStr, displayStr));
		}
		return result;
	}
	
	public List<Map> searchTableByTableName(String tableName){
		Map searchMap = new HashMap();
		searchMap.put("tableName", tableName);
		return commMapper.searchTableByTableName(searchMap);
	}
	
	public List<Map> searchTable(Map searchMap){
		return commMapper.searchTableByTableName(searchMap);
	}
	
	public List<Map> searchSql(String paramSql){
		return commMapper.searchSql(paramSql);
	}
	
}
