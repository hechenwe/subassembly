package com.sooncode.verification.service;
 
import java.util.HashMap;
 
import java.util.Map;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sooncode.verification.moduler.Controller;
import com.sooncode.verification.moduler.VerificationResult;
import com.sooncode.verification.service.VerificationService;

public class ParameterInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger("ParameterInterceptor.class");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Long t1 = System.nanoTime();
		String url = request.getRequestURL().toString();
		logger.info("[接口路径]" + url);
		String[] strs = url.split("/");
		url = strs[strs.length - 2] + "/" + strs[strs.length - 1];
		Controller i = ControllerParameterManager.controllerMap.get(url);
		if (i == null) {
			logger.error("[" + url + "接口] 没有参数描述");
			return true;
		}

		HttpServletRequest hsr = new HttpServletRequestWrapper(request);  
		VerificationResult vr = VerificationService.verificationInterface(hsr, i);
		if (vr.getIsPass()) {
			Long t2 = System.nanoTime();
			logger.info("[验证参数  耗时:]" + (t2 - t1) / 1000000000.0);

			return true;
		} else {
			Map<String, Object> map = new HashMap<>();

			HttpServletStream.putString("", response);

			return false;
		}
	}

}
