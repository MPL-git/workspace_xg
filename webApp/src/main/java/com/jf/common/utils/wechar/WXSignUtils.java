package com.jf.common.utils.wechar;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.jf.common.utils.MD5Util;
import com.jf.common.utils.wecharConfigUtil;

/**
 * 
 * @author chenwf
 *
 */
public class WXSignUtils {
	/**
	 * sign
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding,SortedMap<String,Object> parameters,String key){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
//		System.out.println("key:"+key);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	public static void main(String[] args)
	  {
		String appId = "wxc09b2aa0853d648f";
		String mchId = "1490013172";
		String notifyUrl = wecharConfigUtil.getProperty("WX_NOTIFY_URL");
		String nonceStr = "s9VvKmZln6CwD8hupCEWEER5L3pZQttS";
		String body = "XG201711161702176709";
		String outTradeNo = "XG201711161702176709";
		int totalFee = 100;
		String timeStart = "20171116170217";
		String timeExpire = "20171116173217";
		String tradeType = "JSAPI";
		String spbillCreateIp = "120.41.67.242";
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("notify_url", notifyUrl);
		params.put("nonce_str", nonceStr);
		params.put("body", body);
		params.put("out_trade_no", outTradeNo);
		params.put("total_fee", totalFee);
		params.put("time_start", timeStart);
		params.put("time_expire", timeExpire);
		params.put("trade_type", tradeType);
		params.put("spbill_create_ip", spbillCreateIp);
		
		String sign = WXSignUtils.createSign("UTF-8", params, "1490013172");
//		System.out.println(sign);
	  }

}
