package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.jf.common.utils.HttpXmlUtils;
import com.jf.common.utils.JdomParseXmlUtils;
import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.alipayConfigUtil;
import com.jf.common.utils.wecharConfigUtil;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.entity.CombineOrder;
import com.jf.entity.pay.Unifiedorder;
import com.jf.entity.pay.UnifiedorderResult;

@Service
@Transactional
public class PayService {
	private static Logger logger = LoggerFactory.getLogger(PayService.class);
	@Autowired
	private OrderService orderService;
	
	public boolean queryWxOrAliPaySuccess(CombineOrder combineOrder) {
		boolean isPaySuccess = false;
		Integer paymentId = combineOrder.getPaymentId();//5微信H5 6支付宝H5
		if(paymentId == 5){
			UnifiedorderResult result = queryWxPayOrderInfo(combineOrder);
			if(result != null && !StringUtil.isBlank(result.getTrade_state()) && "SUCCESS".equals(result.getTrade_state())){
				isPaySuccess = true;
			}else{
				logger.info("抖音微信支付失败");
				if(result != null){
					logger.info("失败原因【"+result.getReturn_msg()+result.getReturn_msg()+"】");
				}
			}
		}else if(paymentId == 6){
			AlipayTradeQueryResponse result = queryAliPayOrderInfo(combineOrder);
			if(result != null && !StringUtil.isBlank(result.getTradeStatus()) && "TRADE_SUCCESS".equals(result.getTradeStatus())){
				isPaySuccess = true;
			}else{
				logger.info("抖音支付宝支付失败");
				if(result != null){
					logger.info("失败原因【"+result.getMsg()+result.getSubMsg()+"】");
				}
			}
		}
		return isPaySuccess;
	}
	
	/**
	 * 支付宝订单查询
	 */
	public AlipayTradeQueryResponse queryAliPayOrderInfo(CombineOrder combineOrder) {
		AlipayTradeQueryResponse response = null;
		try {
			String paymentNo = combineOrder.getPaymentNo() == null ? "" : combineOrder.getPaymentNo();
			String combineOrderCode = combineOrder.getPaymentNo();
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					alipayConfigUtil.getProperty("ALIPAY_APP_ID"), 
					alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY"),
					AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
					alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"), AlipayConstants.SIGN_TYPE_RSA2);
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			Map<String, Object> pcont = new HashMap<>();
			// 支付业务请求参数
			pcont.put("out_trade_no", combineOrderCode); // 商户订单号
			pcont.put("trade_no", paymentNo);// 支付宝交易号
			pcont.put("org_pid", "");
			String bizContent = JSON.toJSONString(pcont);// 业务请求参数
			request.setBizContent(bizContent);
			response = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			logger.error("支付宝H5订单查询失败");
		}
		return response;
		
	}
	/**
	 * 微信订单查询
	 * @param combineOrder 
	 */
	public UnifiedorderResult queryWxPayOrderInfo(CombineOrder combineOrder) {
		String paymentNo = combineOrder.getPaymentNo() == null ? "" : combineOrder.getPaymentNo();
		String appId = wecharConfigUtil.getProperty("WX_APP_ID");
		String mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
		String nonceStr = RandCharsUtils.getRandomString(32);
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("transaction_id", paymentNo);
		params.put("out_trade_no", combineOrder.getCombineOrderCode());
		params.put("nonce_str", nonceStr);
		String sign = WXSignUtils.createSign("UTF-8", params, wecharConfigUtil.getProperty("WX_KEY"));
		
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appId);
		unifiedorder.setMch_id(mchId);
		unifiedorder.setTransaction_id(paymentNo);
		unifiedorder.setNonce_str(nonceStr);
		unifiedorder.setSign(sign);
		
		//获取xml信息
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		String wxUrl = wecharConfigUtil.getProperty("WX_H5_QUERY_ORDER_URL");
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
		List<Element> list = JdomParseXmlUtils.getWXPayResultElement(weixinPost);
		UnifiedorderResult unifiedorderResult = orderService.getWxUnifiedorderResult(list);		
		return unifiedorderResult;
	}
}
