package com.jf.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;

import com.jf.common.ext.RegCondition;
import com.jf.entity.*;
import com.jf.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JdomParseXmlUtils;
import com.jf.common.utils.RandCharsUtils;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.WeixinUtil;
import com.jf.common.utils.alipayConfigUtil;
import com.jf.common.utils.wecharConfigUtil;
import com.jf.common.utils.wechar.WXSignUtils;
import com.jf.entity.pay.AlipayRefundModel;
import com.jf.entity.pay.WxRefundModel;
import com.jf.entity.pay.WxRefundResuletModel;

/**
  * 退款日内
  * @author  chenwf: 
  * @date 创建时间：2017年7月10日 下午3:33:35 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@RegCondition
@Component
public class OrderRefundTask {
	
	private static Logger logger = LoggerFactory.getLogger(OrderRefundTask.class);

    @Resource
    private CombineOrderService combineOrderService;
    @Resource
    private SubOrderService subOrderService;
    @Resource
    private OrderDtlService orderDtlService;
    @Resource
    private RefundOrderService refundOrderService;
    @Resource
	private CommonService commonService;
    @Resource
	private CustomerServiceOrderService customerServiceOrderService;
    @Resource
    private CombineDepositOrderService CombineDepositOrderService;
	@Resource
	private SubDepositOrderService subDepositOrderService;

	@Autowired
	private DesignTaskRefundOrderService designTaskRefundOrderService;

	@Autowired
	private DesignTaskOrderService designTaskOrderService;

    @Scheduled(cron="0 0/30 * * * ?")
	public synchronized void orderRefund(){
        logger.info("订单退款:start");
        List<RefundOrder> refundOrders = refundOrderService.findList("1",null);
        if(CollectionUtils.isNotEmpty(refundOrders)){
        	logger.info("扫描到的可退款数：{}", refundOrders.size());
        	for (RefundOrder refundOrder : refundOrders) {
        		try {
					orderRefundInfo(refundOrder);
				} catch (Exception e) {
					e.printStackTrace();
		    		logger.info(e.getMessage());
					logger.info("订单退款失败:end");
				}
			}
        }
        logger.info("订单退款:end");
    }
    
    public void orderRefundInfo(RefundOrder refundOrder) throws Exception{
		logger.info("订单退款:start");
		String orderType = refundOrder.getOrderType();
		String refundReqNo = refundOrder.getRefundReqNo();
		Integer combineOrderId = refundOrder.getCombineOrderId();
		Integer serviceOrderId = refundOrder.getServiceOrderId();
		String orderCode = "";//子订单或预售子订单号
		String paymentNo = "";//商户号
		Integer paymentId = null;//支付渠道
		BigDecimal combinePayAmount = new BigDecimal("0");//实付总金额
		String customerStatus = "";//售后状态
		String refundReason = "";//退款理由
		Map<String,Integer> params = new HashMap<String,Integer>();
		//支付订单退款
		params.put("combineOrderId", combineOrderId);
		params.put("serviceOrderId", serviceOrderId);
		List<OrderDtlCustom> orderDtlCustoms = null;
		if(refundOrder.getServiceType().equals("D")){//D代表直赔，没有订单明细id
			orderDtlCustoms = orderDtlService.getOrderInfoD(params);
		}else{//AB代表退款和退货
			orderDtlCustoms = orderDtlService.getOrderInfoAB(params);
		}
		if(CollectionUtils.isNotEmpty(orderDtlCustoms)){
			OrderDtlCustom orderDtlCustom = orderDtlCustoms.get(0);
			orderCode = orderDtlCustom.getSubOrderCode();
			paymentNo = orderDtlCustom.getPaymentNo();
			paymentId = orderDtlCustom.getPaymentId();
			combinePayAmount = orderDtlCustom.getCombinePayAmount();
			customerStatus = orderDtlCustom.getCustomerStatus();
			refundReason = orderDtlCustom.getReason();
		}
        if(!StringUtil.isBlank(paymentNo)){
	        if(customerStatus.equals(Const.CUSTOMER_ORDER_STATUS_REJECT)
					|| customerStatus.equals(Const.CUSTOMER_ORDER_STATUS_SUCCESS)
					|| customerStatus.equals(Const.CUSTOMER_ORDER_STATUS_CANCEL)){
				refundOrder.setFailedReason("售后不在进行中，退款失败，退售后标识"+customerStatus);
				refundOrder.setTryTimes(refundOrder.getTryTimes()+1);
				refundOrder.setStatus("2");
				refundOrder.setUpdateDate(new Date());
				refundOrderService.updateByPrimaryKeySelective(refundOrder);
				return;
			}
    		if(StringUtil.isBlank(refundReqNo)){
    			refundReqNo = commonService.getSequence("refundReqNo").toString();
    			refundOrder.setRefundReqNo(refundReqNo);
    			refundOrderService.updateByPrimaryKeySelective(refundOrder);
    		}
			if(paymentId == 2 || paymentId == 5 || paymentId == 4 || paymentId == 7){
				WxRefundResuletModel wxRefundResuletModel = wxRefund(paymentNo,refundOrder,orderType,orderCode,combinePayAmount,paymentId);//微信退款
				if(wxRefundResuletModel.getReturn_code().equals("SUCCESS") 
						&& wxRefundResuletModel.getResult_code().equals("SUCCESS")){
					orderDtlService.updateRefundSuccessInfo(wxRefundResuletModel,null,refundOrder,"1");
					depositRefundOrderInfo(refundOrder.getServiceOrderId());//定金退款
					logger.info("微信订单"+orderCode+"申请成功,"+refundOrder.getRefundReqNo());
				}else{
					logger.info("微信订单"+orderCode+"退款失败,"+refundOrder.getRefundReqNo());
					refundOrder.setFailedReason(wxRefundResuletModel.getReturn_msg()+","+wxRefundResuletModel.getErr_code_des());
					refundOrder.setTryTimes(refundOrder.getTryTimes()+1);
					refundOrder.setStatus("2");
					refundOrder.setUpdateDate(new Date());
					refundOrderService.updateByPrimaryKeySelective(refundOrder);
				}
			}else if(paymentId == 1 || paymentId == 6){
				AlipayTradeRefundResponse response = zfbRefund(paymentNo,refundReason,refundOrder,orderType,orderCode);//支付宝退款
				if(response.isSuccess()){
					orderDtlService.updateRefundSuccessInfo(null,response,refundOrder,"2");
					depositRefundOrderInfo(refundOrder.getServiceOrderId());//定金退款
					logger.info("支付宝退款订单"+orderCode+"申请成功,"+refundOrder.getRefundReqNo());
				} else {
					logger.info("支付宝退款订单"+orderCode+"退款失败,"+refundOrder.getRefundReqNo());
					refundOrder.setFailedReason(response.getSubMsg());
					refundOrder.setTryTimes(refundOrder.getTryTimes()+1);
					refundOrder.setStatus("2");
					refundOrder.setUpdateDate(new Date());
					refundOrderService.updateByPrimaryKeySelective(refundOrder);
				}
			}else{
				 logger.info("找不到正确的退款:"+paymentId);
			}
        }
        logger.info("订单退款:end");
    }

	private AlipayTradeRefundResponse zfbRefund(String paymentNo, String refundReason, RefundOrder refundOrder, String orderType, String orderCode) throws AlipayApiException {
		AlipayRefundModel alipayRefundModel = buildAlipayRefundModel(paymentNo,refundReason,refundOrder);
		AlipayClient alipayClient = new DefaultAlipayClient(
				alipayConfigUtil.getProperty("ALIPAY_GATEWAY_URL"),
				alipayRefundModel.getApp_id(),
				alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY"),
				alipayRefundModel.getFormat(),
				alipayRefundModel.getCharset(),
				alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"),
				alipayRefundModel.getSign_type()
				);
		AlipayTradeRefundRequest req = new AlipayTradeRefundRequest();
		req.setBizContent(alipayRefundModel.getBiz_content());
		AlipayTradeRefundResponse response = alipayClient.execute(req);
		return response;
	}

	private void depositRefundOrderInfo(Integer serviceOrderId) throws Exception{
		String orderCode = "";//子订单或预售子订单号
		String paymentNo = "";//商户号
		Integer paymentId = null;//支付渠道
		BigDecimal combinePayAmount = new BigDecimal("0");//实付总金额
		String refundReason = "";//退款理由
		List<RefundOrder> refundOrders = refundOrderService.findList("2",serviceOrderId);
		if(CollectionUtils.isNotEmpty(refundOrders)){
			for (RefundOrder refundOrder:refundOrders) {
				RefundOrder depositRefundOrder = refundOrder;
				String orderType = depositRefundOrder.getOrderType();
				//预售订单退款
				Map<String,Integer> depositParams = new HashMap<String,Integer>();
				depositParams.put("combineDepositOrderId", depositRefundOrder.getCombineDepositOrderId());
				depositParams.put("serviceOrderId", depositRefundOrder.getServiceOrderId());
				List<CombineDepositOrderCustom> combineDepositOrders = orderDtlService.getDepositRefundOrderInfo(depositParams);
				if(CollectionUtils.isNotEmpty(combineDepositOrders)){
					CombineDepositOrderCustom combineDepositOrderCustom = combineDepositOrders.get(0);
					orderCode = combineDepositOrderCustom.getCombineDepositOrderCode();
					paymentNo = combineDepositOrderCustom.getPaymentNo();
					paymentId = combineDepositOrderCustom.getPaymentId();
					combinePayAmount = combineDepositOrderCustom.getTotalDeposit();
					refundReason = combineDepositOrderCustom.getReason();
					String refundReqNo = depositRefundOrder.getRefundReqNo();
					if(StringUtil.isBlank(refundReqNo)){
						refundReqNo = commonService.getSequence("refundReqNo").toString();
						depositRefundOrder.setRefundReqNo(refundReqNo);
						refundOrderService.updateByPrimaryKeySelective(depositRefundOrder);
					}
					if(paymentId == 2 || paymentId == 5 || paymentId == 4 || paymentId == 7){
						WxRefundResuletModel wxRefundResuletModel = wxRefund(paymentNo,depositRefundOrder,orderType,orderCode,combinePayAmount,paymentId);//微信退款
						if(wxRefundResuletModel.getReturn_code().equals("SUCCESS")
								&& wxRefundResuletModel.getResult_code().equals("SUCCESS")){
							//更新预售单据状态，添加日志
							subDepositOrderService.updateSubDepositOrderStatus(combineDepositOrderCustom.getOrderDtlId(),new Date(),Const.SUB_DEPOSIT_STATUS_TAIL_PAID,Const.SUB_DEPOSIT_STATUS_TRAN_REFUND);
							depositRefundOrder.setStatus("1");
							depositRefundOrder.setWxRefundId(wxRefundResuletModel.getRefund_id());
							depositRefundOrder.setSuccessDate(new Date());
							depositRefundOrder.setUpdateDate(new Date());
							refundOrderService.updateByPrimaryKeySelective(depositRefundOrder);
						}else{
							logger.info("微信订单"+orderCode+"定金退款失败,"+depositRefundOrder.getRefundReqNo());
							depositRefundOrder.setFailedReason(wxRefundResuletModel.getReturn_msg()+","+wxRefundResuletModel.getErr_code_des());
							depositRefundOrder.setTryTimes(depositRefundOrder.getTryTimes()+1);
							depositRefundOrder.setStatus("2");
							depositRefundOrder.setUpdateDate(new Date());
							refundOrderService.updateByPrimaryKeySelective(depositRefundOrder);
						}
					}else if(paymentId == 1 || paymentId == 6){
						AlipayTradeRefundResponse response = zfbRefund(paymentNo,refundReason,depositRefundOrder,orderType,orderCode);//支付宝退款
						if(response.isSuccess()){
							//更新预售单据状态，添加日志
							subDepositOrderService.updateSubDepositOrderStatus(combineDepositOrderCustom.getOrderDtlId(),new Date(),Const.SUB_DEPOSIT_STATUS_TAIL_PAID,Const.SUB_DEPOSIT_STATUS_TRAN_REFUND);
							depositRefundOrder.setStatus("1");
							depositRefundOrder.setZfbRefundId(response.getTradeNo());
							depositRefundOrder.setSuccessDate(new Date());
							depositRefundOrder.setUpdateDate(new Date());
							refundOrderService.updateByPrimaryKeySelective(depositRefundOrder);
						}else{
							logger.info("支付宝定金退款订单"+orderCode+"退款失败,"+depositRefundOrder.getRefundReqNo());
							depositRefundOrder.setFailedReason(response.getSubMsg());
							depositRefundOrder.setTryTimes(depositRefundOrder.getTryTimes()+1);
							depositRefundOrder.setStatus("2");
							depositRefundOrder.setUpdateDate(new Date());
							refundOrderService.updateByPrimaryKeySelective(depositRefundOrder);
						}
					}else{
						logger.info("找不到正确的退款:"+paymentId);
					}
				}
			}
		}
	}

	private WxRefundResuletModel wxRefund(String paymentNo, RefundOrder refundOrder, String orderType, String orderCode, BigDecimal combinePayAmount, Integer paymentId) throws Exception{
		String appId = "";
		String mchId = "";
		String certPath = "";
		String key = "";
		if(paymentId == 2 || paymentId == 5){
			//微信
			appId = wecharConfigUtil.getProperty("WX_APP_ID");
			mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
			key = wecharConfigUtil.getProperty("WX_KEY");
			certPath = wecharConfigUtil.getProperty("WX_CERT_PATH");
		}else if(paymentId == 4){
			//微信公众号
			appId = WeixinUtil.appId;
			mchId = WeixinUtil.mchtId;
			key = WeixinUtil.key;
			certPath = wecharConfigUtil.getProperty("WXGZH_CERT_PATH");
		}else{
			//微信小程序
			appId = WeixinUtil.xcxAppId;
			mchId = WeixinUtil.mchtId;
			key = WeixinUtil.key;
			certPath = wecharConfigUtil.getProperty("WXGZH_CERT_PATH");
		}
		WxRefundModel wxRefundModel = buildWxRefundModel(combinePayAmount,paymentNo,refundOrder,appId,mchId);
		SortedMap<String, Object> reqParams = new TreeMap<String, Object>();
		reqParams.put("appid", wxRefundModel.getAppid());
		reqParams.put("mch_id", wxRefundModel.getMch_id());
		reqParams.put("nonce_str", wxRefundModel.getNonce_str());
		reqParams.put("transaction_id", wxRefundModel.getTransaction_id());
		reqParams.put("out_refund_no", wxRefundModel.getOut_refund_no());
		reqParams.put("total_fee", wxRefundModel.getTotal_fee());
		reqParams.put("refund_fee", wxRefundModel.getRefund_fee());
		reqParams.put("notify_url", wxRefundModel.getNotify_url());
		String sign = WXSignUtils.createSign("UTF-8", reqParams,key);
		wxRefundModel.setSign(sign);
		String xmlInfo = createXmlInfo(wxRefundModel);
		String url = wecharConfigUtil.getProperty("WX_REFUND_ORDER");
		String result = doRefund(url, xmlInfo,mchId,certPath);
		List<Element> list = JdomParseXmlUtils.getWXPayResultElement(result);
		WxRefundResuletModel wxRefundResuletModel = buildWxRefundResuletModel(list);
		return wxRefundResuletModel;
	}
	
	private AlipayRefundModel buildAlipayRefundModel(String paymentNo,String refundReason, RefundOrder refundOrder) {
		AlipayRefundModel alipayRefundModel = new AlipayRefundModel();
		alipayRefundModel.setApp_id(alipayConfigUtil.getProperty("ALIPAY_APP_ID"));
		alipayRefundModel.setMethod("alipay.trade.refund");
		alipayRefundModel.setFormat(AlipayConstants.FORMAT_JSON);
		alipayRefundModel.setCharset(AlipayConstants.CHARSET_UTF8);
		alipayRefundModel.setSign_type(AlipayConstants.SIGN_TYPE_RSA2);
		alipayRefundModel.setTimestamp(DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		alipayRefundModel.setVersion("1.0");
		
		Map<String, Object> pcont = new HashMap<>();
		// 支付业务请求参数
		pcont.put("trade_no", paymentNo); // 商户订单号
		pcont.put("refund_amount", refundOrder.getRefundAmount());// 退款金额
		pcont.put("refund_reason", refundReason);// 退款理由
		pcont.put("out_request_no", refundOrder.getRefundReqNo());// 退款编号
		alipayRefundModel.setBiz_content(JSON.toJSONString(pcont));
		return alipayRefundModel;
	}

	private WxRefundResuletModel buildWxRefundResuletModel(List<Element> list) {
		WxRefundResuletModel result = null;
		if (list != null && list.size() > 0) {
			result = new WxRefundResuletModel();
			for (Element element : list) {
				if ("return_code".equals(element.getName())) {
					result.setReturn_code(element.getText());
				}
				
				if ("return_msg".equals(element.getName())) {
					result.setReturn_msg(element.getText());
				}
				
				if ("result_code".equals(element.getName())) {
					result.setResult_code(element.getText());
				}
				
				if ("err_code".equals(element.getName())) {
					result.setErr_code(element.getText());
				}

				if ("err_code_des".equals(element.getName())) {
					result.setErr_code_des(element.getText());
				}
				
				if ("appid".equals(element.getName())) {
					result.setAppid(element.getText());
				}

				if ("mch_id".equals(element.getName())) {
					result.setMch_id(element.getText());
				}
				
				if ("nonce_str".equals(element.getName())) {
					result.setNonce_str(element.getText());
				}
				
				if ("sign".equals(element.getName())) {
					result.setSign(element.getText());
				}
				
				if ("transaction_id".equals(element.getName())) {
					result.setTransaction_id(element.getText());
				}

				if ("out_trade_no".equals(element.getName())) {
					result.setOut_trade_no(element.getText());
				}
				
				if ("out_refund_no".equals(element.getName())) {
					result.setOut_refund_no(element.getText());
				}
				
				if ("refund_id".equals(element.getName())) {
					result.setRefund_id(element.getText());
				}
				
				if ("refund_fee".equals(element.getName())) {
					result.setRefund_fee(element.getText());
				}
				
				if ("settlement_refund_fee".equals(element.getName())) {
					result.setSettlement_refund_fee(element.getText());
				}
				
				if ("total_fee".equals(element.getName())) {
					result.setTotal_fee(element.getText());
				}
				
				if ("settlement_total_fee ".equals(element.getName())) {
					result.setSettlement_total_fee (element.getText());
				}
				
				if ("fee_type".equals(element.getName())) {
					result.setFee_type(element.getText());
				}
				
				if ("cash_fee".equals(element.getName())) {
					result.setCash_fee(element.getText());
				}
				
				if ("cash_fee_type".equals(element.getName())) {
					result.setCash_fee_type (element.getText());
				}
				
				if ("cash_refund_fee".equals(element.getName())) {
					result.setCash_refund_fee(element.getText());
				}
				
				if ("device_info".equals(element.getName())) {
					result.setCoupon_type_$n(element.getText());
				}
				
				if ("coupon_refund_fee".equals(element.getName())) {
					result.setCoupon_refund_fee(element.getText());
				}
				
				if ("coupon_refund_fee_$n".equals(element.getName())) {
					result.setCoupon_refund_fee_$n(element.getText());
				}
				
				if ("coupon_refund_count".equals(element.getName())) {
					result.setCoupon_refund_count(element.getText());
				}
				
				if ("coupon_refund_id_$n".equals(element.getName())) {
					result.setCoupon_refund_id_$n(element.getText());
				}
				
			}
		}
		return result;
	}

	private void initCert() throws KeyStoreException, IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException {
		String certPath = wecharConfigUtil.getProperty("WX_CERT_PATH");
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		//加载本地的证书进行https加密传输
		FileInputStream inputStream = new FileInputStream(new File(certPath));
		try {
			keyStore.load(inputStream, wecharConfigUtil.getProperty("WX_MCH_ID").toCharArray());
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		
		SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, wecharConfigUtil.getProperty("WX_MCH_ID").toCharArray())
                .build();
		
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		
		CloseableHttpClient httpClient  = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		
	}

	private String createXmlInfo(WxRefundModel wxRefundModel) {
		if(wxRefundModel!=null){
			StringBuffer bf = new StringBuffer();
			bf.append("<xml>");

			bf.append("<appid><![CDATA[");
			bf.append(wxRefundModel.getAppid());
			bf.append("]]></appid>");
			
			bf.append("<mch_id><![CDATA[");
			bf.append(wxRefundModel.getMch_id());
			bf.append("]]></mch_id>");
			
			bf.append("<nonce_str><![CDATA[");
			bf.append(wxRefundModel.getNonce_str());
			bf.append("]]></nonce_str>");
			
			bf.append("<transaction_id><![CDATA[");
			bf.append(wxRefundModel.getTransaction_id());
			bf.append("]]></transaction_id>");
			
			bf.append("<out_refund_no><![CDATA[");
			bf.append(wxRefundModel.getOut_refund_no());
			bf.append("]]></out_refund_no>");
			
			bf.append("<total_fee><![CDATA[");
			bf.append(wxRefundModel.getTotal_fee());
			bf.append("]]></total_fee>");
			
			bf.append("<refund_fee><![CDATA[");
			bf.append(wxRefundModel.getRefund_fee());
			bf.append("]]></refund_fee>");

			bf.append("<sign><![CDATA[");
			bf.append(wxRefundModel.getSign());
			bf.append("]]></sign>");
			
			bf.append("<notify_url><![CDATA[");
			bf.append(wxRefundModel.getNotify_url());
			bf.append("]]></notify_url>");

			bf.append("</xml>");
			return bf.toString();
		}

		return "";
	}

	private WxRefundModel buildWxRefundModel(BigDecimal combinePayAmount,String paymentNo, RefundOrder refundOrder, String appId, String mchId) {
		WxRefundModel wxRefundModel = new WxRefundModel();
		wxRefundModel.setAppid(appId);
		wxRefundModel.setMch_id(mchId);
		wxRefundModel.setNonce_str(RandCharsUtils.getRandomString(32));
		wxRefundModel.setTransaction_id(paymentNo);
		wxRefundModel.setOut_refund_no(refundOrder.getRefundReqNo());
		wxRefundModel.setTotal_fee(combinePayAmount.multiply(new BigDecimal(100)).intValue());
		wxRefundModel.setRefund_fee(refundOrder.getRefundAmount().multiply(new BigDecimal(100)).intValue());
		wxRefundModel.setNotify_url(wecharConfigUtil.getProperty("WX_REFUND_NOTIFY_URL"));
		return wxRefundModel;
	}
	
	public static String doRefund(String url,String data, String mchId, String certPath) throws Exception {  
        /** 
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的 
         */  
          
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");  
        FileInputStream instream = new FileInputStream(new File(certPath));//P12文件目录  
        try {  
            /** 
             * 此处要改 
             * */  
            keyStore.load(instream, mchId.toCharArray());//这里写密码..默认是你的MCHID  
        } finally {  
            instream.close();  
        }  
  
        // Trust own CA and all self-signed certs  
        /** 
         * 此处要改 
         * */  
        SSLContext sslcontext = SSLContexts.custom()  
                .loadKeyMaterial(keyStore, mchId.toCharArray())//这里也是写密码的    
                .build();  
        // Allow TLSv1 protocol only  
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                sslcontext,  
                new String[] { "TLSv1" },  
                null,  
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
        CloseableHttpClient httpclient = HttpClients.custom()  
                .setSSLSocketFactory(sslsf)  
                .build();  
        try {  
            HttpPost httpost = new HttpPost(url); // 设置响应头信息  
            httpost.addHeader("Connection", "keep-alive");  
            httpost.addHeader("Accept", "*/*");  
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  
            httpost.addHeader("Host", "api.mch.weixin.qq.com");  
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");  
            httpost.addHeader("Cache-Control", "max-age=0");  
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");  
            httpost.setEntity(new StringEntity(data, "UTF-8"));  
            CloseableHttpResponse response = httpclient.execute(httpost);  
            try {  
                HttpEntity entity = response.getEntity();  
  
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");  
                EntityUtils.consume(entity);  
               return jsonStr;  
            } finally {  
                response.close();  
            }  
        } finally {  
            httpclient.close();  
        }  
    }


	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void designTaskRefundOrder(){
		logger.info("设计任务退款:start");
		DesignTaskRefundOrderExample designTaskRefundOrderExample = new DesignTaskRefundOrderExample();
		designTaskRefundOrderExample.createCriteria().andDelFlagEqualTo("0")
				.andStatusNotEqualTo("2").andTryTimesLessThan(2);
		List<DesignTaskRefundOrder> designTaskRefundOrderList = designTaskRefundOrderService.selectByExample(designTaskRefundOrderExample);
		if(CollectionUtils.isNotEmpty(designTaskRefundOrderList)) {
			logger.info("扫描到的可退款数：{}", designTaskRefundOrderList.size());
			for(DesignTaskRefundOrder designTaskRefundOrder : designTaskRefundOrderList) {
				try {
					DesignTaskOrder designTaskOrder = designTaskOrderService.selectByPrimaryKey(designTaskRefundOrder.getDesignTaskOrderId());
					if(!StringUtil.isEmpty(designTaskRefundOrder.getRefundReqNo())) {
						designTaskRefundOrder.setRefundReqNo(commonService.getSequence("refundReqNo").toString());
						designTaskRefundOrderService.updateByPrimaryKey(designTaskRefundOrder);
					}
					designTaskRefundOrderService.aliDesignTaskRefundOrder(designTaskRefundOrder, designTaskOrder);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info(e.getMessage());
					logger.info("设计任务退款失败:end");
				}
			}
		}
		logger.info("设计任务退款:end");
	}


	/**
	 * 大学生订单退款(系统自动生成售后单与退款单)
	 */
	@Scheduled(cron="0 2/10 * * * ?")
	public synchronized void collegeStudentRefundOrder(){
		logger.info("大学生订单退款:start");

		//指定商品订单：待发货订单付款后的24小时内，用户未上传认证，关闭订单并强制退款
		List<SubOrderCustom> subOrderCustomList = subOrderService.selectCollegeStudentOrderList("2");
		subOrderService.addCollegeStudentRefundOrder(subOrderCustomList);

		//指定商品订单：认证驳回的24小时后 未重新上传的  关闭订单并强制退款
		List<SubOrderCustom> subOrderCustoms = subOrderService.selectCollegeStudentOrderList("4");
		subOrderService.addCollegeStudentRefundOrder(subOrderCustoms);

		logger.info("大学生订单退款:end");
	}



}
