package com.topcheer.controller.mobile;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topcheer.model.mobile.Billsapply;
import com.topcheer.model.mobile.Businessapply;
import com.topcheer.service.mobile.IBillsapplyService;
import com.topcheer.service.mobile.IBusinessapplyService;
import com.topcheer.utils.ConstantUtil;
import com.topcheer.utils.DateUtil;
import com.topcheer.utils.Page;
import com.topcheer.utils.ResultJsonUtil;

@Controller
@RequestMapping("/businessapplys")
public class BusinessapplyController {
	@Autowired
	private IBusinessapplyService businessapplyService;
	
	@Autowired
	private IBillsapplyService billsapplyService;
	public static void main(String args[]){
		List list=new ArrayList();
		Map map5=new HashMap();
		map5.put("businessid", "c0008");
		map5.put("fieldname", "sex");
		map5.put("fieldcnname", "性别");
		map5.put("fieldvalue", "男");
		
		
		
		Map<String, String> map=new HashMap();
		map.put("businessid", "d001");
		map.put("fieldname", "name");
		map.put("fieldcnname", "姓名");
		map.put("fieldvalue", "如花");
		
		Map map2=new HashMap();
		map2.put("businessid", "d001");
		map2.put("fieldname", "sex");
		map2.put("fieldcnname", "性别");
		map2.put("fieldvalue", "女");
		
		Map map6=new HashMap();
		map6.put("businessid", "c0008");
		map6.put("fieldname", "name");
		map6.put("fieldcnname", "姓名");
		map6.put("fieldvalue", "张三");
		
		Map map3=new HashMap();
		map3.put("businessid", "a002");
		map3.put("fieldname", "sex");
		map3.put("fieldcnname", "性别");
		map3.put("fieldvalue", "男");
		
		Map map4=new HashMap();
		map4.put("businessid", "a002");
		map4.put("fieldname", "name");
		map4.put("fieldcnname", "姓名");
		map4.put("fieldvalue", "李四");		
		
		list.add(map);
		list.add(map2);
		list.add(map3);
		list.add(map4);		
		list.add(map5);
		list.add(map6);	
		JSONArray jsonArray = JSONArray.fromObject(list);
	
		System.out.println(jsonArray.size()+"..."+jsonArray);
		//排序
		 for (int i = 0; i < list.size(); i++) { 
	            Map tmp0 = (Map)list.get(i); 
	            String number0 = (String) tmp0.get("businessid"); 	  
	            Map<Object, Object> tmp = null; 
	            for (int j = i; j < list.size(); j++) { 
	                Map tmp1 = (Map)list.get(j); 
	                String number1 = (String) tmp1.get("businessid"); 	  
	                if (!number0.equals(number1) ) { 
	                    tmp = tmp0; 
	                    list.set(i, list.get(j)); 
	                    list.set(j, tmp); 
	                } 
	            } 
	     } 

		String tmpbusinessid="";
		Map resultMap=new HashMap();
		List listResult=new ArrayList();
		for(int i=0;i<list.size();i++){
			Map m=(Map)list.get(i);
			Iterator it = m.entrySet().iterator(); 
			if(!tmpbusinessid.equals((String)m.get("businessid"))){
				resultMap=new HashMap();
				listResult.add(resultMap);
			}
			resultMap.put("businessid", m.get("businessid"));
			while(it.hasNext()){
				Map.Entry entry = (java.util.Map.Entry)it.next();
				 String key = (String)entry.getKey(); 
			     if(key.equals("fieldname")){
			    	 resultMap.put(m.get("fieldcnname"), m.get("fieldvalue"));
			     }			     
			}
			tmpbusinessid=(String)m.get("businessid");
		}
		System.out.println(resultMap.toString());
		System.out.println(listResult.toString());
		System.out.println(list.toString());
	}
	@RequestMapping("/searchBusinessapply")
	@ResponseBody
	public Map<String,Object> searchBusinessapply(HttpServletRequest request) {
		Page page = Page.newBuilder(request);
		businessapplyService.searchBusinessapply(page.getSearchPageMap(request));
		return page.getPageReturn2();
	}
	
	@RequestMapping("/getBusinessapply")
	@ResponseBody
	public Map<String,Object> getBusinessapply(String businessid) {
		List<Businessapply> businessapply = businessapplyService.getBusinessapply(businessid);
		Map<String,Object> result = ResultJsonUtil.getResultMap();
		result.put(ResultJsonUtil.DATA, businessapply);
		return result;
	}
	/***
	 * 票据贴现记录
	 * @param billsapply
	 * @param binding
	 * @return
	 */
	@RequestMapping("/billsapply")
	@ResponseBody
	public Map<String,Object> billsapply(@Valid Billsapply billsapply,BindingResult binding,HttpServletRequest request) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		 Map properties = request.getParameterMap(); 
		    Map paraMap = new HashMap(); 
		    Iterator entries = properties.entrySet().iterator(); 
		    Map.Entry entry; 
		    String name = ""; 
		    String value = ""; 
		    while (entries.hasNext()) { 
		        entry = (Map.Entry) entries.next(); 
		        name = (String) entry.getKey(); 
		        Object valueObj = entry.getValue(); 
		        if(null == valueObj){ 
		            value = ""; 
		        }else if(valueObj instanceof String[]){ 
		            String[] values = (String[])valueObj; 
		            for(int i=0;i<values.length;i++){ 
		                value = values[i] + ","; 
		            } 
		            value = value.substring(0, value.length()-1); 
		        }else{ 
		            value = valueObj.toString(); 
		        } 
		        paraMap.put(name, value); 
		    } 

