package com.topcheer.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.topcheer.model.MyError;

public class ResultJsonUtil {
//	private static ThreadLocal<Map<String,Object>> mapLocal= new ThreadLocal<Map<String,Object>>();
	
	public final static String RESULT = "result";
	public final static String DATA = "data";
	public final static String MSG = "msg";
	//����쳣�ȴ�����Ϣ��������Ϣ�ᵯ����
	public final static String ERROR_MSG = "errorMsg";
	//��������� form��У��Ĵ���һ��list����������ʾ�ǣ��ڿ�������ʾ
	public final static String ERROR = "error";
	//�������� ����У���ϵͳ�쳣
	public final static String EXCEPTION = "exception";
	public static String SUCCEED = "ok";
	public static String FAIL = "fail";
	
	public static Map<String,Object> getResultMap(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put(RESULT, ResultJsonUtil.SUCCEED);
		return result;
	}
	
	public static Map<String,Object> getResultErrorMap(){
		Map<String,Object> result = new HashMap<String, Object>();
		result.put(RESULT, ResultJsonUtil.FAIL);
		return result;
	}
	
//	private static Map<String,Object> isExist(){
//		Map<String,Object> result = mapLocal.get();
//		if(result==null){
//			result = new HashMap<String, Object>();
//			mapLocal.set(result);
//			result.put(RESULT, ResultJsonUtil.SUCCEED);
//		}
//		return result;
//	}
	
	
    
	/**
	 * ����������Ϣ
	 * @param binding
	 * @return
	 */
	public static Map<String,Object> analyzeError(BindingResult binding){
		Map<String,Object> resultMap = ResultJsonUtil.getResultMap();
		List<MyError> errorList = new ArrayList<MyError>();
		resultMap.put(ResultJsonUtil.RESULT, ResultJsonUtil.FAIL);
		resultMap.put(ResultJsonUtil.ERROR, errorList);
		List<ObjectError> objErrorList = binding.getAllErrors();
		
		for(ObjectError error : objErrorList){
			try {
				errorList.add(new MyError(error.getObjectName(),(String)BeanUtils.getProperty(error, "field"),error.getDefaultMessage()));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	/**
	 * ����������Ϣ
	 * @param binding
	 * @return
	 */
	public static Map<String,Object> analyzeError(Map<String,Object> resultMap,BindingResult binding){
		List<MyError> errorList = new ArrayList<MyError>();
		resultMap.put(ResultJsonUtil.RESULT, ResultJsonUtil.FAIL);
		resultMap.put(ResultJsonUtil.ERROR, errorList);
		List<ObjectError> objErrorList = binding.getAllErrors();
		
		for(ObjectError error : objErrorList){
			try {
				errorList.add(new MyError(error.getObjectName(),(String)BeanUtils.getProperty(error, "field"),error.getDefaultMessage()));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
//	public static Map<String,Object> put(String key,Object value){
//		isExist().put(key, value);
//		return mapLocal.get();
//	}
//	
//	public static Map<String,Object> getResultMap(){
//		return isExist();
//	}
	
}
