package com.jf.controller;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.DepositOrder;
import com.jf.entity.DepositOrderCustom;
import com.jf.entity.DepositOrderCustomExample;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositCustom;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositDtlCustom;
import com.jf.entity.MchtDepositDtlCustomExample;
import com.jf.entity.MchtDepositDtlExample;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.PlatformCapitalAccount;
import com.jf.entity.PlatformCapitalAccountExample;
import com.jf.entity.ProductBrand;
import com.jf.entity.ProductBrandExample;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SysStatus;
import com.jf.entity.ViolateOrder;
import com.jf.service.DepositOrderService;
import com.jf.service.MchtDepositDtlService;
import com.jf.service.MchtDepositService;
import com.jf.service.PlatformCapitalAccountService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductTypeService;
import com.jf.service.ViolateOrderService;
import com.jf.vo.Page;

@Controller
public class MchtDepositController extends BaseController {
	
	@Resource
	private PlatformCapitalAccountService platformCapitalAccountService;
	
	@Resource
	private DepositOrderService depositOrderService;
	
	@Resource
	private MchtDepositService mchtDepositService;
	
	@Resource
	private MchtDepositDtlService mchtDepositDtlService;
	
	@Resource
	private ViolateOrderService violateOrderService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductBrandService productBrandService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 保证金每天汇总列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositDtlCountEachDayList.shtml")
	public ModelAndView mchtDepositDtlCountEachDayList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/mchtDepositDtlCountEachDayList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDateBegin = "";
		String createDateEnd = "";
		Date now = new Date();
		if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
			createDateBegin = request.getParameter("create_date_begin");
		}else{
			createDateBegin = dateFormat.format(now).substring(0,7)+"-01";
		}
		if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
			createDateEnd = request.getParameter("create_date_end");
		}else{
			createDateEnd = dateFormat.format(now);
		}
		resMap.put("createDateBegin", createDateBegin);
		resMap.put("createDateEnd", createDateEnd);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保证金每天汇总列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositDtlCountEachDayData.shtml")
	@ResponseBody
	public Map<String, Object> mchtDepositDtlCountEachDayData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createDateBegin = "";
			String createDateEnd = "";
			Date now = new Date();
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				createDateBegin = request.getParameter("create_date_begin")+" 00:00:00";
			}else{
				createDateBegin = dateFormat.format(now).substring(0,7)+"-01 00:00:00";
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				createDateEnd = request.getParameter("create_date_end")+" 23:59:59";
			}else{
				createDateEnd = dateFormat.format(now);
			}
			paramMap.put("createDateBegin", createDateBegin);
			paramMap.put("createDateEnd", createDateEnd);
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectMchtDepositDtlCountEachDayList(paramMap);
			HashMap<String, MchtDepositDtlCustom> map = new HashMap<String, MchtDepositDtlCustom>();
			List<String> containDays = new ArrayList<String>();
			for(MchtDepositDtlCustom mchtDepositDtlCustom:mchtDepositDtlCustoms){
				containDays.add(mchtDepositDtlCustom.getEachDay());
				map.put(mchtDepositDtlCustom.getEachDay(), mchtDepositDtlCustom);
			}
			createDateBegin = createDateBegin.substring(0, 10);
			createDateEnd = createDateEnd.substring(0, 10);
			List<String> betweenDays = this.getBetweenDays(createDateBegin, createDateEnd);
			for(int i= 0;i<betweenDays.size();i++){
				if(!containDays.contains(betweenDays.get(i))){
					MchtDepositDtlCustom mchtDepositDtlCustom = new MchtDepositDtlCustom();
					mchtDepositDtlCustom.setEachDay(betweenDays.get(i));
					mchtDepositDtlCustom.setCashPayment(new BigDecimal(0));
					mchtDepositDtlCustom.setPaymentOfGoods(new BigDecimal(0));
					mchtDepositDtlCustom.setRefundDeposit(new BigDecimal(0));
					mchtDepositDtlCustom.setViolateMoney(new BigDecimal(0));
					mchtDepositDtlCustom.setAppealMoney(new BigDecimal(0));
					if(i == 0){
						mchtDepositDtlCustom.setFirst(new BigDecimal(0));
						mchtDepositDtlCustom.setLast(new BigDecimal(0));
					}else{
						MchtDepositDtlCustom prevMchtDepositDtlCustom = map.get(betweenDays.get(i-1));
						mchtDepositDtlCustom.setFirst(prevMchtDepositDtlCustom.getLast());
						mchtDepositDtlCustom.setLast(prevMchtDepositDtlCustom.getLast());
					}
					mchtDepositDtlCustoms.add(mchtDepositDtlCustom);
					map.put(betweenDays.get(i), mchtDepositDtlCustom);
				}
			}
			Collections.sort(mchtDepositDtlCustoms, new Comparator<MchtDepositDtlCustom>() {
	            @Override
	            public int compare(MchtDepositDtlCustom m1, MchtDepositDtlCustom m2) {
	                //升序
	                return m1.getEachDay().compareTo(m2.getEachDay());
	            }
	        });
			resMap.put("Rows", mchtDepositDtlCustoms);
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
	 * 商家保证金余额表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositDtlCountList.shtml")
	public ModelAndView mchtDepositDtlCountList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/mchtDepositDtlCountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Date now = new Date();
		String nowDate = df.format(now);
		String createDateBegin = nowDate+"-01";
		Date yesterDay = DateUtil.getYesterDayDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createDateEnd = sdf.format(yesterDay);
		resMap.put("createDateBegin", createDateBegin);
		resMap.put("createDateEnd", createDateEnd);
		
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
	 * 商家保证金余额表列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositDtlCountData.shtml")
	@ResponseBody
	public Map<String, Object> mchtDepositDtlCountData(HttpServletRequest request,@RequestParam HashMap<String, Object> paramMap) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String createDateBegin = "";
			String createDateEnd = "";
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				createDateBegin = request.getParameter("create_date_begin")+" 00:00:00";
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				createDateEnd = request.getParameter("create_date_end")+" 23:59:59";
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				paramMap.put("mchtCode", request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				paramMap.put("mchtName", request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){
				paramMap.put("productTypeId", request.getParameter("productTypeId"));
			}
			if (!StringUtil.isEmpty("productBrandId")) {
				paramMap.put("productBrandId", request.getParameter("productBrandId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				paramMap.put("productBrandName", request.getParameter("productBrandName"));
			}
			paramMap.put("createDateBegin", createDateBegin);
			paramMap.put("createDateEnd", createDateEnd);
			totalCount = mchtDepositDtlService.mchtDepositDtlCountCount(paramMap);
			paramMap = this.setPageParametersLiger(request,paramMap);
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectMchtDepositDtlCountList(paramMap);
			resMap.put("Rows", mchtDepositDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出商家保证金余额表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtDeposit/exportMchtDepositDtlCount.shtml")
	public void exportMchtDepositDtlCount(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createDateBegin = "";
			String createDateEnd = "";
			Date now = new Date();
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				createDateBegin = request.getParameter("create_date_begin")+" 00:00:00";
			}else{
				createDateBegin = dateFormat.format(DateUtil.getMonthsAgo(now, -1));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				createDateEnd = request.getParameter("create_date_end")+" 23:59:59";
			}else{
				createDateEnd = dateFormat.format(now);
			}
			if (!StringUtil.isEmpty("productBrandId")) {
				paramMap.put("productBrandId", request.getParameter("productBrandId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productTypeId"))){
				paramMap.put("productTypeId", request.getParameter("productTypeId"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtCode"))){
				paramMap.put("mchtCode", request.getParameter("mchtCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("mchtName"))){
				paramMap.put("mchtName", request.getParameter("mchtName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productBrandName"))){
				paramMap.put("productBrandName", request.getParameter("productBrandName"));
			}
			paramMap.put("createDateBegin", createDateBegin);
			paramMap.put("createDateEnd", createDateEnd);
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectMchtDepositDtlCountList(paramMap);
			String[] titles = { "商家序号", "公司名称", "期初余额（元）","用现金缴纳（元）","用货款抵缴（元）","违规扣款（元）","退保证金（元）","申诉成功（元）","期末余额（元）","应缴金额（元）","还需补缴（元）"};
			ExcelBean excelBean = new ExcelBean(createDateBegin.substring(0, 10)+"至"+createDateEnd.substring(0, 10)+"导出商家保证金余额表.xls",
					"导出商家保证金余额表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtDepositDtlCustom mchtDepositDtlCustom:mchtDepositDtlCustoms){
				String[] data = {
					mchtDepositDtlCustom.getMchtCode()+"-"+mchtDepositDtlCustom.getMchtId().toString(),
					mchtDepositDtlCustom.getCompanyName(),
					mchtDepositDtlCustom.getFirst() == null?"":String.valueOf(mchtDepositDtlCustom.getFirst()),
					mchtDepositDtlCustom.getCashPayment() == null?"":String.valueOf(mchtDepositDtlCustom.getCashPayment()),
					mchtDepositDtlCustom.getPaymentOfGoods() == null?"":String.valueOf(mchtDepositDtlCustom.getPaymentOfGoods()),
					mchtDepositDtlCustom.getViolateMoney() == null?"":String.valueOf(mchtDepositDtlCustom.getViolateMoney()),
					mchtDepositDtlCustom.getRefundDeposit() == null?"":String.valueOf(mchtDepositDtlCustom.getRefundDeposit()),
					mchtDepositDtlCustom.getAppealMoney() == null?"":String.valueOf(mchtDepositDtlCustom.getAppealMoney()),
					mchtDepositDtlCustom.getLast() == null?"":String.valueOf(mchtDepositDtlCustom.getLast()),
					mchtDepositDtlCustom.getTotalAmt() == null?"":String.valueOf(mchtDepositDtlCustom.getTotalAmt()),
					mchtDepositDtlCustom.getNeedPay() == null?"":String.valueOf(mchtDepositDtlCustom.getNeedPay())
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
	 * 余额变化明细表列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositDtlList.shtml")
	public ModelAndView mchtDepositDtlList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/mchtDepositDtlList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("txnTypeList", DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE"));
		resMap.put("mchtCode", request.getParameter("mchtCode"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 余额变化明细表列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositDtlData.shtml")
	@ResponseBody
	public Map<String, Object> mchtDepositDtlData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MchtDepositDtlCustomExample mchtDepositDtlCustomExample = new MchtDepositDtlCustomExample();
			MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria mchtDepositDtlCustomCriteria = mchtDepositDtlCustomExample.createCriteria();
			mchtDepositDtlCustomCriteria.andDelFlagEqualTo("0");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchTxnType = request.getParameter("txnType");
			if(!StringUtil.isEmpty(searchMchtCode)){
				mchtDepositDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				mchtDepositDtlCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchTxnType)){
				mchtDepositDtlCustomCriteria.andTxnTypeEqualTo(searchTxnType);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				mchtDepositDtlCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				mchtDepositDtlCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			totalCount = mchtDepositDtlService.countByExample(mchtDepositDtlCustomExample);
			mchtDepositDtlCustomExample.setLimitStart(page.getLimitStart());
			mchtDepositDtlCustomExample.setLimitSize(page.getLimitSize());
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectByExample(mchtDepositDtlCustomExample);
			resMap.put("Rows", mchtDepositDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 导出余额变化明细表excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/mchtDeposit/exportMchtDepositDtl.shtml")
	public void exportMchtDepositDtl(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			MchtDepositDtlCustomExample mchtDepositDtlCustomExample = new MchtDepositDtlCustomExample();
			MchtDepositDtlCustomExample.MchtDepositDtlCustomCriteria mchtDepositDtlCustomCriteria = mchtDepositDtlCustomExample.createCriteria();
			mchtDepositDtlCustomCriteria.andDelFlagEqualTo("0");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchTxnType = request.getParameter("txnType");
			if(!StringUtil.isEmpty(searchMchtCode)){
				mchtDepositDtlCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				mchtDepositDtlCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchTxnType)){
				mchtDepositDtlCustomCriteria.andTxnTypeEqualTo(searchTxnType);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				mchtDepositDtlCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				mchtDepositDtlCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			List<MchtDepositDtlCustom> mchtDepositDtlCustoms = mchtDepositDtlService.selectByExample(mchtDepositDtlCustomExample);
			String[] titles = {"时间","商家序号","公司名称","类型","子类","摘要","应缴额变化值","保证金余额变化值","变化后保证金余额"};
			ExcelBean excelBean = new ExcelBean("导出余额变化明细表.xls",
					"导出余额变化明细表", titles);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtDepositDtlCustom mchtDepositDtlCustom:mchtDepositDtlCustoms){
				String[] data = {
					df.format(mchtDepositDtlCustom.getCreateDate()),
					mchtDepositDtlCustom.getMchtCode()+"-"+mchtDepositDtlCustom.getMchtDepositid(),
					mchtDepositDtlCustom.getCompanyName(),
					mchtDepositDtlCustom.getTxnTypeDesc(),
					mchtDepositDtlCustom.getTypeSubDesc(),
					mchtDepositDtlCustom.getRemarks(),
					mchtDepositDtlCustom.getTxnType().equals("A")? mchtDepositDtlCustom.getTxnAmt().toString() : "0",
					mchtDepositDtlCustom.getTxnType().equals("A")? "0" : mchtDepositDtlCustom.getTxnAmt().toString(),
					mchtDepositDtlCustom.getPayAmt().toString()
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
	 * 现金缴纳待确认列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/depositOrderList.shtml")
	public ModelAndView depositOrderList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/depositOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("paymentTypeList", DataDicUtil.getStatusList("BU_DEPOSIT_ORDER", "PAYMENT_TYPE"));
		List<String> paymentNames = platformCapitalAccountService.getPaymentNames();
		resMap.put("paymentNames", paymentNames);
		resMap.put("statusList", DataDicUtil.getStatusList("BU_DEPOSIT_ORDER", "STATUS"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 现金缴纳待确认列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/depositOrderData.shtml")
	@ResponseBody
	public Map<String, Object> depositOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			DepositOrderCustomExample depositOrderCustomExample = new DepositOrderCustomExample();
			DepositOrderCustomExample.DepositOrderCustomCriteria depositOrderCustomCriteria = depositOrderCustomExample.createCriteria();
			depositOrderCustomCriteria.andDelFlagEqualTo("0");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchPaymentType = request.getParameter("paymentType");
			String searchPaymentName = request.getParameter("paymentName");
			String status = request.getParameter("status");
			if(!StringUtil.isEmpty(searchMchtCode)){
				depositOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				depositOrderCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchPaymentType)){
				depositOrderCustomCriteria.andPaymentTypeEqualTo(searchPaymentType);
			}
			if(!StringUtil.isEmpty(searchPaymentName)){
				if(searchPaymentName.equals("1") || searchPaymentName.equals("2")){
					depositOrderCustomCriteria.andPaymentTypeEqualTo(searchPaymentName);
				}else{
					depositOrderCustomCriteria.andBankTypeEqualTo(searchPaymentName);
				}
			}
			if(!StringUtil.isEmpty(status)){
				depositOrderCustomCriteria.andStatusEqualTo(status);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				depositOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				depositOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			totalCount = depositOrderService.countDepositOrderCustomByExample(depositOrderCustomExample);
			depositOrderCustomExample.setLimitStart(page.getLimitStart());
			depositOrderCustomExample.setLimitSize(page.getLimitSize());
			List<DepositOrderCustom> depositOrderCustoms = depositOrderService.selectDepositOrderCustomByExample(depositOrderCustomExample);
			for(DepositOrderCustom depositOrderCustom:depositOrderCustoms){
				if(depositOrderCustom.getAccountId()!=null){
					depositOrderCustom.setPlatformCapitalAccount(platformCapitalAccountService.selectByPrimaryKey(depositOrderCustom.getAccountId()));
				}
			}
			resMap.put("Rows", depositOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/* 审核*/
	@RequestMapping(value = "/mchtDeposit/checkComfirm.shtml")
	public ModelAndView checkComfirm(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap) {
		String rtPage = "/mchtDeposit/checkComfirm";// 重定向
		Map<String, Object> resMap = new HashMap<String, Object>();
		int id=Integer.valueOf(request.getParameter("id"));
		DepositOrder depositOrder = depositOrderService.selectByPrimaryKey(id);
		if(depositOrder.getAccountId()!=null){
			PlatformCapitalAccount platformCapitalAccount = platformCapitalAccountService.selectByPrimaryKey(depositOrder.getAccountId());
			resMap.put("platformCapitalAccount", platformCapitalAccount);
		}
		resMap.put("paymentTypeDesc", DataDicUtil.getStatusDesc("BU_DEPOSIT_ORDER", "PAYMENT_TYPE", depositOrder.getPaymentType()));
		resMap.put("depositOrder", depositOrder);
		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 保存保证金明细
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/checkResult.shtml")
	@ResponseBody
	public Map<String, Object> checkResult(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			String receiveDate = request.getParameter("receiveDate");
			DepositOrder depositOrder = depositOrderService.selectByPrimaryKey(id);
			MchtDepositExample example = new MchtDepositExample();
			MchtDepositExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			criteria.andMchtIdEqualTo(depositOrder.getMchtId());
			List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(example);
			MchtDeposit mchtDeposit = mchtDeposits.get(0);
			depositOrder.setStatus(status);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			depositOrder.setReceiveDate(sdf.parse(receiveDate));
			if(status.equals("1")){//成功
				MchtDepositDtl mchtDepositDtl = new MchtDepositDtl();
				mchtDepositDtl.setDelFlag("0");
				mchtDepositDtl.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				mchtDepositDtl.setCreateDate(new Date());
				mchtDepositDtl.setDepositId(mchtDeposit.getId());
				mchtDepositDtl.setTxnType("B");
				if(depositOrder.getPaymentType().equals("1")){//支付宝
					mchtDepositDtl.setTypeSub("B1");
				}else if(depositOrder.getPaymentType().equals("2")){//微信
					mchtDepositDtl.setTypeSub("B2");
				}else if(depositOrder.getPaymentType().equals("3")){//银行
					mchtDepositDtl.setTypeSub("B3");
				}
				mchtDepositDtl.setTxnAmt(depositOrder.getAmount());
				MchtDepositDtlExample mchtDepositDtlExample = new MchtDepositDtlExample();
				mchtDepositDtlExample.setOrderByClause("id desc");
				MchtDepositDtlExample.Criteria mchtDepositDtlCriteria = mchtDepositDtlExample.createCriteria();
				mchtDepositDtlCriteria.andDelFlagEqualTo("0");
				mchtDepositDtlCriteria.andDepositIdEqualTo(mchtDeposit.getId());
				List<MchtDepositDtl> mchtDepositDtls = mchtDepositDtlService.selectByExample(mchtDepositDtlExample);
				if(mchtDepositDtls!=null && mchtDepositDtls.size()>0){
					mchtDepositDtl.setPayAmt(mchtDepositDtls.get(0).getPayAmt().add(depositOrder.getAmount()));
				}else{
					mchtDepositDtl.setPayAmt(depositOrder.getAmount());
				}
				mchtDepositDtl.setBizType("1");
				mchtDepositDtl.setBizId(depositOrder.getId());
				mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().add(depositOrder.getAmount()));
				mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().subtract(depositOrder.getAmount()));
				mchtDepositDtlService.saveMchtDepositDtlAndUpdateDepositOrder(mchtDepositDtl,depositOrder,mchtDeposit);
			}else{
				depositOrderService.updateByPrimaryKey(depositOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 现金缴纳记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/comfirmDepositOrderList.shtml")
	public ModelAndView comfirmDepositOrderList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/comfirmDepositOrderList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("paymentTypeList", DataDicUtil.getStatusList("BU_DEPOSIT_ORDER", "PAYMENT_TYPE"));
		List<String> paymentNames = platformCapitalAccountService.getPaymentNames();
		resMap.put("paymentNames", paymentNames);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 现金缴纳记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/comfirmDepositOrderData.shtml")
	@ResponseBody
	public Map<String, Object> comfirmDepositOrderData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			DepositOrderCustomExample depositOrderCustomExample = new DepositOrderCustomExample();
			DepositOrderCustomExample.DepositOrderCustomCriteria depositOrderCustomCriteria = depositOrderCustomExample.createCriteria();
			depositOrderCustomCriteria.andDelFlagEqualTo("0");
			depositOrderCustomCriteria.andStatusEqualTo("1");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchPaymentType = request.getParameter("paymentType");
			String searchPaymentName = request.getParameter("paymentName");
			if(!StringUtil.isEmpty(searchMchtCode)){
				depositOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				depositOrderCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchPaymentType)){
				depositOrderCustomCriteria.andPaymentTypeEqualTo(searchPaymentType);
			}
			if(!StringUtil.isEmpty(searchPaymentName)){
				if(searchPaymentName.equals("1") || searchPaymentName.equals("2")){
					depositOrderCustomCriteria.andPaymentTypeEqualTo(searchPaymentName);
				}else{
					depositOrderCustomCriteria.andBankTypeEqualTo(searchPaymentName);
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				depositOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				depositOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			totalCount = depositOrderService.countDepositOrderCustomByExample(depositOrderCustomExample);
			depositOrderCustomExample.setLimitStart(page.getLimitStart());
			depositOrderCustomExample.setLimitSize(page.getLimitSize());
			List<DepositOrderCustom> depositOrderCustoms = depositOrderService.selectDepositOrderCustomByExample(depositOrderCustomExample);
			for(DepositOrderCustom depositOrderCustom:depositOrderCustoms){
				if(depositOrderCustom.getAccountId()!=null){
					depositOrderCustom.setPlatformCapitalAccount(platformCapitalAccountService.selectByPrimaryKey(depositOrderCustom.getAccountId()));
				}
			}
			resMap.put("Rows", depositOrderCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 明细类型管理
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/typeList.shtml")
	public ModelAndView typeList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/typeList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<SysStatus> txnTypeList = DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE");
		List<SysStatus> typeSubList = DataDicUtil.getStatusList("BU_MCHT_DEPOSIT_DTL", "TYPE_SUB");
		List<SysStatus> AList = new ArrayList<SysStatus>();
		List<SysStatus> BList = new ArrayList<SysStatus>();
		List<SysStatus> CList = new ArrayList<SysStatus>();
		List<SysStatus> DList = new ArrayList<SysStatus>();
		List<SysStatus> EList = new ArrayList<SysStatus>();
		List<SysStatus> FList = new ArrayList<SysStatus>();
		for(SysStatus typeSub:typeSubList){
			if(typeSub.getStatusValue().substring(0, 1).equals("A")){
				AList.add(typeSub);
			}else if(typeSub.getStatusValue().substring(0, 1).equals("B")){
				BList.add(typeSub);
			}else if(typeSub.getStatusValue().substring(0, 1).equals("C")){
				CList.add(typeSub);
			}else if(typeSub.getStatusValue().substring(0, 1).equals("D")){
				DList.add(typeSub);
			}else if(typeSub.getStatusValue().substring(0, 1).equals("E")){
				EList.add(typeSub);
			}else if(typeSub.getStatusValue().substring(0, 1).equals("F")){
				FList.add(typeSub);
			}
			String txnTypeDesc = DataDicUtil.getStatusDesc("BU_MCHT_DEPOSIT_DTL", "TXN_TYPE", typeSub.getStatusValue().substring(0, 1));
			typeSub.setStatusValue(txnTypeDesc);
		}
		resMap.put("txnTypeList", txnTypeList);
		resMap.put("AList", AList);
		resMap.put("BList", BList);
		resMap.put("CList", CList);
		resMap.put("DList", DList);
		resMap.put("EList", EList);
		resMap.put("FList", FList);
		return new ModelAndView(rtPage,resMap);
	}
	
	
	/**
	 * 保证金账号管理
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/accountList.shtml")
	public ModelAndView accountList(HttpServletRequest request) {
		String rtPage = "/mchtDeposit/accountList";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保证金账号管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/mchtDeposit/accountData.shtml")
	@ResponseBody
	public Map<String, Object> accountData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			PlatformCapitalAccountExample example = new PlatformCapitalAccountExample();
			PlatformCapitalAccountExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			totalCount = platformCapitalAccountService.countByExample(example);
			example.setLimitStart(page.getLimitStart());
			example.setLimitSize(page.getLimitSize());
			List<PlatformCapitalAccount> platformCapitalAccounts = platformCapitalAccountService.selectByExample(example);
			resMap.put("Rows", platformCapitalAccounts);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @Title mchtDepositManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月5日 上午11:51:05
	 */
	@RequestMapping(value = "/mchtDeposit/mchtDepositManager.shtml")
	public ModelAndView mchtDepositManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/mchtDeposit/mchtDepositList");
		return m;
	}
	
	/**
	 * 
	 * @Title mchtDepositList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月5日 下午2:20:20
	 */
	@ResponseBody
	@RequestMapping(value = "/mchtDeposit/mchtDepositList.shtml")
	public Map<String, Object> mchtDepositList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<MchtDepositCustom> dataList = new ArrayList<MchtDepositCustom>();
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			paramMap.put("mchtStatus", "1,2"); // 商家合作状态	1 正常  2 业务暂停
			dataList = mchtDepositService.selectMchtDepositMchtInfoList(paramMap);
			totalCount = mchtDepositService.mchtDepositMchtInfoListCount(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			dataList = new ArrayList<MchtDepositCustom>();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	/**
	 * 
	 * @Title exportMchtDepositListExcel   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年1月5日 下午2:35:19
	 */
	@RequestMapping(value = "/mchtDeposit/exportMchtDepositListExcel.shtml")
	public void exportMchtDepositListExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> paramMap) {
		try {
			paramMap.put("mchtStatus", "1,2"); // 商家合作状态	1 正常  2 业务暂停
			List<MchtDepositCustom> mchtDepositCustomList = mchtDepositService.selectMchtDepositMchtInfoList(paramMap);
			String[] titles = {"商家序号","公司名称","店铺名称","应缴金额（元）","已缴金额（元）","还需补缴（元）","合作状态"};
			ExcelBean excelBean = new ExcelBean("导出商家保证金应缴金额列表.xls", "导出商家保证金应缴金额列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(MchtDepositCustom mchtDepositCustom : mchtDepositCustomList) {
				String[] data = {
					(mchtDepositCustom.getMchtCode()==null?"":mchtDepositCustom.getMchtCode())+(mchtDepositCustom.getMchtId()==null?"":("-"+mchtDepositCustom.getMchtId())),
					mchtDepositCustom.getCompanyName()==null?"":mchtDepositCustom.getCompanyName(),
					mchtDepositCustom.getShopName()==null?"":mchtDepositCustom.getShopName(),
					mchtDepositCustom.getTotalAmt().toString(),
					mchtDepositCustom.getPayAmt().toString(),
					mchtDepositCustom.getUnpayAmt().toString(),
					mchtDepositCustom.getMchtStatusDesc()==null?"":mchtDepositCustom.getMchtStatusDesc()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title comfirmDepositOrderDataExcel   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月24日 下午5:05:31
	 */
	@RequestMapping(value = "/mchtDeposit/comfirmDepositOrderDataExcel.shtml")
	public void comfirmDepositOrderDataExcel(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepositOrderCustomExample depositOrderCustomExample = new DepositOrderCustomExample();
			DepositOrderCustomExample.DepositOrderCustomCriteria depositOrderCustomCriteria = depositOrderCustomExample.createCriteria();
			depositOrderCustomCriteria.andDelFlagEqualTo("0");
			depositOrderCustomCriteria.andStatusEqualTo("1");
			String searchMchtCode = request.getParameter("mchtCode");
			String searchCompanyName = request.getParameter("companyName");
			String searchPaymentType = request.getParameter("paymentType");
			String searchPaymentName = request.getParameter("paymentName");
			if(!StringUtil.isEmpty(searchMchtCode)){
				depositOrderCustomCriteria.andMchtCodeEqualTo(searchMchtCode);
			}
			if(!StringUtil.isEmpty(searchCompanyName)){
				depositOrderCustomCriteria.andCompanyNameEqualTo(searchCompanyName);
			}
			if(!StringUtil.isEmpty(searchPaymentType)){
				depositOrderCustomCriteria.andPaymentTypeEqualTo(searchPaymentType);
			}
			if(!StringUtil.isEmpty(searchPaymentName)){
				if(searchPaymentName.equals("1") || searchPaymentName.equals("2")){
					depositOrderCustomCriteria.andPaymentTypeEqualTo(searchPaymentName);
				}else{
					depositOrderCustomCriteria.andBankTypeEqualTo(searchPaymentName);
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("create_date_begin")) ){
				depositOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_begin")+" 00:00:00"));
			}
			if(!StringUtil.isEmpty(request.getParameter("create_date_end")) ){
				depositOrderCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("create_date_end")+" 23:59:59"));
			}
			List<DepositOrderCustom> depositOrderCustoms = depositOrderService.selectDepositOrderCustomByExample(depositOrderCustomExample);
			for(DepositOrderCustom depositOrderCustom:depositOrderCustoms){
				if(depositOrderCustom.getAccountId()!=null){
					depositOrderCustom.setPlatformCapitalAccount(platformCapitalAccountService.selectByPrimaryKey(depositOrderCustom.getAccountId()));
				}
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] titles = {"时间","商家序号","公司名称","类型","银行类型","摘要","金额","状态","操作人"};
			ExcelBean excelBean = new ExcelBean("现金缴纳记录列表.xls", "现金缴纳记录列表", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(DepositOrderCustom depositOrderCustom : depositOrderCustoms) {
				String paymentType = "";
				if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && "1".equals(depositOrderCustom.getPaymentType())) {
					paymentType = "支付宝";
				}else if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && "2".equals(depositOrderCustom.getPaymentType())) {
					paymentType = "微信";
				}else if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && "3".equals(depositOrderCustom.getPaymentType())) {
					paymentType = "网银";
				}
				
				String paymentName = "";
				if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && "1".equals(depositOrderCustom.getPaymentType())) {
					paymentName = "支付宝";
				}else if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && "2".equals(depositOrderCustom.getPaymentType())) {
					paymentName = "微信";
				}else if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && "3".equals(depositOrderCustom.getPaymentType())) {
					paymentName = depositOrderCustom.getPlatformPaymentName()==null?"":depositOrderCustom.getPlatformPaymentName();
				}
				
				String str = "";
				if(!StringUtil.isEmpty(depositOrderCustom.getPaymentType()) && ("1".equals(depositOrderCustom.getPaymentType()) || "2".equals(depositOrderCustom.getPaymentType()))) {
					str = "交易号："+depositOrderCustom.getPaymentNo();
				}else {
					str = "账户名："+depositOrderCustom.getPlatformCapitalAccount().getAccountName()
							+"\n开户银行："+depositOrderCustom.getPlatformCapitalAccount().getPaymentName()
							+"\n银行账号："+depositOrderCustom.getPlatformCapitalAccount().getAccountNo();
					if(depositOrderCustom.getPayDate() != null ) {
						str += "\n打款时间："+sdf.format(depositOrderCustom.getPayDate());
					}
				}
				String[] data = {
						depositOrderCustom.getCreateDate()==null?"":sdf.format(depositOrderCustom.getCreateDate()),
						depositOrderCustom.getMchtCode()==null?"":depositOrderCustom.getMchtCode(),
						depositOrderCustom.getCompanyName()==null?"":depositOrderCustom.getCompanyName(),
						paymentType,
						paymentName,
						str,
						depositOrderCustom.getAmount()==null?"":depositOrderCustom.getAmount().toString(),
						depositOrderCustom.getStatusDesc(),		
						depositOrderCustom.getStaffName()		
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
