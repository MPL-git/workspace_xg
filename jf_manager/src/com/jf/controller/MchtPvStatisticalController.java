package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jf.entity.MchtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtPvStatisticalService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class MchtPvStatisticalController extends BaseController {

	@Autowired
	private MchtPvStatisticalService mchtPvStatisticalService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 
	 * @MethodName: mchtPvStatisticalSumUp
	 * @Description: (商家历史概况)
	 * @author Pengl
	 * @date 2019年6月5日 下午7:28:40
	 */
	@RequestMapping("/mchtPvStatistical/mchtPvStatisticalSumUp.shtml")
	public ModelAndView mchtPvStatisticalSumUp(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/mchtPvStatisticalSumUp");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String statisticsBeginDate = request.getParameter("statisticsBeginDate");
		String statisticsEndDate = request.getParameter("statisticsEndDate");
		String comparisonBeginDate = request.getParameter("comparisonBeginDate");
		String comparisonEndDate = request.getParameter("comparisonEndDate");
		String mchtId = request.getParameter("mchtId");
		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(Integer.parseInt(mchtId));
		m.addObject("mchtInfoCustom", mchtInfoCustom);
		if(StringUtil.isEmpty(statisticsBeginDate) ) {
			Date date = new Date();
			statisticsBeginDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd");
			statisticsEndDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd");
			comparisonBeginDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -2), "yyyy-MM-dd");
			comparisonEndDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -2), "yyyy-MM-dd");
		}
		paramMap.put("statisticsBeginDate", statisticsBeginDate);
		paramMap.put("statisticsEndDate", statisticsEndDate);
		paramMap.put("comparisonBeginDate", comparisonBeginDate);
		paramMap.put("comparisonEndDate", comparisonEndDate);
		paramMap.put("statisticsBeginDateStr", statisticsBeginDate+" 00:00:00");
		paramMap.put("statisticsEndDateStr", statisticsEndDate+" 23:59:59");
		paramMap.put("comparisonBeginDateStr", comparisonBeginDate+" 00:00:00");
		paramMap.put("comparisonEndDateStr", comparisonEndDate+" 23:59:59");
		paramMap.put("mchtId", mchtId);
		paramMap.put("productTypeId", mchtInfoCustom.getProductTypeId());
		//商家历史总览
		Map<String, Object> mchtPvStatisticalSumUpMap = mchtPvStatisticalService.getMchtPvStatisticalSumUp(paramMap);
		m.addObject("mchtPvStatisticalSumUpMap", mchtPvStatisticalSumUpMap);
		//商家店铺支付金额排名
		Map<String, Object> mchtPvPayAmountRankMap = mchtPvStatisticalService.getMchtPvPayAmountRank(paramMap);
		m.addObject("mchtPvPayAmountRankMap", mchtPvPayAmountRankMap);
		//前一百家店铺支付金额平均值
		Map<String, Object> mchtPvPayAmountAvgMap = mchtPvStatisticalService.getMchtPvPayAmountAvg(paramMap);
		m.addObject("mchtPvPayAmountAvgMap", mchtPvPayAmountAvgMap);
		//商家店铺访客数排名
		Map<String, Object> mchtPvTotalVisitorCountRankMap = mchtPvStatisticalService.getMchtPvTotalVisitorCountRank(paramMap);
		m.addObject("mchtPvTotalVisitorCountRankMap", mchtPvTotalVisitorCountRankMap);
		//前一百家店铺访客数平均值
		Map<String, Object> mchtPvTotalVisitorCountAvgMap = mchtPvStatisticalService.getMchtPvTotalVisitorCountAvg(paramMap);
		m.addObject("mchtPvTotalVisitorCountAvgMap", mchtPvTotalVisitorCountAvgMap);
		//商家店铺支付买家数排名
		Map<String, Object> mchtPvPayMemberCountRankMap = mchtPvStatisticalService.getMchtPvPayMemberCountRank(paramMap);
		m.addObject("mchtPvPayMemberCountRankMap", mchtPvPayMemberCountRankMap);
		//前一百家店铺支付买家数平均值
		Map<String, Object> mchtPvPayMemberCountAvgMap = mchtPvStatisticalService.getMchtPvPayMemberCountAvg(paramMap);
		m.addObject("mchtPvPayMemberCountAvgMap", mchtPvPayMemberCountAvgMap);
		m.addObject("statisticsBeginDate", statisticsBeginDate);
		m.addObject("statisticsEndDate", statisticsEndDate);
		m.addObject("comparisonBeginDate", comparisonBeginDate);
		m.addObject("comparisonEndDate", comparisonEndDate);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: mchtPvStatisticalProduct
	 * @Description: (历史商品概况)
	 * @author Pengl
	 * @date 2019年6月6日 上午9:27:59
	 */
	@RequestMapping("/mchtPvStatistical/mchtPvStatisticalProduct.shtml")
	public ModelAndView mchtPvStatisticalProduct(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/mchtPvStatisticalProduct");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String statisticsBeginDate = request.getParameter("statisticsBeginDate");
		String statisticsEndDate = request.getParameter("statisticsEndDate");
		String comparisonBeginDate = request.getParameter("comparisonBeginDate");
		String comparisonEndDate = request.getParameter("comparisonEndDate");
		String clientSource = request.getParameter("clientSource");
		String mchtId = request.getParameter("mchtId");
		MchtInfo mchtInfo =mchtInfoService.selectByPrimaryKey(Integer.parseInt(mchtId));
		m.addObject("mchtInfo", mchtInfo);
		if(StringUtil.isEmpty(statisticsBeginDate) ) {
			Date date = new Date();
			statisticsBeginDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd");
			statisticsEndDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd");
			comparisonBeginDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -2), "yyyy-MM-dd");
			comparisonEndDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -2), "yyyy-MM-dd");
		}
		if(!StringUtil.isEmpty(clientSource) ) {
			paramMap.put("clientSource", clientSource);
		}
		paramMap.put("statisticsBeginDate", statisticsBeginDate+" 00:00:00");
		paramMap.put("statisticsEndDate", statisticsEndDate+" 23:59:59");
		paramMap.put("comparisonBeginDate", comparisonBeginDate+" 00:00:00");
		paramMap.put("comparisonEndDate", comparisonEndDate+" 23:59:59");
		paramMap.put("mchtId", mchtId);
		Map<String, Object> flowMchtProductPvMap = mchtPvStatisticalService.getFlowMchtProductPvMap(paramMap);
		Map<String, Object> mchtProductPvMap = mchtPvStatisticalService.getMchtProductPvMap(paramMap);
		m.addObject("flowMchtProductPvMap", flowMchtProductPvMap);
		m.addObject("mchtProductPvMap", mchtProductPvMap);
		m.addObject("clientSourceList", DataDicUtil.getTableStatus("BU_PLATFORM_PV_STATISTICAL", "CLIENT_SOURCE"));
		m.addObject("statisticsBeginDate", statisticsBeginDate);
		m.addObject("statisticsEndDate", statisticsEndDate);
		m.addObject("comparisonBeginDate", comparisonBeginDate);
		m.addObject("comparisonEndDate", comparisonEndDate);
		m.addObject("clientSource", clientSource);
		ProductTypeExample productTypeExample = new ProductTypeExample();
		productTypeExample.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypeList = productTypeService.selectByExample(productTypeExample);
		m.addObject("productTypeList", productTypeList);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: mchtPvStatisticalProductList
	 * @Description: (商品效果明细)
	 * @author Pengl
	 * @date 2019年6月6日 上午9:28:08
	 */
	@ResponseBody
	@RequestMapping("/mchtPvStatistical/mchtPvStatisticalProductList.shtml")
	public Map<String, Object> mchtPvStatisticalProductList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			String mchtId = request.getParameter("mchtId");
			String clientSource = request.getParameter("clientSource");
			String statisticsBeginDate = request.getParameter("statisticsBeginDate");
			String statisticsEndDate = request.getParameter("statisticsEndDate");
			String saleType = request.getParameter("saleType");
			String productName = request.getParameter("productName");
			String productCode = request.getParameter("productCode");
			String productTypeId = request.getParameter("productTypeId");
			/*String sortname = request.getParameter("sortname"); //字段名称
			String sortorder = request.getParameter("sortorder"); //排序方式*/
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mchtId", mchtId);
			paramMap.put("clientSource", clientSource);
			if(StringUtil.isEmpty(statisticsBeginDate) ) {
				Date date = new Date();
				paramMap.put("statisticsBeginDate", DateUtil.formatDateByFormat(date, "yyyy-MM-dd")+" 00:00:00");
				paramMap.put("statisticsEndDate", DateUtil.formatDateByFormat(date, "yyyy-MM-dd")+" 23:59:59");
			}else {
				paramMap.put("statisticsBeginDate", statisticsBeginDate+" 00:00:00");
				paramMap.put("statisticsEndDate", statisticsEndDate+" 23:59:59");
			}
			paramMap.put("saleType", saleType);
			if(!StringUtil.isEmpty(productName) ) {
				paramMap.put("productName", "%"+productName+"%");
			}
			paramMap.put("productCode", productCode);
			paramMap.put("productTypeId", productTypeId);
			totalCount = mchtPvStatisticalService.getMchtProductPvCount(paramMap);
			/*if(!StringUtil.isEmpty(sortname) ) {
				paramMap.put("orderByClause", sortname+" "+sortorder);
			}*/
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			paramMap.put("orderByClause", "total_pv_count desc, p.update_date desc");
			dataList = mchtPvStatisticalService.getMchtProductPvList(paramMap);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	/**
	 * 
	 * @MethodName: mchtPvStatisticalShow
	 * @Description: (历史流量看板)
	 * @author Pengl
	 * @date 2019年6月6日 下午2:26:04
	 */
	@RequestMapping("/mchtPvStatistical/mchtPvStatisticalShow.shtml")
	public ModelAndView mchtPvStatisticalShow(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/mchtPvStatisticalShow");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String statisticsBeginDate = request.getParameter("statisticsBeginDate");
		String statisticsEndDate = request.getParameter("statisticsEndDate");
		String comparisonBeginDate = request.getParameter("comparisonBeginDate");
		String comparisonEndDate = request.getParameter("comparisonEndDate");
		String mchtId = request.getParameter("mchtId");
		MchtInfoCustom mchtInfoCustom = mchtInfoService.selectMchtInfoCustomById(Integer.parseInt(mchtId));
		m.addObject("mchtInfoCustom", mchtInfoCustom);
		if(StringUtil.isEmpty(statisticsBeginDate) ) {
			Date date = new Date();
			statisticsBeginDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd");
			statisticsEndDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd");
			comparisonBeginDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -2), "yyyy-MM-dd");
			comparisonEndDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -2), "yyyy-MM-dd");
		}
		paramMap.put("statisticsBeginDate", statisticsBeginDate+" 00:00:00");
		paramMap.put("statisticsEndDate", statisticsEndDate+" 23:59:59");
		paramMap.put("comparisonBeginDate", comparisonBeginDate+" 00:00:00");
		paramMap.put("comparisonEndDate", comparisonEndDate+" 23:59:59");
		paramMap.put("statisticsBeginStr", statisticsBeginDate);
		paramMap.put("statisticsEndStr", statisticsEndDate);
		paramMap.put("comparisonBeginStr", comparisonBeginDate);
		paramMap.put("comparisonEndStr", comparisonEndDate);
		paramMap.put("mchtId", mchtId);
		Map<String, Object> mchtVisitorMap = mchtPvStatisticalService.getMchtVisitorMap(paramMap);
		m.addObject("mchtVisitorMap", mchtVisitorMap);
		Map<String, Object> mchtProductVisitorMap = mchtPvStatisticalService.getMchtProductVisitorMap(paramMap);
		m.addObject("mchtProductVisitorMap", mchtProductVisitorMap);
		m.addObject("statisticsBeginDate", statisticsBeginDate);
		m.addObject("statisticsEndDate", statisticsEndDate);
		m.addObject("comparisonBeginDate", comparisonBeginDate);
		m.addObject("comparisonEndDate", comparisonEndDate);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: getMchtVisitorPvMap
	 * @Description: (访问店铺)
	 * @author Pengl
	 * @date 2019年6月6日 下午5:07:02
	 */
	@ResponseBody
	@RequestMapping("/mchtPvStatistical/getMchtVisitorPvMap.shtml")
	public Map<String, Object> getMchtVisitorPvMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String mchtId = request.getParameter("mchtId");
			String statisticsBeginDate = request.getParameter("statisticsBeginDate");
			String statisticsEndDate = request.getParameter("statisticsEndDate");
			String comparisonBeginDate = request.getParameter("comparisonBeginDate");
			String comparisonEndDate = request.getParameter("comparisonEndDate");
			paramMap.put("statisticsBeginDate", statisticsBeginDate+" 00:00:00");
			paramMap.put("statisticsEndDate", statisticsEndDate+" 23:59:59");
			paramMap.put("comparisonBeginDate", comparisonBeginDate+" 00:00:00");
			paramMap.put("comparisonEndDate", comparisonEndDate+" 23:59:59");
			paramMap.put("statisticsBeginStr", statisticsBeginDate);
			paramMap.put("statisticsEndStr", statisticsEndDate);
			paramMap.put("comparisonBeginStr", comparisonBeginDate);
			paramMap.put("comparisonEndStr", comparisonEndDate);
			paramMap.put("mchtId", mchtId);
			Map<String, Object> mchtVisitorMap = mchtPvStatisticalService.getMchtVisitorPvMap(paramMap);
			mchtVisitorMap.put("total_visitor_count_rate", mchtVisitorMap.get("total_visitor_count_rate")==null?"":mchtVisitorMap.get("total_visitor_count_rate").toString());
			mchtVisitorMap.put("total_visitor_count_rate_tourist", mchtVisitorMap.get("total_visitor_count_rate_tourist")==null?"":mchtVisitorMap.get("total_visitor_count_rate_tourist").toString());
			mchtVisitorMap.put("total_pv_count_rate", mchtVisitorMap.get("total_pv_count_rate")==null?"":mchtVisitorMap.get("total_pv_count_rate").toString());
			mchtVisitorMap.put("total_pv_count_rate_tourist", mchtVisitorMap.get("total_pv_count_rate_tourist")==null?"":mchtVisitorMap.get("total_pv_count_rate_tourist").toString());
			mchtVisitorMap.put("total_pv_avg_rate", mchtVisitorMap.get("total_pv_avg_rate")==null?"":mchtVisitorMap.get("total_pv_avg_rate").toString());
			mchtVisitorMap.put("total_pv_avg_rate_tourist", mchtVisitorMap.get("total_pv_avg_rate_tourist")==null?"":mchtVisitorMap.get("total_pv_avg_rate_tourist").toString());
			mchtVisitorMap.put("member_remind_count_rate", mchtVisitorMap.get("member_remind_count_rate")==null?"":mchtVisitorMap.get("member_remind_count_rate").toString());
			map.put("mchtVisitorMap", mchtVisitorMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvProductVisitorMap
	 * @Description: (访问商品)
	 * @author Pengl
	 * @date 2019年6月6日 下午5:07:39
	 */
	@ResponseBody
	@RequestMapping("/mchtPvStatistical/getMchtPvProductVisitorMap.shtml")
	public Map<String, Object> getMchtPvProductVisitorMap(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String mchtId = request.getParameter("mchtId");
			String statisticsBeginDate = request.getParameter("statisticsBeginDate");
			String statisticsEndDate = request.getParameter("statisticsEndDate");
			String comparisonBeginDate = request.getParameter("comparisonBeginDate");
			String comparisonEndDate = request.getParameter("comparisonEndDate");
			paramMap.put("statisticsBeginDate", statisticsBeginDate+" 00:00:00");
			paramMap.put("statisticsEndDate", statisticsEndDate+" 23:59:59");
			paramMap.put("comparisonBeginDate", comparisonBeginDate+" 00:00:00");
			paramMap.put("comparisonEndDate", comparisonEndDate+" 23:59:59");
			paramMap.put("statisticsBeginStr", statisticsBeginDate);
			paramMap.put("statisticsEndStr", statisticsEndDate);
			paramMap.put("comparisonBeginStr", comparisonBeginDate);
			paramMap.put("comparisonEndStr", comparisonEndDate);
			paramMap.put("mchtId", mchtId);
			Map<String, Object> mchtPvProductVisitorMap = mchtPvStatisticalService.getMchtPvProductVisitorMap(paramMap);
			mchtPvProductVisitorMap.put("total_visitor_count_rate", mchtPvProductVisitorMap.get("total_visitor_count_rate")==null?"":mchtPvProductVisitorMap.get("total_visitor_count_rate").toString());
			mchtPvProductVisitorMap.put("total_visitor_count_rate_tourist", mchtPvProductVisitorMap.get("total_visitor_count_rate_tourist")==null?"":mchtPvProductVisitorMap.get("total_visitor_count_rate_tourist").toString());
			mchtPvProductVisitorMap.put("total_pv_count_rate", mchtPvProductVisitorMap.get("total_pv_count_rate")==null?"":mchtPvProductVisitorMap.get("total_pv_count_rate").toString());
			mchtPvProductVisitorMap.put("total_pv_count_rate_tourist", mchtPvProductVisitorMap.get("total_pv_count_rate_tourist")==null?"":mchtPvProductVisitorMap.get("total_pv_count_rate_tourist").toString());
			mchtPvProductVisitorMap.put("total_pv_count_avg_rate", mchtPvProductVisitorMap.get("total_pv_count_avg_rate")==null?"":mchtPvProductVisitorMap.get("total_pv_count_avg_rate").toString());
			mchtPvProductVisitorMap.put("total_pv_count_avg_rate_tourist", mchtPvProductVisitorMap.get("total_pv_count_avg_rate_tourist")==null?"":mchtPvProductVisitorMap.get("total_pv_count_avg_rate_tourist").toString());
			mchtPvProductVisitorMap.put("member_remind_count_rate", mchtPvProductVisitorMap.get("member_remind_count_rate")==null?"":mchtPvProductVisitorMap.get("member_remind_count_rate").toString());
			map.put("mchtPvProductVisitorMap", mchtPvProductVisitorMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvVisitorMap
	 * @Description: (转化)
	 * @author Pengl
	 * @date 2019年6月6日 下午5:07:56
	 */
	@ResponseBody
	@RequestMapping("/mchtPvStatistical/getMchtPvVisitorMap.shtml")
	public Map<String, Object> getMchtPvVisitorMap(HttpServletRequest request, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String code = "200";
		String msg = "操作成功！";
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String mchtId = request.getParameter("mchtId");
			String statisticsBeginDate = request.getParameter("statisticsBeginDate");
			String statisticsEndDate = request.getParameter("statisticsEndDate");
			String comparisonBeginDate = request.getParameter("comparisonBeginDate");
			String comparisonEndDate = request.getParameter("comparisonEndDate");
			paramMap.put("statisticsBeginDate", statisticsBeginDate+" 00:00:00");
			paramMap.put("statisticsEndDate", statisticsEndDate+" 23:59:59");
			paramMap.put("comparisonBeginDate", comparisonBeginDate+" 00:00:00");
			paramMap.put("comparisonEndDate", comparisonEndDate+" 23:59:59");
			paramMap.put("statisticsBeginStr", statisticsBeginDate);
			paramMap.put("statisticsEndStr", statisticsEndDate);
			paramMap.put("comparisonBeginStr", comparisonBeginDate);
			paramMap.put("comparisonEndStr", comparisonEndDate);
			paramMap.put("mchtId", mchtId);
			Map<String, Object> mchtPvVisitorMap = mchtPvStatisticalService.getMchtPvVisitorMap(paramMap);
			mchtPvVisitorMap.put("pay_member_count_rate", mchtPvVisitorMap.get("pay_member_count_rate")==null?"0.00":mchtPvVisitorMap.get("pay_member_count_rate").toString());
			mchtPvVisitorMap.put("combine_order_count_rate", mchtPvVisitorMap.get("combine_order_count_rate")==null?"0.00":mchtPvVisitorMap.get("combine_order_count_rate").toString());
			mchtPvVisitorMap.put("shopping_cart_count_rate", mchtPvVisitorMap.get("shopping_cart_count_rate")==null?"0.00":mchtPvVisitorMap.get("shopping_cart_count_rate").toString());
			map.put("mchtPvVisitorMap", mchtPvVisitorMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			code = "9999";
			msg = "系统异常请稍后再试！";
		}
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * 
	 * @MethodName: getMchtPvStatisticalSourceList
	 * @Description: (来源)
	 * @author Pengl
	 * @date 2019年6月10日 上午11:20:03
	 */
	@RequestMapping("/mchtPvStatistical/getMchtPvStatisticalSourceList.shtml")
	public ModelAndView getMchtPvStatisticalSourceList(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/mchtPvStatisticalSourceList");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mchtId", request.getParameter("mchtId"));
		paramMap.put("statisticsBeginDate", request.getParameter("statisticsBeginDate")+" 00:00:00");
		paramMap.put("statisticsEndDate", request.getParameter("statisticsEndDate")+" 23:59:59");
		paramMap.put("flag", request.getParameter("flag"));
		List<Map<String, Object>> sourceList =  mchtPvStatisticalService.getMchtPvStatisticalSourceList(paramMap);
		m.addObject("sourceList", sourceList);
		return m;
	}
	
	
}
