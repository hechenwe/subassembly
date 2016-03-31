package com.sooncode.subassembly;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.text.html.StyleSheet;

public class SMSGateway {
  
  public String send2(String telephone,String msg){
    StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?"); // 创建StringBuffer对象用来操作字符串
    String  code = null;
    try {
        code = randomCode();
    sb.append("apikey=2eee080d1703f1d404de7f289b94c27f");  // APIKEY
    sb.append("&username=grts"); //用户名
    sb.append("&password=dxfs20151209"); // 向StringBuffer追加密码
    sb.append("&mobile="+telephone); // 向StringBuffer追加手机号码
    sb.append("&content="+URLEncoder.encode(msg+code+"【好学生】","GBK"));// 向StringBuffer追加消息内容转URL标准码
    
    URL url = new URL(sb.toString());
    System.out.println("SMSGateway.send2()"+sb.toString());
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开url连接
    connection.setRequestMethod("POST");// 设置url请求方式 ‘get’ 或者 ‘post’
    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); // 发送
    System.out.println(in.readLine()); // 输出结果
   
    } catch (Exception e) {
      e.printStackTrace();
    }
    return code;
  }
  
  
  public  String randomCode() {
    String sRand = "";
    for (int i = 0; i < 6; i++) {
        String rand = getRandomChar();
        sRand = sRand.concat(rand);
    }
    return sRand;
}

private String getRandomChar() {
    String randChar = "";
    randChar = String.valueOf(Math.round(Math.random() * 9));
    return randChar;
}

public static void main(String[] args) {
  SMSGateway s = new SMSGateway();
 String code = s.send2("13681004142", "欢迎使用好学生，您的验证码为:");
 System.out.println(code);
}
}