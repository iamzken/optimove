package com.topcheer.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;


public class MobileServlet extends DispatcherServlet {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.DispatcherServlet#getHandler(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected HandlerExecutionChain getHandler(HttpServletRequest arg0)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("2........来自移动端的请求..........getHandler");
		return super.getHandler(arg0);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.DispatcherServlet#initStrategies(org.springframework.context.ApplicationContext)
	 */
	@Override
	protected void initStrategies(ApplicationContext context) {
		// TODO Auto-generated method stub
		System.out.println("1........来自移动端的请求..........initStrategies");
		super.initStrategies(context);
	}
	
	
}
