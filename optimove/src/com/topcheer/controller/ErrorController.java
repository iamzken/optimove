package com.topcheer.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ErrorController {
	
	@ExceptionHandler(Exception.class)   //在Controller类中添加该注解方法即可(注意：添加到某个controller，只针对该controller起作用)
	public void exceptionHandler(Exception ex,HttpServletResponse response,HttpServletRequest request) throws Exception{  
//		if(ex.getClass() == NoSuchRequestHandlingMethodException.class){
//			response.sendRedirect(request.getContextPath()+"/common/view/404.jsp");
//		}else{
//			response.sendRedirect(request.getContextPath()+"/common/view/500.jsp");
//		}
	}

}
