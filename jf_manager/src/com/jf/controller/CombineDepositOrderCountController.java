package com.jf.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.RefundOrderCustom;
import com.jf.entity.RefundOrderCustomExample;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderCustomExample;
import com.jf.service.CombineDepositOrderService;
import com.jf.service.CombineOrderService;
import com.jf.service.RefundOrderService;
import com.jf.service.SubDepositOrderService;
import com.jf.vo.Page;

@Controller
public class CombineDepositOrderCountController extends BaseController {
	
	@Resource
	private CombineOrderService combineOrderService;
	
	@Resource
	private CombineDepositOrderService combineDepositOrderService;
	
	@Resource
	private SubDepositOrderService subDepositOrderService;
	
	@Resource
	private RefundOrderService refundOrderService;
	
	
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 每日定金汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/list.shtml")
	public ModelAndView combineDepositOrderCountList(HttpServletRequest request) {
		String rtPage = "/combineDepositOrderCount/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String pay_date_end = df.format(new Date());
		String pay_date_begin = pay_date_end.substring(0,7)+"-01";
		resMap.put("pay_date_end", pay_date_end);
		resMap.put("pay_date_begin", pay_date_begin);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日定金汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/data.shtml")
	@ResponseBody
	public Map<String, Object> combineDepositOrderCountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(payDateBegin) ){
				paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			}else{
				paramMap.put("payDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(payDateEnd) ){
				paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			}else{
				paramMap.put("payDateEnd", df.format(new Date()));
			}
			List<Map<String,Object>> combineDepositOrderCustoms = combineDepositOrderService.combineDepositOrderCountEachDayList(paramMap);
			resMap.put("Rows", combineDepositOrderCustoms);
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
	 * 导出每日收款汇总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/combineDepositOrderCount/export.shtml")
	public void exportCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(payDateBegin) ){
				paramMap.put("payDateBegin", payDateBegin+" 00:00:00");
			}else{
				paramMap.put("payDateBegin", df.format(new Date()).substring(0,7)+"-01 00:00:00");
			}
			if(!StringUtil.isEmpty(payDateEnd) ){
				paramMap.put("payDateEnd", payDateEnd+" 23:59:59");
			}else{
				paramMap.put("payDateEnd", df.format(new Date()));
			}
			List<Map<String,Object>> combineDepositOrderCustoms = combineDepositOrderService.combineDepositOrderCountEachDayList(paramMap);
			String[] titles = { "付款日期", "笔数", "微信APP/H5金额","支付宝金额","微信公众号金额","微信APP/H5笔数","支付宝笔数","微信公众号笔数"};
			ExcelBean excelBean = new ExcelBean("导出每日定金汇总列表.xls",
					"导出每日定金汇总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String,Object> map:combineDepositOrderCustoms){
				String[] data = {
						map.get("each_day").toString(),
						map.get("total_count") == null? "":map.get("total_count").toString(),
						map.get("wx_amount") == null? "":map.get("wx_amount").toString(),
						map.get("zfb_amount") == null? "":map.get("zfb_amount").toString(),
						map.get("gzh_amount") == null? "":map.get("gzh_amount").toString(),
						map.get("wx_count") == null? "":map.get("wx_count").toString(),
						map.get("zfb_count") == null? "":map.get("zfb_count").toString(),
						map.get("gzh_count") == null? "":map.get("gzh_count").toString(),
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
	 * 定金收款明细列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/dtl/list.shtml")
	public ModelAndView dtlList(HttpServletRequest request) {
		String rtPage = "/combineDepositOrderCount/dtlList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 定金收款明细列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/dtl/data.shtml")
	@ResponseBody
	public Map<String, Object> dtlData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SubDepositOrderCustomExample e = new SubDepositOrderCustomExample();
			SubDepositOrderCustomExample.SubDepositOrderCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andCombineDepositOrderStatusEqualTo(request.getParameter("combineDepositOrderStatus"));
			e.setOrderByClause("t.id desc");
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					c.andPaymentIdIn("('2','5')");
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					c.andPaymentIdIn("('1','6')");
				}
				if (paymentId.equals("3")){//微信公众号
					c.andPaymentIdIn("('4','7')");
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("combineDepositOrderCode"))){
				String combineDepositOrderCode=request.getParameter("combineDepositOrderCode");
				c.andCombineDepositOrderCodeEqualTo(combineDepositOrderCode);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ){
				String paymentNo=request.getParameter("paymentNo");
				c.andPaymentNoEqualTo(paymentNo);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
				c.andCombineDepositOrderPayDateGreaterThanOrEqualTo(request.getParameter("pay_date_begin")+" 00:00:00");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
				c.andCombineDepositOrderPayDateLessThanOrEqualTo(request.getParameter("pay_date_end")+" 23:59:59");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				c.andStatusEqualTo(status);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				c.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				c.andMchtNameLikeTo(request.getParameter("mchtName"));
			}
			
