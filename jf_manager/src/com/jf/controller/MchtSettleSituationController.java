package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MchtSettleSituationController extends BaseController {
	
	@Resource
	private MchtSettleSituationService mchtSettleSituationService;
	
	@Resource
	private MchtInfoService mchtInfoService;
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	
	@Resource
	private PayToMchtLogService payToMchtLogService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductBrandService productBrandService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * POP商家结算情况列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettleSituation/list.shtml")
	public ModelAndView list(HttpServletRequest request) {
		String rtPage = "/mchtSettleSituation/mchtSettleSituationList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(request.getParameter("year")) && !StringUtils.isEmpty(request.getParameter("month"))){
			resMap.put("year", request.getParameter("year"));
			resMap.put("month", request.getParameter("month"));
		}else{
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = df.format(now);
			String year = nowDate.substring(0,4);
			String month = nowDate.substring(5, 7);
			resMap.put("year", year);
			resMap.put("month", month);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * POP商家结算情况列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/mchtSettleSituation/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String nowMonth = nowDate.substring(5, 7);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			if(searchYear.equals(nowYear) && searchMonth.equals(nowMonth)){
				HashMap<String,String> paramMap = new HashMap<String,String>();
				if(!StringUtils.isEmpty(searchMchtCode)){
					paramMap.put("mchtCode", searchMchtCode);
				}
				paramMap.put("settleDate", searchYear+"-"+searchMonth);
				paramMap.put("mchtType", "2");
				List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.getSettleSituationList(paramMap);
				List<MchtSettleSituationCustom> result = new ArrayList<MchtSettleSituationCustom>();
				if (page.getPage() * page.getPagesize() > mchtSettleSituationCustoms.size()) {
					result = (mchtSettleSituationCustoms.subList(page.getPagesize() * (page.getPage() - 1), mchtSettleSituationCustoms.size()));
				} else {
					result = (mchtSettleSituationCustoms.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
				}
				resMap.put("Rows", result);
				resMap.put("Total", mchtSettleSituationCustoms.size());
			}else{
				MchtSettleSituationCustomExample example = new MchtSettleSituationCustomExample();
				MchtSettleSituationCustomExample.MchtSettleSituationCustomCriteria criteria = example.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
					criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
				}else{
					criteria.andDelFlagEqualTo("0");
				}
				criteria.andMchtTypeEqualTo("2");
				if(!StringUtils.isEmpty(searchMchtCode)){
					criteria.andMchtCodeEqualTo(searchMchtCode);
				}
				criteria.andSettleDateEqualTo(searchYear+"-"+searchMonth);
				totalCount = mchtSettleSituationService.countByExample(example);
				example.setLimitStart(page.getLimitStart());
				example.setLimitSize(page.getLimitSize());
				List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.selectByExample(example);
				resMap.put("Rows", mchtSettleSituationCustoms);
				resMap.put("Total", totalCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出POP excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtSettleSituation/export.shtml")
	public void exportMchtSettleOrder(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			String createDateBegin = request.getParameter("createDateBegin");
			String createDateEnd = request.getParameter("createDateEnd");
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria criteria = orderDtlCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtSettleOrderIdIsNotNull();
			criteria.andMchtIdEqualTo(mchtId);
			criteria.andSubOrderStatusEqualTo("3");//订单已完成
			criteria.andCompleteDateGreaterThanOrEqualTo(createDateBegin);
			criteria.andCompleteDateLessThanOrEqualTo(createDateEnd);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
			String[] titles = { "类型", "子订单编号", "日期","收货人","会员ID","订单商品ID","品牌","货号","规格","吊牌价","醒购价","数量","商家优惠","平台优惠","积分抵用","客户实付金额","收款渠道","POP结算单ID","类型","供应商序号","供应商","技术服务系数","POP服务费","商品结算金额","直赔单","本期应付","付款金额"};
			ExcelBean excelBean = new ExcelBean("导出POP结算列表.xls",
					"导出POP结算列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				String mchtTypeDesc = "自营POP";
				if(orderDtlCustom.getMchtType().equals("1")){//1.SPOP
					mchtTypeDesc = "SPOP";
				}
				String[] data = {
					"商品",
					orderDtlCustom.getSubOrderCode(),
					df.format(orderDtlCustom.getCreateDate()),
					orderDtlCustom.getReceiverName(),
					orderDtlCustom.getMemberId() == null? "" : orderDtlCustom.getMemberId().toString(),
					orderDtlCustom.getProductId() == null? "" : orderDtlCustom.getProductId().toString(),
					orderDtlCustom.getBrandName(),
					orderDtlCustom.getArtNo(),
					orderDtlCustom.getProductPropDesc(),
					orderDtlCustom.getTagPrice() == null? "" : orderDtlCustom.getTagPrice().toString(),
					orderDtlCustom.getSalePrice() == null? "" : orderDtlCustom.getSalePrice().toString(),
					orderDtlCustom.getQuantity() == null? "" : orderDtlCustom.getQuantity().toString(),
					orderDtlCustom.getMchtPreferential() == null? "" : orderDtlCustom.getMchtPreferential().toString(),
					orderDtlCustom.getPlatformPreferential() == null? "" : orderDtlCustom.getPlatformPreferential().toString(),
					orderDtlCustom.getIntegralPreferential() == null? "" : orderDtlCustom.getIntegralPreferential().toString(),
					orderDtlCustom.getPayAmount() == null? "" : orderDtlCustom.getPayAmount().toString(),
					orderDtlCustom.getPaymentName(),
					orderDtlCustom.getMchtSettleOrderId() == null? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
					mchtTypeDesc,
					orderDtlCustom.getMchtCode(),
					orderDtlCustom.getMchtShortName(),
					orderDtlCustom.getPopCommissionRate() == null? "" : orderDtlCustom.getPopCommissionRate().toString(),
					orderDtlCustom.getCommissionAmount() == null? "" : orderDtlCustom.getCommissionAmount().toString(),
					orderDtlCustom.getSettleAmount() == null? "" : orderDtlCustom.getSettleAmount().toString(),
					"",
					orderDtlCustom.getSettleAmount() == null? "" : orderDtlCustom.getSettleAmount().toString(),
					""
				};
				datas.add(data);
			}
			//直赔单
			CustomerServiceOrderCustomExample customerServiceOrderCustomExample = new CustomerServiceOrderCustomExample();
			CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customerServiceOrderCustomCriteria = customerServiceOrderCustomExample.createCriteria();
			customerServiceOrderCustomCriteria.andDelFlagEqualTo("0");
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo("D");
			customerServiceOrderCustomCriteria.andMchtIdEqualTo(mchtId);
			customerServiceOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateformat.parse(createDateBegin));
			customerServiceOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateformat.parse(createDateEnd));
			List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
			for(CustomerServiceOrderCustom customerServiceOrderCustom:customerServiceOrderCustoms){
				String mchtTypeDesc = "自营POP";
				if(customerServiceOrderCustom.getMchtType().equals("1")){//1.SPOP
					mchtTypeDesc = "SPOP";
				}
				String[] data = {
					"直赔单",
					customerServiceOrderCustom.getSubOrderCode(),
					df.format(customerServiceOrderCustom.getCreateDate()),
					customerServiceOrderCustom.getReceiverName(),
					customerServiceOrderCustom.getMemberId() == null? "" : customerServiceOrderCustom.getMemberId().toString(),
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					customerServiceOrderCustom.getMchtSettleOrderId() == null? "" : customerServiceOrderCustom.getMchtSettleOrderId().toString(),
					mchtTypeDesc,
					customerServiceOrderCustom.getMchtCode(),
					customerServiceOrderCustom.getShortName(),
					"",
					"",
					"",
					customerServiceOrderCustom.getAmount() == null? "" : customerServiceOrderCustom.getAmount().toString(),
					"",
					customerServiceOrderCustom.getAmount() == null? "" : "-"+customerServiceOrderCustom.getAmount().toString(),
					""
				};
				datas.add(data);
			}
			//付款
			PayToMchtLogCustomExample payToMchtLogCustomExample = new PayToMchtLogCustomExample();
			PayToMchtLogCustomExample.PayToMchtLogCustomCriteria payToMchtLogCustomCriteria = payToMchtLogCustomExample.createCriteria();
			payToMchtLogCustomCriteria.andDelFlagEqualTo("0");
			payToMchtLogCustomCriteria.andTypeEqualTo("1");
			payToMchtLogCustomCriteria.andMchtIdEqualTo(mchtId);
			payToMchtLogCustomCriteria.andPayDateGreaterThanOrEqualTo(dateformat.parse(createDateBegin));
			payToMchtLogCustomCriteria.andPayDateLessThanOrEqualTo(dateformat.parse(createDateEnd));
			List<PayToMchtLogCustom> payToMchtLogCustoms = payToMchtLogService.selectByExample(payToMchtLogCustomExample);
			for(PayToMchtLogCustom payToMchtLogCustom:payToMchtLogCustoms){
				String[] data = {
						"付款",
						"",
						df.format(payToMchtLogCustom.getCreateDate()),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						payToMchtLogCustom.getMchtSettleOrderId() == null? "" : payToMchtLogCustom.getMchtSettleOrderId().toString(),
						"自营POP",
						payToMchtLogCustom.getMchtCode(),
						payToMchtLogCustom.getShortName(),
						"",
						"",
						"",
						"",
						"",
						"",
						payToMchtLogCustom.getPayAmount() == null? "" : payToMchtLogCustom.getPayAmount().toString()
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
	 * SPOP商家结算情况列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettleSituation/poolList.shtml")
	public ModelAndView poolList(HttpServletRequest request) {
		String rtPage = "/mchtSettleSituation/poolList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(request.getParameter("year")) && !StringUtils.isEmpty(request.getParameter("month"))){
			resMap.put("year", request.getParameter("year"));
			resMap.put("month", request.getParameter("month"));
		}else{
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = df.format(now);
			String year = nowDate.substring(0,4);
			String month = nowDate.substring(5, 7);
			resMap.put("year", year);
			resMap.put("month", month);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * SPOP商家结算情况列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettleSituation/poolData.shtml")
	@ResponseBody
	public Map<String, Object> poolData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String nowMonth = nowDate.substring(5, 7);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			if(searchYear.equals(nowYear) && searchMonth.equals(nowMonth)){
				HashMap<String,String> paramMap = new HashMap<String,String>();
				if(!StringUtils.isEmpty(searchMchtCode)){
					paramMap.put("mchtCode", searchMchtCode);
				}
				paramMap.put("settleDate", searchYear+"-"+searchMonth);
				paramMap.put("mchtType", "1");
				List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.getSettleSituationList(paramMap);
				List<MchtSettleSituationCustom> result = new ArrayList<MchtSettleSituationCustom>();
				if (page.getPage() * page.getPagesize() > mchtSettleSituationCustoms.size()) {
					result = (mchtSettleSituationCustoms.subList(page.getPagesize() * (page.getPage() - 1), mchtSettleSituationCustoms.size()));
				} else {
					result = (mchtSettleSituationCustoms.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
				}
				resMap.put("Rows", result);
				resMap.put("Total", mchtSettleSituationCustoms.size());
			}else{
				MchtSettleSituationCustomExample example = new MchtSettleSituationCustomExample();
				MchtSettleSituationCustomExample.MchtSettleSituationCustomCriteria criteria = example.createCriteria();
				if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
					criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
				}else{
					criteria.andDelFlagEqualTo("0");
				}
				criteria.andMchtTypeEqualTo("1");
				if(!StringUtils.isEmpty(searchMchtCode)){
					criteria.andMchtCodeEqualTo(searchMchtCode);
				}
				criteria.andSettleDateEqualTo(searchYear+"-"+searchMonth);
				totalCount = mchtSettleSituationService.countByExample(example);
				example.setLimitStart(page.getLimitStart());
				example.setLimitSize(page.getLimitSize());
				List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.selectByExample(example);
				resMap.put("Rows", mchtSettleSituationCustoms);
				resMap.put("Total", totalCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出SPOP excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtSettleSituation/exportPool.shtml")
	public void exportPool(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			Integer mchtId = Integer.parseInt(request.getParameter("mchtId"));
			String createDateBegin = request.getParameter("createDateBegin");
			String createDateEnd = request.getParameter("createDateEnd");
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			OrderDtlCustomExample orderDtlCustomExample = new OrderDtlCustomExample();
			OrderDtlCustomExample.OrderDtlCustomCriteria criteria = orderDtlCustomExample.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtSettleOrderIdIsNotNull();
			criteria.andMchtIdEqualTo(mchtId);
			criteria.andSubOrderStatusEqualTo("3");//订单已完成
			criteria.andCompleteDateGreaterThanOrEqualTo(createDateBegin);
			criteria.andCompleteDateLessThanOrEqualTo(createDateEnd);
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.selectOrderDtlCustomByExample(orderDtlCustomExample);
			String[] titles = { "类型", "子订单编号", "日期","收货人","会员ID","订单商品ID","品牌","货号","规格","吊牌价","醒购价","数量","商家优惠","平台优惠","积分抵用","客户实付金额","收款渠道","供应商序号","供应商","SPOP结算单ID","商品结算金额","直赔单","本期付款"};
			ExcelBean excelBean = new ExcelBean("导出SPOP结算列表.xls",
					"导出SPOP结算列表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> datas = new ArrayList<String[]>();
			for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
				String[] data = {
					"商品",
					orderDtlCustom.getSubOrderCode(),
					df.format(orderDtlCustom.getCreateDate()),
					orderDtlCustom.getReceiverName(),
					orderDtlCustom.getMemberId() == null? "" : orderDtlCustom.getMemberId().toString(),
					orderDtlCustom.getProductId() == null? "" : orderDtlCustom.getProductId().toString(),
					orderDtlCustom.getBrandName(),
					orderDtlCustom.getArtNo(),
					orderDtlCustom.getProductPropDesc(),
					orderDtlCustom.getTagPrice() == null? "" : orderDtlCustom.getTagPrice().toString(),
					orderDtlCustom.getSalePrice() == null? "" : orderDtlCustom.getSalePrice().toString(),
					orderDtlCustom.getQuantity() == null? "" : orderDtlCustom.getQuantity().toString(),
					orderDtlCustom.getMchtPreferential() == null? "" : orderDtlCustom.getMchtPreferential().toString(),
					orderDtlCustom.getPlatformPreferential() == null? "" : orderDtlCustom.getPlatformPreferential().toString(),
					orderDtlCustom.getIntegralPreferential() == null? "" : orderDtlCustom.getIntegralPreferential().toString(),
					orderDtlCustom.getPayAmount() == null? "" : orderDtlCustom.getPayAmount().toString(),
					orderDtlCustom.getPaymentName(),
					orderDtlCustom.getMchtCode(),
					orderDtlCustom.getMchtShortName(),
					orderDtlCustom.getMchtSettleOrderId() == null? "" : orderDtlCustom.getMchtSettleOrderId().toString(),
					orderDtlCustom.getSettleAmount() == null? "" : orderDtlCustom.getSettleAmount().toString(),
					"",
					""
				};
				datas.add(data);
			}
			//直赔单
			CustomerServiceOrderCustomExample customerServiceOrderCustomExample = new CustomerServiceOrderCustomExample();
			CustomerServiceOrderCustomExample.CustomerServiceOrderCustomCriteria customerServiceOrderCustomCriteria = customerServiceOrderCustomExample.createCriteria();
			customerServiceOrderCustomCriteria.andDelFlagEqualTo("0");
			customerServiceOrderCustomCriteria.andServiceTypeEqualTo("D");
			customerServiceOrderCustomCriteria.andMchtIdEqualTo(mchtId);
			customerServiceOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateformat.parse(createDateBegin));
			customerServiceOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateformat.parse(createDateEnd));
			List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomerServiceOrderCustomByExample(customerServiceOrderCustomExample);
			for(CustomerServiceOrderCustom customerServiceOrderCustom:customerServiceOrderCustoms){
				String[] data = {
					"直赔单",
					customerServiceOrderCustom.getSubOrderCode(),
					df.format(customerServiceOrderCustom.getCreateDate()),
					customerServiceOrderCustom.getReceiverName(),
					customerServiceOrderCustom.getMemberId() == null? "" : customerServiceOrderCustom.getMemberId().toString(),
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					"",
					customerServiceOrderCustom.getMchtCode(),
					customerServiceOrderCustom.getShortName(),
					customerServiceOrderCustom.getMchtSettleOrderId() == null? "" : customerServiceOrderCustom.getMchtSettleOrderId().toString(),
					"",
					"",
					customerServiceOrderCustom.getAmount() == null? "" : "-"+customerServiceOrderCustom.getAmount().toString(),
					""
				};
				datas.add(data);
			}
			//付款
			PayToMchtLogCustomExample payToMchtLogCustomExample = new PayToMchtLogCustomExample();
			PayToMchtLogCustomExample.PayToMchtLogCustomCriteria payToMchtLogCustomCriteria = payToMchtLogCustomExample.createCriteria();
			payToMchtLogCustomCriteria.andDelFlagEqualTo("0");
			payToMchtLogCustomCriteria.andTypeEqualTo("1");
			payToMchtLogCustomCriteria.andMchtIdEqualTo(mchtId);
			payToMchtLogCustomCriteria.andPayDateGreaterThanOrEqualTo(dateformat.parse(createDateBegin));
			payToMchtLogCustomCriteria.andPayDateLessThanOrEqualTo(dateformat.parse(createDateEnd));
			List<PayToMchtLogCustom> payToMchtLogCustoms = payToMchtLogService.selectByExample(payToMchtLogCustomExample);
			for(PayToMchtLogCustom payToMchtLogCustom:payToMchtLogCustoms){
				String[] data = {
						"付款",
						"",
						df.format(payToMchtLogCustom.getCreateDate()),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						payToMchtLogCustom.getMchtCode(),
						payToMchtLogCustom.getShortName(),
						payToMchtLogCustom.getMchtSettleOrderId() == null? "" : payToMchtLogCustom.getMchtSettleOrderId().toString(),
						"",
						"",
						"",
						payToMchtLogCustom.getPayAmount() == null? "" : payToMchtLogCustom.getPayAmount().toString()
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
	 * 往期POP可结算情况列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettleSituation/beforeSituationList.shtml")
	public ModelAndView beforeSituationList(HttpServletRequest request) {
		String rtPage = "/mchtSettleSituation/beforeSituationList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(request.getParameter("year")) && !StringUtils.isEmpty(request.getParameter("month"))){
			resMap.put("year", request.getParameter("year"));
			resMap.put("month", request.getParameter("month"));
		}else{
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = df.format(now);
			String year = nowDate.substring(0,4);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String month = format.format(c.getTime());
			resMap.put("year", year);
			resMap.put("month", month);
		}
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			resMap.put("isCwOrgStatus", isCwOrgStatus);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 往期POP可结算情况列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/mchtSettleSituation/beforeSituationData.shtml")
	@ResponseBody
	public Map<String, Object> beforeSituationData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchYear = request.getParameter("year");
			String productTypeId=request.getParameter("productTypeId");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String nowMonth = format.format(c.getTime());
			
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			MchtSettleSituationCustomExample example = new MchtSettleSituationCustomExample();
			MchtSettleSituationCustomExample.MchtSettleSituationCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("2");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				criteria.andNameLikeTo(searchName);
			}
			if (!StringUtil.isEmpty(productTypeId)) {
				criteria.andProductTypeIdEqualTo(productTypeId);
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				criteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			criteria.andSettleDateEqualTo(searchYear+"-"+searchMonth);
			totalCount = mchtSettleSituationService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.selectByExample(example);
			resMap.put("Rows", mchtSettleSituationCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出往期POP可结算情况 excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtSettleSituation/exportBefore.shtml")
	public void exportBefore(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String nowDate = df.format(now);
			String nowYear = nowDate.substring(0,4);
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String searchYear = request.getParameter("year");
			String productTypeId=request.getParameter("productTypeId");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String nowMonth = format.format(c.getTime());
			
			String searchMonth = request.getParameter("month");
			if(StringUtils.isEmpty(searchYear)||StringUtils.isEmpty(searchMonth)){
				searchYear = nowYear;
				searchMonth = nowMonth;
			}
			MchtSettleSituationCustomExample example = new MchtSettleSituationCustomExample();
			MchtSettleSituationCustomExample.MchtSettleSituationCustomCriteria criteria = example.createCriteria();
			if(!StringUtil.isEmpty(request.getParameter("delFlag"))){
				criteria.andDelFlagEqualTo(request.getParameter("delFlag"));
			}else{
				criteria.andDelFlagEqualTo("0");
			}
			criteria.andMchtTypeEqualTo("2");
			if(!StringUtils.isEmpty(searchMchtCode)){
				criteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				criteria.andNameLikeTo(searchName);
			}
			if (!StringUtil.isEmpty(productTypeId)) {
				criteria.andProductTypeIdEqualTo(productTypeId);
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				criteria.andProductBrandNameEqualTo("%"+request.getParameter("productBrandName")+"%");
			}
			criteria.andSettleDateEqualTo(searchYear+"-"+searchMonth);
			List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.selectByExample(example);
			String[] titles = { "商家序号", "商家公司名称", "店铺名称","期初可结算","期初未出账","跨期结算","本期可结算","本期未出账","直赔单","实付款","抵缴保证金","折让","期末可结算","期末未出账"};
			ExcelBean excelBean = new ExcelBean("每月POP可结算情况_"+searchYear+searchMonth+".xls",
					"每月POP可结算情况_"+searchYear+searchMonth, titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtSettleSituationCustom mchtSettleSituationCustom:mchtSettleSituationCustoms){
				String[] data = {
					mchtSettleSituationCustom.getMchtCode(),
					mchtSettleSituationCustom.getCompanyName(),
					mchtSettleSituationCustom.getShopName(),
					mchtSettleSituationCustom.getBeginSettleAmout()==null?"":mchtSettleSituationCustom.getBeginSettleAmout().toString(),
					mchtSettleSituationCustom.getBeginNotOutAccount()==null?"":mchtSettleSituationCustom.getBeginNotOutAccount().toString(),
					mchtSettleSituationCustom.getAcrossMonthSettleAmount()==null?"":mchtSettleSituationCustom.getAcrossMonthSettleAmount().toString(),
					mchtSettleSituationCustom.getCurrentMonthSettleAmount()==null?"":mchtSettleSituationCustom.getCurrentMonthSettleAmount().toString(),
					mchtSettleSituationCustom.getNeedPayAmount().subtract(mchtSettleSituationCustom.getCurrentMonthSettleAmount()==null?new BigDecimal(0):mchtSettleSituationCustom.getCurrentMonthSettleAmount()).toString(),
					mchtSettleSituationCustom.getRefundAmount()==null?"":mchtSettleSituationCustom.getRefundAmount().toString(),
					mchtSettleSituationCustom.getPayAmount()==null?"":mchtSettleSituationCustom.getPayAmount().toString(),
					mchtSettleSituationCustom.getDeductionDepositTotal()==null?"":mchtSettleSituationCustom.getDeductionDepositTotal().toString(),
					mchtSettleSituationCustom.getDiscount()==null?"":mchtSettleSituationCustom.getDiscount().toString(),
					mchtSettleSituationCustom.getEndSettleAmount()==null?"":mchtSettleSituationCustom.getEndSettleAmount().toString(),
					mchtSettleSituationCustom.getEndNotOutAccount()==null?"":mchtSettleSituationCustom.getEndNotOutAccount().toString()
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
	 * 本月POP可结算情况列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtSettleSituation/currentSituationList.shtml")
	public ModelAndView currentSituationList(HttpServletRequest request) {
		String rtPage = "/mchtSettleSituation/currentSituationList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 resMap.put("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
			resMap.put("isCwOrgStatus", isCwOrgStatus);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 本月POP可结算情况列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/mchtSettleSituation/currentSituationData.shtml")
	@ResponseBody
	public Map<String, Object> currentSituationData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String productTypeId=request.getParameter("productTypeId");
			String productBrandName=request.getParameter("productBrandName");
			Date now = new Date();
			String endDate = df.format(now);
			String nowYear = endDate.substring(0,4);
			String nowMonth = endDate.substring(5, 7);
			String beginDate = nowYear+"-"+nowMonth+"-01";
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String preMonth = format.format(c.getTime());
			Map<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				paramMap.put("searchName", searchName);
			}
			if(!StringUtils.isEmpty(productTypeId)){
				paramMap.put("productTypeId", productTypeId);
			}
			if(!StringUtil.isEmpty(productBrandName)){
				paramMap.put("productBrandName", productBrandName);
			}
			paramMap.put("beginDate", beginDate);
			paramMap.put("endDate", endDate);
			paramMap.put("settleDate", nowYear+"-"+preMonth);
			List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.currentSituationData(paramMap);
			List<MchtSettleSituationCustom> result = new ArrayList<MchtSettleSituationCustom>();
			if (page.getPage() * page.getPagesize() > mchtSettleSituationCustoms.size()) {
				result = (mchtSettleSituationCustoms.subList(page.getPagesize() * (page.getPage() - 1), mchtSettleSituationCustoms.size()));
			} else {
				result = (mchtSettleSituationCustoms.subList(page.getPagesize() * (page.getPage() - 1), page.getPage() * page.getPagesize()));
			}
			resMap.put("Rows", result);
			resMap.put("Total", mchtSettleSituationCustoms.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出本月POP可结算情况 excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtSettleSituation/exportCurrent.shtml")
	public void exportCurrent(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchName = request.getParameter("name");
			String productTypeId=request.getParameter("productTypeId");
			String productBrandName=request.getParameter("productBrandName");
			Date now = new Date();
			String endDate = df.format(now);
			String nowYear = endDate.substring(0,4);
			String nowMonth = endDate.substring(5, 7);
			String beginDate = nowYear+"-"+nowMonth+"-01";
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("MM");
			String preMonth = format.format(c.getTime());
			Map<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtils.isEmpty(searchMchtCode)){
				paramMap.put("mchtCode", searchMchtCode);
			}
			if(!StringUtils.isEmpty(searchName)){
				paramMap.put("searchName", searchName);
			}
			if(!StringUtils.isEmpty(productTypeId)){
				paramMap.put("productTypeId", productTypeId);
			}
			if(!StringUtil.isEmpty(productBrandName)){
				paramMap.put("productBrandName", productBrandName);
			}
			paramMap.put("beginDate", beginDate);
			paramMap.put("endDate", endDate);
			paramMap.put("settleDate", nowYear+"-"+preMonth);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			List<MchtSettleSituationCustom> mchtSettleSituationCustoms = mchtSettleSituationService.currentSituationData(paramMap);
			String[] titles = { "商家序号", "商家公司名称", "店铺名称","期初可结算","期初未出账","跨期结算","本期可结算","本期未出账","直赔单","实付款","抵缴保证金","折让","期末可结算","期末未出账"};
			ExcelBean excelBean = new ExcelBean("本月POP可结算情况_"+nowYear+nowMonth+"01到"+sdf.format(new Date())+".xls",
					"本月POP可结算情况_"+nowYear+nowMonth+"01到"+sdf.format(new Date()), titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtSettleSituationCustom mchtSettleSituationCustom:mchtSettleSituationCustoms){
				String[] data = {
					mchtSettleSituationCustom.getMchtCode(),
					mchtSettleSituationCustom.getCompanyName(),
					mchtSettleSituationCustom.getShopName(),
					mchtSettleSituationCustom.getBeginSettleAmout()==null?"":mchtSettleSituationCustom.getBeginSettleAmout().toString(),
					mchtSettleSituationCustom.getBeginNotOutAccount()==null?"":mchtSettleSituationCustom.getBeginNotOutAccount().toString(),
					mchtSettleSituationCustom.getAcrossMonthSettleAmount()==null?"":mchtSettleSituationCustom.getAcrossMonthSettleAmount().toString(),
					mchtSettleSituationCustom.getCurrentMonthSettleAmount()==null?"":mchtSettleSituationCustom.getCurrentMonthSettleAmount().toString(),
					(mchtSettleSituationCustom.getNeedPayAmount()==null?new BigDecimal(0):mchtSettleSituationCustom.getNeedPayAmount()).subtract(mchtSettleSituationCustom.getCurrentMonthSettleAmount()==null?new BigDecimal(0):mchtSettleSituationCustom.getCurrentMonthSettleAmount()).toString(),
					mchtSettleSituationCustom.getRefundAmount()==null?"":mchtSettleSituationCustom.getRefundAmount().toString(),
					mchtSettleSituationCustom.getPayAmount()==null?"":mchtSettleSituationCustom.getPayAmount().toString(),
					mchtSettleSituationCustom.getDeductionDepositTotal()==null?"":mchtSettleSituationCustom.getDeductionDepositTotal().toString(),
					mchtSettleSituationCustom.getDiscount()==null?"":mchtSettleSituationCustom.getDiscount().toString(),
					mchtSettleSituationCustom.getEndSettleAmount()==null?"":mchtSettleSituationCustom.getEndSettleAmount().toString(),
					mchtSettleSituationCustom.getEndNotOutAccount()==null?"":mchtSettleSituationCustom.getEndNotOutAccount().toString()
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
	 * 
	 * @Title settlementAmountManager   
	 * @Description TODO(结算金额情况)   
	 * @author pengl
	 * @date 2018年3月15日 下午1:56:45
	 */
	@RequestMapping("/mchtSettleSituation/settlementAmountManager.shtml")
	public ModelAndView settlementAmountManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mchtSettleSituation/settlementAmountList");
		
		 ProductTypeExample productTypeExample = new ProductTypeExample();
		 ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		 productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		 List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		 m.addObject("productTypeList", productTypeList); //1级类目
		 
		 String isCwOrgStatus = this.getParamSession(request, "isCwOrgStatus");
		 if(!StringUtil.isEmpty(isCwOrgStatus) && "1".equals(isCwOrgStatus)) {
			  String isCwOrgProductTypeId = this.getParamSession(request, "isCwOrgProductTypeId");
			   if(!StringUtil.isEmpty(isCwOrgProductTypeId)) {
					productTypeCriteria.andIdEqualTo(Integer.parseInt(isCwOrgProductTypeId));
				}
			}
		 m.addObject("isCwOrgStatus", isCwOrgStatus);
		return m;
	}
	
	/**
	 * 
	 * @Title settlementAmountList   
	 * @Description TODO(结算金额情况)   
	 * @author pengl
	 * @date 2018年3月15日 下午3:10:18
	 */
	@ResponseBody
	@RequestMapping("/mchtSettleSituation/settlementAmountList.shtml")
	public Map<String, Object> settlementAmountList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderByClause", "A.mcht_id ASC");
			map.put("limitStart", page.getLimitStart());
			map.put("limitSize", page.getLimitSize());
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))) {
				map.put("mchtCode", request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))) {
				map.put("mchtName", request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("abnormityFlag"))) {
				map.put("abnormityFlag", request.getParameter("abnormityFlag"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))) {
				map.put("productTypeId", request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandId"))) {
				map.put("productBrandId", request.getParameter("productBrandId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				map.put("productBrandName", request.getParameter("productBrandName"));
			}
			dataList = mchtInfoService.selectSettlementAmount(map);
			totalCount = mchtInfoService.selectSettlementAmountCount(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	        
			
}
