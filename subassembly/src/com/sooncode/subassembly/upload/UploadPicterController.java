package com.sooncode.subassembly.upload;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



/**
 * 
 * @author hechen
 *
 */
@Controller
@RequestMapping("/uploadPicterController") 
public class UploadPicterController {
	
	
	/**
	 *  在applicationContext.xml 配置如下bean
	 *  org.springframework.web.multipart.commons.CommonsMultipartResolver 
	 */
	@RequestMapping(value = "/upload" , method=RequestMethod.POST)  
	@ResponseBody
    public Map<String,Object> upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws IllegalStateException, IOException {  
  
        String  filePath  =  UploadFileServic.saveFile(file); 
        System.out.println("UploadPicterController.upload()" +filePath);
        Map<String,Object> map = new HashMap<>();
        map.put("upload", "success");
        map.put("image", filePath);
        return map;  
    }  
	
	
	 
	
	 
  
}
