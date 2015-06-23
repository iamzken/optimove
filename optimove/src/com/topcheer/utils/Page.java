package com.topcheer.utils;

/**
 * 封装分页数据
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

//import com.google.common.base.Joiner;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;

public class Page {

  private static final Logger logger = Logger.getLogger(Page.class);
  private static final String PAGE_NO = "page";
  private static final String PAGE_SIZE = "rows";
  private static ObjectMapper mapper = new ObjectMapper();

  public static String DEFAULT_PAGESIZE = "10";
  private int pageNo;          //当前页码
  private int pageSize;        //每页行数
  private int totalRecord;      //总记录数
  private int totalPage;        //总页数
  private Map<String, String> params;  //查询条件
  private Map<String, List<String>> paramLists;  //数组查询条件
  private String searchUrl;      //Url地址
  private String pageNoDisp;       //可以显示的页号(分隔符"|"，总页数变更时更新)
  
  private Collection dataCollection;

  private static ThreadLocal<Page> pageLocal = new ThreadLocal<Page>();
  
  private Page() {
    pageNo = 1;
    pageSize = Integer.valueOf(DEFAULT_PAGESIZE);
    totalRecord = 0;
    totalPage = 0;
//    params = Maps.newHashMap();
//    paramLists = Maps.newHashMap();
    params = new HashMap();
    paramLists = new HashMap();
    searchUrl = "";
    pageNoDisp = "";
    pageLocal.set(this);
  }
  
  public static Page newBuilder(HttpServletRequest request){
	    Page page = new Page();
	    Map map = request.getParameterMap();
	    Iterator iterator = map.keySet().iterator();
	    while(iterator.hasNext()){
	    	Object key = iterator.next();
	    	if(PAGE_NO.equals(key.toString())){
	    		String[] pageNos = (String[]) map.get(PAGE_NO);
	    		if(pageNos!=null&&pageNos.length>0){
	    	    	page.setPageNo(Integer.valueOf(pageNos[0]));
	    	    }
	    	}else if(PAGE_SIZE.equals(key.toString())){
	    		String[] pageSizes = (String[]) map.get(PAGE_SIZE);
	    		if(pageSizes!=null&&pageSizes.length>0){
	    		    page.setPageSize(Integer.valueOf(pageSizes[0]));
	    		}
	    	}else{
	    		Object value = map.get(key);
	    		if(value instanceof List){
	    			page.paramLists.put(key.toString(), (List)value);
	    		}else if(value!=null){
	    			page.params.put(key.toString(), value.toString());
	    		}
	    	}
	    }
	    return page;
  }
  
  public static Page newBuilder(int pageNo, int pageSize){
	    Page page = new Page();
	    page.setPageNo(pageNo);
	    page.setPageSize(pageSize);
	    return page;
	  }
   
  public static Page newBuilder(int pageNo, int pageSize, String url){
    Page page = new Page();
    page.setPageNo(pageNo);
    page.setPageSize(pageSize);
    page.setSearchUrl(url);
    return page;
  }
  
  /**
   * 生成查询map
   * @param request
   * @return
   */
  public static Map<String,Object> getSearchPageMap(HttpServletRequest request){
  	Map searchMap = new HashMap();
  	Page page = pageLocal.get();
  	if(page ==null){
  		page = page.newBuilder(request);
  	}
  	searchMap.put("page", page);
  	Map map = request.getParameterMap();
    Iterator iterator = map.keySet().iterator();
    while(iterator.hasNext()){
    	Object key = iterator.next();
    	if(PAGE_NO.equals(key.toString())){
    		String[] pageNos = (String[]) request.getParameterMap().get("page");
    		if(pageNos!=null&&pageNos.length>0){
    			page.setPageNo(Integer.valueOf(pageNos[0]));
    	    }
    	}else if(PAGE_SIZE.equals(key.toString())){
    		String[] pageSizes = (String[]) request.getParameterMap().get("rows");
    		if(pageSizes!=null&&pageSizes.length>0){
    			page.setPageSize(Integer.valueOf(pageSizes[0]));
    		}
    	}else{
    		Object value = map.get(key);
    		if(value!=null&&value instanceof Object[]&&((Object[])value).length==1){
    			searchMap.put(key, ((Object[])value)[0]);
    		}else{
    			searchMap.put(key, value);
    		}
    		
    		if(value instanceof List){
    			page.paramLists.put(key.toString(), (List)value);
    		}else if(value!=null){
    			page.params.put(key.toString(), value.toString());
    		}
    	}
    }

	return searchMap;
  }
  
  public static Page getCurrentPage(){
	  return pageLocal.get();
  }
  
  public Map<String, Object> getPageReturn(){
	  Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", this.getTotalRecord());
		map.put("from", (this.getPageNo()-1)*this.getPageSize());
		if(dataCollection!=null){
			map.put("to", (this.getPageNo()-1)*this.getPageSize()+dataCollection.size());
			map.put("rows", dataCollection);
		}
		map.put("result", "ok");
		return map;
  }
  public Map<String, Object> getPageReturn2(){
	  Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", this.getTotalRecord());
		map.put("from", (this.getPageNo()-1)*this.getPageSize());
		if(dataCollection!=null){
			dataCollection=convt(dataCollection);
			map.put("to", (this.getPageNo()-1)*this.getPageSize()+dataCollection.size());
			map.put("rows", dataCollection);
		}
		map.put("result", "ok");
		return map;
  }
  public Collection convt(Collection coll){
	  
	  List list=(List)coll;
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
	coll=listResult;
	  return coll;
  }
  public Map<String, Object> getPageReturn(Collection resultData){
	  Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", this.getTotalRecord());
		map.put("from", (this.getPageNo()-1)*this.getPageSize());
		if(resultData!=null){
			map.put("to", (this.getPageNo()-1)*this.getPageSize()+resultData.size());
			map.put("rows", resultData);
		}
		
		return map;
  }
  
