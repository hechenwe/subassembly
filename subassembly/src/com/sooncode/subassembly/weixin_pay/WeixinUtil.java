package com.sooncode.subassembly.weixin_pay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

public class WeixinUtil {
    /**
     * 获取UUID
     * @return
     */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 生成订单号
	 * 
	 * @param preFixString
	 * @return
	 */
	public static String getOrderNumber(String preFixString) {

		 Date today = new Date();
		 int orderIndex = 0;
		Date n = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime = outFormat.format(n);

		if (orderIndex > 0) {
			if (n.getYear() == today.getYear() && n.getMonth() == today.getMonth() && n.getDay() == today.getDay()) {
				orderIndex += 1;
			} else {
				today = n;
				orderIndex = 1;
			}
		} else {
			today = n;
			orderIndex = 1;
		}
		if (orderIndex > 999999) {
			orderIndex = 1;
		}
		String indexString = String.format("%s%06d", currTime, orderIndex);
		String orderNumberString = preFixString + indexString;
		return orderNumberString;
	}
	
	public static String xmlPost(String urlStr, String xml){
		try{  
            URL url = new URL(urlStr);  
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setRequestMethod("POST");  
            if (null != xml) {  
                OutputStream outputStream = connection.getOutputStream();  
                outputStream.write(xml.getBytes("UTF-8"));  
                outputStream.close();  
            }  
            // 从输入流读取返回内容  
            InputStream inputStream = connection.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
            String str = null;  
            StringBuffer buffer = new StringBuffer();  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            inputStream.close();  
            inputStream = null;  
            connection.disconnect();  
            return buffer.toString();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
  
        return "";  
	}
	
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
