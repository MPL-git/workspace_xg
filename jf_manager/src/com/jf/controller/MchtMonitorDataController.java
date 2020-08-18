package com.jf.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.jf.entity.*;
import com.jf.service.*;
import com.vdurmont.emoji.EmojiParser;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.vo.Page;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年1月23日 下午6:25:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class MchtMonitorDataController extends BaseController{
	
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Resource
	private ProductBrandService productBrandService;
	@Resource
	private PlatformContactService platformContactService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private ProductTypeService productTypeService;

	@Autowired
	private SysStaffInfoService sysStaffInfoService;

	@Autowired
	private SysStaffProductTypeService sysStaffProductTypeService;

	@Autowired
	private RefundOrderService refundOrderService;

	/**
	 * 每日售后数量统计初始化
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/customServiceOrderCount.shtml")
	public ModelAndView customServiceOrderCount(HttpServletRequest request) {
		String rtPage = "/mchtMonitorData/customServiceOrderCountList";
		Date now  = new Date();
		Map<String, Object> resMap = new HashMap<String, Object>();
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String productBrandId = request.getParameter("productBrandId");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			payDateBegin = DateUtil.getMonthFirstDay();
			payDateEnd = df.format(now);
		}
		resMap.put("payDateBegin", payDateBegin);
		resMap.put("payDateEnd", payDateEnd);
		resMap.put("mchtCode", mchtCode);
		if(!StringUtils.isEmpty(productBrandId) && !productBrandId.equals("0")){
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.parseInt(productBrandId));
			resMap.put("productBrandName", productBrand.getName()); 
			resMap.put("productBrandId", productBrandId); 
		}
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		//类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); 
		
		resMap.put("isContact", isContact);
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 每日售后数量统计 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/customServiceOrderCountList.shtml")
	@ResponseBody
	public Map<String, Object> customServiceOrderCountList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		Date now = new Date();
		String begin = " 00:00:00";
		String end = " 23:59:59";
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String brandId = request.getParameter("productBrandId");
		String shopName = request.getParameter("shopName");
		String platformContactId = request.getParameter("platformContactId");
		String productTypeId = request.getParameter("productTypeId");
		if(StringUtil.isEmpty(payDateBegin)){
			payDateBegin = DateUtil.getMonthFirstDay();
		}
		if(StringUtil.isEmpty(payDateEnd) ){
			payDateEnd = dateFormat.format(now);
		}
		paramMap.put("mchtCode", mchtCode);
		if(!StringUtils.isEmpty(brandId) && !brandId.equals("0")){
			paramMap.put("brandId", brandId);
		}
		if (!StringUtil.isEmpty(platformContactId)) {
			paramMap.put("platformContactId", platformContactId);
		}
		if (!StringUtil.isEmpty(productTypeId)) {
			paramMap.put("productTypeId", productTypeId);
		}
		paramMap.put("shopName", shopName);
		paramMap.put("payDateBegin", payDateBegin+begin);
		paramMap.put("payDateEnd", payDateEnd+end);
		List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomServiceOrderCountList(paramMap);
		Integer payOrderCount = 0;
		Integer deliveryCount = 0;
		Integer refundOrderCount = 0;
		Integer returnBillCount = 0;
		Integer swapOrderCount = 0;
		BigDecimal zero = new BigDecimal("0");
		List<String> containDays = new ArrayList<String>();
		for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
			payOrderCount = customerServiceOrderCustom.getPayOrderCount() == null ? 0 : customerServiceOrderCustom.getPayOrderCount();
			deliveryCount = customerServiceOrderCustom.getDeliveryCount() == null ? 0 : customerServiceOrderCustom.getDeliveryCount();
			refundOrderCount = customerServiceOrderCustom.getRefundOrderCount() == null ? 0 : customerServiceOrderCustom.getRefundOrderCount();
			returnBillCount = customerServiceOrderCustom.getReturnBillCount() == null ? 0 : customerServiceOrderCustom.getReturnBillCount();
			swapOrderCount = customerServiceOrderCustom.getSwapOrderCount() == null ? 0 : customerServiceOrderCustom.getSwapOrderCount();
			BigDecimal refundAmountRate = zero;
			BigDecimal refundbillRate = zero;
			BigDecimal turnoverRate = zero;
			if(payOrderCount > 0 && refundOrderCount > 0){
				refundAmountRate = new BigDecimal(refundOrderCount.toString()).divide(new BigDecimal(payOrderCount.toString()),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));;
			}
			if(payOrderCount > 0 && returnBillCount > 0){
				refundbillRate = new BigDecimal(returnBillCount).divide(new BigDecimal(payOrderCount),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));;
			}
			if(payOrderCount > 0 && swapOrderCount > 0){
				turnoverRate = new BigDecimal(swapOrderCount).divide(new BigDecimal(payOrderCount),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));;
			}
			customerServiceOrderCustom.setPayOrderCount(payOrderCount);
			customerServiceOrderCustom.setDeliveryCount(deliveryCount);
			customerServiceOrderCustom.setRefundOrderCount(refundOrderCount);
			customerServiceOrderCustom.setRefundbillRate(refundbillRate);
			customerServiceOrderCustom.setSwapOrderCount(swapOrderCount);
			customerServiceOrderCustom.setRefundAmountRate(refundAmountRate);
			customerServiceOrderCustom.setRefundbillRate(refundbillRate);
			customerServiceOrderCustom.setTurnoverRate(turnoverRate);
			containDays.add(customerServiceOrderCustom.getPayDate());
			map.put(customerServiceOrderCustom.getPayDate(), customerServiceOrderCustom);
		}
		List<String> betweenDays = this.getBetweenDays(payDateBegin, payDateEnd);
		for(int i= 0;i<betweenDays.size();i++){
			if(!containDays.contains(betweenDays.get(i))){
				CustomerServiceOrderCustom orderCustom = new CustomerServiceOrderCustom();
				orderCustom.setPayDate(betweenDays.get(i));
				orderCustom.setPayOrderCount(0);
				orderCustom.setDeliveryCount(0);
				orderCustom.setRefundOrderCount(0);
				orderCustom.setReturnBillCount(0);
				orderCustom.setSwapOrderCount(0);
				orderCustom.setRefundAmountRate(zero);
				orderCustom.setRefundbillRate(zero);
				orderCustom.setTurnoverRate(zero);
				customerServiceOrderCustoms.add(orderCustom);
				map.put(betweenDays.get(i), orderCustom);
			}
		}
		Collections.sort(customerServiceOrderCustoms, new Comparator<CustomerServiceOrderCustom>() {
            @Override
            public int compare(CustomerServiceOrderCustom c1, CustomerServiceOrderCustom c2) {
                //升序
                return c1.getPayDate().compareTo(c2.getPayDate());
            }
        });
		resMap.put("Rows", customerServiceOrderCustoms);
		return resMap;
	}
	
	
	/**
	 * 每日售后金额统计初始化
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/customServiceOrderAmountCount.shtml")
	public ModelAndView customServiceOrderAmountCount(HttpServletRequest request) {
		String rtPage = "/mchtMonitorData/customServiceOrderAmountCountList";
		Date now  = new Date();
		Map<String, Object> resMap = new HashMap<String, Object>();
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String productBrandId = request.getParameter("productBrandId");
		if(StringUtils.isEmpty(payDateBegin) || StringUtils.isEmpty(payDateEnd)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			payDateBegin = DateUtil.getMonthFirstDay();
			payDateEnd = df.format(now);
		}
		resMap.put("payDateBegin", payDateBegin);
		resMap.put("payDateEnd", payDateEnd);
		resMap.put("mchtCode", mchtCode);
		if(!StringUtils.isEmpty(productBrandId) && !productBrandId.equals("0")){
			ProductBrand productBrand = productBrandService.selectByPrimaryKey(Integer.parseInt(productBrandId));
			resMap.put("productBrandName", productBrand.getName()); 
			resMap.put("productBrandId", productBrandId); 
		}
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		
		//类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList); 
		
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 每日售后金额统计 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/customServiceOrderAmountCountList.shtml")
	@ResponseBody
	public Map<String, Object> customServiceOrderAmountCountList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		Date now = new Date();
		String begin = " 00:00:00";
		String end = " 23:59:59";
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String brandId = request.getParameter("productBrandId");
		String shopName = request.getParameter("shopName");
		String platformContactId = request.getParameter("platformContactId");
		String productTypeId = request.getParameter("productTypeId");
		if(StringUtil.isEmpty(payDateBegin)){
			payDateBegin = DateUtil.getMonthFirstDay();
		}
		if(StringUtil.isEmpty(payDateEnd) ){
			payDateEnd = dateFormat.format(now);
		}
		paramMap.put("mchtCode", mchtCode);
		if(!StringUtils.isEmpty(brandId) && !brandId.equals("0")){
			paramMap.put("brandId", brandId);
		}
		if (!StringUtil.isEmpty(platformContactId)) {
			paramMap.put("platformContactId", platformContactId);
		}
		if (!StringUtil.isEmpty(productTypeId)) {
			paramMap.put("productTypeId", productTypeId);
		}
		paramMap.put("shopName", shopName);
		paramMap.put("payDateBegin", payDateBegin+begin);
		paramMap.put("payDateEnd", payDateEnd+end);
		List<CustomerServiceOrderCustom> customerServiceOrderCustoms = customerServiceOrderService.selectCustomServiceOrderAmountCountList(paramMap);
		BigDecimal zero = new BigDecimal("0");
		List<String> containDays = new ArrayList<String>();
		for (CustomerServiceOrderCustom customerServiceOrderCustom : customerServiceOrderCustoms) {
			BigDecimal payOrderAmount = customerServiceOrderCustom.getPayOrderAmount() == null ? zero : customerServiceOrderCustom.getPayOrderAmount();
			BigDecimal deliveryAmount = customerServiceOrderCustom.getDeliveryAmount() == null ? zero : customerServiceOrderCustom.getDeliveryAmount();
			BigDecimal refundOrderAmount = customerServiceOrderCustom.getRefundOrderAmount() == null ? zero : customerServiceOrderCustom.getRefundOrderAmount();
			BigDecimal returnBillAmount = customerServiceOrderCustom.getReturnBillAmount() == null ? zero : customerServiceOrderCustom.getReturnBillAmount();
			BigDecimal swapOrderAmount = customerServiceOrderCustom.getSwapOrderAmount() == null ? zero : customerServiceOrderCustom.getSwapOrderAmount();
			BigDecimal refundAmountRate = zero;
			BigDecimal refundbillRate = zero;
			BigDecimal turnoverRate = zero;
			if(payOrderAmount.compareTo(zero) > 0 && refundOrderAmount.compareTo(zero) > 0){
				refundAmountRate = refundOrderAmount.divide(payOrderAmount,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
			}
			if(payOrderAmount.compareTo(zero) > 0 && returnBillAmount.compareTo(zero) > 0){
				refundbillRate = returnBillAmount.divide(payOrderAmount,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
			}
			if(payOrderAmount.compareTo(zero) > 0 && swapOrderAmount.compareTo(zero) > 0){
				turnoverRate = swapOrderAmount.divide(payOrderAmount,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
			}
			customerServiceOrderCustom.setPayOrderAmount(payOrderAmount);
			customerServiceOrderCustom.setDeliveryAmount(deliveryAmount);
			customerServiceOrderCustom.setRefundOrderAmount(refundOrderAmount);
			customerServiceOrderCustom.setRefundbillRate(refundbillRate);
			customerServiceOrderCustom.setSwapOrderAmount(swapOrderAmount);
			customerServiceOrderCustom.setRefundAmountRate(refundAmountRate);
			customerServiceOrderCustom.setRefundbillRate(refundbillRate);
			customerServiceOrderCustom.setTurnoverRate(turnoverRate);
			containDays.add(customerServiceOrderCustom.getPayDate());
			map.put(customerServiceOrderCustom.getPayDate(), customerServiceOrderCustom);
		}
		List<String> betweenDays = this.getBetweenDays(payDateBegin, payDateEnd);
		for(int i= 0;i<betweenDays.size();i++){
			if(!containDays.contains(betweenDays.get(i))){
				CustomerServiceOrderCustom orderCustom = new CustomerServiceOrderCustom();
				orderCustom.setPayDate(betweenDays.get(i));
				orderCustom.setPayOrderAmount(zero);
				orderCustom.setDeliveryAmount(zero);
				orderCustom.setRefundOrderAmount(zero);
				orderCustom.setReturnBillAmount(zero);
				orderCustom.setSwapOrderAmount(zero);
				orderCustom.setRefundAmountRate(zero);
				orderCustom.setRefundbillRate(zero);
				orderCustom.setTurnoverRate(zero);
				customerServiceOrderCustoms.add(orderCustom);
				map.put(betweenDays.get(i), orderCustom);
			}
		}
		Collections.sort(customerServiceOrderCustoms, new Comparator<CustomerServiceOrderCustom>() {
            @Override
            public int compare(CustomerServiceOrderCustom c1, CustomerServiceOrderCustom c2) {
                //升序
                return c1.getPayDate().compareTo(c2.getPayDate());
            }
        });
		resMap.put("Rows", customerServiceOrderCustoms);
		return resMap;
	}
	
	/**
	 * 各商家售后数量统计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/mchtCustomServiceQuantityList.shtml")
	public ModelAndView mchtCustomServiceQuantityList(HttpServletRequest request) {
		String rtPage = "/mchtMonitorData/mchtCustomServiceQuantityList";
		Date now  = new Date();
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = DateUtil.getMonthFirstDay();
		String payDateEnd = df.format(now);
		resMap.put("payDateBegin", payDateBegin);
		resMap.put("payDateEnd", payDateEnd);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		
		//类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList);
		
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 各商家售后数量统计 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/mchtCustomServiceQuantityData.shtml")
	@ResponseBody
	public Map<String, Object> mchtCustomServiceQuantityData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> paramMap = new HashMap<String, Object>(); 
		Date now = new Date();
		String begin = " 00:00:00";
		String end = " 23:59:59";
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String productBrandId = request.getParameter("productBrandId");
		String shopName = request.getParameter("shopName");
		String platformContactId = request.getParameter("platformContactId");
		String productTypeId = request.getParameter("productTypeId");
		if(StringUtil.isEmpty(payDateBegin)){
			payDateBegin = DateUtil.getMonthFirstDay();
		}
		if(StringUtil.isEmpty(payDateEnd) ){
			payDateEnd = dateFormat.format(now);
		}
		paramMap.put("payDateBegin", payDateBegin+begin);
		paramMap.put("payDateEnd", payDateEnd+end);
		if(!StringUtil.isEmpty(mchtCode) ){
			paramMap.put("mchtCode", mchtCode);
		}
		if(!StringUtil.isEmpty(productBrandId) ){
			paramMap.put("productBrandId", productBrandId);
		}
		if(!StringUtil.isEmpty(shopName) ){
			paramMap.put("shopName", shopName);
		}
		if(!StringUtil.isEmpty(platformContactId) ){
			paramMap.put("platformContactId", platformContactId);
		}
		if(!StringUtil.isEmpty(productTypeId) ){
			paramMap.put("productTypeId", productTypeId);
		}
	
		List<HashMap<String, Object>> list = customerServiceOrderService.mchtCustomServiceQuantityList(paramMap);
		resMap.put("Rows", list);
		return resMap;
	}
	
	/**
	 * 各商家售后金额统计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/mchtCustomServiceAmountList.shtml")
	public ModelAndView mchtCustomServiceAmountList(HttpServletRequest request) {
		String rtPage = "/mchtMonitorData/mchtCustomServiceAmountList";
		Date now  = new Date();
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = DateUtil.getMonthFirstDay();
		String payDateEnd = df.format(now);
		resMap.put("payDateBegin", payDateBegin);
		resMap.put("payDateEnd", payDateEnd);
		
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		Integer isContact = 0; //默认不是对接人
		
		PlatformContactExample platformContactExamples=new PlatformContactExample();//当用户是对接人时,获取所对接的商家列表
		platformContactExamples.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andStaffIdEqualTo(staffID);
		List<PlatformContact> platformContacts=platformContactService.selectByExample(platformContactExamples);
		if (platformContacts !=null && platformContacts.size()>0) {
			isContact=1;
			Integer myContactId=platformContacts.get(0).getId();//目前系统有限制，一个工号只能关联一个对接人
			PlatformContactExample platformContactExample=new PlatformContactExample();//当用户是对接人时,获取所协助的商家列表
			platformContactExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAssistantIdEqualTo(myContactId);
			List<PlatformContact> platformAssistantContact=platformContactService.selectByExample(platformContactExample);
			resMap.put("myContactId", myContactId);
			resMap.put("platformAssistantContacts", platformAssistantContact);
		}			
		
		PlatformContactExample platformContactExamplee=new PlatformContactExample();//当用户不是不对接人时,获取商家运营对接人列表
		platformContactExamplee.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andContactTypeEqualTo("2");
		List<PlatformContact> platformMchtContact=platformContactService.selectByExample(platformContactExamplee);				
		resMap.put("platformMchtContacts", platformMchtContact); 
		
		resMap.put("isContact", isContact);
		
		//类目
		ProductTypeExample productTypeExample = new ProductTypeExample();
		ProductTypeExample.Criteria productTypeCriteria = productTypeExample.createCriteria();
		productTypeCriteria.andParentIdEqualTo(1).andStatusEqualTo("1").andDelFlagEqualTo("0");
		productTypeExample.setOrderByClause(" seq_no");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		resMap.put("productTypeList", productTypeList);
		
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 各商家售后金额统计 
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/mchtCustomServiceAmountData.shtml")
	@ResponseBody
	public Map<String, Object> mchtCustomServiceAmountData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> paramMap = new HashMap<String, Object>(); 
		Date now = new Date();
		String begin = " 00:00:00";
		String end = " 23:59:59";
		String payDateBegin = request.getParameter("payDateBegin");
		String payDateEnd = request.getParameter("payDateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String productBrandId = request.getParameter("productBrandId");
		String shopName = request.getParameter("shopName");
		String platformContactId = request.getParameter("platformContactId");
		String productTypeId = request.getParameter("productTypeId");
		if(StringUtil.isEmpty(payDateBegin)){
			payDateBegin = DateUtil.getMonthFirstDay();
		}
		if(StringUtil.isEmpty(payDateEnd) ){
			payDateEnd = dateFormat.format(now);
		}
		paramMap.put("payDateBegin", payDateBegin+begin);
		paramMap.put("payDateEnd", payDateEnd+end);
		if(!StringUtil.isEmpty(mchtCode) ){
			paramMap.put("mchtCode", mchtCode);
		}
		if(!StringUtil.isEmpty(productBrandId) ){
			paramMap.put("productBrandId", productBrandId);
		}
		if(!StringUtil.isEmpty(shopName) ){
			paramMap.put("shopName", shopName);
		}
		if(!StringUtil.isEmpty(platformContactId) ){
			paramMap.put("platformContactId", platformContactId);
		}
		if(!StringUtil.isEmpty(productTypeId) ){
			paramMap.put("productTypeId", productTypeId);
		}
		
		List<HashMap<String, Object>> list = customerServiceOrderService.mchtCustomServiceAmountList(paramMap);
		resMap.put("Rows", list);
		return resMap;
	}
	
	/**
	 * 订单相关统计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/orderStatisticsList.shtml")
	public ModelAndView orderStatisticsList(HttpServletRequest request) {
		String rtPage = "/mchtMonitorData/orderStatisticsList";
		Date now  = new Date();
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateBegin = DateUtil.getMonthFirstDay();
		String dateEnd = df.format(now);
		resMap.put("dateBegin", dateBegin);
		resMap.put("dateEnd", dateEnd);
		ProductTypeExample e = new ProductTypeExample();
		ProductTypeExample.Criteria c = e.createCriteria();
		c.andDelFlagEqualTo("0");
		c.andStatusEqualTo("1");
		c.andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(e);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	/**
	 * 订单相关统计
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtMonitorData/orderStatisticsData.shtml")
	@ResponseBody
	public Map<String, Object> orderStatisticsData(HttpServletRequest request,Page page) {
		Integer totalCount = 0;
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> paramMap = new HashMap<String, Object>(); 
		Date now = new Date();
		String begin = " 00:00:00";
		String end = " 23:59:59";
		String dateBegin = request.getParameter("dateBegin");
		String dateEnd = request.getParameter("dateEnd");
		String mchtCode = request.getParameter("mchtCode");
		String productTypeId = request.getParameter("productTypeId");
		String companyName = request.getParameter("companyName");
		String shopName = request.getParameter("shopName");
		if(StringUtil.isEmpty(dateBegin)){
			dateBegin = DateUtil.getMonthFirstDay();
		}
		if(StringUtil.isEmpty(dateEnd) ){
			dateEnd = dateFormat.format(now);
		}
		paramMap.put("dateBegin", dateBegin+begin);
		paramMap.put("dateEnd", dateEnd+end);
		if(!StringUtil.isEmpty(mchtCode) ){
			paramMap.put("mchtCode", mchtCode);
		}
		if(!StringUtil.isEmpty(productTypeId) ){
			paramMap.put("productTypeId", productTypeId);
		}
		if(!StringUtil.isEmpty(companyName) ){
			paramMap.put("companyName", companyName);
		}
		if(!StringUtil.isEmpty(shopName) ){
			paramMap.put("shopName", shopName);
		}
		paramMap.put("limitStar", (page.getPage()-1)*page.getLimitSize());
		paramMap.put("pageSize", page.getLimitSize());
		totalCount = subOrderService.countOrderStatisticsList(paramMap);
		List<HashMap<String, Object>> list = subOrderService.getOrderStatisticsList(paramMap);
		resMap.put("Rows", list);
		resMap.put("Total", totalCount);
		return resMap;
	}



	@RequestMapping("/mchtMonitorData/refundOrderStatisticsManager.shtml")
	public ModelAndView refundOrderStatisticsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mchtMonitorData/refundOrderStatisticsList");
		String staffID = this.getSessionStaffBean(request).getStaffID();
		Date date  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		m.addObject("createDateBegin", sdf.format(date));
		m.addObject("createDateEnd", sdf.format(date));

		String isManagement = this.getSessionStaffBean(request).getIsManagement();//管理层
		m.addObject("isManagement", isManagement);
		m.addObject("staffID", staffID);
		SysStaffInfoCustomExample sysStaffInfoCustomExample = new SysStaffInfoCustomExample();
		SysStaffInfoCustomExample.SysStaffInfoCustomCriteria sysStaffInfoCustomCriteria = sysStaffInfoCustomExample.createCriteria();
		sysStaffInfoCustomCriteria.andGetSubordinateStaffIds(staffID);
		List<SysStaffInfoCustom> sysStaffInfoCustomList = sysStaffInfoService.selectByCustomExample(sysStaffInfoCustomExample);
		m.addObject("sysStaffInfoCustomList", sysStaffInfoCustomList);

		SysStaffProductTypeCustomExample sysStaffProductTypeCustomExample = new SysStaffProductTypeCustomExample();
		SysStaffProductTypeCustomExample.SysStaffProductTypeCustomCriteria sysstaffProductTypeexCriteria = sysStaffProductTypeCustomExample.createCriteria();
		sysstaffProductTypeexCriteria.andDelFlagEqualTo("0").andStaffIdEqualTo(Integer.valueOf(staffID));
		List<SysStaffProductTypeCustom> sysStaffProductTypeList = sysStaffProductTypeService.selectByCustomExample(sysStaffProductTypeCustomExample);
		m.addObject("sysStaffProductTypeList", sysStaffProductTypeList);

		return m;
	}


	@ResponseBody
	@RequestMapping("/mchtMonitorData/refundOrderStatisticsList.shtml")
	public Map<String, Object> refundOrderStatisticsList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String createDateBegin = null;
			String createDateEnd = null;
			String productBrandId = request.getParameter("productBrandId");
			String mchtCode = request.getParameter("mchtCode");
			String shopName = request.getParameter("shopName");
			String platContactStaffId = request.getParameter("platContactStaffId");
			String productTypeId = request.getParameter("productTypeId");
			if(!StringUtils.isEmpty(request.getParameter("createDateBegin"))) {
				createDateBegin = request.getParameter("createDateBegin")+" 00:00:00";
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateEnd"))) {
				createDateEnd = request.getParameter("createDateEnd")+" 23:59:59";
			}
			paramMap.put("createDateBegin", createDateBegin);
			paramMap.put("createDateEnd", createDateEnd);
			paramMap.put("productBrandId", productBrandId);
			paramMap.put("mchtCode", mchtCode);
			paramMap.put("shopName", shopName);
			paramMap.put("platContactStaffId", platContactStaffId);
			paramMap.put("productTypeId", productTypeId);
			dataList = refundOrderService.refundOrderStatistics(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}



}
