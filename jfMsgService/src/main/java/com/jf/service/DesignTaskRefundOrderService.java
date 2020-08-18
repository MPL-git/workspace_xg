package com.jf.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.alipayConfigUtil;
import com.jf.dao.DesignTaskRefundOrderMapper;
import com.jf.entity.DesignTaskOrder;
import com.jf.entity.DesignTaskRefundOrder;
import com.jf.entity.DesignTaskRefundOrderExample;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pengl
 * @create 2020-03-20 下午 1:36
 */
@Service
@Transactional
public class DesignTaskRefundOrderService extends BaseService<DesignTaskRefundOrder, DesignTaskRefundOrderExample> {

	@Autowired
	private DesignTaskRefundOrderMapper designTaskRefundOrderMapper;

	@Autowired
	public void setDesignTaskRefundOrderMapper(DesignTaskRefundOrderMapper designTaskRefundOrderMapper) {
		super.setDao(designTaskRefundOrderMapper);
		this.designTaskRefundOrderMapper = designTaskRefundOrderMapper;
	}

	public void aliDesignTaskRefundOrder(DesignTaskRefundOrder designTaskRefundOrder, DesignTaskOrder designTaskOrder) {
		try {
			AlipayClient alipayClient = new DefaultAlipayClient(
					alipayConfigUtil.getProperty("ALIPAY_GATEWAY_URL"),
					alipayConfigUtil.getProperty("ALIPAY_APP_ID"),
					alipayConfigUtil.getProperty("ALIPAY_PRIVATE_KEY"),
					AlipayConstants.FORMAT_JSON,
					AlipayConstants.CHARSET_UTF8,
					alipayConfigUtil.getProperty("ALIPAY_PUBLIC_KEY"),
					AlipayConstants.SIGN_TYPE_RSA2
			);
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("out_trade_no", designTaskOrder.getOrderCode());
			paramMap.put("refund_amount", designTaskRefundOrder.getRefundAmount());
			paramMap.put("out_request_no", designTaskRefundOrder.getRefundReqNo());
			request.setBizContent(JSON.toJSONString(paramMap));
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			boolean flag = true;
			if(response.isSuccess()){
				String body = response.getBody();
				if (!StringUtil.isBlank(body)) {
					JSONObject json = JSONObject.fromObject(body);
					JSONObject reqDataJson = json.getJSONObject("alipay_trade_refund_response");
					if (reqDataJson != null) {
						String code = reqDataJson.getString("code");
						if (code.equals("10000")) {
							if (reqDataJson.containsKey("out_trade_no") && !StringUtil.isBlank(reqDataJson.getString("out_trade_no"))) {
								if (reqDataJson.getString("out_trade_no").equals(designTaskOrder.getOrderCode())
										&& reqDataJson.getDouble("refund_fee") == Double.parseDouble(designTaskRefundOrder.getRefundAmount().toString())) {
									designTaskRefundOrder.setTryTimes(designTaskRefundOrder.getTryTimes()+1);
									designTaskRefundOrder.setStatus("2");
									designTaskRefundOrder.setSuccessDate(response.getGmtRefundPay());
									designTaskRefundOrder.setZfbRefundId(response.getTradeNo());
									designTaskRefundOrder.setUpdateDate(new Date());
									designTaskRefundOrderMapper.updateByPrimaryKey(designTaskRefundOrder);
									flag = false;
								}
							}
						}
					}
				}
			}
			if(flag) {
				designTaskRefundOrder.setFailedReason(response.getSubMsg());
				designTaskRefundOrder.setTryTimes(designTaskRefundOrder.getTryTimes()+1);
				designTaskRefundOrder.setStatus("3");
				designTaskRefundOrder.setUpdateDate(new Date());
				designTaskRefundOrderMapper.updateByPrimaryKey(designTaskRefundOrder);
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}



}
