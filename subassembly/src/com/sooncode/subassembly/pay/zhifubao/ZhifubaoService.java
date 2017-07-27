package com.sooncode.subassembly.pay.zhifubao;

 
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sooncode.subassembly.path.PathUtil;
import com.sooncode.subassembly.pay.zhifubao.config.AlipayConfig;
import com.sooncode.subassembly.pay.zhifubao.config.AlipayOrder;
import com.sooncode.subassembly.pay.zhifubao.sign.RSA;
import com.sooncode.subassembly.pay.zhifubao.util.AlipayCore;
import com.sooncode.subassembly.properties.PropertiesUtil;

 
@Service
public class ZhifubaoService {

	private  static Map<String,AlipayConfig> map;
	
	static{
		PropertiesUtil pu = new PropertiesUtil(PathUtil.getClassPath()+"zhifubao.properties");
		String notify_url = pu.getString("NOTIFY_URL");
		Map<String,AlipayConfig> m = new HashMap<String, AlipayConfig>();
		AlipayConfig xiaoxue = new AlipayConfig();
		xiaoxue.setApp_id(pu.getString("XIAOXUE_APP_ID"));
		xiaoxue.setPrivate_key(pu.getString("XIAOXUE_APP_SECRET"));
		xiaoxue.setPartner(pu.getString("XIAOXUE_PARTNER"));
		xiaoxue.setAli_public_key(pu.getString("XIAOXUE_PUBLIC_KEY"));
		xiaoxue.setRsa(pu.getString("XIAOXUE_RSA"));
		xiaoxue.setSeller_id(pu.getString("XIAOXUE_SELLER_ID"));
		xiaoxue.setNotify_url(notify_url);
		m.put("XIAO_XUE",xiaoxue);
		
		
		AlipayConfig chuzhong = new AlipayConfig();
		chuzhong.setApp_id(pu.getString("CHUZHONG_APP_ID"));
		chuzhong.setPrivate_key(pu.getString("CHUZHONG_APP_SECRET"));
		chuzhong.setPartner(pu.getString("CHUZHONG_PARTNER"));
		chuzhong.setAli_public_key(pu.getString("CHUZHONG_PUBLIC_KEY"));
		chuzhong.setRsa(pu.getString("CHUZHONG_RSA"));
		chuzhong.setNotify_url(notify_url);
		chuzhong.setSeller_id(pu.getString("CHUZHONG_SELLER_ID"));
		m.put("CHU_ZHONG",chuzhong);
		
		AlipayConfig gaozhong = new AlipayConfig();
		gaozhong.setApp_id(pu.getString("GAOZHONG_APP_ID"));
		gaozhong.setPrivate_key(pu.getString("GAOZHONG_APP_SECRET"));
		gaozhong.setPartner(pu.getString("GAOZHONG_PARTNER"));
		gaozhong.setAli_public_key(pu.getString("GAOZHONG_PUBLIC_KEY"));
		gaozhong.setRsa(pu.getString("GAOZHONG_RSA"));
		gaozhong.setNotify_url(notify_url);
		gaozhong.setSeller_id(pu.getString("GAOZHONG_SELLER_ID"));
		m.put("GAO_ZHONG",gaozhong);
		
	 
		map = m;
	}
	
	/**
	 * 
	 * @param type
	 * @param ao
	 * @return orderInfo
	 */
	@SuppressWarnings("deprecation")
	public static String getOrderInfo(String type ,AlipayOrder ao){
		  AlipayConfig ac = map.get(type);
		
		  Map<String, String> params = new HashMap<>();
		  
		  params.put("service","\"mobile.securitypay.pay\"");
		  params.put("partner","\""+ac.getPartner()+"\"");
		  params.put("_input_charset","\"UTF-8\"");
		  
		 
		  params.put("notify_url","\""+ac.getNotify_url()+"\"");
		  params.put("out_trade_no","\""+ao.getOut_trade_no()+"\"");
		  params.put("subject","\""+ao.getSubject()+"\"");
		  
		  params.put("payment_type","\"1\"");
		  params.put("seller_id","\""+ac.getSeller_id()+"\"");
		  
		  params.put("total_fee","\""+ao.getTotal_fee()+"\"");
		  
		  params.put("body","\""+ao.getBody()+"\"");
		 
		  String string = AlipayCore.createLinkString(params);
		  String sign = RSA.sign(string, ac.getRsa(), "UTF-8");
		  
		 
          
		  params= new HashMap<>();
		  params.put("service","\\\"mobile.securitypay.pay\\\"");
		  params.put("partner","\\\""+ac.getPartner()+"\\\"");
		  params.put("_input_charset","\\\"UTF-8\\\"");
		  params.put("notify_url","\\\""+ac.getNotify_url()+"\\\"");
		  params.put("out_trade_no","\\\""+ao.getOut_trade_no()+"\\\"");
		  params.put("subject","\\\""+ao.getSubject()+"\\\"");
		  params.put("payment_type","\\\"1\\\"");
		  params.put("seller_id","\\\""+ac.getSeller_id()+"\\\"");
		 
		  params.put("total_fee","\\\""+ao.getTotal_fee()+"\\\"");
		  params.put("body","\\\""+ao.getBody()+"\\\"");
		  
		  params.put("sign_type","\\\"RSA\\\"");
		  sign = URLEncoder.encode(sign);
		  params.put("sign","\\\""+sign+"\\\"");
		   
		  String orderInfo = AlipayCore.createLinkString(params);
		   
		  return orderInfo;
	}
	
	
	
	
	
	public static void main(String[] args) {
		AlipayOrder ao = new AlipayOrder();
		ao.setBody("测试测试");
		ao.setOut_trade_no("0819145412-6177");
		ao.setSubject("测试");
		ao.setTotal_fee("0.01");
		
		String str = getOrderInfo("CHU_ZHONG",ao);
		
		System.out.println("ZhifubaoService.main()  :\r\n"+str);
		
	}
	
	 
}