		System.out.println("............票据贴现记录........."+paraMap.toString());
		billsapply.setSkrqc((String)paraMap.get("skr_qc"));
		billsapply.setFkryh((String)paraMap.get("fkr_yh"));
		billsapply.setJyhthm((String)paraMap.get("jyhthm"));
		billsapply.setFkrzh((String)paraMap.get("fkr_zh"));
		billsapply.setProductcode((String)paraMap.get("productcode"));
		billsapply.setBusinesstype((String)paraMap.get("businesstype"));
		billsapply.setCpje(new Double((String)paraMap.get("cpje")==null?"0.00":(String)paraMap.get("cpje")));
		billsapply.setSkrzh((String)paraMap.get("skr_zh"));
		billsapply.setFkrkhhdz((String)paraMap.get("fkr_khh_dz"));
		billsapply.setFkrkhhhh((String)paraMap.get("fkr_khh_hh"));
		billsapply.setFkrqc((String)paraMap.get("fkr_qc"));
		billsapply.setSkryh((String)paraMap.get("skr_yh"));
		billsapply.setDqr((String)paraMap.get("dqr"));
		billsapply.setBusinessid((String)paraMap.get("businessid"));
		billsapply.setTableid((String)paraMap.get("tableid"));
		billsapply.setCustno((String)paraMap.get("custno"));
		billsapply.setTranschannel("01");//01-手机  02-pad
		
		billsapply.setUpdatedate(DateUtil.getCurrentDate());//系统日期
		billsapply.setUpdatetime(DateUtil.getCurrentTime());//系统时间
		
		billsapply.setOperator(""); //操作人员/客户经理
		billsapply.setOperatororg("");//操作机构
		billsapplyService.insert(billsapply);
		return ResultJsonUtil.getResultMap();
	}
	/***
	 * 业务申请记录（通用记录表）
	 * @param businessapply
	 * @param binding
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/billssave")
	@ResponseBody
	public Map<String,Object> billssave(@Valid Businessapply businessapply,BindingResult binding,HttpServletRequest request,HttpServletResponse response) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonStr=request.getParameter("para");
		JSONObject object=JSONObject.fromObject(jsonStr);
		businessapply.setBusinessid((String)object.get("businessid"));
		businessapply.setBusinesstype((String)object.get("businesstype"));
		businessapply.setProductcode((String)object.get("productcode"));
		businessapply.setTableid((String)object.get("tableid"));
		String jsonArray=object.get("rows").toString();
		JSONArray array=JSONArray.fromObject(jsonArray);
		 for(int i=0;i< array.size();i++){
		     Map o=(Map)array.get(i);
		     String fieldname=(String)o.get("fieldname");
		     String fieldcnname=(String)o.get("fieldcnname");
		     String fieldvalue=(String)o.get("fieldvalue");
		     businessapply.setFieldname(fieldname);
		     businessapply.setFieldcnname(fieldcnname);
		     businessapply.setFieldvalue(fieldvalue);
		     businessapply.setTranschannel("01");//01-手机 02-pad
		     if(null!=businessapply.getBusinessid() && !businessapply.getBusinessid().equals("")){
					businessapplyService.insert(businessapply);
			}
		 }

		
		
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Businessapply businessapply,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(ResultJsonUtil.getResultMap(),binding);
		}
		businessapply.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		businessapply.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		businessapply.setUpdatedate(DateUtil.getCurrentDate());
		businessapply.setUpdatetime(DateUtil.getCurrentTime());
		businessapplyService.insert(businessapply);
		return ResultJsonUtil.getResultMap();
	}
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@Valid Businessapply businessapply,HttpServletRequest request,BindingResult binding) {
		if(binding.hasErrors()){
			return ResultJsonUtil.analyzeError(binding);
		}
		businessapply.setOperator((String)request.getSession().getAttribute(ConstantUtil.WORKID));
		businessapply.setOperatororg((String)request.getSession().getAttribute(ConstantUtil.BRANCHCODE));
		businessapply.setUpdatedate(DateUtil.getCurrentDate());
		businessapply.setUpdatetime(DateUtil.getCurrentTime());
		businessapplyService.update(businessapply);
		return ResultJsonUtil.getResultMap();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(String businessids) {
		if(businessids!=null){
			String[] _ids = businessids.split(",");
			for(String businessid : _ids){
				if(!businessid.equals("")){
					businessapplyService.delete(businessid);
				}
			}
		}
		return ResultJsonUtil.getResultMap();
	}



}
