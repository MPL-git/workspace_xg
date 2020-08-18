package com.jf.controller;

import com.gzs.common.utils.StringUtil;
import com.jf.common.utils.DateUtil;
import com.jf.service.AppLoginDistinctLogService;
import com.jf.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Pengl
 * @create 2019-11-22 上午 9:26
 */
@Controller
public class AppLoginDistinctLogController extends BaseController {

	@Autowired
	private AppLoginDistinctLogService appLoginDistinctLogService;

	@RequestMapping("/appLoginDistinctLog/memberKeepReport.shtml")
	public ModelAndView memberKeepReport(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/appLoginDistinctLog/memberKeepReport");
		String dateStatus = null;
		String distinctDate = null;
		String sexType = null;
		String keepStatus = "7|day";
		if(!StringUtil.isEmpty(request.getParameter("dateStatus")) ) {
			dateStatus = request.getParameter("dateStatus");
		}else {
			dateStatus = "begin";
		}
		if(!StringUtil.isEmpty(request.getParameter("distinctDate")) ) {
			distinctDate = request.getParameter("distinctDate");
		}else {
			distinctDate = DateUtil.getMonthFirstDay();
		}
		m.addObject("distinctDate", distinctDate);
		if(!StringUtil.isEmpty(request.getParameter("sexType")) ) {
			sexType = request.getParameter("sexType");
		}
		if(!StringUtil.isEmpty(request.getParameter("keepStatus")) ) {
			keepStatus = request.getParameter("keepStatus");
		}
		String[] keeyStatusStr = keepStatus.split("\\|");
		keepStatus = keeyStatusStr[1];
		m.addObject("status", keepStatus);
		String resultNum = keeyStatusStr[0];
		List<Integer> resultNumList = new ArrayList<>();
		for (int i = 1; i <= Integer.parseInt(resultNum); i++) {
			resultNumList.add(i);
		}
		m.addObject("resultNumList", resultNumList);
		if("week".equals(keepStatus) && "end".equals(dateStatus) ) {
			distinctDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(DateUtil.getDateByFormat(distinctDate), -Integer.parseInt(resultNum)*7-6), "yyyy-MM-dd");
		}
		if("day".equals(keepStatus) && "end".equals(dateStatus) ) {
			distinctDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(DateUtil.getDateByFormat(distinctDate), -Integer.parseInt(resultNum)), "yyyy-MM-dd");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("distinctDate", distinctDate);
		paramMap.put("sexType", sexType);
		paramMap.put("keepStatus", keepStatus);
		paramMap.put("resultNum", resultNum);
		Map<String, Object> returnMap = appLoginDistinctLogService.memberKeepReport(paramMap);
		m.addObject("list", returnMap.get("list"));
		m.addObject("dataXList", returnMap.get("dataXList"));
		m.addObject("memberList", returnMap.get("memberList"));
		m.addObject("rateList", returnMap.get("rateList"));
		m.addObject("dateStatus", dateStatus);
		return m;
	}

	@RequestMapping("/appLoginDistinctLog/selectMemberKeepListManager.shtml")
	public ModelAndView selectMemberKeepListManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/appLoginDistinctLog/selectMemberKeepList");
		return m;
	}

	@ResponseBody
	@RequestMapping("/appLoginDistinctLog/selectMemberKeepList.shtml")
	public Map<String, Object> selectMemberKeepList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String distinctDate = request.getParameter("distinctDate");
			String sexType = request.getParameter("sexType");
			String status = request.getParameter("status");
			String resultNum = request.getParameter("resultNum");
			if(!StringUtil.isEmpty(distinctDate) && !StringUtil.isEmpty(status) ) {
				paramMap.put("distinctDate", distinctDate);
				paramMap.put("status", status);
				if(!StringUtil.isEmpty(resultNum) ) {
					paramMap.put("resultNum", resultNum);
				}
				if(!StringUtil.isEmpty(sexType) ) {
					paramMap.put("sexType", sexType);
				}

				if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
					paramMap.put("memberId", request.getParameter("memberId"));
				}
				if(!StringUtil.isEmpty(request.getParameter("memberNick")) ) {
					paramMap.put("memberNick", request.getParameter("memberNick"));
				}
				if(!StringUtil.isEmpty(request.getParameter("memberSexType")) ) {
					paramMap.put("memberSexType", request.getParameter("memberSexType"));
				}
				if(!StringUtil.isEmpty(request.getParameter("orderDateBegin")) ) {
					paramMap.put("orderDateBegin", request.getParameter("orderDateBegin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("orderDateEnd")) ) {
					paramMap.put("orderDateEnd", request.getParameter("orderDateEnd")+" 23:59:59");
				}
				String yearBegin = request.getParameter("yearBegin");
				if(!StringUtil.isEmpty(yearBegin) ) {
					paramMap.put("yearDateBegin", DateUtil.formatDateByFormat(DateUtil.getYearsAgo(new Date(), -Integer.parseInt(yearBegin)), "yyyy")+"-01-01");
				}
				String yearEnd = request.getParameter("yearEnd");
				if(!StringUtil.isEmpty(yearEnd) ) {
					paramMap.put("yearDateEnd", DateUtil.formatDateByFormat(DateUtil.getYearsAgo(DateUtil.getYearLast(Integer.parseInt(DateUtil.formatDateByFormat(new Date(), "yyyy"))), -Integer.parseInt(yearEnd)), "yyyy-MM-dd"));
				}
				if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) ) {
					paramMap.put("loginDateBegin", request.getParameter("loginDateBegin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
					paramMap.put("loginDateEnd", request.getParameter("loginDateEnd")+" 23:59:59");
				}
				if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
					paramMap.put("createDateBegin", request.getParameter("createDateBegin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
					paramMap.put("createDateEnd", request.getParameter("createDateEnd")+" 23:59:59");
				}

				paramMap.put("limitStart", page.getLimitStart());
				paramMap.put("limitSize", page.getLimitSize());
				dataList = appLoginDistinctLogService.selectMemberKeepList(paramMap);
				totalCount = appLoginDistinctLogService.selectMemberKeepCount(paramMap);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}


	@RequestMapping("/appLoginDistinctLog/memberRepurchaseReport.shtml")
	public ModelAndView memberRepurchaseReport(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/appLoginDistinctLog/memberRepurchaseReport");
		String dateStatus = null;
		String payDate = null;
		String sexType = null;
		String keepStatus = "8|week";
		if(!StringUtil.isEmpty(request.getParameter("dateStatus")) ) {
			dateStatus = request.getParameter("dateStatus");
		}else {
			dateStatus = "begin";
		}
		if(!StringUtil.isEmpty(request.getParameter("payDate")) ) {
			payDate = request.getParameter("payDate");
		}else {
			payDate = DateUtil.getMonthFirstDay();
		}
		m.addObject("payDate", payDate);
		if(!StringUtil.isEmpty(request.getParameter("sexType")) ) {
			sexType = request.getParameter("sexType");
		}
		if(!StringUtil.isEmpty(request.getParameter("keepStatus")) ) {
			keepStatus = request.getParameter("keepStatus");
		}
		String[] keeyStatusStr = keepStatus.split("\\|");
		keepStatus = keeyStatusStr[1];
		m.addObject("status", keepStatus);
		String resultNum = keeyStatusStr[0];
		List<Integer> resultNumList = new ArrayList<>();
		for (int i = 1; i <= Integer.parseInt(resultNum); i++) {
			resultNumList.add(i);
		}
		m.addObject("resultNumList", resultNumList);
		if("week".equals(keepStatus) && "end".equals(dateStatus) ) {
			payDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(DateUtil.getDateByFormat(payDate), -Integer.parseInt(resultNum)*7-6), "yyyy-MM-dd");
		}
		if("month".equals(keepStatus) && "end".equals(dateStatus) ) {
			payDate = DateUtil.formatDateByFormat(DateUtil.getDateAfter(DateUtil.getMonthsAgo(DateUtil.getDateByFormat(payDate), -Integer.parseInt(resultNum)-1), 1), "yyyy-MM-dd");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payDate", payDate);
		paramMap.put("sexType", sexType);
		paramMap.put("keepStatus", keepStatus);
		paramMap.put("resultNum", resultNum);
		Map<String, Object> returnMap = appLoginDistinctLogService.memberRepurchaseReport(paramMap);
		m.addObject("list", returnMap.get("list"));
		m.addObject("dataXList", returnMap.get("dataXList"));
		m.addObject("memberList", returnMap.get("memberList"));
		m.addObject("rateList", returnMap.get("rateList"));
		m.addObject("dateStatus", dateStatus);
		return m;
	}

	@RequestMapping("/appLoginDistinctLog/selectMemberRepurchaseListManager.shtml")
	public ModelAndView selectMemberRepurchaseListManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/appLoginDistinctLog/selectMemberRepurchaseList");
		return m;
	}

	@ResponseBody
	@RequestMapping("/appLoginDistinctLog/selectMemberRepurchaseList.shtml")
	public Map<String, Object> selectMemberRepurchaseList(HttpServletRequest request, Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = null;
		Integer totalCount = 0;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String payDate = request.getParameter("payDate");
			String sexType = request.getParameter("sexType");
			String status = request.getParameter("status");
			String resultNum = request.getParameter("resultNum");
			if(!StringUtil.isEmpty(payDate) && !StringUtil.isEmpty(status) ) {
				paramMap.put("payDate", payDate);
				paramMap.put("status", status);
				if(!StringUtil.isEmpty(resultNum) ) {
					paramMap.put("resultNum", resultNum);
				}
				if(!StringUtil.isEmpty(sexType) ) {
					paramMap.put("sexType", sexType);
				}

				if(!StringUtil.isEmpty(request.getParameter("memberId")) ) {
					paramMap.put("memberId", request.getParameter("memberId"));
				}
				if(!StringUtil.isEmpty(request.getParameter("memberNick")) ) {
					paramMap.put("memberNick", request.getParameter("memberNick"));
				}
				if(!StringUtil.isEmpty(request.getParameter("memberSexType")) ) {
					paramMap.put("memberSexType", request.getParameter("memberSexType"));
				}
				if(!StringUtil.isEmpty(request.getParameter("orderDateBegin")) ) {
					paramMap.put("orderDateBegin", request.getParameter("orderDateBegin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("orderDateEnd")) ) {
					paramMap.put("orderDateEnd", request.getParameter("orderDateEnd")+" 23:59:59");
				}
				String yearBegin = request.getParameter("yearBegin");
				if(!StringUtil.isEmpty(yearBegin) ) {
					paramMap.put("yearDateBegin", DateUtil.formatDateByFormat(DateUtil.getYearsAgo(new Date(), -Integer.parseInt(yearBegin)), "yyyy")+"-01-01");
				}
				String yearEnd = request.getParameter("yearEnd");
				if(!StringUtil.isEmpty(yearEnd) ) {
					paramMap.put("yearDateEnd", DateUtil.formatDateByFormat(DateUtil.getYearsAgo(DateUtil.getYearLast(Integer.parseInt(DateUtil.formatDateByFormat(new Date(), "yyyy"))), -Integer.parseInt(yearEnd)), "yyyy-MM-dd"));
				}
				if(!StringUtil.isEmpty(request.getParameter("loginDateBegin")) ) {
					paramMap.put("loginDateBegin", request.getParameter("loginDateBegin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("loginDateEnd")) ) {
					paramMap.put("loginDateEnd", request.getParameter("loginDateEnd")+" 23:59:59");
				}
				if(!StringUtil.isEmpty(request.getParameter("createDateBegin")) ) {
					paramMap.put("createDateBegin", request.getParameter("createDateBegin")+" 00:00:00");
				}
				if(!StringUtil.isEmpty(request.getParameter("createDateEnd")) ) {
					paramMap.put("createDateEnd", request.getParameter("createDateEnd")+" 23:59:59");
				}

				paramMap.put("limitStart", page.getLimitStart());
				paramMap.put("limitSize", page.getLimitSize());
				dataList = appLoginDistinctLogService.selectMemberRepurchaseList(paramMap);
				totalCount = appLoginDistinctLogService.selectMemberRepurchaseCount(paramMap);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resMap.put("Rows", dataList);
		resMap.put("Total", totalCount);
		return resMap;
	}



}
