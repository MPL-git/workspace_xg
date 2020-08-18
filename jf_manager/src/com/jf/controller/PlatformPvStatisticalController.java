package com.jf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.service.PlatformPvStatisticalService;
import com.jf.service.ProductTypeService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class PlatformPvStatisticalController extends BaseController {

	@Autowired
	private PlatformPvStatisticalService platformPvStatisticalService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	/**
	 * 
	 * @MethodName: platformPvStatisticalSumUp
	 * @Description: (平台流量统计)
	 * @author Pengl
	 * @date 2019年6月3日 下午2:43:18
	 */
	@RequestMapping("/platformPvStatistical/platformPvStatisticalSumUp.shtml")
	public ModelAndView platformPvStatisticalSumUp(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/platformPvStatisticalSumUp");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String statisticsBeginDate = request.getParameter("statisticsBeginDate");
		String statisticsEndDate = request.getParameter("statisticsEndDate");
		String comparisonBeginDate = request.getParameter("comparisonBeginDate");
		String comparisonEndDate = request.getParameter("comparisonEndDate");
		String clientSource = request.getParameter("clientSource");
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
		paramMap.put("statisticsBeginDate", statisticsBeginDate);
		paramMap.put("statisticsEndDate", statisticsEndDate);
		paramMap.put("comparisonBeginDate", comparisonBeginDate);
		paramMap.put("comparisonEndDate", comparisonEndDate);
		Map<String, Object> platformPvStatisticalMap = platformPvStatisticalService.getPlatformPvStatisticalSumUp(paramMap);
		m.addObject("platformPvStatisticalMap", platformPvStatisticalMap);
		m.addObject("clientSourceList", DataDicUtil.getTableStatus("BU_PLATFORM_PV_STATISTICAL", "CLIENT_SOURCE"));
		m.addObject("statisticsBeginDate", statisticsBeginDate);
		m.addObject("statisticsEndDate", statisticsEndDate);
		m.addObject("comparisonBeginDate", comparisonBeginDate);
		m.addObject("comparisonEndDate", comparisonEndDate);
		m.addObject("clientSource", clientSource);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: platformPvStatisticalMSA
	 * @Description: (历史页面分析)
	 * @author Pengl
	 * @date 2019年6月4日 上午9:26:24
	 */
	@RequestMapping("/platformPvStatistical/platformPvStatisticalMSA.shtml")
	public ModelAndView platformPvStatisticalMSA(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/platformPvStatisticalMSA");
		Date date = new Date();
		m.addObject("clientSourceList", DataDicUtil.getTableStatus("BU_PLATFORM_PV_STATISTICAL", "CLIENT_SOURCE"));
		m.addObject("statisticsBeginDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd"));
		m.addObject("statisticsEndDate", DateUtil.formatDateByFormat(DateUtil.getDateAfter(date, -1), "yyyy-MM-dd"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: platformPvStatisticalMSAList
	 * @Description: (历史页面分析)
	 * @author Pengl
	 * @date 2019年6月4日 上午10:45:05
	 */
	@ResponseBody
	@RequestMapping("/platformPvStatistical/platformPvStatisticalMSAList.shtml")
	public Map<String, Object> platformPvStatisticalMSAList(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("statisticsBeginDate", request.getParameter("statisticsBeginDate")+" 00:00:00");
			paramMap.put("statisticsEndDate", request.getParameter("statisticsEndDate")+" 23:59:59");
			paramMap.put("clientSource", request.getParameter("clientSource"));
			dataList = platformPvStatisticalService.getPlatformPvStatisticalMSAList(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: platformPvStatisticalFlow
	 * @Description: (贡献下游)
	 * @author Pengl
	 * @date 2019年6月4日 下午4:54:38
	 */
	@RequestMapping("/platformPvStatistical/platformPvStatisticalFlow.shtml")
	public ModelAndView platformPvStatisticalFlow(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/platformPvStatisticalFlow");
		Map<String, Object> upstreamMap = new HashMap<String, Object>();
		upstreamMap.put("pageClassify", request.getParameter("pageClassify"));
		upstreamMap.put("statisticsBeginDate", request.getParameter("statisticsBeginDate")+" 00:00:00");
		upstreamMap.put("statisticsEndDate", request.getParameter("statisticsEndDate")+" 23:59:59");
		upstreamMap.put("clientSource", request.getParameter("clientSource"));
		Map<String, Object> downstreamMap = new HashMap<String, Object>();
		downstreamMap.put("pageClassify", request.getParameter("pageClassify"));
		downstreamMap.put("statisticsBeginDate", request.getParameter("statisticsBeginDate")+" 00:00:00");
		downstreamMap.put("statisticsEndDate", request.getParameter("statisticsEndDate")+" 23:59:59");
		downstreamMap.put("clientSource", request.getParameter("clientSource"));
		List<Map<String, Object>> upstreamList =  platformPvStatisticalService.getPlatformPvStatisticalUpstreamList(upstreamMap);
		List<Map<String, Object>> downstreamList =  platformPvStatisticalService.getPlatformPvStatisticalDownstreamList(downstreamMap);
		m.addObject("upstreamList", upstreamList);
		m.addObject("downstreamList", downstreamList);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: platformPvStatisticalDtl
	 * @Description: (详情)
	 * @author Pengl
	 * @date 2019年6月4日 下午5:02:31
	 */
	@RequestMapping("/platformPvStatistical/platformPvStatisticalDtl.shtml")
	public ModelAndView platformPvStatisticalDtl(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/platformPvStatisticalDtl");
		m.addObject("statisticsBeginDate", request.getParameter("statisticsBeginDate"));
		m.addObject("statisticsEndDate", request.getParameter("statisticsEndDate"));
		m.addObject("pageClassify", request.getParameter("pageClassify"));
		m.addObject("totalPvCount", request.getParameter("totalPvCount"));
		m.addObject("totalPvCountTourist", request.getParameter("totalPvCountTourist"));
		return m;
	}
	
	@ResponseBody
	@RequestMapping("/platformPvStatistical/platformPvStatisticalDtlList.shtml")
	public Map<String, Object> platformPvStatisticalDtlList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String pageClassify = request.getParameter("pageClassify");
			if("2".equals(pageClassify) ) { //单品爆款
				paramMap.put("pageType", "4");
			}else if("9".equals(pageClassify) ) { //特卖
				paramMap.put("valueId", "valueId");
			}else if("10".equals(pageClassify) ) { //商品
				paramMap.put("valueId", "valueId");
			}else if("11".equals(pageClassify) ) { //会场
				paramMap.put("valueId", "valueId");
			}
			paramMap.put("pageClassify", pageClassify);
			paramMap.put("statisticsBeginDate", request.getParameter("statisticsBeginDate")+" 00:00:00");
			paramMap.put("statisticsEndDate", request.getParameter("statisticsEndDate")+" 23:59:59");
			paramMap.put("clientSource", request.getParameter("clientSource"));
			paramMap.put("totalPvCount", request.getParameter("totalPvCount"));
			paramMap.put("totalPvCountTourist", request.getParameter("totalPvCountTourist"));
			totalCount = platformPvStatisticalService.getPlatformPvStatisticalDtlCount(paramMap);
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			dataList = platformPvStatisticalService.getPlatformPvStatisticalDtlList(paramMap);
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
	 * @MethodName: platformPvStatisticalProduct
	 * @Description: (历史商品概况)
	 * @author Pengl
	 * @date 2019年6月4日 下午8:05:19
	 */
	@RequestMapping("/platformPvStatistical/platformPvStatisticalProduct.shtml")
	public ModelAndView platformPvStatisticalProduct(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/dataCenter/trafficData/platformPvStatisticalProduct");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String statisticsBeginDate = request.getParameter("statisticsBeginDate");
		String statisticsEndDate = request.getParameter("statisticsEndDate");
		String comparisonBeginDate = request.getParameter("comparisonBeginDate");
		String comparisonEndDate = request.getParameter("comparisonEndDate");
		String clientSource = request.getParameter("clientSource");
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
		Map<String, Object> flowProductPvMap = platformPvStatisticalService.getFlowProductPvMap(paramMap);
		Map<String, Object> productPvListMap = platformPvStatisticalService.getProductPvMap(paramMap);
		m.addObject("flowProductPvMap", flowProductPvMap);
		m.addObject("productPvListMap", productPvListMap);
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
	 * @MethodName: platformPvStatisticalProductList
	 * @Description: (商品效果明细)
	 * @author Pengl
	 * @date 2019年6月5日 下午2:47:13
	 */
	@ResponseBody
	@RequestMapping("/platformPvStatistical/platformPvStatisticalProductList.shtml")
	public Map<String, Object> platformPvStatisticalProductList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
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
			totalCount = platformPvStatisticalService.getProductPvCount(paramMap);
			/*if(!StringUtil.isEmpty(sortname) ) {
				paramMap.put("orderByClause", sortname+" "+sortorder);
			}*/
			paramMap.put("limitStart", page.getLimitStart());
			paramMap.put("limitSize", page.getLimitSize());
			paramMap.put("orderByClause", "total_pv_count desc, p.update_date desc");
			dataList = platformPvStatisticalService.getProductPvList(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}
	
	
	
	
}
