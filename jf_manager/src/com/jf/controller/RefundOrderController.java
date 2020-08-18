package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.dao.RefundRegisterDateLockMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.CustomerServiceStatusLog;
import com.jf.entity.OrderDtl;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderCustom;
import com.jf.entity.RefundOrderCustomExample;
import com.jf.entity.RefundOrderExample;
import com.jf.entity.RefundRegisterDateLock;
import com.jf.entity.RefundRegisterDateLockExample;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageCustom;
import com.jf.entity.SysStatus;
import com.jf.service.AppealLogService;
import com.jf.service.AppealOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.CustomerServiceLogService;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.CustomerServicePicService;
import com.jf.service.ExpressService;
import com.jf.service.OrderDtlService;
import com.jf.service.OrderLogService;
import com.jf.service.OrderPreferentialInfoService;
import com.jf.service.RefundOrderService;
import com.jf.service.RemarksLogService;
import com.jf.service.ServiceLogPicService;
import com.jf.service.SubOrderService;
import com.jf.service.SysPaymentService;
import com.jf.service.SysStatusService;
import com.jf.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RefundOrderController extends BaseController {
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private SysPaymentService sysPaymentService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private SubOrderService subOrderService;
	
	@Resource
	private OrderPreferentialInfoService orderPreferentialInfoService;
	
	@Resource
	private RemarksLogService remarksLogService;
	
	@Resource
	private OrderLogService orderLogService;
	
	@Resource
	private ExpressService expressService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private CustomerServicePicService customerServicePicService;
	
	@Resource
	private CustomerServiceLogService customerServiceLogService;
	
	@Resource
	private ServiceLogPicService serviceLogPicService;
	
	@Resource
	private SysStatusService sysStatusService;
	
	@Resource
	private AppealOrderService appealOrderService;

	@Resource
	private AppealLogService appealLogService;
	
	@Resource
	private RefundRegisterDateLockMapper refundRegisterDateLockMapper;
	
	@Resource
	private RefundOrderService refundOrderService;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 退款任务列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/list.shtml")
	public ModelAndView refundOrderList(HttpServletRequest request) {
		String rtPage = "/refundOrder/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("statusList", DataDicUtil.getStatusList("BU_REFUND_ORDER", "STATUS"));
		resMap.put("refundMethodList", DataDicUtil.getStatusList("BU_REFUND_ORDER", "REFUND_METHOD"));
		resMap.put("serviceTypeList", DataDicUtil.getStatusList("BU_REFUND_ORDER", "SERVICE_TYPE"));
		String paymentId = request.getParameter("paymentId");
		if(!StringUtils.isEmpty(paymentId)){
			if(!paymentId.equals("-1")){
				resMap.put("paymentId", paymentId);
			}else{
				resMap.put("refundPaymentId", "-1");
			}
		}
		resMap.put("mchtCode", request.getParameter("mchtCode"));
		resMap.put("serviceType", request.getParameter("serviceType"));
		if(StringUtils.isEmpty(request.getParameter("success_date_begin"))||StringUtils.isEmpty(request.getParameter("success_date_end"))){
			if(!StringUtils.isEmpty(request.getParameter("successDate"))){
				resMap.put("success_date_begin", request.getParameter("successDate"));
				resMap.put("success_date_end", request.getParameter("successDate"));
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				resMap.put("success_date_begin", sdf.format(new Date()));
				resMap.put("success_date_end", sdf.format(new Date()));
			}
		}else{
			resMap.put("success_date_begin", request.getParameter("success_date_begin"));
			resMap.put("success_date_end", request.getParameter("success_date_end"));
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 退款任务列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/data.shtml")
	@ResponseBody
	public Map<String, Object> refundOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			RefundOrderCustomExample refundOrderCustomExample = new RefundOrderCustomExample();
			RefundOrderCustomExample.RefundOrderCustomCriteria refundOrderCustomCriteria = refundOrderCustomExample.createCriteria();
			refundOrderCustomCriteria.andDelFlagEqualTo("0");
			refundOrderCustomExample.setOrderByClause("t.id asc,t.status asc,t.success_date asc,t.refund_agree_date asc");
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					refundOrderCustomCriteria.andPaymentIdIn("2,5");
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					refundOrderCustomCriteria.andPaymentIdIn("1,6");
				}
				if (paymentId.equals("3")){//微信公众号/小程序
					refundOrderCustomCriteria.andPaymentIdIn("4,7");
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("refundPaymentId"))){
				if(request.getParameter("refundPaymentId").equals("-1")){
					refundOrderCustomCriteria.andRefundMethodEqualTo("2");
				}else{
					refundOrderCustomCriteria.andRefundMethodEqualTo("1");
					refundOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("refundPaymentId")));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))){
				refundOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("serviceType"))){
				refundOrderCustomCriteria.andServiceTypeEqualTo(request.getParameter("serviceType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				refundOrderCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("refund_agree_date_begin")) ){
				refundOrderCustomCriteria.andRefundAgreeDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("refund_agree_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refund_agree_date_end")) ){
				refundOrderCustomCriteria.andRefundAgreeDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("refund_agree_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("combineOrderCode"))){
				refundOrderCustomCriteria.andCombineOrderCodeEqualTo(request.getParameter("combineOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("serviceOrderCode"))){
				refundOrderCustomCriteria.andServiceOrderCodeEqualTo(request.getParameter("serviceOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("success_date_begin"))){
				refundOrderCustomCriteria.andSuccessDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("success_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("success_date_end"))){
				refundOrderCustomCriteria.andSuccessDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("success_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refundCode"))){
				refundOrderCustomCriteria.andRefundCodeEqualTo(request.getParameter("refundCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refund_register_date_begin"))){
				refundOrderCustomCriteria.andRefundRegisterDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("refund_register_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refund_register_date_end"))){
				refundOrderCustomCriteria.andRefundRegisterDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("refund_register_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("orderDtlId"))){
				refundOrderCustomCriteria.andOrderDtlIdEqualTo(Integer.parseInt(request.getParameter("orderDtlId")));
			}
			refundOrderCustomExample.setLimitSize(page.getLimitSize());
			refundOrderCustomExample.setLimitStart(page.getLimitStart());
			totalCount = refundOrderService.countRefundOrderCustomByExample(refundOrderCustomExample);
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.selectRefundOrderCustomByExample(refundOrderCustomExample);
			resMap.put("Rows", refundOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/refundOrder/export.shtml")
	public void exportRefundOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			RefundOrderCustomExample refundOrderCustomExample = new RefundOrderCustomExample();
			RefundOrderCustomExample.RefundOrderCustomCriteria refundOrderCustomCriteria = refundOrderCustomExample.createCriteria();
			refundOrderCustomCriteria.andDelFlagEqualTo("0");
			refundOrderCustomExample.setOrderByClause("t.id asc,t.status asc,t.success_date asc,t.refund_agree_date asc");
			if(StringUtil.isEmpty(request.getParameter("allOrder"))) {
				refundOrderCustomCriteria.andOrderTypeEqualTo("2");
			}
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					refundOrderCustomCriteria.andPaymentIdIn("2,5");
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					refundOrderCustomCriteria.andPaymentIdIn("1,6");
				}
				if (paymentId.equals("3")){//微信公众号
					refundOrderCustomCriteria.andPaymentIdEqualTo(4);
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("refundPaymentId"))){
				if(request.getParameter("refundPaymentId").equals("-1")){
					refundOrderCustomCriteria.andRefundMethodEqualTo("2");
				}else{
					refundOrderCustomCriteria.andRefundMethodEqualTo("1");
					refundOrderCustomCriteria.andPaymentIdEqualTo(Integer.parseInt(request.getParameter("refundPaymentId")));
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))){
				refundOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("serviceType"))){
				refundOrderCustomCriteria.andServiceTypeEqualTo(request.getParameter("serviceType"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				refundOrderCustomCriteria.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("refund_agree_date_begin")) ){
				refundOrderCustomCriteria.andRefundAgreeDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("refund_agree_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refund_agree_date_end")) ){
				refundOrderCustomCriteria.andRefundAgreeDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("refund_agree_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("combineDepositOrderCode"))){
				refundOrderCustomCriteria.andCombineDepositOrderCodeEqualTo(request.getParameter("combineDepositOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("serviceOrderCode"))){
				refundOrderCustomCriteria.andServiceOrderCodeEqualTo(request.getParameter("serviceOrderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("success_date_begin")) ){
				refundOrderCustomCriteria.andSuccessDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("success_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("success_date_end")) ){
				refundOrderCustomCriteria.andSuccessDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("success_date_end")+" 23:59:59"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refundCode"))){
				refundOrderCustomCriteria.andRefundCodeEqualTo(request.getParameter("refundCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refund_register_date_begin")) ){
				refundOrderCustomCriteria.andRefundRegisterDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("refund_register_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("refund_register_date_end")) ){
				refundOrderCustomCriteria.andRefundRegisterDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("refund_register_date_end")+" 23:59:59"));
			}
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.selectRefundOrderCustomByExample(refundOrderCustomExample);
			
			String[] titles = { "母订单号", "实付金额", "母订单状态","原付款渠道","支付交易号","退款同意时间","售后类型","应退金额","退款状态","退款时间","退款编号","退款登记日期","退款出纳","退款渠道","退款方式","售后编号","子订单号","微信业务单号"};
			ExcelBean excelBean = new ExcelBean("导出退款任务列表.xls",
					"导出退款任务列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(RefundOrderCustom refundOrderCustom:refundOrderCustoms){
				String refundPayment = "";
				if(refundOrderCustom.getRefundMethod().equals("2")){
					refundPayment = "其他";
				}else if(refundOrderCustom.getRefundMethod().equals("1")){
					refundPayment = refundOrderCustom.getPaymentName();
				}
				String[] data = {
						refundOrderCustom.getCombineOrderCode(),
						refundOrderCustom.getTotalPayAmount() == null ? "" : refundOrderCustom.getTotalPayAmount().toString(),
						refundOrderCustom.getCombineOrderStatusDesc(),
						refundOrderCustom.getPaymentName() == null ? "" : refundOrderCustom.getPaymentName(),
						'`'+refundOrderCustom.getPaymentNo(),
						refundOrderCustom.getSuccessDate() == null ? "" : df.format(refundOrderCustom.getRefundAgreeDate()),
						refundOrderCustom.getServiceTypeDesc()== null ? "" : refundOrderCustom.getServiceTypeDesc(),
						refundOrderCustom.getRefundAmount() == null ? "" : refundOrderCustom.getRefundAmount().toString(),
						refundOrderCustom.getStatusDesc()== null ? "" : refundOrderCustom.getStatusDesc(),
						refundOrderCustom.getSuccessDate() == null ? "" : df.format(refundOrderCustom.getSuccessDate()),
						refundOrderCustom.getRefundCode()== null ? "" : refundOrderCustom.getRefundCode(),
						refundOrderCustom.getRefundRegisterDate()==null?"":df.format(refundOrderCustom.getRefundRegisterDate()),
						refundOrderCustom.getRefundStaffName()== null ? "" : refundOrderCustom.getRefundStaffName(),
						refundPayment,
						refundOrderCustom.getRefundMethodDesc()== null ? "" : refundOrderCustom.getRefundMethodDesc(),
						refundOrderCustom.getCustomerServiceOrderCode()== null ? "" : refundOrderCustom.getCustomerServiceOrderCode(),
						refundOrderCustom.getSubOrderCode()== null ? "" : refundOrderCustom.getSubOrderCode(),
						refundOrderCustom.getWxRefundId()==null?"":'`'+refundOrderCustom.getWxRefundId()
			};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 退款查看页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/toView.shtml")
	public ModelAndView toView(HttpServletRequest request){
		String rtPage = "/refundOrder/toView";
		Map<String, Object> resMap = new HashMap<String, Object>();
		int refundOrderId=Integer.valueOf(request.getParameter("id"));
		RefundOrder refundOrder=refundOrderService.selectByPrimaryKey(refundOrderId);
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(refundOrder.getCombineOrderId());
		String refundMethodDesc = DataDicUtil.getStatusDesc("BU_REFUND_ORDER", "REFUND_METHOD", refundOrder.getRefundMethod());
		resMap.put("refundOrder", refundOrder);
		resMap.put("paymentId", combineOrder.getPaymentId());
		resMap.put("refundMethodDesc", refundMethodDesc);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 退款操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/toRefund.shtml")
	public ModelAndView toRefund(HttpServletRequest request){
		String rtPage = "/refundOrder/toRefund";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int refundOrderId=Integer.valueOf(request.getParameter("id"));
		RefundOrder refundOrder=refundOrderService.selectByPrimaryKey(refundOrderId);
		List<SysStatus> refundMethods = DataDicUtil.getStatusList("BU_REFUND_ORDER", "REFUND_METHOD");
		CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(refundOrder.getCombineOrderId());
		resMap.put("refundOrder", refundOrder);
		resMap.put("refundMethods", refundMethods);
		resMap.put("paymentId", combineOrder.getPaymentId());
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(refundOrder.getServiceOrderId());
		CustomerServiceOrderExample e = new CustomerServiceOrderExample();
		CustomerServiceOrderExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
		List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderService.selectByExample(e);
		if(customerServiceOrders!=null && customerServiceOrders.size()>0){
			List<Integer> csoIds = new ArrayList<Integer>();
			for(CustomerServiceOrder cso:customerServiceOrders){
				csoIds.add(cso.getId());
			}
			RefundOrderExample roe = new RefundOrderExample();
			RefundOrderExample.Criteria roec = roe.createCriteria();
			roec.andDelFlagEqualTo("0");
			roec.andServiceOrderIdIn(csoIds);
			List<RefundOrder> refundOrders = refundOrderService.selectByExample(roe);
			resMap.put("count", refundOrders.size());
		}
		resMap.put("orderDtlId", customerServiceOrder.getOrderDtlId());
		resMap.put("nowDate", sdf.format(new Date()));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 退款操作
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/refund.shtml")
	@ResponseBody
	public Map<String, Object> refund(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Date date = new Date();
			String refundMethod = request.getParameter("refundMethod");
			String refundRegisterDate = request.getParameter("refundRegisterDate");
			String remarks =request.getParameter("remarks");
			String refundCode =request.getParameter("refundCode");
			RefundRegisterDateLockExample example = new RefundRegisterDateLockExample();
			RefundRegisterDateLockExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(refundRegisterDate);
			List<RefundRegisterDateLock> refundRegisterDateLocks = refundRegisterDateLockMapper.selectByExample(example);
			if(refundRegisterDateLocks!=null && refundRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "退款日期已被锁住，无法使用当前日期，请更换退款日期");
				return resMap;
			}
			RefundOrder refundOrder = refundOrderService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")));
			RefundOrderExample e = new RefundOrderExample();
			RefundOrderExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andServiceOrderIdEqualTo(refundOrder.getServiceOrderId());
			List<RefundOrder> refundOrders = refundOrderService.selectByExample(e);
			if(refundOrders.size()>=2){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "重复的退款单，请勿手动退款");
				return resMap;
			}
			
//			if(refundMethod.equals("1")){//原路返还
//				refundOrder.setStatus("0");//未退
//				refundOrder.setTryTimes(0);
//			}else if(refundMethod.equals("2")){//现金支付,转账
//				refundOrder.setStatus("1");//已退
//				refundOrder.setSuccessDate(new Date());
//			}
			refundOrder.setStatus("1");//已退
			refundOrder.setSuccessDate(date);
			refundOrder.setRefundMethod(refundMethod);
			refundOrder.setRefundRegisterDate(date);
			refundOrder.setCashierStaffId(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			if(!StringUtils.isEmpty(remarks)){
				refundOrder.setRemarks(remarks);
			}else{
				refundOrder.setRemarks("手动退款,默认退款成功");
			}
			refundOrder.setRefundCode(refundCode);
			refundOrder.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			refundOrder.setUpdateDate(date);
			
			CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(refundOrder.getServiceOrderId());
			customerServiceOrder.setStatus("1");
			OrderDtl orderDtl = null;
			if(!refundOrder.getServiceType().equals("D")){//不是直赔
				orderDtl = orderDtlService.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());
				orderDtl.setRefundDate(date);
				if(refundOrder.getServiceType().equals("A")){//退款
					orderDtl.setProductStatus("2");
					customerServiceOrder.setProStatus("A4");
				}else if(refundOrder.getServiceType().equals("B")){//退货
					orderDtl.setProductStatus("3");
					customerServiceOrder.setProStatus("B7");
				}
			}else{
				customerServiceOrder.setProStatus("D2");
			}
			CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
			customerServiceStatusLog.setDelFlag("0");
			customerServiceStatusLog.setCreateDate(date);
			customerServiceStatusLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceStatusLog.setStatus(customerServiceOrder.getStatus());
			customerServiceStatusLog.setProStatus(customerServiceOrder.getProStatus());
			if(!StringUtils.isEmpty(remarks)){
				customerServiceStatusLog.setRemarks(remarks);
			}else{
				customerServiceStatusLog.setRemarks("手动退款,默认退款成功");
			}
			CustomerServiceLog customerServiceLog = new CustomerServiceLog();
			customerServiceLog.setDelFlag("0");
			customerServiceLog.setCreateDate(date);
			customerServiceLog.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
			customerServiceLog.setOperatorType("3");//系统
			if(!StringUtils.isEmpty(remarks)){
				customerServiceLog.setContent(remarks);
			}else{
				customerServiceLog.setContent("手动退款,默认退款成功");
			}
			if(!StringUtils.isEmpty(remarks)){
				customerServiceLog.setRemarks(remarks);
			}else{
				customerServiceLog.setRemarks("手动退款,默认退款成功");
			}
			SysAppMessage sysAppMessage = new SysAppMessage();
			sysAppMessage.setType(SysAppMessageCustom.TYPE_WULIU);
			sysAppMessage.setTitle(SysAppMessageCustom.TITLE_REFUND_PROGRESS_REMIND);
			sysAppMessage.setContent("你的"+refundOrder.getRefundAmount()+"元退款进度有更新");
			sysAppMessage.setLinkType(SysAppMessageCustom.LINKTYPE_SERVICE_ORDER);
			sysAppMessage.setLinkId(refundOrder.getServiceOrderId());
			CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(refundOrder.getCombineOrderId());
			sysAppMessage.setMemberId(combineOrder.getMemberId());
			sysAppMessage.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			sysAppMessage.setCreateDate(date);
			sysAppMessage.setDelFlag("0");
			refundOrderService.updateRefundOrder(refundOrder,customerServiceOrder,orderDtl,sysAppMessage,customerServiceStatusLog,customerServiceLog);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 退款登记操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/toRegister.shtml")
	public ModelAndView toRegister(HttpServletRequest request){
		String rtPage = "/refundOrder/toRegister";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String idStr = request.getParameter("id");
		resMap.put("id", idStr);
		if(idStr.split(",").length>1){
			resMap.put("batch", 1);
		}else{
			resMap.put("batch", 0);
			int refundOrderId=Integer.valueOf(request.getParameter("id"));
			RefundOrder refundOrder=refundOrderService.selectByPrimaryKey(refundOrderId);
			resMap.put("refundOrder", refundOrder);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 退款登记操作
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/register.shtml")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String status =request.getParameter("status");
			String refundCode =request.getParameter("refundCode");
			String refundRegisterDate = request.getParameter("refundRegisterDate");
			RefundRegisterDateLockExample example = new RefundRegisterDateLockExample();
			RefundRegisterDateLockExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(refundRegisterDate);
			List<RefundRegisterDateLock> refundRegisterDateLocks = refundRegisterDateLockMapper.selectByExample(example);
			if(refundRegisterDateLocks!=null && refundRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "退款日期已被锁住，无法使用当前日期，请更换退款日期");
				return resMap;
			}
			//修改退款单
			RefundOrder refundOrder = new RefundOrder();
			refundOrder.setId(Integer.parseInt(request.getParameter("id")));
			refundOrder.setStatus(status);
			refundOrder.setRefundCode(refundCode);
			refundOrder.setSuccessDate(DateUtil.getDateByFormat(refundRegisterDate));
			refundOrder.setRefundRegisterDate(DateUtil.getDateByFormat(refundRegisterDate));
			refundOrder.setCashierStaffId(staffID);
			refundOrder.setUpdateBy(staffID);
			refundOrder.setUpdateDate(date);
			RefundOrderCustomExample refundOrderCustomExample = new RefundOrderCustomExample();
			refundOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("id")));
			refundOrderCustomExample.setLimitSize(1);
			List<RefundOrderCustom> refundOrderCustomList =  refundOrderService.selectRefundOrderCustomByExample(refundOrderCustomExample);
			OrderDtl orderDtl = null;
			if(refundOrderCustomList != null && refundOrderCustomList.size() > 0) {
				RefundOrderCustom refundOrderCustom = refundOrderCustomList.get(0);
				//修改子订单明细的退款时间
				orderDtl = new OrderDtl();
				orderDtl.setId(refundOrderCustom.getOrderDtlId());
				orderDtl.setRefundDate(DateUtil.getDateByFormat(refundRegisterDate));
				orderDtl.setUpdateBy(staffID);
				orderDtl.setUpdateDate(date);
			}
			refundOrderService.updateRefundOrderAndOrderDtl(refundOrder, orderDtl);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 退款确认操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/toConfirm.shtml")
	public ModelAndView toConfirm(HttpServletRequest request) {
		String rtPage = "/refundOrder/toConfirm";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String idStr = request.getParameter("id");
		resMap.put("id", idStr);
		if(idStr.split(",").length>1){
			resMap.put("batch", 1);
		}else{
			resMap.put("batch", 0);
			int refundOrderId=Integer.valueOf(request.getParameter("id"));
			RefundOrder refundOrder=refundOrderService.selectByPrimaryKey(refundOrderId);
			resMap.put("refundOrder", refundOrder);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 退款确认操作
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/confirm.shtml")
	@ResponseBody
	public Map<String, Object> confirm(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String status =request.getParameter("status");
			String refundRegisterDate =request.getParameter("refundRegisterDate");
			String refundCode =request.getParameter("refundCode");
			RefundRegisterDateLockExample example = new RefundRegisterDateLockExample();
			RefundRegisterDateLockExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(refundRegisterDate);
			List<RefundRegisterDateLock> refundRegisterDateLocks = refundRegisterDateLockMapper.selectByExample(example);
			if(refundRegisterDateLocks!=null && refundRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "退款日期已被锁住，无法使用当前日期，请更换退款日期");
				return resMap;
			}
			//修改退款单
			RefundOrder refundOrder = new RefundOrder();
			refundOrder.setId(Integer.parseInt(request.getParameter("id")));
			refundOrder.setStatus(status);
			refundOrder.setRefundCode(refundCode);
			refundOrder.setSuccessDate(DateUtil.getDateByFormat(refundRegisterDate));
			refundOrder.setRefundRegisterDate(DateUtil.getDateByFormat(refundRegisterDate));
			refundOrder.setCashierStaffId(staffID);
			refundOrder.setUpdateBy(staffID);
			refundOrder.setUpdateDate(date);
			RefundOrderCustomExample refundOrderCustomExample = new RefundOrderCustomExample();
			refundOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(request.getParameter("id")));
			refundOrderCustomExample.setLimitSize(1);
			List<RefundOrderCustom> refundOrderCustomList =  refundOrderService.selectRefundOrderCustomByExample(refundOrderCustomExample);
			OrderDtl orderDtl = null;
			CustomerServiceOrder customerServiceOrder = null;
			if(refundOrderCustomList != null && refundOrderCustomList.size() > 0) {
				RefundOrderCustom refundOrderCustom = refundOrderCustomList.get(0);
				//修改子订单明细的退款时间
				orderDtl = new OrderDtl();
				orderDtl.setId(refundOrderCustom.getOrderDtlId());
				orderDtl.setRefundDate(DateUtil.getDateByFormat(refundRegisterDate));
				orderDtl.setUpdateBy(staffID);
				orderDtl.setUpdateDate(date);
				//修改售后单
				customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(refundOrderCustom.getServiceOrderId());
				customerServiceOrder.setStatus("1");
				if(customerServiceOrder.getServiceType().equals("A")){//退款单
					customerServiceOrder.setProStatus("A4");
				}else if(customerServiceOrder.getServiceType().equals("B")){//退货单
					customerServiceOrder.setProStatus("B7");
				}else if(customerServiceOrder.getServiceType().equals("D")){//直赔单
					customerServiceOrder.setProStatus("D2");
				}
				customerServiceOrder.setUpdateBy(staffID);
				customerServiceOrder.setUpdateDate(date);
			}
			refundOrderService.updateRefundOrderAndOrderDtlAndCustomer(refundOrder, orderDtl, customerServiceOrder);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 批量退款确认操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/toBatch.shtml")
	public ModelAndView toBatch(HttpServletRequest request) {
		String rtPage = "/refundOrder/toBatch";
		String ids = request.getParameter("ids");
		Map<String, Object> resMap = new HashMap<String, Object>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		String refundCode = request.getParameter("refundCode");
		if(StringUtils.isEmpty(refundCode)){
			paramMap.put("refundCode", "");
		}else{
			paramMap.put("refundCode",refundCode);
			resMap.put("refundCode", refundCode);
		}
		List<String> statusList = new ArrayList<String>();
		statusList.add("1");
		statusList.add("3");
		paramMap.put("list",statusList);
		List<Integer> idsList = refundOrderService.getIdsByRefundCode(paramMap);
		if(idsList!=null && idsList.size()>0){
			String tmpStr="";
			for(int i=0;i<idsList.size();i++){
				if(i!=idsList.size()-1){
					tmpStr+=idsList.get(i).toString()+",";
				}else{
					tmpStr+=idsList.get(i).toString();
				}
			}
			ids = tmpStr;
		}
		resMap.put("ids", ids);
		if(ids.split(",").length>1){
			
		}else{
			if(!StringUtils.isEmpty(request.getParameter("ids"))){
				int refundOrderId=Integer.valueOf(request.getParameter("ids"));
				RefundOrder refundOrder=refundOrderService.selectByPrimaryKey(refundOrderId);
				resMap.put("refundOrder", refundOrder);
				resMap.put("refundCode", refundOrder.getRefundCode());
			}
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 批量退款确认操作
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/batch.shtml")
	@ResponseBody
	public Map<String, Object> batch(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String status =request.getParameter("status");
			String refundCode =request.getParameter("refundCode");
			String refundRegisterDate =request.getParameter("refundRegisterDate");
			String ids = request.getParameter("ids");
			RefundRegisterDateLockExample example = new RefundRegisterDateLockExample();
			RefundRegisterDateLockExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(refundRegisterDate);
			List<RefundRegisterDateLock> refundRegisterDateLocks = refundRegisterDateLockMapper.selectByExample(example);
			if(refundRegisterDateLocks!=null && refundRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "退款日期已被锁住，无法使用当前日期，请更换退款日期");
				return resMap;
			}
			if(!StringUtil.isEmpty(ids)) {
				String[] idsArray = ids.split(",");
				List<RefundOrder> refundOrderList = new ArrayList<RefundOrder>();
				List<OrderDtl> orderDtlList = new ArrayList<OrderDtl>();
				List<CustomerServiceOrder> customerServiceOrderList = new ArrayList<CustomerServiceOrder>();
				for(String id : idsArray) {
					//修改退款单
					RefundOrder refundOrder = new RefundOrder();
					refundOrder.setId(Integer.parseInt(id));
					refundOrder.setStatus(status);
					refundOrder.setRefundCode(refundCode);
					refundOrder.setRefundRegisterDate(DateUtil.getDateByFormat(refundRegisterDate));
					refundOrder.setCashierStaffId(staffID);
					refundOrder.setUpdateBy(staffID);
					refundOrder.setUpdateDate(date);
					refundOrderList.add(refundOrder);
					RefundOrderCustomExample refundOrderCustomExample = new RefundOrderCustomExample();
					refundOrderCustomExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
					refundOrderCustomExample.setLimitSize(1);
					List<RefundOrderCustom> refundOrderCustomList =  refundOrderService.selectRefundOrderCustomByExample(refundOrderCustomExample);
					OrderDtl orderDtl = null;
					CustomerServiceOrder customerServiceOrder = null;
					if(refundOrderCustomList != null && refundOrderCustomList.size() > 0) {
						RefundOrderCustom refundOrderCustom = refundOrderCustomList.get(0);
						//修改子订单明细的退款时间
						orderDtl = new OrderDtl();
						orderDtl.setId(refundOrderCustom.getOrderDtlId());
						orderDtl.setRefundDate(DateUtil.getDateByFormat(refundRegisterDate));
						orderDtl.setUpdateBy(staffID);
						orderDtl.setUpdateDate(date);
						orderDtlList.add(orderDtl);
						if(status.equals("4")) { //已确认
							//修改售后单
							customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(refundOrderCustom.getServiceOrderId());
							customerServiceOrder.setStatus("1");
							if(customerServiceOrder.getServiceType().equals("A")){//退款单
								customerServiceOrder.setProStatus("A4");
							}else if(customerServiceOrder.getServiceType().equals("B")){//退货单
								customerServiceOrder.setProStatus("B7");
							}else if(customerServiceOrder.getServiceType().equals("D")){//直赔单
								customerServiceOrder.setProStatus("D2");
							}
							customerServiceOrder.setUpdateBy(staffID);
							customerServiceOrder.setUpdateDate(date);
							customerServiceOrderList.add(customerServiceOrder);
						}
					}
				}
				refundOrderService.updateRefundOrderAndOrderDtlAndCustomerList(refundOrderList, orderDtlList, customerServiceOrderList);
			}
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 每日退款汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/count/list.shtml")
	public ModelAndView countList(HttpServletRequest request) {
		String rtPage = "/refundOrder/count/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String success_date_end = df.format(new Date());
		String success_date_begin = success_date_end.substring(0,7)+"-01";
		resMap.put("success_date_end", success_date_end);
		resMap.put("success_date_begin", success_date_begin);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日退款汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/count/data.shtml")
	@ResponseBody
	public Map<String, Object> countData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String successDateBegin = request.getParameter("success_date_begin");
			String successDateEnd = request.getParameter("success_date_end");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(successDateBegin) ){
				paramMap.put("successDateBegin", successDateBegin+" 00:00:00");
			}else{
				paramMap.put("successDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(successDateEnd) ){
				paramMap.put("successDateEnd", successDateEnd+" 23:59:59");
			}else{
				paramMap.put("successDateEnd", df.format(new Date()));
			}
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.refundOrderCountEachDayList(paramMap);
			resMap.put("Rows", refundOrderCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	public List<String> getBetweenDays(String stime,String etime){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date sdate=null;
        Date eDate=null;
        try {
             sdate=df.parse(stime);
             eDate=df.parse(etime);
        } catch (Exception e) {
              e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
              list.add(df.format(sdate));
              c.setTime(sdate);
              c.add(Calendar.DATE, 1); // 日期加1天
              sdate = c.getTime();
              }
        return list;
  }
	
	/**
	 * 导出每日退款汇总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/refundOrder/count/export.shtml")
	public void exportCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String successDateBegin = request.getParameter("success_date_begin");
			String successDateEnd = request.getParameter("success_date_end");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(successDateBegin) ){
				paramMap.put("successDateBegin", successDateBegin+" 00:00:00");
			}else{
				paramMap.put("successDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(successDateEnd) ){
				paramMap.put("successDateEnd", successDateEnd+" 23:59:59");
			}else{
				paramMap.put("successDateEnd", df.format(new Date()));
			}
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.refundOrderCountEachDayList(paramMap);
			String[] titles = { "退款日期", "笔数", "退款金额","微信APP/H5金额","支付宝金额","微信公众号金额","其他金额","微信APP/H5笔数","支付宝笔数","微信公众号笔数","其他笔数","已确认金额","锁住日期","已登记金额","未处理金额","异常金额"};
			ExcelBean excelBean = new ExcelBean("导出每日退款汇总列表.xls",
					"导出每日退款汇总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(RefundOrderCustom refundOrderCustom:refundOrderCustoms){
				String lockDesc = "";
				if(refundOrderCustom.getTotalCount()>0){
	            	if(!StringUtils.isEmpty(refundOrderCustom.getLockDate())){
	            		lockDesc = "已锁";	                		
                	}else{
                		lockDesc ="未锁";
                	}
            	}
				String[] data = {
					refundOrderCustom.getEachDay(),
					refundOrderCustom.getTotalCount() == null? "":refundOrderCustom.getTotalCount().toString(),
					refundOrderCustom.getRefundAmount() == null? "":refundOrderCustom.getRefundAmount().toString(),
					refundOrderCustom.getWxAmount() == null? "":refundOrderCustom.getWxAmount().toString(),
					refundOrderCustom.getZfbAmount() == null? "":refundOrderCustom.getZfbAmount().toString(),
					refundOrderCustom.getGzhAmount() == null? "":refundOrderCustom.getGzhAmount().toString(),
					refundOrderCustom.getOtherAmount() == null? "":refundOrderCustom.getOtherAmount().toString(),
					refundOrderCustom.getWxCount() == null? "":refundOrderCustom.getWxCount().toString(),
					refundOrderCustom.getZfbCount() == null? "":refundOrderCustom.getZfbCount().toString(),
					refundOrderCustom.getGzhCount() == null? "":refundOrderCustom.getGzhCount().toString(),
					refundOrderCustom.getOtherCount() == null? "":refundOrderCustom.getOtherCount().toString(),
					refundOrderCustom.getConfirmAmount() == null? "":refundOrderCustom.getConfirmAmount().toString(),
					lockDesc,
					refundOrderCustom.getRegisterAmount() == null? "":refundOrderCustom.getRegisterAmount().toString(),
					refundOrderCustom.getNoDealAmount() == null? "":refundOrderCustom.getNoDealAmount().toString(),
					refundOrderCustom.getUnusualAmount() == null? "":refundOrderCustom.getUnusualAmount().toString()
			};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 锁住退款日期操作页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/count/toLockDate.shtml")
	public ModelAndView toLockDate(HttpServletRequest request){
		String rtPage = "/refundOrder/count/toLockDate";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String lockDate = request.getParameter("lockDate");
		resMap.put("lockDate", lockDate);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 锁住退款日期
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/count/lockDate.shtml")
	@ResponseBody
	public Map<String, Object> lockDate(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String lockDate = request.getParameter("lockDate");
			RefundRegisterDateLockExample RefundRegisterDateLockExample = new RefundRegisterDateLockExample();
			RefundRegisterDateLockExample.Criteria criteria = RefundRegisterDateLockExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andLockDateEqualTo(lockDate);
			List<RefundRegisterDateLock> refundRegisterDateLocks = refundRegisterDateLockMapper.selectByExample(RefundRegisterDateLockExample);
			if(refundRegisterDateLocks!=null && refundRegisterDateLocks.size()>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "当前日期已被锁住，退款不能用此日期");
				return resMap;
			}
			RefundRegisterDateLock refundRegisterDateLock = new RefundRegisterDateLock();
			refundRegisterDateLock.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			refundRegisterDateLock.setCreateDate(new Date());
			refundRegisterDateLock.setDelFlag("0");
			refundRegisterDateLock.setLockDate(lockDate);
			refundRegisterDateLockMapper.insertSelective(refundRegisterDateLock);
			resMap.put("returnCode", "0000");
			resMap.put("returnMsg", "确认成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 检验能否批量全部处理 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/refundOrder/check.shtml")
	@ResponseBody
	public Map<String, Object> check(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			String refundCode =request.getParameter("refundCode");
			RefundOrderExample example = new RefundOrderExample();
			RefundOrderExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andRefundCodeEqualTo(refundCode);
			List<String> statusList = new ArrayList<String>();
			statusList.add("1");
			statusList.add("3");
			criteria.andStatusIn(statusList);
			List<RefundOrder> refundOrders = refundOrderService.selectByExample(example);
			if(refundOrders!=null && refundOrders.size()>0){
				resMap.put("returnCode", "0000");
				resMap.put("returnMsg", "确认成功");
			}else{
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "条件不符，无法批量处理");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 退款合计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/refundOrder/count.shtml")
	public ModelAndView count(HttpServletRequest request) throws ParseException {
		String rtPage = "/refundOrder/count";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
			int paymentId=Integer.valueOf(request.getParameter("paymentId"));
			paramMap.put("paymentId", paymentId);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("refundPaymentId")) ){
			String refundPaymentId=request.getParameter("refundPaymentId");
			paramMap.put("refundPaymentId", refundPaymentId);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("status"))){
			String status=request.getParameter("status");
			paramMap.put("status", status);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("serviceType")) ){
			String serviceType=request.getParameter("serviceType");
			paramMap.put("serviceType", serviceType);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("mchtCode")) ){
			String mchtCode=request.getParameter("mchtCode");
			paramMap.put("mchtCode", mchtCode);
		}
		
		if(!StringUtil.isEmpty(request.getParameter("refund_agree_date_begin")) ){
			paramMap.put("refund_agree_date_begin", request.getParameter("refund_agree_date_begin")+" 00:00:00");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("refund_agree_date_end")) ){
			paramMap.put("refund_agree_date_end", request.getParameter("refund_agree_date_end")+" 23:59:59");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("combineOrderCode")) ){
			paramMap.put("combineOrderCode", request.getParameter("combineOrderCode"));
		}
		
		if(!StringUtil.isEmpty(request.getParameter("serviceOrderCode")) ){
			paramMap.put("serviceOrderCode", request.getParameter("serviceOrderCode"));
		}
		
		if(!StringUtil.isEmpty(request.getParameter("success_date_begin")) ){
			paramMap.put("success_date_begin", request.getParameter("success_date_begin")+" 00:00:00");
		}
		
		if(!StringUtil.isEmpty(request.getParameter("success_date_end")) ){
			paramMap.put("success_date_end", request.getParameter("success_date_end")+" 23:59:59");
		}
		if(!StringUtil.isEmpty(request.getParameter("refundCode")) ){
			paramMap.put("refundCode", request.getParameter("refundCode"));
		}
		if(!StringUtil.isEmpty(request.getParameter("refund_register_date_begin")) ){
			paramMap.put("refund_register_date_begin", request.getParameter("refund_register_date_begin")+" 23:59:59");
		}
		if(!StringUtil.isEmpty(request.getParameter("refund_register_date_end")) ){
			paramMap.put("refund_register_date_end", request.getParameter("refund_register_date_end")+" 23:59:59");
		}
		List<Map<String, Object>> list = refundOrderService.refundOrderCount(paramMap);
		if(list!=null && list.size()>0){
			resMap.put("result", list.get(0));
		}
		return new ModelAndView(rtPage,resMap);
	}
}