			String refundDateBegin = request.getParameter("refund_date_begin");
			String refundDateEnd = request.getParameter("refund_date_end");
			if(!StringUtil.isEmpty(refundDateBegin) && !StringUtil.isEmpty(refundDateEnd)) {
				c.andRefundDateBetween(refundDateBegin+" 00:00:00", refundDateEnd+" 23:59:59");
			}else {
				if(!StringUtil.isEmpty(refundDateBegin) ){
					c.andRefundDateGreaterThanOrEqualTo(refundDateBegin+" 00:00:00");
				}
				if(!StringUtil.isEmpty(refundDateEnd) ){
					c.andRefundDateLessThanOrEqualTo(refundDateEnd+" 23:59:59");
				}
			}
			
			totalCount = subDepositOrderService.countSubDepositOrderCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<SubDepositOrderCustom> subDepositOrderCustoms = subDepositOrderService.selectSubDepositOrderCustomByExample(e);
			resMap.put("Rows", subDepositOrderCustoms);
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
	@RequestMapping("/combineDepositOrderCount/dtl/export.shtml")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SubDepositOrderCustomExample e=new SubDepositOrderCustomExample();
			SubDepositOrderCustomExample.SubDepositOrderCustomCriteria c=e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andCombineDepositOrderStatusEqualTo(request.getParameter("combineDepositOrderStatus"));
			e.setOrderByClause("t.id desc");
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					c.andPaymentIdIn("('2','5')");
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					c.andPaymentIdIn("('1','6')");
				}
				if (paymentId.equals("3")){//微信公众号
					c.andPaymentIdIn("('4','7')");
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("combineDepositOrderCode"))){
				String combineDepositOrderCode=request.getParameter("combineDepositOrderCode");
				c.andCombineDepositOrderCodeEqualTo(combineDepositOrderCode);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("paymentNo")) ){
				String paymentNo=request.getParameter("paymentNo");
				c.andPaymentNoEqualTo(paymentNo);
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("pay_date_begin")) ){
				c.andCombineDepositOrderPayDateGreaterThanOrEqualTo(request.getParameter("pay_date_begin")+" 00:00:00");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("pay_date_end")) ){
				c.andCombineDepositOrderPayDateLessThanOrEqualTo(request.getParameter("pay_date_end")+" 23:59:59");
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				c.andStatusEqualTo(status);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				c.andMchtCodeEqualTo(request.getParameter("mchtCode"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				c.andMchtNameLikeTo(request.getParameter("mchtName"));
			}
			
			String refundDateBegin = request.getParameter("refund_date_begin");
			String refundDateEnd = request.getParameter("refund_date_end");
			if(!StringUtil.isEmpty(refundDateBegin) && !StringUtil.isEmpty(refundDateEnd)) {
				c.andRefundDateBetween(refundDateBegin+" 00:00:00", refundDateEnd+" 23:59:59");
			}else {
				if(!StringUtil.isEmpty(refundDateBegin) ){
					c.andRefundDateGreaterThanOrEqualTo(refundDateBegin+" 00:00:00");
				}
				if(!StringUtil.isEmpty(refundDateEnd) ){
					c.andRefundDateLessThanOrEqualTo(refundDateEnd+" 23:59:59");
				}
			}
			
			List<SubDepositOrderCustom> subDepositOrderCustoms=subDepositOrderService.selectSubDepositOrderCustomByExample(e);
			String[] titles = { "预售订单编号", "商家序号", "公司名称", "店铺名称", "品牌/货号", "付款渠道", "实付金额（元）", "订单状态", "付款时间", "退款时间",
					"支付交易号", "佣金比例", "技术服务费", "结算金额", "尾款子订单", "交易完成时间"};
			ExcelBean excelBean = new ExcelBean("导出收款母订单列表.xls",
					"导出定金收款明细", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(SubDepositOrderCustom subDepositOrderCustom:subDepositOrderCustoms){
				String[] data = {
						subDepositOrderCustom.getCombineDepositOrderCode(),
						subDepositOrderCustom.getMchtCode()==null?"":subDepositOrderCustom.getMchtCode(),
						subDepositOrderCustom.getCompanyName()==null?"":subDepositOrderCustom.getCompanyName(),
						subDepositOrderCustom.getShopName()==null?"":subDepositOrderCustom.getShopName(),
						"【"+subDepositOrderCustom.getBrandName()+"】"+subDepositOrderCustom.getArtNo(),
						subDepositOrderCustom.getPaymentName()==null?"":subDepositOrderCustom.getPaymentName(),
						subDepositOrderCustom.getPayAmount().toString(),
						subDepositOrderCustom.getStatusDesc(),
						subDepositOrderCustom.getPayDate()==null?"":dateFormat.format(subDepositOrderCustom.getPayDate()),
						subDepositOrderCustom.getRefundDate()==null?"":dateFormat.format(subDepositOrderCustom.getRefundDate()),
						subDepositOrderCustom.getPaymentNo()==null?"":"`"+subDepositOrderCustom.getPaymentNo(),
						subDepositOrderCustom.getPopCommissionRate()==null?"":subDepositOrderCustom.getPopCommissionRate().toString(),
						subDepositOrderCustom.getCommissionAmount()==null?"":subDepositOrderCustom.getCommissionAmount().toString(),
						subDepositOrderCustom.getSettleAmount()==null?"":subDepositOrderCustom.getSettleAmount().toString(),
						subDepositOrderCustom.getSubOrderCode()==null?"":subDepositOrderCustom.getSubOrderCode(),
						subDepositOrderCustom.getCompleteDate()==null?"":dateFormat.format(subDepositOrderCustom.getCompleteDate())
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
	 * 退款任务列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/refundOrder/list.shtml")
	public ModelAndView refundOrderList(HttpServletRequest request) {
		String rtPage = "/combineDepositOrderCount/refundOrderList";
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
	@RequestMapping(value = "/combineDepositOrderCount/refundOrder/data.shtml")
	@ResponseBody
	public Map<String, Object> refundOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			RefundOrderCustomExample refundOrderCustomExample = new RefundOrderCustomExample();
			RefundOrderCustomExample.RefundOrderCustomCriteria refundOrderCustomCriteria = refundOrderCustomExample.createCriteria();
			refundOrderCustomCriteria.andDelFlagEqualTo("0");
			refundOrderCustomExample.setOrderByClause("t.id asc,t.status asc,t.success_date asc,t.refund_agree_date asc");
			refundOrderCustomCriteria.andOrderTypeEqualTo("2");
			if(!StringUtil.isEmpty(request.getParameter("paymentId"))){
				String paymentId=request.getParameter("paymentId");
				if (paymentId.equals("1")){//微信APP/H5
					refundOrderCustomCriteria.andDepositPaymentIdIn("2,5");
				}
				if (paymentId.equals("2")){//支付宝APP/H5
					refundOrderCustomCriteria.andDepositPaymentIdIn("1,6");
				}
				if (paymentId.equals("3")){//微信公众号/小程序
					refundOrderCustomCriteria.andDepositPaymentIdIn("4,7");
				}
			}
			if(!StringUtil.isEmpty(request.getParameter("refundPaymentId"))){
				if(request.getParameter("refundPaymentId").equals("-1")){
					refundOrderCustomCriteria.andRefundMethodEqualTo("2");
				}else{
					refundOrderCustomCriteria.andRefundMethodEqualTo("1");
					refundOrderCustomCriteria.andDepositPaymentIdEqualTo(Integer.parseInt(request.getParameter("refundPaymentId")));
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
	@RequestMapping("/combineDepositOrderCount/refundOrder/export.shtml")
	public void exportRefundOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
			if(!StringUtil.isEmpty(request.getParameter("combineOrderCode"))){
				refundOrderCustomCriteria.andCombineOrderCodeEqualTo(request.getParameter("combineOrderCode"));
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
	 * 每日退款汇总
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/refundOrder/count/list.shtml")
	public ModelAndView countList(HttpServletRequest request) {
		String rtPage = "/combineDepositOrderCount/refundOrderCountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String success_date_end = df.format(new Date());
		String success_date_begin = success_date_end.substring(0,7)+"-01";
		resMap.put("success_date_end", success_date_end);
		resMap.put("success_date_begin", success_date_begin);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日定金退款汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/combineDepositOrderCount/refundOrder/count/data.shtml")
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
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.depositOrderRefundOrderCountEachDayList(paramMap);
			resMap.put("Rows", refundOrderCustoms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出每日定金退款汇总excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/combineDepositOrderCount/refundOrder/count/export.shtml")
	public void refundOrderCountExport(HttpServletRequest request,HttpServletResponse response) throws Exception {
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
			List<RefundOrderCustom> refundOrderCustoms = refundOrderService.depositOrderRefundOrderCountEachDayList(paramMap);
			String[] titles = { "退款日期", "笔数", "退款金额","微信APP/H5金额","支付宝金额","微信公众号金额","其他金额","微信APP/H5笔数","支付宝笔数","微信公众号笔数","其他笔数"};
			ExcelBean excelBean = new ExcelBean("导出每日定金退款汇总列表.xls",
					"导出每日定金退款汇总列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(RefundOrderCustom refundOrderCustom:refundOrderCustoms){
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
					refundOrderCustom.getOtherCount() == null? "":refundOrderCustom.getOtherCount().toString()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