//  /**
//   * 查询条件转JSON
//   */
//  public String getParaJson(){
//    Map<String, Object> map = Maps.newHashMap();
//    for (String key : params.keySet()){
//      if ( params.get(key) != null  ){
//        map.put(key, params.get(key));
//      }
//    }
//    String json="";
//    try {
//      json = mapper.writeValueAsString(map);
//    } catch (Exception e) {
//      logger.error("转换JSON失败", params, e);
//    }
//    return json;
//  }
//
//  /**
//   * 数组查询条件转JSON
//   */
//  public String getParaListJson(){
//    Map<String, Object> map = Maps.newHashMap();
//    for (String key : paramLists.keySet()){
//      List<String> lists = paramLists.get(key);
//      if ( lists != null && lists.size()>0 ){
//        map.put(key, lists);
//      }
//    }
//    String json="";
//    try {
//      json = mapper.writeValueAsString(map);
//    } catch (Exception e) {
//      logger.error("转换JSON失败", params, e);
//    }
//    return json;
//  }

//  /**
//   * 总件数变化时，更新总页数并计算显示样式
//   */
//  private void refreshPage(){
//    //总页数计算
//    totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : (totalRecord/pageSize + 1);
//    //防止超出最末页（浏览途中数据被删除的情况）
//    if ( pageNo > totalPage && totalPage!=0){
//        pageNo = totalPage;
//    }
//    pageNoDisp = computeDisplayStyleAndPage();
//  }
//  
//  /**
//   * 计算页号显示样式
//   *  这里实现以下的分页样式("[]"代表当前页号)，可根据项目需求调整
//&nbsp;&nbsp; *&nbsp;&nbsp; [1],2,3,4,5,6,7,8..12,13
//&nbsp;&nbsp; *&nbsp;&nbsp; 1,2..5,6,[7],8,9..12,13
//&nbsp;&nbsp; *&nbsp;&nbsp; 1,2..6,7,8,9,10,11,12,[13]
//   */
//  private String computeDisplayStyleAndPage(){
//    List<Integer> pageDisplays = Lists.newArrayList();
//    if ( totalPage <= 11 ){
//      for (int i=1; i<=totalPage; i++){
//        pageDisplays.add(i);
//      }
//    }else if ( pageNo < 7 ){
//      for (int i=1; i<=8; i++){
//        pageDisplays.add(i);
//      }
//      pageDisplays.add(0);// 0 表示 省略部分(下同)
//      pageDisplays.add(totalPage-1);       
//      pageDisplays.add(totalPage);
//    }else if ( pageNo> totalPage-6 ){
//      pageDisplays.add(1);
//      pageDisplays.add(2);
//      pageDisplays.add(0);
//      for (int i=totalPage-7; i<=totalPage; i++){
//        pageDisplays.add(i);
//      }       
//    }else{
//      pageDisplays.add(1);
//      pageDisplays.add(2);
//      pageDisplays.add(0);
//      for (int i=pageNo-2; i<=pageNo+2; i++){
//        pageDisplays.add(i);
//      }
//      pageDisplays.add(0);
//      pageDisplays.add(totalPage-1);
//      pageDisplays.add(totalPage);
//    }
//    return Joiner.on("|").join(pageDisplays.toArray());
//  }
 
  public int getPageNo() {
     return pageNo;
  }
 
  public void setPageNo(int pageNo) {
     this.pageNo = pageNo;
  }
 
  public int getPageSize() {
     return pageSize;
  }
 
  public void setPageSize(int pageSize) {
     this.pageSize = pageSize;
  }
 
  public int getTotalRecord() {
     return totalRecord;
  }
 
  public void setTotalRecord(int totalRecord) {
    this.totalRecord = totalRecord;
//    refreshPage();     
  }

  public int getTotalPage() {
     return totalPage;
  }
 
  public void setTotalPage(int totalPage) {
     this.totalPage = totalPage;
  }
 
  public Map<String, String> getParams() {
     return params;
  }
   
  public void setParams(Map<String, String> params) {
     this.params = params;
  }
  
  public Map<String, List<String>> getParamLists() {
    return paramLists;
  }

  public void setParamLists(Map<String, List<String>> paramLists) {
    this.paramLists = paramLists;
  }
  public String getSearchUrl() {
    return searchUrl;
  }
  public void setSearchUrl(String searchUrl) {
    this.searchUrl = searchUrl;
  }
  public String getPageNoDisp() {
    return pageNoDisp;
  }
  public void setPageNoDisp(String pageNoDisp) {
    this.pageNoDisp = pageNoDisp;
  }

public Collection getDataCollection() {
	return dataCollection;
}

public void setDataCollection(Collection dataCollection) {
	this.dataCollection = dataCollection;
}
  
  
}