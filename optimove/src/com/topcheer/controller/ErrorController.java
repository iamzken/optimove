package com.topcheer.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ErrorController {
	
	@ExceptionHandler(Exception.class)   //��Controller������Ӹ�ע�ⷽ������(ע�⣺��ӵ�ĳ��controller��ֻ��Ը�controller������)
	public void exceptionHandler(Exception ex,HttpServletResponse response,HttpServletRequest request) throws Exception{  
//		if(ex.getClass() == NoSuchRequestHandlingMethodException.class){
//			response.sendRedirect(request.getContextPath()+"/common/view/404.jsp");
//		}else{
//			response.sendRedirect(request.getContextPath()+"/common/view/500.jsp");
//		}
	}

}
