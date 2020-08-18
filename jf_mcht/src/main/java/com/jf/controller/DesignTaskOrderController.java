package com.jf.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.*;
import com.jf.entity.*;
import com.jf.service.DesignTaskOrderPicDetailService;
import com.jf.service.DesignTaskOrderPicService;
import com.jf.service.DesignTaskOrderService;
import com.jf.service.ProblemGuidelineService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Pengl
 * @create 2020-03-16 下午 2:58
 */
@Controller
public class DesignTaskOrderController extends BaseController {

	@Autowired
	private DesignTaskOrderService designTaskOrderService;

	@Autowired
	private DesignTaskOrderPicDetailService designTaskOrderPicDetailService;

	@Autowired
	private DesignTaskOrderPicService designTaskOrderPicService;

	@Autowired
	private ProblemGuidelineService problemGuidelineService;

	@RequestMapping("/designTaskOrder/designTaskOrderManager")
	public ModelAndView designTaskOrderManager() {
		ModelAndView m = new ModelAndView("/designTaskOrder/designTaskOrderList");
		m.addObject("taskTypeList", DataDicUtil.getStatusList("BU_DESIGN_TASK_ORDER", "TASK_TYPE"));
		List<SysStatus> payStatusList = DataDicUtil.getStatusList("BU_DESIGN_TASK_ORDER", "PAY_STATUS");
		SysStatus sysStatus = new SysStatus();
		sysStatus.setStatusValue("2");
		sysStatus.setStatusDesc("退款中");
		payStatusList.add(sysStatus);
		SysStatus ss = new SysStatus();
		ss.setStatusValue("3");
		ss.setStatusDesc("退款成功");
		payStatusList.add(ss);
		SysStatus s = new SysStatus();
		s.setStatusValue("4");
		s.setStatusDesc("退款失败");
		payStatusList.add(s);
		m.addObject("payStatusList", payStatusList);
		m.addObject("designStatusList", DataDicUtil.getStatusList("BU_DESIGN_TASK_ORDER", "DESIGN_STATUS"));
		return m;
	}

