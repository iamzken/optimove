package com.topcheer.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.topcheer.utils.ResultJsonUtil;

public class ExceptionHandler implements HandlerExceptionResolver {  
	private static final Logger logger = Logger.getLogger(ExceptionHandler.class);
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
    	logger.error("拦截到异常信息："+ex.getMessage());
    	ModelAndView mav = new ModelAndView();
        Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
        ex.printStackTrace();
        HandlerMethod methodHandler = (HandlerMethod) handler;
        ResponseBody responseBody = methodHandler.getMethodAnnotation(ResponseBody.class);
        if(responseBody !=null){
        	Map<String,Object> map = ResultJsonUtil.getResultErrorMap();
        	map.put(ResultJsonUtil.ERROR_MSG, ex.getMessage());
        	map.put("rows", "");
        	MappingJacksonJsonView view = new MappingJacksonJsonView();
        	view.setAttributesMap(map);
        	mav.setView(view);
        	return mav;
        }
        
//        if(ex instanceof BusinessException) {  
//            return new ModelAndView("error-business", model);  
//        }else if(ex instanceof ParameterException) {  
//            return new ModelAndView("error-parameter", model);  
//        } else {  
//            return new ModelAndView("error", model);  
//        }  
        return new ModelAndView("error", model);  
    }  
}  