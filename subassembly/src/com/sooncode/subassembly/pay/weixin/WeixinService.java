package com.sooncode.subassembly.pay.weixin;

import java.util.TreeMap;

 
import com.sooncode.subassembly.pay.weixin.module.Orderquery;
import com.sooncode.subassembly.pay.weixin.module.OrderqueryReturn;
import com.sooncode.subassembly.pay.weixin.module.UnifiedOrder;
import com.sooncode.subassembly.pay.weixin.module.UnifiedOrderReturn;

import java.util.Map.Entry;

 

 

/**
 * 微信支付 服务
 * 
 * @author pc
 *
 */
public class WeixinService {
	/**
	 * 统一下单URL
	 */
	private static String unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 订单查询URL
	 */
	private static String orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

	/**
	 * 统一下单(APP 支付)
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static UnifiedOrderReturn unifiedorder4App(UnifiedOrder uo) {

		TreeMap<String, String> map = new TreeMap<>();

		map.put("appid", uo.getAppid());
		map.put("mch_id", uo.getMch_id());
		map.put("nonce_str", uo.getNonce_str());

		map.put("out_trade_no", uo.getOut_trade_no());
		map.put("body", uo.getBody());
		map.put("total_fee", uo.getTotal_fee());

		map.put("notify_url", uo.getNotify_url());
		map.put("trade_type", "APP");
		map.put("spbill_create_ip", uo.getSpbill_create_ip());

		String sign = WeixinUtil.getSign(map, uo.getKey());

		StringBuffer xml = new StringBuffer();
		xml.append("<xml>");

		xml.append("<appid>" + uo.getAppid() + "</appid>");
		xml.append("<mch_id>" + uo.getMch_id() + "</mch_id>");
		xml.append("<body><![CDATA[" + uo.getBody() + "]]></body>");// <![CDATA[诊断报告]]>

		xml.append("<nonce_str>" + uo.getNonce_str() + "</nonce_str>");
		xml.append("<out_trade_no>" + uo.getOut_trade_no() + "</out_trade_no>");
		xml.append("<spbill_create_ip>" + uo.getSpbill_create_ip() + "</spbill_create_ip>");

		xml.append("<notify_url>" + uo.getNotify_url() + "</notify_url>");
		xml.append("<trade_type>" + "APP" + "</trade_type>");
		xml.append("<total_fee>" + uo.getTotal_fee() + "</total_fee>");

		xml.append("<sign><![CDATA[" + sign + "]]></sign>");

		xml.append("</xml>");

		String reslutXml = WeixinUtil.post4xml(unifiedorderUrl, xml.toString());

		UnifiedOrderReturn uor = new UnifiedOrderReturn();

		ParaXml px = new ParaXml(reslutXml);
		uor.setResult_code(px.getValue("result_code"));
		uor.setReturn_code(px.getValue("return_code"));

		if (uor.getResult_code()!=null && uor.getResult_code().equals("SUCCESS") && uor.getReturn_code().equals("SUCCESS")) {
			uor.setAppid(px.getValue("appid"));
			uor.setMch_id(px.getValue("mch_id"));
			uor.setSign(px.getValue("sign"));
			uor.setPrepay_id(px.getValue("prepay_id"));
			uor.setTrade_type(px.getValue("trade_type"));
			uor.setReturn_msg(px.getValue("return_msg"));
			uor.setNonce_str(px.getValue("nonce_str"));
		}

		return uor;
	}

	/**
	 * 统一下单 (扫描支付)
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static UnifiedOrderReturn unifiedorder(UnifiedOrder uo,String weixinOpenid) {

		TreeMap<String, String> map = new TreeMap<>();

		map.put("appid", uo.getAppid());
		map.put("mch_id", uo.getMch_id());
		map.put("nonce_str", uo.getNonce_str());
		map.put("openid", weixinOpenid);
		map.put("out_trade_no", uo.getOut_trade_no());
		map.put("body", uo.getBody());
		map.put("total_fee", uo.getTotal_fee());

		map.put("notify_url", uo.getNotify_url());
		map.put("trade_type", "JSAPI");
		map.put("spbill_create_ip", uo.getSpbill_create_ip());

		String sign = WeixinUtil.getSign(map, uo.getKey());

		StringBuffer xml = new StringBuffer();
		xml.append("<xml>");

		xml.append("<appid>" + uo.getAppid() + "</appid>");
		xml.append("<mch_id>" + uo.getMch_id() + "</mch_id>");
		xml.append("<body><![CDATA[" + uo.getBody() + "]]></body>");// <![CDATA[诊断报告]]>
		xml.append("<openid>" + weixinOpenid + "</openid>");
		xml.append("<nonce_str>" + uo.getNonce_str() + "</nonce_str>");
		xml.append("<out_trade_no>" + uo.getOut_trade_no() + "</out_trade_no>");
		xml.append("<spbill_create_ip>" + uo.getSpbill_create_ip() + "</spbill_create_ip>");

		xml.append("<notify_url>" + uo.getNotify_url() + "</notify_url>");
		xml.append("<trade_type>" +"JSAPI" + "</trade_type>");
		xml.append("<total_fee>" + uo.getTotal_fee() + "</total_fee>");

		xml.append("<sign><![CDATA[" + sign + "]]></sign>");

		xml.append("</xml>");

		String reslutXml = WeixinUtil.post4xml(unifiedorderUrl, xml.toString());

		UnifiedOrderReturn uor = new UnifiedOrderReturn();

		ParaXml px = new ParaXml(reslutXml);
		uor.setResult_code(px.getValue("result_code"));
		uor.setReturn_code(px.getValue("return_code"));

		if (uor.getResult_code()!=null && uor.getResult_code().equals("SUCCESS") && uor.getReturn_code().equals("SUCCESS")) {
			uor.setAppid(px.getValue("appid"));
			uor.setMch_id(px.getValue("mch_id"));
			uor.setSign(px.getValue("sign"));
			uor.setPrepay_id(px.getValue("prepay_id"));
			uor.setTrade_type(px.getValue("trade_type"));
			uor.setReturn_msg(px.getValue("return_msg"));
			uor.setNonce_str(px.getValue("nonce_str"));
		}

		return uor;
	}
	
	
	/**
	 * 微信支付 查询订单
	 */
	public static OrderqueryReturn orderquery(Orderquery o) {

		TreeMap<String, String> map = new TreeMap<>();
		map.put("appid", o.getAppid());
		map.put("mch_id", o.getMch_id());
		map.put("nonce_str", o.getNonce_str());
		map.put("out_trade_no", o.getOut_trade_no());
		String sign = WeixinUtil.getSign(map, o.getKey());

		map.put("sign", sign);
		String xml = "<xml>";
		for (Entry<String, String> ent : map.entrySet()) {
			xml = xml + "<" + ent.getKey() + ">" + ent.getValue() + "</" + ent.getKey() + ">";
		}
		xml = xml + "</xml>";

		String reslutXml = WeixinUtil.post4xml(orderqueryUrl, xml.toString());
		ParaXml px = new ParaXml(reslutXml);

		OrderqueryReturn or = new OrderqueryReturn();

		or.setResult_code(px.getValue("result_code"));
		or.setReturn_code(px.getValue("return_code"));
		if ( or.getReturn_code().equals("SUCCESS") && or.getResult_code().equals("SUCCESS") ) {

			or.setAppid(px.getValue("appid"));
			or.setOut_trade_no(px.getValue("out_trade_no"));
			or.setTrade_state(px.getValue("trade_state"));
			
			or.setTrade_state_desc(px.getValue("trade_state_desc"));
			or.setNonce_str(px.getValue("nonce_str"));
			or.setReturn_msg(px.getValue("return_msg"));
			
			or.setMch_id(px.getValue("mch_id"));
			or.setSign(px.getValue("sign"));
		}
		return or;
	}

	public static void main(String[] args) {

		UnifiedOrder u = new UnifiedOrder();
		u.setAppid("wxfd22896c1e4be407");
		u.setMch_id("1335354601");
		u.setKey("yjkjhaoxueshenggaozhong201604270");

		u.setBody("诊断报告");
		u.setNotify_url("http://220.249.22.170:8080/fighting/weixin/notify");
		u.setOut_trade_no("20160504132625000001");

		u.setNonce_str(WeixinUtil.getUUID());
		u.setSpbill_create_ip("220.249.22.170");
		u.setTotal_fee("1");
		u.setTrade_type("APP");

		// System.out.println("WeixinService.main()"+unifiedorder(u));

		Orderquery o = new Orderquery();
		o.setAppid("wxfd22896c1e4be407");
		o.setKey("yjkjhaoxueshenggaozhong201604270");
		o.setMch_id("1335354601");
		o.setNonce_str(WeixinUtil.getUUID());
		o.setOut_trade_no("20160504132625000001");
		orderquery(o);
        
	}

}
