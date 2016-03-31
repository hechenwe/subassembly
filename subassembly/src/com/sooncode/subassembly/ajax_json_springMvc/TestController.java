package com.sooncode.subassembly.ajax_json_springMvc;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {
	
	 //接收前台传过来的字符串格式的json对象，在后台进行解析  
    @RequestMapping("/resolveJsonObject")  
    public void resolveJsonObject(HttpServletRequest request,HttpServletResponse response) throws IOException {  
        //解码  
        String str = URLDecoder.decode(request.getParameter("orderJson"),"UTF-8");  
        //JSONObject jb=new JSONObject();   
        //将json格式的字符串转换为json对象，并取得该对象的“userName”属性值  
        String o=(String)JSONObject.fromObject(str).get("userName");  
        System.out.println(o);  
    } 
	
	 //传递json数组字符串  
    @RequestMapping(value = "/resolveJsonArray", method = RequestMethod.POST)  
    public void resolveJsonArray(HttpServletRequest request,HttpServletResponse response) throws IOException {  
        //解码，为了解决中文乱码  
        String jsonString = URLDecoder.decode(request.getParameter("json"),"UTF-8");  
        System.out.println("TestController.resolveJsonArray()" +request.getParameter("json") );
        //将json格式的字符串转换为json数组对象  
        JSONArray array=(JSONArray)JSONObject.fromObject(jsonString).get("menu");   
        //取得json数组中的第一个对象  
        JSONObject o = (JSONObject) array.get(0);//获得第一个array结果  
        //取出json数组中第一个对象的“userName”属性值  
        String name=o.get("userName").toString();//获得属性值  
        System.out.println(name);  
    }   

}
