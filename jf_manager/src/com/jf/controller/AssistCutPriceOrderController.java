package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.CutPriceOrderCustom;
import com.jf.entity.CutPriceOrderCustomExample;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlCustomExample;
import com.jf.service.CutPriceOrderDtlService;
import com.jf.service.CutPriceOrderService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SuppressWarnings("serial")
public class AssistCutPriceOrderController extends BaseController {

	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	
	@Autowired
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	
	/**
	 * 
	 * @Title assistC   
	 * @Description TODO(助力减价)   
	 * @author pengl
	 * @date 2019年2月14日 上午11:04:58
	 */
	@RequestMapping("/assistCutPriceOrder/assistCutPriceOrderManager.shtml")
	public ModelAndView assistC(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/assistCutPriceOrder/getAssistCutPriceOrderList");
		return m;
	}
	
	/**
	 * 
	 * @Title getCutPriceOrderList   
	 * @Description TODO(助力减价列表)   
	 * @author pengl
	 * @date 2019年2月14日 下午3:44:18
	 */
	@ResponseBody
	@RequestMapping("/assistCutPriceOrder/getAssistCutPriceOrderList.shtml")
	public Map<String, Object> getCutPriceOrderList(HttpServletRequest request, Page page, Integer memberId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CutPriceOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			CutPriceOrderCustomExample cutPriceOrderCustomExample = new CutPriceOrderCustomExample();
			CutPriceOrderCustomExample.CutPriceOrderCriteria cutPriceOrderCustomCriteria = cutPriceOrderCustomExample.createCriteria();
			cutPriceOrderCustomCriteria.andDelFlagEqualTo("0").andActivityTypeEqualTo("10").andStatusNotEqualTo("0");
			if(memberId != null) {
				cutPriceOrderCustomCriteria.andMemberIdEqualTo(memberId);
			}
			if(!StringUtil.isEmpty(request.getParameter("productCode"))) {
				cutPriceOrderCustomCriteria.andProductCodeEqualTo(request.getParameter("productCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("shopName"))) {
				cutPriceOrderCustomCriteria.andShopNameLike(request.getParameter("shopName"));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				cutPriceOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("payStatus"))) {
				cutPriceOrderCustomCriteria.andPayStatus(request.getParameter("payStatus"));
			}
			cutPriceOrderCustomExample.setOrderByClause(" t.id desc");
			cutPriceOrderCustomExample.setLimitStart(page.getLimitStart());
			cutPriceOrderCustomExample.setLimitSize(page.getLimitSize());
			totalCount = cutPriceOrderService.countByCustomExample(cutPriceOrderCustomExample);
			dataList = cutPriceOrderService.selectByCustomExample(cutPriceOrderCustomExample);
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
	 * @Title cutPriceOrderDtlManager   
	 * @Description TODO(助力详情)   
	 * @author pengl
	 * @date 2019年2月14日 下午3:44:39
	 */
	@RequestMapping("/assistCutPriceOrder/assistCutPriceOrderDtlManager.shtml")
	public ModelAndView cutPriceOrderDtlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/assistCutPriceOrder/getAssistCutPriceOrderDtlList");
		String cutPriceOrderId = request.getParameter("cutPriceOrderId");
		m.addObject("cutPriceOrderId", cutPriceOrderId);
		return m;
	}
	
	/**
	 * 
	 * @Title getCutPriceOrderDtlList   
	 * @Description TODO(助力详情列表)   
	 * @author pengl
	 * @date 2019年2月14日 下午3:44:54
	 */
	@ResponseBody
	@RequestMapping("/assistCutPriceOrder/getAssistCutPriceOrderDtlList.shtml")
	public Map<String, Object> getCutPriceOrderDtlList(HttpServletRequest request, Page page, Integer memberId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CutPriceOrderDtlCustom> dataList = null;
		Integer totalCount = 0;
		try {
			String cutPriceOrderId = request.getParameter("cutPriceOrderId");
			if(!StringUtil.isEmpty(cutPriceOrderId)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				CutPriceOrderDtlCustomExample cutPriceOrderDtlCustomExample = new CutPriceOrderDtlCustomExample();
				CutPriceOrderDtlCustomExample.CutPriceOrderDtlCriteria cutPriceOrderDtlCustomCriteria = cutPriceOrderDtlCustomExample.createCriteria();
				cutPriceOrderDtlCustomCriteria.andDelFlagEqualTo("0").andCutPriceOrderIdEqualTo(Integer.parseInt(cutPriceOrderId));
				if(!StringUtil.isEmpty(request.getParameter("memberNick"))) {
					cutPriceOrderDtlCustomCriteria.andMemberNickLike("%"+request.getParameter("memberNick")+"%");
				}
				if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
					cutPriceOrderDtlCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")));
				}
				if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
					cutPriceOrderDtlCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")));
				}
				if(memberId != null ) {
					cutPriceOrderDtlCustomCriteria.andMemberIdEqualTo(memberId);
				}
				cutPriceOrderDtlCustomExample.setOrderByClause(" t.id desc");
				cutPriceOrderDtlCustomExample.setLimitStart(page.getLimitStart());
				cutPriceOrderDtlCustomExample.setLimitSize(page.getLimitSize());
				totalCount = cutPriceOrderDtlService.countByCustomExample(cutPriceOrderDtlCustomExample);
				dataList = cutPriceOrderDtlService.selectByCustomExample(cutPriceOrderDtlCustomExample);
			}
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
	 * @Title assistCnfStatisticsManager   
	 * @Description TODO(助力减价数据分析)   
	 * @author pengl
	 * @date 2019年2月15日 上午10:33:48
	 */
	@RequestMapping("/assistCutPriceOrder/assistCutPriceOrderStatisticsManager.shtml")
	public ModelAndView assistCnfStatisticsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/assistCutPriceOrder/assistCutPriceOrderStatisticsList");
		m.addObject("dateFlag", "day");
		return m;
	}
	
