package com.topcheer.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

public class MapBeanUtil {


	public static Map<String, Object> getBeanToMap(Object obj) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		BeanMap bean = new BeanMap(obj);
		Iterator iterator = bean.keySet().iterator();
		
		while(iterator.hasNext()){
			String key = iterator.next().toString();
			Object value = bean.get(key);
			System.out.println(key +"========"+value);
			if(value!=null&&! (value instanceof Class)){
				result.put(key, value);
			}
		}
		return result;
	}
	
	
	public static Object getMapToBean(Map map,Object obj) throws Exception {
		BeanUtils.populate(obj, map);
		return obj;
	}
	
	
	private static Map<String, String> getSendMap2(Object obj) throws IllegalAccessException, InvocationTargetException{
		Map<String, String> result = new HashMap<String, String>();
		Class clz = obj.getClass();
		Field[] fields = clz.getFields();
		Method[] methods = clz.getMethods();
		for(Field field : fields){
			String name = field.getName();
			String setMethodName = "set"+toFirstUp(name);
			String getMethodName = "get"+toFirstUp(name);
			boolean bl1 = false;
			boolean bl2 = false;
			Method getMethod = null;
			for(Method method : methods){
				if(method.getName().equals(getMethodName)){
					getMethod = method;
					bl1 = true;
				}else if(method.getName().equals(setMethodName)){
					bl2 = true;
				}
			}
			if(bl1&&bl2){
				Object invObj = getMethod.invoke(obj, new Object[0]);
				if(invObj!=null){
					result.put(name,invObj.toString() );
				}
			}
				
			}
			return result;
		}
		
	public static Object getReceiveMap2(Map recvMap,Object obj) throws IllegalAccessException, InvocationTargetException{
		Class clz = obj.getClass();
		Field[] fields = clz.getFields();
		Method[] methods = clz.getMethods();
		Iterator iterator = recvMap.keySet().iterator();
		while(iterator.hasNext()){
			Object key = iterator.next();
			Object value = recvMap.get(key);
			BeanUtils.setProperty(obj, "set"+toFirstUp(key.toString()), value);
		}
		
//		BeanMap bean = new BeanMap(obj);
//		Iterator iterator = bean.keySet().iterator();
//		
//		while(iterator.hasNext()){
//			String key = iterator.next().toString();
//			System.out.println(key+""+bean.get(key));
//		}
		
		return obj;
	}
	
	public static String toFirstUp(String str){
		if(str!=null&&!"".equals(str)){
			str = str.substring(0, 1).toUpperCase()+str.substring(1, str.length());
		}
		
		return str;
	}
	
	public static void main(String[] args) throws Exception {
		
	}
}
