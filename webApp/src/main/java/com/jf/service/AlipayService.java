package com.jf.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.jf.common.utils.Config;
import com.jf.common.utils.alipayConfigUtil;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年10月27日 下午3:47:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
public class AlipayService {
		
	// 初始化alipayClient对象
    public static AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
			alipayConfigUtil.getProperty("ALIPAY_APP_ID"), alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY"),
			AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
			alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.SIGN_TYPE_RSA2);
	
    public static AlipayTradeWapPayResponse pay(String content, String type){
    	try {
    		String notifyUrl = alipayConfigUtil.getProperty("ALIPAY_NOTIFY_URL");
    		if("2".equals(type)){
    			notifyUrl = alipayConfigUtil.getProperty("ALIPAY_DEPOSIT_NOTIFY_URL");
    		}
    		// 创建API对应的request
	         AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
	         // 在公共参数中设置回跳和通知地址(应用提供给支付宝的请求路径),沙箱模式中不起作用(不知道是不是这个原因,支付宝技术客服告诉我正式上线后就没问题)
	         alipayRequest.setReturnUrl(Config.getProperty("mUrl")+"/xgbuy/views/?redirect_url=b3JkZXIvaW5kZXguaHRtbD9yZWRpcmVjdF9kYXRhPXR5cGU9");
	         alipayRequest.setNotifyUrl(notifyUrl);
	         // 填充业务参数
	         alipayRequest.setBizContent(content);
	         AlipayTradeWapPayResponse alipayResponse = alipayClient
	                 .pageExecute(alipayRequest);
	         return alipayResponse;
		} catch (Exception e) {
			return new AlipayTradeWapPayResponse();
		}
		         
	}

}
