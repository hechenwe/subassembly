package com.sooncode.subassembly.response_code_util;

import java.util.HashMap;
 
import java.util.Map;

/**
 * 消息响应 工具类
 * @author pc
 *
 */
public class RCUtil {
    public static enum Type{
    	SUCCESS,PARAMETER_ERROR,PARAMETER_NULL,PARAMETER_TYPE_ERROR,JSON_ERROR
    }
    public static enum Key{
    	responseCode,responseMesg
    }
	private static Map<String,ResponseCode> responseCodes;
	
	static {
		Map<String,ResponseCode> map = new HashMap<String, ResponseCode>();
		ResponseCode rc1 = new ResponseCode();
		rc1.setResponseCode("SUCCESS");
		rc1.setResponseMesg("成功");
		map.put(Type.SUCCESS.name(),rc1);
		
		ResponseCode rc2 = new ResponseCode();
		rc2 .setResponseCode("PARAMETER_ERROR");
		rc2 .setResponseMesg("参数错误");
		map.put(Type.PARAMETER_ERROR.name(),rc2);
		 
		ResponseCode rc3 = new ResponseCode();
		rc3 .setResponseCode("PARAMETER_NULL");
		rc3 .setResponseMesg("缺少参数");
		map.put(Type.PARAMETER_NULL.name(),rc3);
		 
		ResponseCode rc4 = new ResponseCode();
		rc4 .setResponseCode("PARAMETER_TYPE_ERROR");
		rc4 .setResponseMesg("参数类型错误");
		map.put(Type.PARAMETER_TYPE_ERROR.name(),rc4);
	 
		ResponseCode rc5 = new ResponseCode();
		rc5 .setResponseCode("JSON_ERROR");
		rc5 .setResponseMesg("JSON 格式错误");
		map.put(Type.JSON_ERROR.name(),rc5);
		responseCodes= map;
	}
	
	
	
	public static ResponseCode getRC(Type type){
		ResponseCode rc = responseCodes.get(type.name());
		
		return rc;
	}
	
	
	public static void main(String[] args) {
		ResponseCode rc1 = getRC(RCUtil.Type.SUCCESS);
		ResponseCode rc2 = getRC(RCUtil.Type.PARAMETER_ERROR);
		 
	}
}

class ResponseCode {
    /**
     * 响应码
     */
	private String responseCode;
	/***
	 * 响应消息
	 */
	private String responseMesg;
	
	 

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMesg() {
		return responseMesg;
	}

	public void setResponseMesg(String responseMesg) {
		this.responseMesg = responseMesg;
	}

	 
	
	
}