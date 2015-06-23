package com.topcheer.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ʵ�ֳ�������ת���Ĺ��ù����ࡣ
 * 
 * @author nfs on 2007-07-23
 *
 */
public class ArrayListUtil 
{
	private ArrayListUtil()
	{	
	}
	
	/**
	 * ���ܣ����ַ�����������ת��Ϊ�������顣
	 * 
	 * @param strIds
	 * @return int[]
	 */
	public static int[] convertStrArrToIntArr(String[] strIds)
	{
		int[] intIds = new int[strIds.length];
		for (int i=0; i<strIds.length; i++)
			intIds[i] = Integer.parseInt(strIds[i]);
		return intIds;
	}
	
	/**
	 * ���ܣ�ת��MapListΪ����List
	 * @param list
	 * @return
	 */
	public static List convertMapListToArrayList(List list)
	{
		List content = new ArrayList();
		String[] row = null;
		Map map = null;
		
		if (list!=null)
		{
			Iterator iterator = list.iterator();
			while (iterator.hasNext())
			{
				int i = 0;
				map = (Map)iterator.next();
				row = new String[map.size()];
				Iterator keyIt = map.keySet().iterator();
				while (keyIt.hasNext())
				{
					String key = (String)keyIt.next();
					row[i++] = ((Object)map.get(key))==null?"":((Object)map.get(key)).toString();
				}
				
				content.add(row);
			}
		}
		
		return content;
	}
	
	/**
	 * ����ת��Ϊ�ַ���
	 * @param l
	 * @param regex
	 * @return
	 */
	public static String convertListToString(List l,String regex){
		StringBuffer s = new StringBuffer();
		for(int i = 0;i < l.size();i++){
			s.append(l.get(i).toString() + regex);
		}
		return s.toString().equals("") ? "" : s.substring(0,s.length() - regex.length());
	}
	
	/**
	 * Map����
	 * @param sourceMap
	 * @param targetMap
	 */
	@SuppressWarnings("unchecked")
	public static void mapCopy(Map<String, String> sourceMap, Map<String, String> targetMap){
    	Iterator it = targetMap.entrySet().iterator(); 
    	while (it.hasNext()) { 
    		Entry entry = (Entry) it.next(); 
    		sourceMap.put((String)entry.getKey(), (String)entry.getValue());
    	}
	}
	
	/**
	 * ������ids[]ת��ΪList
	 */
	@SuppressWarnings("unchecked")
	public static List convertArrayToList(String ids) {
		List list = new ArrayList<Long>();
		String[] str = ids.split(",");
		for(int i=0;i<str.length;i++){
			list.add(str[i]);
		}
		return list;
	}
	
	/**
	 * ɾ���������ظ�����
	 * @param sourceList
	 * @param tragetList
	 * @return
	 */
	public static void removeSameToSource(List<String> sourceList, List<String> tragetList) {
		for(int i=0;i<sourceList.size();i++){
			String sourceAccNo = (String)sourceList.get(i);			
			for(int j=0;j<tragetList.size();j++){
				String tragetAccNo = (String)tragetList.get(j);
				if (sourceAccNo.equals(tragetAccNo)) {  
					sourceList.remove(i);  
		            i--;  
		        }  
			}
		}
	}

}
