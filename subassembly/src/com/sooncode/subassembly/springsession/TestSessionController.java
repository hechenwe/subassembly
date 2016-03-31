package com.sooncode.subassembly.springsession;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller()
@RequestMapping("/testSessionController")
public class TestSessionController {
	@RequestMapping(value = "/test" , method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> test(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//request.getSession().setAttribute("user", new User("张三", "0001"));
		map.put("user", new User("张三", "0001"));
		return map;
	}
	
	@RequestMapping(value = "/testPost" , method=RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> testPost(HttpServletRequest request) throws Exception {
		String userName= request.getParameter("userName");
		String password= request.getParameter("password");
		System.out.println("TestSessionController.testPost()"+"userName="+userName+";password="+password);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", new User("张三", "0001"));
		//request.getSession().setAttribute("user", new User("张三", "0001"));
		return map;
	}
}












