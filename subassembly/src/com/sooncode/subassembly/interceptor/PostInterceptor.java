package com.sooncode.subassembly.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PostInterceptor extends HandlerInterceptorAdapter {
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
		String userName= request.getParameter("userName");
		String password= request.getParameter("password");
		System.out.println("----拦截器-----"+"userName="+userName+";password="+password);
        if(userName.equals("hechen"))  {
        	return true;
        }else{
        	
        	return false;//如果返回false，则不再调用之后的方法  
        }
    }  
  
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        
    	//System.out.println("-------postHandle1-------"); 
    	String userName= request.getParameter("userName");
		String password= request.getParameter("password");
		System.out.println("----拦截器-----"+"userName="+userName+";password="+password);
        if(modelAndView != null){  //加入当前时间  
           modelAndView.addObject("now", new Date());  
        }  
    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
        //System.out.println("-------afterCompletion1-------");  
    }  
}