	/**
	 * 
	 * @Title assistCutPriceOrderStatisticsList   
	 * @Description TODO(助力减价数据分析)   
	 * @author pengl
	 * @date 2019年2月15日 上午10:34:33
	 */
	@ResponseBody
	@RequestMapping("/assistCutPriceOrder/assistCutPriceOrderStatisticsList.shtml")
	public Map<String, Object> assistCutPriceOrderStatisticsList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			List<String> dateList = new ArrayList<String>();
			Date date = new Date();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			String dateFormat = "";
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			String productCode = request.getParameter("productCode");
			if(!StringUtil.isEmpty(beginDate) || !StringUtil.isEmpty(endDate)) {
				if(!StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						if(sdfDay.parse(endDate).getTime() > date.getTime()) {
							endDate = sdfDay.format(date);
						}
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(endDate), sdfDay, "day");
					}
				}else if(!StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
					}
				}else if(StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(endDate).getTime() > date.getTime()) {
						endDate = sdfDay.format(date);
					}
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(endDate), sdfDay, "day");
				}
				dateFormat = "%Y-%m-%d";
			}else {
				if("day".equals(request.getParameter("dateFlag"))) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
					dateFormat = "%Y-%m-%d";
				}else if("month".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH, 0);
					dateList = DateUtil.getBetweenDates(sdf.parse(sdf.format(cal.getTime())), sdf.parse(sdf.format(date)), sdf, "month");
					dateFormat = "%Y-%m";
				}else if("year".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -1);
					String beginYear = sdf.format(cal.getTime());
					String endYear = sdf.format(date);
					dateList.add(beginYear);
					dateList.add(endYear);
					dateFormat = "%Y";
				}
			}
			totalCount = dateList.size();
			int limitStart = page.getLimitStart();
			int limitSize = page.getLimitSize();
			int endIndex = limitStart + limitSize;
			if(endIndex > totalCount.intValue()) {
				endIndex = totalCount.intValue();
			}
			List<String> dates = new ArrayList<String>();
			for(int i=limitStart;i<endIndex;i++) {
				dates.add(dateList.get(i));
			}
			dataList = cutPriceOrderService.cutPriceOrderAssistStatisticsList(dateList.get(limitStart), dateList.get(endIndex-1), productCode, dates, "10", dateFormat);
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
	 * @Title exportCutPriceOrderStatistics   
	 * @Description TODO(导出助力减价数据分析)   
	 * @author pengl
	 * @date 2019年2月15日 上午11:23:15
	 */
	@RequestMapping("/assistCutPriceOrder/exportCutPriceOrderStatistics.shtml")
	public void exportCutPriceOrderStatistics(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> dataList = null;
		try {
			List<String> dateList = new ArrayList<String>();
			Date date = new Date();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			String dateFormat = "";
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			String productCode = request.getParameter("productCode");
			if(!StringUtil.isEmpty(beginDate) || !StringUtil.isEmpty(endDate)) {
				if(!StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						if(sdfDay.parse(endDate).getTime() > date.getTime()) {
							endDate = sdfDay.format(date);
						}
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(endDate), sdfDay, "day");
					}
				}else if(!StringUtil.isEmpty(beginDate) && StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(beginDate).getTime() < date.getTime()) {
						dateList = DateUtil.getBetweenDates(sdfDay.parse(beginDate), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
					}
				}else if(StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate)) {
					if(sdfDay.parse(endDate).getTime() > date.getTime()) {
						endDate = sdfDay.format(date);
					}
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(endDate), sdfDay, "day");
				}
				dateFormat = "%Y-%m-%d";
			}else {
				if("day".equals(request.getParameter("dateFlag"))) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
					dateFormat = "%Y-%m-%d";
				}else if("month".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH, 0);
					dateList = DateUtil.getBetweenDates(sdf.parse(sdf.format(cal.getTime())), sdf.parse(sdf.format(date)), sdf, "month");
					dateFormat = "%Y-%m";
				}else if("year".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -1);
					String beginYear = sdf.format(cal.getTime());
					String endYear = sdf.format(date);
					dateList.add(beginYear);
					dateList.add(endYear);
					dateFormat = "%Y";
				}
			}
			
			dataList = cutPriceOrderService.cutPriceOrderAssistStatisticsList(dateList.get(0), dateList.get(dateList.size()-1), productCode, dateList, "10", dateFormat);
			String[] titles = {"时间","发起助力总数","助力人数(新用户)","助力完成订单数","下单订单数量","付款成功订单数","总交易额","平台收益"};
			ExcelBean excelBean = new ExcelBean("助力减价数据分析.xls", "助力减价数据分析", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> map : dataList) {
				String[] data = {
						map.get("create_date").toString(),
						map.get("member_id_count").toString(),
						map.get("cut_link_click_log_sum").toString(),
//						map.get("cut_new_member_sum").toString(),
						map.get("cut_price_order_dtl_sum").toString(),
						map.get("cut_price_order_success_sum").toString(),
						map.get("combine_order_success_sum").toString(),
						map.get("total_pay_amount_sum").toString(),
						map.get("pop_rate_amount_sum").toString()
					};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
