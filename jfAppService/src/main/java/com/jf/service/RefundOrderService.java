package com.jf.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.HttpXmlUtils;
import com.jf.common.utils.JdomParseXmlUtils;
import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.alipay.PayUtil;
import com.jf.common.utils.alipayConfigUtil;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.common.utils.wecharConfigUtil;
import com.jf.dao.RefundOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.OrderDtl;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import com.jf.entity.pay.UnifiedorderResult;
import net.sf.json.JSONObject;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月10日 下午2:45:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class RefundOrderService extends BaseService<RefundOrder, RefundOrderExample> {
	
	@Resource
	private RefundOrderMapper refundOrderMapper;
	
	@Resource
	private RefundOrderService refundOrderService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	/**
	 * 订单明细
	 */
	@Resource
	private OrderDtlService orderDtlService;

	@Resource
	public void setRefundOrderMapper(RefundOrderMapper refundOrderMapper) {
		this.setDao(refundOrderMapper);
		this.refundOrderMapper = refundOrderMapper;
	}

	public void getZfbRefundQuery(RefundOrder refundOrder, CombineOrder combineOrder) {
		try {
			Map<String,String> param = new HashMap<String,String>();
			// 公共请求参数
			param.put("app_id", alipayConfigUtil.getProperty("ALIPAY_APP_ID"));// 商户订单号
			param.put("method", "alipay.trade.fastpay.refund.query");// 交易金额
			param.put("format", AlipayConstants.FORMAT_JSON);
			param.put("charset", AlipayConstants.CHARSET_UTF8);
			param.put("sign_type", AlipayConstants.SIGN_TYPE_RSA2);
			param.put("timestamp", DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
			param.put("version", "1.0");
			param.put("notify_url", alipayConfigUtil.getProperty("ALIPAY_NOTIFY_URL")); // 支付宝服务器主动通知商户服务
			param.put("sign_type", AlipayConstants.SIGN_TYPE_RSA2);

			Map<String, Object> pcont = new HashMap<>();
			// 支付业务请求参数
			pcont.put("out_trade_no", combineOrder.getCombineOrderCode()); // 商户订单号
			pcont.put("out_request_no", refundOrder.getRefundReqNo());
			param.put("biz_content", JSON.toJSONString(pcont)); // 业务请求参数
			param.put("sign", PayUtil.getSign(param, alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY")));
			
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					alipayConfigUtil.getProperty("ALIPAY_APP_ID"),
					alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY"),
					AlipayConstants.FORMAT_JSON,
					AlipayConstants.CHARSET_UTF8,
					alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"),
					AlipayConstants.SIGN_TYPE_RSA2);
			AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
			request.setBizContent(JSON.toJSONString(pcont));
			AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				String body = response.getBody();
				if(!StringUtil.isBlank(body)){
					JSONObject json=JSONObject.fromObject(body);
					JSONObject reqDataJson = json.getJSONObject("alipay_trade_fastpay_refund_query_response");
					if(reqDataJson != null){
						String code = reqDataJson.getString("code");
						if(code.equals("10000")){
							if(reqDataJson.containsKey("out_request_no") && !StringUtil.isBlank(reqDataJson.getString("out_request_no"))){
								if(reqDataJson.getString("out_request_no").equals(refundOrder.getRefundReqNo())){
									updateRefundInfo(refundOrder);
								}
							}
						}
					}
				}
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void getWxRefundQuery(RefundOrder refundOrder, CombineOrder combineOrder) {
		String appId = wecharConfigUtil.getProperty("WX_APP_ID");
		String mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
		String nonceStr = RandCharsUtils.getRandomString(32);
		
		SortedMap<String, Object> params = new TreeMap<String, Object>();
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("nonce_str", nonceStr);
		params.put("refund_id", refundOrder.getWxRefundId());
		String sign = WXSignUtils.createSign("UTF-8", params);
		String xmlInfo = "<xml>"
						+"<appid>"+appId+"</appid>"
						+"<mch_id>"+mchId+"</mch_id>"
						+"<nonce_str>"+nonceStr+"</nonce_str>"
						+"<out_refund_no></out_refund_no>"
						+"<out_trade_no></out_trade_no>"
						+"<refund_id>"+refundOrder.getWxRefundId()+"</refund_id>"
						+"<transaction_id></transaction_id>"
						+"<sign>"+sign+"</sign>"
						+"</xml>";
		String wxUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		List<Element> list = JdomParseXmlUtils.getWXPayResultElement(weixinPost);
		UnifiedorderResult result = getWxRefundQueryResult(list);
		if(result != null){
			if(result.getReturn_code().equals("SUCCESS")){
				if(result.getResult_code().equals("SUCCESS")){
					if(appId.equals(result.getAppid()) && mchId.equals(result.getMch_id())){
						updateRefundInfo(refundOrder);
					}
				}
			}
		}
	}
	
	private void updateRefundInfo(RefundOrder refundOrder) {
		Date date = new Date();
		refundOrder.setStatus("1");
		refundOrder.setSuccessDate(date);
		refundOrder.setUpdateDate(date);
		refundOrderService.updateByPrimaryKeySelective(refundOrder);
		//修改售后单据状态
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(refundOrder.getServiceOrderId());
		customerServiceOrderService.updateRefundSuccess(customerServiceOrder,"退款成功");
		if ("A".equals(customerServiceOrder.getServiceType())||"B".equals(customerServiceOrder.getServiceType())){
			OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
			orderDtl.setRefundDate(refundOrder.getSuccessDate());
			orderDtl.setUpdateDate(date);
			orderDtlService.updateByPrimaryKeySelective(orderDtl);
		}
	}
	
	
	private UnifiedorderResult getWxRefundQueryResult(List<Element> list) {
		UnifiedorderResult result = null;
		if (list != null && list.size() > 0) {
			result = new UnifiedorderResult();
			for (Element element : list) {

				if ("return_code".equals(element.getName())) {
					result.setReturn_code(element.getText());
				}

				if ("return_msg".equals(element.getName())) {
					result.setReturn_msg(element.getText());
				}

				if ("appid".equals(element.getName())) {
					result.setAppid(element.getText());
				}

				if ("mch_id".equals(element.getName())) {
					result.setMch_id(element.getText());
				}
				
				if ("device_info".equals(element.getName())) {
					result.setDevice_info(element.getText());
				}

				if ("nonce_str".equals(element.getName())) {
					result.setNonce_str(element.getText());
				}

				if ("sign".equals(element.getName())) {
					result.setSign(element.getText());
				}

				if ("err_code".equals(element.getName())) {
					result.setErr_code(element.getText());
				}

				if ("err_code_des".equals(element.getName())) {
					result.setErr_code_des(element.getText());
				}
				
				if ("result_code".equals(element.getName())) {
					result.setResult_code(element.getText());
				}
			}
		}
		return result;
	}
}
