package com.topcheer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;

import com.topcheer.utils.ConstantUtil;

public class LoginFilter implements Filter {
    private String permitUrls[] = null;
    private String gotoUrl = null;
    private String[] interceptSuffix = null;
    
    public void destroy() {
        permitUrls = null;
        gotoUrl = null;
        interceptSuffix=null;
    }
 
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest res=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse)response;
        boolean bl = false;
        if(res.getSession().getAttribute(ConstantUtil.IS_LOGIN)!=null)
        	bl=true;
        if(!bl&&isInterceptSuffix(request)&&!isPermitUrl(request)){
            if(filterCurrUrl(request)){
                resp.sendRedirect(res.getContextPath()+gotoUrl);
                return;
            }
        }
        chain.doFilter(request, response);
    }
 
     
    public boolean filterCurrUrl(ServletRequest request){
         
        boolean filter=false;
        HttpServletRequest res=(HttpServletRequest) request;
        User user =(User) res.getSession().getAttribute("user");
        if(null==user)
            filter=true;
         
        return filter;
         
    }
     
    public boolean isPermitUrl(ServletRequest request) {
        boolean isPermit = false;
        String currentUrl = currentUrl(request);
//        String[] strs = currentUrl.split("\\.");
//        if(!(strs[strs.length-1].equals("do")||strs[strs.length-1].equals("jsp")||strs[strs.length-1].equals("html")||strs[strs.length-1].equals("/"))){
//        	return true; 
//        }
        if (permitUrls != null && permitUrls.length > 0) {
            for (int i = 0; i < permitUrls.length; i++) {
                if (permitUrls[i].equals(currentUrl)) {
                    isPermit = true;
                    break;
                }
            }
        }
       
        return isPermit;
    }
    
    public boolean isInterceptSuffix(ServletRequest request) {
        boolean isPermit = false;
        String currentUrl = currentUrl(request);
        String[] strs = currentUrl.split("\\.");
        String suffix = strs[strs.length-1];
        if (interceptSuffix != null && interceptSuffix.length > 0) {
	        for(String str : interceptSuffix){
	        	if(suffix.equals(str)){
	        		isPermit = true;
	        		break;
	        	}
	        }
        }
        if(suffix.equals("/")){
        	isPermit = true;
        }
        return isPermit;
    }
    
     
    //请求地址
    public String currentUrl(ServletRequest request) {
 
        HttpServletRequest res = (HttpServletRequest) request;
        String task = request.getParameter("task");
        String path = res.getContextPath();
        String uri = res.getRequestURI();
        if (task != null) {// uri格式 xx/ser
            uri = uri.substring(path.length(), uri.length()) + "?" + "task="
                    + task;
        } else {
            uri = uri.substring(path.length(), uri.length());
        }
        return uri;
    }
 
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        String permitUrls = filterConfig.getInitParameter("permitUrls");
        String gotoUrl = filterConfig.getInitParameter("gotoUrl");
        String interceptSuffix = filterConfig.getInitParameter("interceptSuffix");
 
        this.gotoUrl = gotoUrl;
 
        if (permitUrls != null && permitUrls.length() > 0) {
            this.permitUrls = permitUrls.split(",");
        }
        if (interceptSuffix != null && interceptSuffix.length() > 0) {
            this.interceptSuffix = interceptSuffix.split(",");
        }
    }
 
    
}