package com.topcheer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ApiTestController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ApiTestController.class);
	
	@RequestMapping("/testAPI")
	@ResponseBody
	public Map<String,Object>  testAPI(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.getWriter().write(request.getParameter("callbackparam")+"([ { \"name\":\"John\"}])");
		return null;
	}
	
}
