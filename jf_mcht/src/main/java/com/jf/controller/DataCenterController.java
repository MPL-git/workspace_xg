package com.jf.controller;

import com.jf.bean.ExcelBean;
import com.jf.common.base.Page;
import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/dataCenter")
public class DataCenterController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(DataCenterController.class);

	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private ProductService productService;
	@Resource
	private ProductItemService productItemService;
	@Resource
	private ActivityProductService activityProductService;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private OrderDtlService orderDtlService;
	
	private ArrayList<Integer> mchtIdList = new ArrayList<Integer>() {{
	    add(8);
	    add(13);
	    add(92);
	    add(97);
	    add(102);
	    add(113);
	}};
	
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	/**
	 * 每天销售数据列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/eachDaySaleList")
	public String eachDaySaleList(Model model, HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateEnd = df.format(new Date());
		String payDateBegin = payDateEnd.substring(0,7)+"-01";
		model.addAttribute("payDateEnd", payDateEnd);
		model.addAttribute("payDateBegin", payDateBegin);
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		return "dataCenter/eachDaySaleList";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getEachDaySaleData")
	@ResponseBody
	public ResponseMsg getSubOrderList(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = request.getParameter("timeBegin");
		String payDateEnd = request.getParameter("timeEnd");
		String productCode = request.getParameter("search_productCode");
		String brandId = request.getParameter("search_brandId");
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId",this.getSessionMchtInfo(request).getId());
		
		if(!StringUtil.isEmpty(payDateBegin)){
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}else{
			payDateEnd = df.format(new Date());
			payDateBegin = payDateEnd.substring(0,7)+"-01";
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}
		if(!StringUtil.isEmpty(payDateEnd)){
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}else{
			payDateEnd = df.format(new Date());
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}
		if(!StringUtil.isEmpty(productCode)){
			paramMap.put("productCode",productCode.trim());
		}
		if(!StringUtil.isEmpty(brandId)){
			paramMap.put("brandId",brandId);
		}
		List<OrderDtlCustom> orderDtlCustoms = orderDtlService.eachDaySaleData(paramMap);
		HashMap<String, OrderDtlCustom> map = new HashMap<String, OrderDtlCustom>();
		List<String> containDays = new ArrayList<String>();
		for(OrderDtlCustom orderDtlCustom:orderDtlCustoms){
			containDays.add(orderDtlCustom.getEachDay());
			map.put(orderDtlCustom.getEachDay(), orderDtlCustom);
		}
		List<String> betweenDays = this.getBetweenDays(payDateBegin, payDateEnd);
		for(int i= 0;i<betweenDays.size();i++){
			if(!containDays.contains(betweenDays.get(i))){
				OrderDtlCustom orderDtlCustom = new OrderDtlCustom();
				orderDtlCustom.setEachDay(betweenDays.get(i));
				orderDtlCustom.setProductCount(0);
				orderDtlCustom.setProductAmount(new BigDecimal(0));
				orderDtlCustom.setMchtPreferential(new BigDecimal(0));
				orderDtlCustom.setPlatformPreferential(new BigDecimal(0));
				orderDtlCustom.setIntegralPreferential(new BigDecimal(0));
				orderDtlCustom.setPayAmount(new BigDecimal(0));
				orderDtlCustoms.add(orderDtlCustom);
				map.put(betweenDays.get(i), orderDtlCustom);
			}
		}
		Collections.sort(orderDtlCustoms, new Comparator<OrderDtlCustom>() {
            @Override
            public int compare(OrderDtlCustom c1, OrderDtlCustom c2) {
                //升序
                return c1.getEachDay().compareTo(c2.getEachDay());
            }
        });
		returnData.put("Rows", orderDtlCustoms);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);

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
	 * 商品销售数据
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/productSaleList")
	public String productSaleList(Model model, HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateEnd = df.format(new Date());
		String payDateBegin = payDateEnd.substring(0,7)+"-01";
		model.addAttribute("payDateEnd", payDateEnd);
		model.addAttribute("payDateBegin", payDateBegin);
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		return "dataCenter/productSaleList";
	}
	
	/**
	 * 数据列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getProductSaleData")
	@ResponseBody
	public ResponseMsg getProductSaleData(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = request.getParameter("timeBegin");
		String payDateEnd = request.getParameter("timeEnd");
		String productName = request.getParameter("search_productName");
		String productCode = request.getParameter("search_productCode");
		String brandId = request.getParameter("search_brandId");
		String orderByType = request.getParameter("orderByType");
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId",this.getSessionMchtInfo(request).getId());
		if(!StringUtil.isEmpty(payDateBegin)){  //不为空即设置从0点开始
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}else{
			payDateEnd = df.format(new Date());
			payDateBegin = payDateEnd.substring(0,7)+"-01";
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}
		if(!StringUtil.isEmpty(payDateEnd)){
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}else{
			payDateEnd = df.format(new Date());
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}
		if(!StringUtil.isEmpty(productName)){
			paramMap.put("productName",productName.trim());
		}
		if(!StringUtil.isEmpty(productCode)){
			paramMap.put("productCode",productCode.trim());
		}
		if(!StringUtil.isEmpty(brandId)){
			paramMap.put("brandId",brandId);
		}
		if(!StringUtil.isEmpty(orderByType)){
			paramMap.put("orderByType",orderByType);
		}
		int totalCount = productService.countProductSaleData(paramMap);
		paramMap.put("limitStart", page.getLimitStart());
		paramMap.put("limitSize", page.getLimitSize());
		List<HashMap<String, Object>> list = productService.getProductSaleData(paramMap);
		//分割total_data
        for (HashMap<String, Object> map:list) {
            String total_data = (String) map.get("total_data");
            String[] split = total_data.split("/");
            map.put("product_amount",split[0]);
			map.put("mcht_preferential",split[1]);
			map.put("platform_preferential",split[2]);
        }
		returnData.put("Rows", list);
		returnData.put("Total", totalCount);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 导出商品销售数据excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/exportProductSale")
	public void exportProductSale(HttpServletRequest request,HttpServletResponse response) throws Exception {
		OrderDtlExample orderDtlExample = new OrderDtlExample();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = request.getParameter("timeBegin");
		String payDateEnd = request.getParameter("timeEnd");
		String productName = request.getParameter("search_productName");
		String productCode = request.getParameter("search_productCode");
		String brandId = request.getParameter("search_brandId");

		String activityType = request.getParameter("search_activityType");
		String orderByType = request.getParameter("orderByType");

		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId",this.getSessionMchtInfo(request).getId());
		if(!StringUtil.isEmpty(payDateBegin)){
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}else{
			payDateEnd = df.format(new Date());
			payDateBegin = payDateEnd.substring(0,7)+"-01";
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}
		if(!StringUtil.isEmpty(payDateEnd)){
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}else{
			payDateEnd = df.format(new Date());
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}
		if(!StringUtil.isEmpty(productName)){
			paramMap.put("productName",productName);
		}
		if(!StringUtil.isEmpty(productCode)){
			paramMap.put("productCode",productCode);
		}
		if(!StringUtil.isEmpty(brandId)){
			paramMap.put("brandId",brandId);
		}
		if(!StringUtil.isEmpty(activityType)){
			paramMap.put("activityType",activityType);
		}
		if(!StringUtil.isEmpty(orderByType) && orderByType.equals("0")){
			orderDtlExample.setOrderByClause("product_count DESC");
			paramMap.put("orderByType",orderByType);
		}else if (!StringUtil.isEmpty(orderByType) && orderByType.equals("1")){
			orderDtlExample.setOrderByClause("pay_amount DESC ");
			paramMap.put("orderByType",orderByType);
		}
		List<HashMap<String, Object>> list = productService.getProductSaleData(paramMap);
		//分割total_data
		for (HashMap<String, Object> map:list) {
			String total_data = (String) map.get("total_data");
			String[] split = total_data.split("/");
			map.put("product_amount",split[0]);
			map.put("mcht_preferential",split[1]);
			map.put("platform_preferential",split[2]);
		}
		String[] titles = { "商品ID", "商品名称", "品牌","商品货号","数量","商品金额","商家优惠","平台优惠","实付金额"};
		ExcelBean excelBean = new ExcelBean("导出商品销售数据.xls",
				"导出商品销售数据", titles);
		List<String[]> datas = new ArrayList<>();
		for (HashMap<String, Object> map : list) {
			BigDecimal platformPreferential = new BigDecimal((String) map.get("platform_preferential"));
			BigDecimal integralPreferential = (BigDecimal)map.get("integral_preferential");
			String[] data = {
				"`"+map.get("code").toString(),
				map.get("product_name").toString(),
				map.get("brand_name").toString(),
				map.get("art_no").toString(),
				map.get("product_count").toString(),
				map.get("product_amount").toString(),
				map.get("mcht_preferential").toString(),
				integralPreferential==null?platformPreferential.toString():platformPreferential.add(integralPreferential).toString(),
				map.get("pay_amount").toString()
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	
	/**
	 * 单品活动销售数据列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/singleProductActivitySaleList")
	public String singleProductActivitySaleList(Model model, HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateEnd = df.format(new Date());
		String payDateBegin = payDateEnd.substring(0,7)+"-01";
		model.addAttribute("payDateEnd", payDateEnd);
		model.addAttribute("payDateBegin", payDateBegin);
		List<ProductBrand> productBrandList = mchtProductBrandService.getMchtProductBrandList(this.getSessionMchtInfo(request).getId());
		model.addAttribute("productBrandList", productBrandList);
		List<SysStatus> typeList = DataDicUtil.getStatusList("BU_SINGLE_PRODUCT_ACTIVITY", "TYPE");
		model.addAttribute("typeList", typeList);
		return "dataCenter/singleProductActivitySaleList";
	}
	
	/**
	 * 单品活动销售数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getSingleProductActivitySaleData")
	@ResponseBody
	public ResponseMsg getSingleProductActivitySaleData(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = request.getParameter("timeBegin");
		String payDateEnd = request.getParameter("timeEnd");
		String productName = request.getParameter("search_productName");
		String productCode = request.getParameter("search_productCode");
		String brandId = request.getParameter("search_brandId");
		String activityType = request.getParameter("search_activityType");
		String orderByType = request.getParameter("orderByType");
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId",this.getSessionMchtInfo(request).getId());
		if(!StringUtil.isEmpty(payDateBegin)){
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}else{
			payDateEnd = df.format(new Date());
			payDateBegin = payDateEnd.substring(0,7)+"-01";
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}
		if(!StringUtil.isEmpty(payDateEnd)){
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}else{
			payDateEnd = df.format(new Date());
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}
		if(!StringUtil.isEmpty(productName)){
			paramMap.put("productName",productName.trim());
		}
		if(!StringUtil.isEmpty(productCode)){
			paramMap.put("productCode",productCode.trim());
		}
		if(!StringUtil.isEmpty(brandId)){
			paramMap.put("brandId",brandId);
		}
		if(!StringUtil.isEmpty(activityType)){
			paramMap.put("activityType",activityType);
		}
		if(!StringUtil.isEmpty(orderByType)){
			paramMap.put("orderByType",orderByType);
		}
		List<HashMap<String, Object>> list = orderDtlService.getSingleProductActivitySaleData(paramMap);
		returnData.put("Rows", list);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
	
	/**
	 * 导出单品活动销售数据excel
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/exportSingleProductActivitySale")
	public void exportSingleProductActivitySale(HttpServletRequest request,HttpServletResponse response) throws Exception {
		OrderDtlExample orderDtlExample = new OrderDtlExample();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateBegin = request.getParameter("timeBegin");
		String payDateEnd = request.getParameter("timeEnd");
		String productName = request.getParameter("search_productName");
		String productCode = request.getParameter("search_productCode");
		String brandId = request.getParameter("search_brandId");

		String activityType = request.getParameter("search_activityType");
		String orderByType = request.getParameter("orderByType");

		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId",this.getSessionMchtInfo(request).getId());
		if(!StringUtil.isEmpty(payDateBegin)){
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}else{
			payDateEnd = df.format(new Date());
			payDateBegin = payDateEnd.substring(0,7)+"-01";
			paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
		}
		if(!StringUtil.isEmpty(payDateEnd)){
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}else{
			payDateEnd = df.format(new Date());
			paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
		}
		if(!StringUtil.isEmpty(productName)){
			paramMap.put("productName",productName);
		}
		if(!StringUtil.isEmpty(productCode)){
			paramMap.put("productCode",productCode);
		}
		if(!StringUtil.isEmpty(brandId)){
			paramMap.put("brandId",brandId);
		}
		if(!StringUtil.isEmpty(activityType)){
			paramMap.put("activityType",activityType);
		}
		if(!StringUtil.isEmpty(orderByType) && orderByType.equals("0")){
			orderDtlExample.setOrderByClause("SUM(od.quantity) DESC");
			paramMap.put("orderByType",orderByType);
		}else if (!StringUtil.isEmpty(orderByType) && orderByType.equals("1")){
			orderDtlExample.setOrderByClause("SUM(od.pay_amount) DESC ");
			paramMap.put("orderByType",orderByType);
		}
		List<HashMap<String, Object>> list = orderDtlService.getSingleProductActivitySaleData(paramMap);
		String[] titles = { "商品ID", "商品名称", "品牌","商品货号","数量","商品金额","商家优惠","平台优惠","实付金额","点击数" };
		ExcelBean excelBean = new ExcelBean("导出单品活动销售数据.xls",
				"导出单品活动销售数据", titles);
		List<String[]> datas = new ArrayList<>();
		for (HashMap<String, Object> map : list) {
			BigDecimal platformPreferential = (BigDecimal)map.get("platform_preferential");
			BigDecimal integralPreferential = (BigDecimal)map.get("integral_preferential");
			String[] data = {
				"`"+map.get("code").toString(),
				map.get("product_name").toString(),
				map.get("brand_name").toString(),
				map.get("art_no").toString(),
				map.get("product_count").toString(),
				map.get("product_amount").toString(),
				map.get("mcht_preferential").toString(),
				integralPreferential==null?platformPreferential.toString():platformPreferential.add(integralPreferential).toString(),
				map.get("pay_amount").toString(),
				map.get("hit_count").toString()
			};
			datas.add(data);
		}
		excelBean.setDataList(datas);
		ExcelUtils.export(excelBean,response);
	}
	
	/**
	 * 品牌特卖销售数据列表
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityAreaSaleList")
	public String activityAreaSaleList(Model model, HttpServletRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateEnd = df.format(new Date());
		Date preMonthDate = DateUtil.getMonthsAgo(new Date(), -1);
		String dateBegin = df.format(preMonthDate).substring(0,7)+"-01";
		model.addAttribute("dateBegin", dateBegin);
		model.addAttribute("dateEnd", dateEnd);
		return "dataCenter/activityAreaSaleList";
	}
	
	/**
	 * 品牌特卖销售数据
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getActivityAreaSaleData")
	@ResponseBody
	public ResponseMsg getActivityAreaSaleData(HttpServletRequest request, Page page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateBegin = request.getParameter("dateBegin");
		String dateEnd = request.getParameter("dateEnd");
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("mchtId",this.getSessionMchtInfo(request).getId());
		if(!StringUtil.isEmpty(dateBegin)){
			paramMap.put("dateBegin",dateBegin+" 00:00:00");
		}else{
			dateEnd = df.format(new Date());
			dateBegin = dateEnd.substring(0,7)+"-01";
			paramMap.put("dateBegin",dateBegin+" 00:00:00");
		}
		if(!StringUtil.isEmpty(dateEnd)){
			paramMap.put("dateEnd",dateEnd+" 23:59:59");
		}else{
			dateEnd = df.format(new Date());
			paramMap.put("dateEnd",dateEnd+" 23:59:59");
		}
		List<HashMap<String, Object>> list = orderDtlService.activityAreaSaleData(paramMap);
		returnData.put("Rows", list);
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG, returnData);
	}
}
