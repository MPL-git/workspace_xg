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
import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlCustomExample;
import com.jf.entity.CutPriceOrderDtlExample;
import com.jf.entity.StateCode;
import com.jf.service.BlackListService;
import com.jf.service.CutPriceOrderDtlService;
import com.jf.service.CutPriceOrderService;
import com.jf.vo.Page;

@SuppressWarnings("serial")
@Controller
public class CutPriceOrderController extends BaseController {

	@Autowired
	private CutPriceOrderService cutPriceOrderService;
	
	@Autowired
	private CutPriceOrderDtlService cutPriceOrderDtlService;
	
	@Autowired
	private BlackListService blackListService;
	
	/**
	 * 
	 * @Title cutPriceOrderManager   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月8日 下午2:12:21
	 */
	@RequestMapping("/cutPriceOrder/cutPriceOrderManager.shtml")
	public ModelAndView cutPriceOrderManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceOrder/getCutPriceOrderList");
		m.addObject("statusList", DataDicUtil.getTableStatus("BU_CUT_PRICE_ORDER", "STATUS"));
		m.addObject("auditStatusList", DataDicUtil.getTableStatus("BU_CUT_PRICE_ORDER", "AUDIT_STATUS"));
		return m;
	}
	
	/**
	 * 
	 * @Title getCutPriceOrderList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月8日 下午2:12:24
	 */
	@ResponseBody
	@RequestMapping("/cutPriceOrder/getCutPriceOrderList.shtml")
	public Map<String, Object> getCutPriceOrderList(HttpServletRequest request, Page page, Integer memberId) {
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
			cutPriceOrderCustomCriteria.andCutPrice();
			cutPriceOrderCustomCriteria.andBlackListNull();
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
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月8日 下午2:20:17
	 */
	@RequestMapping("/cutPriceOrder/cutPriceOrderDtlManager.shtml")
	public ModelAndView cutPriceOrderDtlManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceOrder/getCutPriceOrderDtlList");
		String cutPriceOrderId = request.getParameter("cutPriceOrderId");
		if(!StringUtil.isEmpty(cutPriceOrderId)) {
			CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByPrimaryKey(Integer.parseInt(cutPriceOrderId));
			if(cutPriceOrder.getStatus().equals("2")) {
				Date beginDate = null;
				Date endDate = null;
				String returnDate = null;
				Integer totalCount = null;
				CutPriceOrderDtlExample cutPriceOrderDtlAExample = new CutPriceOrderDtlExample();
				cutPriceOrderDtlAExample.createCriteria().andDelFlagEqualTo("0")
					.andCutPriceOrderIdEqualTo(cutPriceOrder.getId());
				totalCount = cutPriceOrderDtlService.countByExample(cutPriceOrderDtlAExample);
				cutPriceOrderDtlAExample.setOrderByClause(" create_date asc");
				cutPriceOrderDtlAExample.setLimitStart(0);
				cutPriceOrderDtlAExample.setLimitSize(1);
				List<CutPriceOrderDtl> cutPriceOrderDtlList = cutPriceOrderDtlService.selectByExample(cutPriceOrderDtlAExample);
				beginDate = cutPriceOrderDtlList.get(0).getCreateDate();
				CutPriceOrderDtlExample cutPriceOrderDtlBExample = new CutPriceOrderDtlExample();
				cutPriceOrderDtlBExample.createCriteria().andDelFlagEqualTo("0")
					.andCutPriceOrderIdEqualTo(cutPriceOrder.getId());
				cutPriceOrderDtlBExample.setOrderByClause(" create_date desc");
				cutPriceOrderDtlBExample.setLimitStart(0);
				cutPriceOrderDtlBExample.setLimitSize(1);
				List<CutPriceOrderDtl> cutPriceOrderDtls = cutPriceOrderDtlService.selectByExample(cutPriceOrderDtlBExample);
				endDate = cutPriceOrderDtls.get(0).getCreateDate();
				returnDate = DateUtil.getHourAndMin(beginDate, endDate);
				m.addObject("returnDate", returnDate);
				m.addObject("totalCount", totalCount);
			}
		}
		m.addObject("cutPriceOrderId", cutPriceOrderId);
		return m;
	}
	
	/**
	 * 
	 * @Title getCutPriceOrderDtlList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月8日 下午2:20:21
	 */
	@ResponseBody
	@RequestMapping("/cutPriceOrder/getCutPriceOrderDtlList.shtml")
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
	 * @Title addblackListManager   
	 * @Description TODO(拉黑)   
	 * @author pengl
	 * @date 2018年6月8日 下午2:33:50
	 */
	@RequestMapping("/cutPriceOrder/addblackListManager.shtml")
	public ModelAndView addblackListManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceOrder/addBlackList");
		m.addObject("memberId", request.getParameter("memberId"));
		return m;
	}
	
	/**
	 * 
	 * @Title addblackList   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年6月8日 下午2:34:04
	 */
	@RequestMapping("/cutPriceOrder/addblackList.shtml")
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
	 * @Title cutPriceOrderStatisticsManager   
	 * @Description TODO(免费砍价拿数据统计)   
	 * @author pengl
	 * @date 2018年6月12日 下午2:40:45
	 */
	@RequestMapping("/cutPriceOrder/cutPriceOrderStatisticsManager.shtml")
	public ModelAndView signInCnfStatisticsManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceOrder/cutPriceOrderStatisticsList");
		m.addObject("dateFlag", "day");
		return m;
	}
	
	/**
	 * 
	 * @Title cutPriceOrderStatisticsList   
	 * @Description TODO(免费砍价拿数据统计)   
	 * @author pengl
	 * @date 2018年6月12日 下午2:40:49
	 */
	@ResponseBody
	@RequestMapping("/cutPriceOrder/cutPriceOrderStatisticsList.shtml")
	public Map<String, Object> cutPriceOrderStatisticsList(HttpServletRequest request, Page page) {
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
			dataList = cutPriceOrderService.cutPriceOrderStatisticsList(dates, "7", dateFlag);
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
	 * @Description TODO(导出)   
	 * @author pengl
	 * @date 2018年6月13日 下午3:35:25
	 */
	@RequestMapping("/cutPriceOrder/exportCutPriceOrderStatistics.shtml")
	public void exportCutPriceOrderStatistics(HttpServletRequest request, HttpServletResponse response) {
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
			List<Map<String, Object>> dataList = cutPriceOrderService.cutPriceOrderStatisticsList(dateList, "7", dateFlag);
			String[] titles = {"时间","发起人数","其中新用户人数","砍价成功订单数","砍价支出（售价）","购买人数","其中新用户购买人数","订单数","其中新用户产生订单数","预估收益","其中新用户产生预估收益","消费转化率%"};
			ExcelBean excelBean = new ExcelBean("免费砍价拿数据统计.xls", "免费砍价拿数据统计", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(Map<String, Object> map : dataList) {
				String[] data = {
						map.get("date").toString(),
						map.get("fq_count").toString(),
						map.get("new_member_count").toString(),
						map.get("combine_order_count").toString(),
						map.get("sale_price_sum").toString(),
						map.get("gm_count").toString(),
						map.get("xy_gm_count").toString(),
						map.get("dd_count").toString(),
						map.get("xy_dd_count").toString(),
						map.get("yg_commission_amount_sum").toString(),
						map.get("xy_yg_commission_amount_sum").toString(),
						map.get("con_percent").toString()
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
	
	/**
	 * 
	 * @Title updateAuditStatusManager   
	 * @Description TODO(审核)   
	 * @author pengl
	 * @date 2018年8月3日 下午5:26:43
	 */
	@RequestMapping("/cutPriceOrder/updateAuditStatusManager.shtml")
	public ModelAndView updateAuditStatusManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/cutPrice/cutPriceOrder/updateAuditStatus");
		if(!StringUtil.isEmpty(request.getParameter("ids"))) {
			String[] ids = request.getParameter("ids").split(",");
			m.addObject("ids", request.getParameter("ids"));
			m.addObject("idsLength", ids.length);
			if(ids.length == 1) {
				CutPriceOrder cutPriceOrder = cutPriceOrderService.selectByCustomPrimaryKey(Integer.parseInt(ids[0]));
				m.addObject("cutPriceOrder", cutPriceOrder);
			}
		}
		m.addObject("auditStatus", request.getParameter("auditStatus"));
		return m;
	}
	
	/**
	 * 
	 * @Title updateAuditStatus   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年8月3日 下午5:35:56
	 */
	@RequestMapping("/cutPriceOrder/updateAuditStatus.shtml")
	public ModelAndView updateAuditStatus(HttpServletRequest request, BlackList blackList) {
		String rtPage = "/success/success";
		Map<String, Object> resMap=new HashMap<String, Object>();
		String code = null;
		String msg = "";
		try {
			Date date = new Date();
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			List<CutPriceOrder> cutPriceOrderList = new ArrayList<CutPriceOrder>();
			if(!StringUtil.isEmpty(request.getParameter("ids"))) {
				String[] ids = request.getParameter("ids").split(",");
				for(String id : ids) {
					CutPriceOrder cutPriceOrder = new CutPriceOrder();
					cutPriceOrder.setId(Integer.parseInt(id));
					cutPriceOrder.setAuditStatus(request.getParameter("auditStatus"));
					if(!StringUtil.isEmpty(request.getParameter("auditRemarks"))) {
						cutPriceOrder.setAuditRemarks(request.getParameter("auditRemarks"));
					}
					cutPriceOrder.setUpdateBy(staffID);
					cutPriceOrder.setUpdateDate(date);
					cutPriceOrderList.add(cutPriceOrder);
				}
			}
			cutPriceOrderService.updateCutPriceOrderListSelective(cutPriceOrderList);
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
	
}
