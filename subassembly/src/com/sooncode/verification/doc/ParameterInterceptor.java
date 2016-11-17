/*package com.sooncode.verification.doc;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eduspace.entity.InterfaceLog;
import com.sooncode.verification.InterfaceCache;
import com.sooncode.verification.moduler.Interface;
import com.sooncode.verification.moduler.VerificationResult;
import com.sooncode.verification.service.VerificationService;
import com.eduspace.serverce.implementses.mq.MessageQueue;
import com.eduspace.util.HttpServletStream;
import com.eduspace.util.JsonUitl;
import com.eduspace.util.RESPONSE_CODE;
import com.eduspace.util.RESPONSE_MESG;
 
 
 
public class ParameterInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger("ParameterInterceptor.class");
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    InterfaceLog il = new InterfaceLog();
		Long t1 = System.nanoTime() ;
	    String url = request.getRequestURL().toString();
	    logger.info("[接口路径]"+url);
	    String[] strs = url.split("/");
	    url  = strs[strs.length-2]+"/"+strs[strs.length-1];
	    Interface i = InterfaceCache.getInterface(url);
	    if(i==null){
	    	logger.error("["+url+"接口] 没有参数描述");
			return true;
	    }
	   
	    il.setIp(request.getRemoteAddr());
	    il.setMethod(i.getMethod());
	    il.setParameterType(i.getParameterType());
	    il.setUrl(url);
	  
	    VerificationResult vr = VerificationService.verificationInterface(request, i);
	    if(vr.getIsPass()){
	    	 Long t2 = System.nanoTime();
	    	 logger.info("[验证参数  耗时:]"+ (t2 - t1)/1000000000.0);
	    	 il.setIsPass("true");
	    	 MessageQueue.putMessage("INTERFACE_LOG", new JsonUitl<InterfaceLog>().getJson(il));
	    	return true;
	    }else{
	    	Map<String,Object> map  = new HashMap<>();
	    	map.put(RESPONSE_CODE.getKey(),RESPONSE_CODE.FAILURE);
	    	map.put(RESPONSE_MESG.getKey(),vr.getReason());
	    	String jsonData  = JsonUitl.getJson(map);
			HttpServletStream.putString(jsonData, response); 
			il.setIsPass("false");
			 MessageQueue.putMessage("INTERFACE_LOG", new JsonUitl<InterfaceLog>().getJson(il));
			return false;
	    }
	    
		 
	}
	
}
*/