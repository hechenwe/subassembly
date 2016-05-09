package com.sooncode.subassembly.weixin_pay;

 
import java.util.Map.Entry;
import java.util.TreeMap;
 


/*
 * 计算签名
 */
public class SignService {

	/**
	 * 获取签名  
	 * @param map 参加签名算法的字段
	 * @param apiKey API密钥
	 * @return
	 */
	public static String getSign(TreeMap<String,String> map,String apiKey){
		String string = "";
		for (Entry<String, String> en : map.entrySet()) {
			string = string + en.getKey() + "=" + en.getValue() + "&";
		}
		string = string + "key=" + apiKey;

		string = Md5.GetMD5Code(string).toUpperCase();
		return string;
	}

}

 