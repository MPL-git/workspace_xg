package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.*;
import com.jf.entity.*;
import com.jf.entity.pay.WxRefundResuletBackInfoModel;
import com.jf.entity.pay.WxRefundResuletBackModel;
import com.jf.service.*;
import com.jf.task.OrderRefundTask;
import org.apache.commons.collections.CollectionUtils;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年3月14日 下午4:47:26 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class OrderRefundController {
	private static Logger logger = LoggerFactory.getLogger(OrderRefundController.class);
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

    @Autowired
	private DesignTaskOrderService designTaskOrderService;

    @Autowired
	private DesignTaskRefundOrderService designTaskRefundOrderService;

    @Autowired
	private OrderRefundTask orderRefundTask;
    
    @SuppressWarnings({ "unused", "null" })
	@RequestMapping(value = "/app/wxRefundBack")
	@ResponseBody
	public void wxRefundBack(HttpServletRequest request, HttpServletResponse response){
    	BufferedReader reader = null;
		try {
			logger.info("微信退款回调开始start--》》》");
			String responseCode = "<xml> "
					+ "<return_code><![CDATA[SUCCESS]]></return_code>"
					+ "<return_msg><![CDATA[OK]]></return_msg>"
					+ "</xml>";
			reader = request.getReader();
			String line = "";
			//获取输入流数据
			StringBuffer inputString = new StringBuffer();
			PrintWriter writer = response.getWriter();

			while ((line = reader.readLine()) != null) {
				inputString.append(line);
			}
			List<Element> resuletBackModelList = JdomParseXmlUtils.getWXPayResultElement(inputString.toString());
			WxRefundResuletBackModel wxRefundResuletBackModel = buildWxRefundResuletBackModel(resuletBackModelList);
			//SUCCESS/FAIL 
			//此字段是通信标识，非结果标识，退款是否成功需要解密后查看refund_status 来判断
			String return_code = wxRefundResuletBackModel.getReturnCode();
			if(!StringUtil.isBlank(return_code) && return_code.equals("SUCCESS")){
				Date date = new Date();
				String appId = "";
				String mchId = "";
				String key = "";
				if("1490013172".equals(wxRefundResuletBackModel.getMchId())){
					//公众号商户
					appId = WeixinUtil.appId;
    				mchId = WeixinUtil.mchtId;
    				key = WeixinUtil.key;
				}else{
					appId = wecharConfigUtil.getProperty("WX_APP_ID");
					mchId = wecharConfigUtil.getProperty("WX_MCH_ID");
					key = wecharConfigUtil.getProperty("WX_KEY");
				}
				
				if(appId.equals(wxRefundResuletBackModel.getAppid()) && mchId.equals(wxRefundResuletBackModel.getMchId())){
					String result = AESUtil.decryptData(wxRefundResuletBackModel.getReq_info(),key);
					if(!StringUtil.isBlank(result)){
						List<Element> modelInfoList = JdomParseXmlUtils.getWXPayResultElement(result);
    					WxRefundResuletBackInfoModel wxRefundResuletBackInfoModel = buildWxRefundResuletBackInfoModel(modelInfoList);
    					if(wxRefundResuletBackInfoModel != null){
    						String refundStatus = wxRefundResuletBackInfoModel.getRefundStatus() == null ? "" : wxRefundResuletBackInfoModel.getRefundStatus();
    						if(refundStatus.equals("SUCCESS")){
    							String refundId = wxRefundResuletBackInfoModel.getRefundId();
    							String successtime = wxRefundResuletBackInfoModel.getSuccesstime();
    							String refundDate = null;
    							if(StringUtil.isBlank(successtime)){
    								refundDate = DateUtil.getFormatDate(date, "yyyy-MM-dd HH:mm:ss");
    							}else{
    								refundDate = successtime;
    							}
    							//异步暂时不做任何的处理
//    							RefundOrderExample refundOrderExample = new RefundOrderExample();
//    							refundOrderExample.createCriteria().andDelFlagEqualTo("0").andWxRefundIdEqualTo(refundId);
//    							List<RefundOrder> refundOrders = refundOrderService.selectByExample(refundOrderExample);
//    							if(CollectionUtils.isNotEmpty(refundOrders)){
//    								RefundOrder refundOrder = refundOrders.get(0);
//    								refundOrder.setRemarks(refundDate);
//    								refundOrderService.updateByPrimaryKeySelective(refundOrder);
//    	        					logger.info("退款单号"+refundId+"退款成功");
//    							}else{
//    								responseCode = "<xml> "
//    										+ "<return_code><![CDATA[faile]]></return_code>"
//    										+ "<return_msg><![CDATA[faile]]></return_msg>"
//    										+ "</xml>";
//    							}
	    					}
	    				}
					}
				}else{
					logger.info("appId或商户号不匹配");
				}
			}
			response.getWriter().write(responseCode);
			logger.info("微信退款回调开始end--》》》");
		} catch (Exception e) {
			logger.info("报错信息--》：");
			e.printStackTrace();
		} finally {
			if (reader == null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private WxRefundResuletBackInfoModel buildWxRefundResuletBackInfoModel(List<Element> list) {
		WxRefundResuletBackInfoModel result = null;
		if (list != null && list.size() > 0) {
			result = new WxRefundResuletBackInfoModel();
			for (Element element : list) {
				if ("transaction_id".equals(element.getName())) {
					result.setTransactionId(element.getText());
				}
				
				if ("out_trade_no".equals(element.getName())) {
					result.setOutTradeNo(element.getText());
				}
				
				if ("refund_id".equals(element.getName())) {
					result.setRefundId(element.getText());
				}
				
				if ("out_refund_no".equals(element.getName())) {
					result.setOutRefundNo(element.getText());
				}

				if ("total_fee".equals(element.getName())) {
					result.setTotalfee(element.getText());
				}
				
				if ("settlement_total_fee".equals(element.getName())) {
					result.setSettlementTotalFee(element.getText());
				}

				if ("refund_fee".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
				if ("settlement_refund_fee".equals(element.getName())) {
					result.setSettlementRefundFee(element.getText());
				}
				
				if ("refund_status".equals(element.getName())) {
					result.setRefundStatus(element.getText());
				}
				
				if ("success_time".equals(element.getName())) {
					result.setSuccesstime(element.getText());
				}

				if ("refund_recv_accout".equals(element.getName())) {
					result.setRefundRecvAccout(element.getText());
				}
				
				if ("refund_account".equals(element.getName())) {
					result.setRefundAccount(element.getText());
				}
				
				if ("refund_request_source".equals(element.getName())) {
					result.setRefundRequestSource(element.getText());
				}

				if ("return_code".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
				if ("return_msg".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
				if ("appid".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
				if ("mch_id".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
				if ("nonce_str".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
				if ("req_info".equals(element.getName())) {
					result.setRefundFee(element.getText());
				}
				
			}
		}
		return result;
	}
	
	private WxRefundResuletBackModel buildWxRefundResuletBackModel(List<Element> list) {
		WxRefundResuletBackModel result = null;
		if (list != null && list.size() > 0) {
			result = new WxRefundResuletBackModel();
			for (Element element : list) {
				
				if ("return_code".equals(element.getName())) {
					result.setReturnCode(element.getText());
				}
				
				if ("return_msg".equals(element.getName())) {
					result.setReturnMsg(element.getText());
				}
				
				if ("appid".equals(element.getName())) {
					result.setAppid(element.getText());
				}
				
				if ("mch_id".equals(element.getName())) {
					result.setMchId(element.getText());
				}
				
				if ("nonce_str".equals(element.getName())) {
					result.setNonce_str(element.getText());
				}
				
				if ("req_info".equals(element.getName())) {
					result.setReq_info(element.getText());
				}
				
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/refundOrder/designTaskRefundOrder")
	public synchronized ResponseMsg designTaskRefundOrder(HttpServletRequest request) {
		logger.info("设计任务退款:start");
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
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
					responseMsg.setReturnCode(ResponseMsg.ERROR);
					responseMsg.setReturnMsg(e.getMessage());
				}
			}
		}
		logger.info("设计任务退款:end");
		return responseMsg;
	}

	@ResponseBody
	@RequestMapping("/refundOrder/collegeStudentRefundOrder")
	public synchronized ResponseMsg collegeStudentRefundOrder(HttpServletRequest request) {
		logger.info("大学生订单退款:start");

		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);

		try {
			//指定商品订单：待发货订单付款后的4小时内，用户未上传认证，关闭订单并强制退款
			List<SubOrderCustom> subOrderCustomList = subOrderService.selectCollegeStudentOrderList("2");
			subOrderService.addCollegeStudentRefundOrder(subOrderCustomList);

			//指定商品订单：认证驳回的24小时后 未重新上传的  关闭订单并强制退款
			List<SubOrderCustom> subOrderCustoms = subOrderService.selectCollegeStudentOrderList("4");
			subOrderService.addCollegeStudentRefundOrder(subOrderCustoms);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			logger.info("大学生订单退款失败:end");
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(e.getMessage());
		}

		logger.info("大学生订单退款:end");

		return responseMsg;
	}

	@ResponseBody
	@RequestMapping("/refundOrder/orderRefund")
	public synchronized ResponseMsg orderRefund(HttpServletRequest request) {

		logger.info("订单退款:start");
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			List<RefundOrder> refundOrders = refundOrderService.findList("1",null);
			if(CollectionUtils.isNotEmpty(refundOrders)){
				logger.info("扫描到的可退款数：{}", refundOrders.size());
				for (RefundOrder refundOrder : refundOrders) {
					orderRefundTask.orderRefundInfo(refundOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			logger.info("订单退款失败:end");
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(e.getMessage());
		}
		logger.info("订单退款:end");

		return responseMsg;
	}


}