	@ResponseBody
	@RequestMapping("/designTaskOrder/getDesignTaskOrderListList")
	public ResponseMsg getDesignTaskOrderListList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<DesignTaskOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			DesignTaskOrderCustomExample designTaskOrderCustomExample = new DesignTaskOrderCustomExample();
			DesignTaskOrderCustomExample.DesignTaskOrderCustomCriteria designTaskOrderCustomCriteria = designTaskOrderCustomExample.createCriteria();
			designTaskOrderCustomCriteria.andMchtIdEqualTo(this.getMchtId());
			if (!StringUtil.isEmpty(request.getParameter("taskType"))) {
				designTaskOrderCustomCriteria.andTaskTypeEqualTo(request.getParameter("taskType"));
			}
			if (!StringUtil.isEmpty(request.getParameter("orderCode"))) {
				designTaskOrderCustomCriteria.andOrderCodeLike("%" + request.getParameter("orderCode") + "%");
			}
			if (!StringUtil.isEmpty(request.getParameter("taskName"))) {
				designTaskOrderCustomCriteria.andTaskNameLike("%" + request.getParameter("taskName") + "%");
			}
			if (!StringUtil.isEmpty(request.getParameter("payStatus"))) {
				if (Integer.parseInt(request.getParameter("payStatus").toString()) < 1) {
					designTaskOrderCustomCriteria.andPayStatusEqualTo(request.getParameter("payStatus"));
				} else {
					designTaskOrderCustomCriteria.andPayStatusCustomEqualTo(request.getParameter("payStatus"));
				}
			}
			if (!StringUtil.isEmpty(request.getParameter("designStatus"))) {
				designTaskOrderCustomCriteria.andDesignStatusEqualTo(request.getParameter("designStatus"));
			}
			if (!StringUtil.isEmpty(request.getParameter("status"))) {
				designTaskOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}else {
				designTaskOrderCustomCriteria.andStatusEqualTo("0");
			}
			designTaskOrderCustomExample.setOrderByClause(" t.id desc");
			designTaskOrderCustomExample.setLimitStart(page.getLimitStart());
			designTaskOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = designTaskOrderService.countByCustomExample(designTaskOrderCustomExample);
			dataList = designTaskOrderService.selectByCustomExample(designTaskOrderCustomExample);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		returnData.put("Rows", dataList);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}

	@ResponseBody
	@RequestMapping("/designTaskOrder/cancelDesignTaskOrder")
	public String cancelDesignTaskOrder(HttpServletRequest request) {
		if (!StringUtil.isEmpty(request.getParameter("designTaskOrderId"))) {
			Integer designTaskOrderId = Integer.parseInt(request.getParameter("designTaskOrderId"));
			DesignTaskOrder designTaskOrder = new DesignTaskOrder();
			designTaskOrder.setId(designTaskOrderId);
			designTaskOrder.setStatus("1");
			designTaskOrder.setUpdateDate(new Date());
			designTaskOrderService.updateByPrimaryKeySelective(designTaskOrder);
		}
		return json();
	}

	@RequestMapping("/designTaskOrder/editDesignTaskOrderManager")
	public ModelAndView editTaskOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/designTaskOrder/editDesignTaskOrder");
		if (!StringUtil.isEmpty(request.getParameter("designTaskOrderId"))) {
			DesignTaskOrderCustom designTaskOrderCustom = designTaskOrderService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("designTaskOrderId")));
			if (!StringUtil.isEmpty(designTaskOrderCustom.getFilePath())) {
				designTaskOrderCustom.setRemarks(designTaskOrderCustom.getFilePath().substring(designTaskOrderCustom.getFilePath().lastIndexOf("/") + 1));
			}
			m.addObject("designTaskOrderCustom", designTaskOrderCustom);
		}
		m.addObject("taskTypeList", DataDicUtil.getStatusList("BU_DESIGN_TASK_ORDER", "TASK_TYPE"));
		DesignTaskOrderPicDetailExample designTaskOrderPicDetailExample = new DesignTaskOrderPicDetailExample();
		designTaskOrderPicDetailExample.createCriteria().andDelFlagEqualTo("0");
		m.addObject("designTaskOrderPicDetailList", designTaskOrderPicDetailService.selectByExample(designTaskOrderPicDetailExample));
		return m;
	}

	@ResponseBody
	@RequestMapping("/designTaskOrder/editDesignTaskOrder")
	public String editDesignTaskOrder(HttpServletRequest request, DesignTaskOrder designTaskOrder) {
		BigDecimal payAmount = null;
		if (designTaskOrder.getId() != null) {
			if (!StringUtil.isEmpty(designTaskOrder.getTaskType())
					&& "1".equals(designTaskOrder.getTaskType())) {
				payAmount = new BigDecimal(60);
			} else if(!StringUtil.isEmpty(designTaskOrder.getTaskType())
					&& "2".equals(designTaskOrder.getTaskType())) {
				payAmount = new BigDecimal(150);
			}
			designTaskOrder.setPayAmount(payAmount);
			designTaskOrder.setUpdateDate(new Date());
			designTaskOrderService.updateByPrimaryKeySelective(designTaskOrder);
		} else {
			designTaskOrder.setMchtId(this.getMchtId());
			designTaskOrder.setOrderCode("SJ" + CommonUtil.getOrderCode());
			if (!StringUtil.isEmpty(designTaskOrder.getTaskType())
					&& "1".equals(designTaskOrder.getTaskType())) {
				payAmount = new BigDecimal(60);
			} else {
				payAmount = new BigDecimal(150);
			}
			designTaskOrder.setPayAmount(payAmount);
			designTaskOrder.setCreateDate(new Date());
			designTaskOrder.setDelFlag("0");
			designTaskOrderService.insertSelective(designTaskOrder);
		}
		return json();
	}

	@RequestMapping("/designTaskOrder/viewDesignTaskOrderManager")
	public ModelAndView viewDesignTaskOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/designTaskOrder/viewDesignTaskOrder");
		DesignTaskOrderCustom designTaskOrderCustom = designTaskOrderService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("designTaskOrderId")));
		if (!StringUtil.isEmpty(designTaskOrderCustom.getFilePath())) {
			designTaskOrderCustom.setRemarks(designTaskOrderCustom.getFilePath().substring(designTaskOrderCustom.getFilePath().lastIndexOf("/") + 1));
		}
		m.addObject("designTaskOrderCustom", designTaskOrderCustom);
		m.addObject("commentTypeList", DataDicUtil.getStatusList("BU_DESIGN_TASK_ORDER", "COMMENT_TYPE"));

		DesignTaskOrderPicExample designTaskOrderPicExample = new DesignTaskOrderPicExample();
		designTaskOrderPicExample.createCriteria().andDelFlagEqualTo("0").andDesignTaskOrderIdEqualTo(designTaskOrderCustom.getId());
		m.addObject("designTaskOrderPicList", designTaskOrderPicService.selectByExample(designTaskOrderPicExample));

		DesignTaskOrderPicDetailExample designTaskOrderPicDetailExample = new DesignTaskOrderPicDetailExample();
		designTaskOrderPicDetailExample.createCriteria().andDelFlagEqualTo("0").andTaskTypeEqualTo(designTaskOrderCustom.getTaskType());
		m.addObject("designTaskOrderPicDetailList", designTaskOrderPicDetailService.selectByExample(designTaskOrderPicDetailExample));

		return m;
	}

	@ResponseBody
	@RequestMapping("/designTaskOrder/viewDesignTaskOrder")
	public String viewDesignTaskOrder(HttpServletRequest request, DesignTaskOrder designTaskOrder) {
		designTaskOrder.setCommentDate(new Date());
		designTaskOrderService.updateByPrimaryKeySelective(designTaskOrder);
		return json();
	}

	@RequestMapping("/designTaskOrder/payDesignTaskOrderManager")
	public ModelAndView payDesignTaskOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/designTaskOrder/payDesignTaskOrder");
		DesignTaskOrderCustom designTaskOrderCustom = designTaskOrderService.selectByCustomPrimaryKey(Integer.parseInt(request.getParameter("designTaskOrderId")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		designTaskOrderCustom.setRemarks(sdf.format(designTaskOrderCustom.getCreateDate()));
		if (!StringUtil.isEmpty(designTaskOrderCustom.getRequirement())) {
			designTaskOrderCustom.setRequirement(designTaskOrderCustom.getRequirement().replaceAll("[\r\n]+", "\r\n"));
		}
		if (!StringUtil.isEmpty(designTaskOrderCustom.getRequirement()) && designTaskOrderCustom.getRequirement().length() > 200) {
			designTaskOrderCustom.setRequirement(designTaskOrderCustom.getRequirement().substring(0, 200) + "...");
		}
		m.addObject("designTaskOrderCustom", designTaskOrderCustom);
		m.addObject("qrCode", alipayDesignTask(designTaskOrderCustom));
		return m;
	}

	public String alipayDesignTask(DesignTaskOrderCustom designTaskOrderCustom) {
		String qrCode = null;
		try {
			AlipayClient alipayClient = new DefaultAlipayClient(
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_GATEWAY_URL"),
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_APP_ID"),
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_PRIVATE_KEY"),
					AlipayConstants.FORMAT_JSON,
					AlipayConstants.CHARSET_UTF8,
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_PUBLIC_KEY"),
					AlipayConstants.SIGN_TYPE_RSA2
			); //获得初始化的AlipayClient
			AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest(); //创建API对应的request类
			request.setNotifyUrl(PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_NOTIFY_DESIGN_URL")); //支付宝服务器主动通知
			// 支付业务请求参数
			Map<String, String> param = new HashMap<String, String>();
			param.put("out_trade_no", designTaskOrderCustom.getOrderCode()); //商户订单号
			param.put("total_amount", designTaskOrderCustom.getPayAmount().stripTrailingZeros().toPlainString()); //交易金额
			param.put("subject", designTaskOrderCustom.getTaskName()); //订单标题
			param.put("body", "聚买商家设计付费"); //对交易或商品的描述
			request.setBizContent(JSON.toJSONString(param));
			AlipayTradePrecreateResponse response = alipayClient.execute(request);
			if (response.isSuccess()) {
				String body = response.getBody();
				if (!StringUtil.isBlank(body)) {
					JSONObject json = JSONObject.fromObject(body);
					JSONObject reqDataJson = json.getJSONObject("alipay_trade_precreate_response");
					if (reqDataJson != null) {
						String code = reqDataJson.getString("code");
						if (code.equals("10000")) {
							if (reqDataJson.containsKey("out_trade_no") && !StringUtil.isBlank(reqDataJson.getString("out_trade_no"))) {
								if (reqDataJson.getString("out_trade_no").equals(designTaskOrderCustom.getOrderCode())) {
									qrCode = reqDataJson.getString("qr_code");
								}
							}
						}
					}
				}
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return qrCode;
	}

	@RequestMapping("/designTaskOrder/problemGuidelineManager")
	public ModelAndView problemGuidelineManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/designTaskOrder/problemGuideline");
		ProblemGuidelineExample problemGuidelineExample = new ProblemGuidelineExample();
		problemGuidelineExample.createCriteria().andDelFlagEqualTo("0");
		m.addObject("problemGuidelineList", problemGuidelineService.selectByExample(problemGuidelineExample));
		return m;
	}

	@ResponseBody
	@RequestMapping("/designTaskOrder/payStatus")
	public Map<String, Object> aliPayStatus(HttpServletRequest request) {
		Map<String, Object> renderMap = new HashMap<String, Object>();
		String designTaskOrderId = request.getParameter("designTaskOrderId");
		boolean flag = false;
		if (!StringUtil.isEmpty(designTaskOrderId)) {
			DesignTaskOrder designTaskOrder = designTaskOrderService.selectByPrimaryKey(Integer.parseInt(designTaskOrderId));
			if ("0".equals(designTaskOrder.getPayStatus())) {
				Map<String, Object> map = aliPayStatus(designTaskOrder.getOrderCode());
				if (map != null && "TRADE_SUCCESS".equals(map.get("trade_status").toString())
						&& designTaskOrder.getPayAmount().doubleValue() == Double.valueOf(map.get("total_amount").toString())) {
					Date date = new Date();
					designTaskOrder.setPayStatus("1");
					designTaskOrder.setPayDate(date);
					designTaskOrder.setPaymentId(1);
					designTaskOrder.setPaymentNo(map.get("trade_no").toString());
					designTaskOrder.setUpdateDate(date);
					designTaskOrderService.updateByPrimaryKey(designTaskOrder);
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		renderMap.put("success", true);
		renderMap.put("code", "0000");
		renderMap.put("flag", flag);
		return renderMap;
	}

	public Map<String, Object> aliPayStatus(String orderCode) {
		Map<String, Object> map = null;
		try {
			AlipayClient alipayClient = new DefaultAlipayClient(
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_GATEWAY_URL"),
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_APP_ID"),
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_PRIVATE_KEY"),
					AlipayConstants.FORMAT_JSON,
					AlipayConstants.CHARSET_UTF8,
					PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_PUBLIC_KEY"),
					AlipayConstants.SIGN_TYPE_RSA2
			); //获得初始化的AlipayClient
			AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest(); //创建API对应的request类
			// 支付业务请求参数
			Map<String, String> param = new HashMap<String, String>();
			param.put("out_trade_no", orderCode); //商户订单号
			alipayTradeQueryRequest.setBizContent(JSON.toJSONString(param));
			AlipayTradeQueryResponse response = alipayClient.execute(alipayTradeQueryRequest);
			if (response.isSuccess()) {
				String body = response.getBody();
				if (!StringUtil.isBlank(body)) {
					JSONObject json = JSONObject.fromObject(body);
					JSONObject reqDataJson = json.getJSONObject("alipay_trade_query_response");
					if (reqDataJson != null) {
						String code = reqDataJson.getString("code");
						if (code.equals("10000")) {
							if (reqDataJson.containsKey("out_trade_no") && !StringUtil.isBlank(reqDataJson.getString("out_trade_no"))) {
								if (reqDataJson.getString("out_trade_no").equals(orderCode)) {
									map = new HashMap<String, Object>();
									map.put("out_trade_no", reqDataJson.getString("out_trade_no"));
									map.put("trade_status", reqDataJson.getString("trade_status"));
									map.put("trade_no", reqDataJson.getString("trade_no"));
									map.put("total_amount", reqDataJson.getString("total_amount"));
								}
							}
						}
					}
				}
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/designTaskOrder/aliPayNotifyDesignUrl")
	public String aliPayNotifyDesignUrl(HttpServletRequest request) {
		try {
			boolean failure = true;
			Enumeration<?> pNames = request.getParameterNames();
			Map<String, String> param = new HashMap<String, String>();
			while (pNames.hasMoreElements()) {
				String pName = (String) pNames.nextElement();
				param.put(pName, request.getParameter(pName));
			}
			if ("TRADE_SUCCESS".equals(request.getParameter("trade_status"))) {
				// 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
				String outTradeNo = request.getParameter("out_trade_no");
				// 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
				String totalAmount = request.getParameter("total_amount");
				// 3、校验通知中的seller_id（或者seller_email)
				// 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				String sellerEmail = request.getParameter("seller_email");
				// 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
				String appId = request.getParameter("app_id");
				String tradeNo = request.getParameter("trade_no");
				//交易付款时间
				String gmt_payment = request.getParameter("gmt_payment");
				Date payDate = new Date();
				if (!StringUtil.isBlank(gmt_payment)) {
					payDate = DateUtil.getDate(gmt_payment);
				}
				boolean signVerified = AlipaySignature.rsaCheckV1(param,
						PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_PUBLIC_KEY"),
						AlipayConstants.CHARSET_UTF8,
						AlipayConstants.SIGN_TYPE_RSA2); // 校验签名是否正确
				if (signVerified) {
					DesignTaskOrderExample designTaskOrderExample = new DesignTaskOrderExample();
					designTaskOrderExample.createCriteria().andDelFlagEqualTo("0").andPayStatusEqualTo("0").andOrderCodeEqualTo(outTradeNo);
					List<DesignTaskOrder> designTaskOrderList = designTaskOrderService.selectByExample(designTaskOrderExample);
					DesignTaskOrder designTaskOrder = null;
					if (designTaskOrderList.size() > 0) {
						designTaskOrder = designTaskOrderList.get(0);
						if (!appId.equals(PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_APP_ID")) && failure) {
							failure = false;
						}
						if (!sellerEmail.equals(PropertiesUtil.getProperty("pay/alipayConfig.properties", "ALIPAY_SELLER_EMAIL")) && failure) {
							failure = false;
						}
						if (designTaskOrder.getPayAmount().doubleValue() != Double.parseDouble(totalAmount) && failure) {
							failure = false;
						}
					} else {
						failure = false;
					}
					if (failure) {
						designTaskOrder.setPayStatus("1");
						designTaskOrder.setPayDate(payDate);
						designTaskOrder.setPaymentId(1);
						designTaskOrder.setPaymentNo(tradeNo);
						designTaskOrder.setUpdateDate(new Date());
						designTaskOrderService.updateByPrimaryKey(designTaskOrder);
						return "success";
					} else {
						return "failure";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "failure";
	}


}
