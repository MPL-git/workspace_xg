package com.jf.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.entity.BlackList;
import com.jf.entity.CutPriceOrder;
import com.jf.entity.CutPriceOrderCustom;
import com.jf.entity.CutPriceOrderCustomExample;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlCustomExample;
import com.jf.entity.StateCode;
import com.jf.service.BlackListService;
import com.jf.service.CutPriceOrderDtlService;
import com.jf.service.CutPriceOrderService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class SuperCutPriceOrderController extends BaseController {

	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	
	@Autowired
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	
	@Autowired
	private BlackListService blackListService;
	
	/**
	 * 
	 * @Title cutPriceOrderManager   
	 * @Description TODO(超级砍价)   
	 * @author pengl
	 * @date 2018年6月11日 下午3:11:17
	 */
	@RequestMapping("/superCutPriceOrder/superCutPriceOrderManager.shtml")
	public ModelAndView cutPriceOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceOrder/getSuperCutPriceOrderList");
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_CUT_PRICE_ORDER", "STATUS"));
		m.addObject("auditStatusList", DataDicUtil.getTableStatus("BU_CUT_PRICE_ORDER", "AUDIT_STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSuperCutPriceOrderList   
	 * @Description TODO(超级砍价)   
	 * @author pengl
	 * @date 2018年6月11日 下午3:11:24
	 */
	@ResponseBody
	@RequestMapping("/superCutPriceOrder/getSuperCutPriceOrderList.shtml")
	public Map<String, Object> getSuperCutPriceOrderList(HttpServletRequest request, Page page, Integer memberId) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CutPriceOrderCustom> dataList = null;
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CutPriceOrderCustomExample cutPriceOrderCustomExample = new CutPriceOrderCustomExample();
			CutPriceOrderCustomExample.CutPriceOrderCriteria cutPriceOrderCustomCriteria = cutPriceOrderCustomExample.createCriteria();
			cutPriceOrderCustomCriteria.andDelFlagEqualTo("0");
			if(!StringUtil.isEmpty(request.getParameter("orderCode"))) {
				cutPriceOrderCustomCriteria.andOrderCodeEqualTo(request.getParameter("orderCode"));
			}
			if(!StringUtil.isEmpty(request.getParameter("memberNick"))) {
				cutPriceOrderCustomCriteria.andMemberNickLike("%"+request.getParameter("memberNick")+"%");
			}
			if(!StringUtil.isEmpty(request.getParameter("beginCreateDate"))) {
				cutPriceOrderCustomCriteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("beginCreateDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("endCreateDate"))) {
				cutPriceOrderCustomCriteria.andCreateDateLessThanOrEqualTo(sdf.parse(request.getParameter("endCreateDate")));
			}
			if(!StringUtil.isEmpty(request.getParameter("status"))) {
				cutPriceOrderCustomCriteria.andStatusEqualTo(request.getParameter("status"));
			}
			if(!StringUtil.isEmpty(request.getParameter("auditStatus"))) {
				cutPriceOrderCustomCriteria.andAuditStatusEqualTo(request.getParameter("auditStatus"));
			}
			if(!StringUtil.isEmpty(request.getParameter("productName"))) {
				cutPriceOrderCustomCriteria.andProductNameLike("%"+request.getParameter("productName")+"%");
			}
			if(memberId != null) {
				cutPriceOrderCustomCriteria.andMemberIdEqualTo(memberId);
			}
			cutPriceOrderCustomCriteria.andSuperCutPrice();
			cutPriceOrderCustomCriteria.andSuperBlackListNull();
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
	 * @Title superCutPriceOrderDtlManager   
	 * @Description TODO(砍价记录)   
	 * @author pengl
	 * @date 2018年6月11日 下午3:11:29
	 */
	@RequestMapping("/superCutPriceOrder/superCutPriceOrderDtlManager.shtml")
	public ModelAndView superCutPriceOrderDtlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceOrder/getSuperCutPriceOrderDtlList");
		m.addObject("cutPriceOrderId", request.getParameter("cutPriceOrderId"));
		return m;
	}
	
	/**
	 * 
	 * @Title getSuperCutPriceOrderDtlList   
	 * @Description TODO(砍价记录)   
	 * @author pengl
	 * @date 2018年6月11日 下午3:11:32
	 */
	@ResponseBody
	@RequestMapping("/superCutPriceOrder/getSuperCutPriceOrderDtlList.shtml")
	public Map<String, Object> getSuperCutPriceOrderDtlList(HttpServletRequest request, Page page, Integer memberId) {
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
				if(memberId != null) {
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
	 * @Title addblackListManager   
	 * @Description TODO(拉黑)   
	 * @author pengl
	 * @date 2018年6月11日 下午3:11:04
	 */
	@RequestMapping("/superCutPriceOrder/addblackListManager.shtml")
	public ModelAndView addblackListManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceOrder/addBlackList");
		m.addObject("memberId", request.getParameter("memberId"));
		return m;
	}
	
	/**
	 * 
	 * @Title addblackList   
	 * @Description TODO(拉黑)  
	 * @author pengl
	 * @date 2018年6月11日 下午3:10:24
	 */
	@RequestMapping("/superCutPriceOrder/addblackList.shtml")
	public ModelAndView addblackList(HttpServletRequest request, BlackList blackList) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			blackList.setBlackDate(date);
			if(!StringUtil.isEmpty(request.getParameter("blackToDate"))) {
				blackList.setBlackToDate(sdf.parse(request.getParameter("blackToDate")));
			}
			blackList.setCreateBy(staffID);
			blackList.setCreateDate(date);
			blackListService.insertSelective(blackList);
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.JSON_AJAX_ERROR.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);
		return new ModelAndView(rtPage, resMap); 
	}
	
	
	/**
	 * 
	 * @Title superCutPriceOrderStatisticsManager   
	 * @Description TODO(邀请免费拿数据统计)   
	 * @author pengl
	 * @date 2018年6月12日 下午2:40:45
	 */
	@RequestMapping("/superCutPriceOrder/superCutPriceOrderStatisticsManager.shtml")
	public ModelAndView superCutPriceOrderStatisticsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/superCutPriceOrder/superCutPriceOrderStatisticsList");
		m.addObject("dateFlag", "day");
		return m;
	}
	
	/**
	 * 
	 * @Title superCutPriceOrderStatisticsList   
	 * @Description TODO(邀请免费拿数据统计)   
	 * @author pengl
	 * @date 2018年6月12日 下午2:40:49
	 */
	@ResponseBody
	@RequestMapping("/superCutPriceOrder/superCutPriceOrderStatisticsList.shtml")
	public Map<String, Object> superCutPriceOrderStatisticsList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			List<String> dateList = new ArrayList<String>();
			Date date = new Date();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			String dateFlag = "";
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
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
				dateFlag = "day";
			}else {
				if("day".equals(request.getParameter("dateFlag"))) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
				}else if("month".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH, 0);
					dateList = DateUtil.getBetweenDates(sdf.parse(sdf.format(cal.getTime())), sdf.parse(sdf.format(date)), sdf, "month");
				}else if("year".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -1);
					String beginYear = sdf.format(cal.getTime());
					String endYear = sdf.format(date);
					dateList.add(beginYear);
					dateList.add(endYear);
				}
				dateFlag = request.getParameter("dateFlag");
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
			dataList = cutPriceOrderService.cutPriceOrderNewStatisticsList(dates, "8", dateFlag);
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
	 * @Title exportSuperCutPriceOrderStatistics   
	 * @Description TODO(导出)   
	 * @author pengl
	 * @date 2018年6月13日 下午3:35:25
	 */
	@RequestMapping("/superCutPriceOrder/exportSuperCutPriceOrderStatistics.shtml")
	public void exportSuperCutPriceOrderStatistics(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> dateList = new ArrayList<String>();
			Date date = new Date();
			SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
			String dateFlag = "";
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
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
				dateFlag = "day";
			}else {
				if("day".equals(request.getParameter("dateFlag"))) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.DAY_OF_MONTH, 1);
					dateList = DateUtil.getBetweenDates(sdfDay.parse(sdfDay.format(cal.getTime())), sdfDay.parse(sdfDay.format(date)), sdfDay, "day");
				}else if("month".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.MONTH, 0);
					dateList = DateUtil.getBetweenDates(sdf.parse(sdf.format(cal.getTime())), sdf.parse(sdf.format(date)), sdf, "month");
				}else if("year".equals(request.getParameter("dateFlag"))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.YEAR, -1);
					String beginYear = sdf.format(cal.getTime());
					String endYear = sdf.format(date);
					dateList.add(beginYear);
					dateList.add(endYear);
				}
				dateFlag = request.getParameter("dateFlag");
			}
			List<Map<String, Object>> dataList = cutPriceOrderService.cutPriceOrderNewStatisticsList(dateList, "8", dateFlag);
			String[] titles = {"时间","发起人数","被邀请人数","已下载APP人数","被邀请人下单人数","下载率","购买率","发起享免单数","审核通过的享免单数","平台支出","成功率","被邀请人订单数","被邀请人消费金额","平台收益","消费率"};
			ExcelBean excelBean = new ExcelBean("邀请免费拿数据统计.xls", "邀请免费拿数据统计", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> map : dataList) {
				String[] data = {
						map.get("date").toString(),
						map.get("count_member").toString(),
						map.get("sum_cut_price_order_dtl").toString(),
						map.get("sum_cut_price_order_dtl_status").toString(),
						map.get("sum_combine_order_member").toString(),
						map.get("download_rate").toString(),
						map.get("buy_rate").toString(),
						map.get("count_cut_price_order").toString(),
						map.get("count_cut_price_order_audit_status").toString(),
						map.get("sum_total_platform_preferential").toString(),
						map.get("success_rate").toString(),
						map.get("sum_combine_order").toString(),
						map.get("sum_total_pay_amount").toString(),
						map.get("sum_commission_amount").toString(),
						map.get("expense_rate").toString()
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
