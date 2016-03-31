package com.sooncode.subassembly.springaop;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



/**
 * 日志 切面
 * 
 * @author hechen
 *
 */
@Aspect
@Component
public class LoggerAspect {
	/**
	 * 前置通知
	 */
//	@Before("execution(public * com.sooncode.goodstudent.app.controller.t.UserController.login(..))")
//	public void before(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().getName();
//		Object [] args = joinPoint.getArgs();
//		//System.out.println(Arrays.asList(args));
//		System.out.println("LoggerAspect.before()" + args.length);
//		HttpServletRequest request = (HttpServletRequest) args[0];
//		
//		String username = request.getParameter("username");
//		System.out.println("INFO--------------- 登录("+methodName+")接口,即将被访问  "+ new Date()+"  username="+username);
//
//	}
	
//  /**
//	 * 后置通知
//	 */
//	@After("execution(public * com.sooncode.goodstudent.app.controller.t.UserController.login(..))")
//	public void afer() {
//		System.out.println("INFO-------------- 登录接口,访问结束  "+ new Date());
//	}
//	
//	
//	 /**
//     * 环绕通知
//     * @param proceedingJoinPoint
//     * @return
//     * @throws Throwable
//     */
//	@Around("execution(public * cn.yjkjedu.goodstudent.app.controller.user.LoginController.login(..))")
//	public Object aroundPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//		Object result = proceedingJoinPoint.proceed(); // [prə'siːd]开始；继续进行；发生；行进
//		if (result instanceof Map) {
//			@SuppressWarnings("unchecked")
//			Map<String, Object> json = ((Map<String, Object>) result);
//			Object message = json.get("message");
//			if ("1".equals(message.toString())) {
//				Object sessionId = (String) json.get("sessionId");
//				User user = userService.selectUserBySessionId(sessionId.toString());
//				UserLogin userLogin = new UserLogin();
//				userLogin.setLoginDate(new Date());
//				userLogin.setUserId(user.getUserId());
//				userLoginService.insertUserLogin(userLogin);
//				logger.info("---[日志]---添加登录日志成功:用户[ID] "+user.getUserId()+"");
//			}
//		}
//		return result;
//	}
}
