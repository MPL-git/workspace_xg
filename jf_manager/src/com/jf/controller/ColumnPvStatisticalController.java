package com.jf.controller;

import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtils;
import com.jf.entity.SysStatus;
import com.jf.service.ColumnPvStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Pengl
 * @create 2019-10-08 下午 4:27
 */
@Controller
public class ColumnPvStatisticalController extends BaseController {

    @Autowired
    private ColumnPvStatisticalService columnPvStatisticalService;

    @RequestMapping("/columnPvStatistical/columnPvStatisticalDayManager.shtml")
    public ModelAndView columnPvStatisticalDayManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/columnPvStatistical/columnPvStatisticalDayList");
        m.addObject("statisticalDateStart", DateUtil.getMonthFirstDay());
        m.addObject("statisticalDateEnd", DateUtil.formatDateByFormat(DateUtil.getYesterDayDate(), "yyyy-MM-dd"));
        m.addObject("columnTypeList", DataDicUtil.getStatusList("BU_MEMBER_PV", "COLUMN_TYPE"));
        return m;
    }

    @ResponseBody
    @RequestMapping("/columnPvStatistical/columnPvStatisticalDayList.shtml")
    public Map<String, Object> columnPvStatisticalDayList(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("statisticalDateStart", request.getParameter("statisticalDateStart"));
			paramMap.put("statisticalDateEnd", request.getParameter("statisticalDateEnd"));
            if(!StringUtils.isEmpty(request.getParameter("columnType")) ) {
                paramMap.put("columnType", request.getParameter("columnType"));
            }
            if(!StringUtils.isEmpty(request.getParameter("columnId")) ) {
                paramMap.put("columnId", request.getParameter("columnId"));
            }
            paramMap.put("orderBy", "statistical_date");
			List<Map<String, Object>> columnPvStatisticalDayList = columnPvStatisticalService.selectColumnPvStatisticalDayList(paramMap);
			List<String> betweenDates = DateUtil.getBetweenDates(DateUtil.StringToDate(request.getParameter("statisticalDateStart")),
					DateUtil.StringToDate(request.getParameter("statisticalDateEnd")),
					new SimpleDateFormat("yyyy-MM-dd"), "day");
			for(String betweenDate : betweenDates ) {
				boolean flag = true;
				for(Map<String, Object> map : columnPvStatisticalDayList ) {
					if(betweenDate.equals(map.get("statistical_date").toString()) ) {
						flag = false;
						dataList.add(map);
					}
				}
				if(flag) {
					Map<String, Object> columnPvStatisticalDay = new HashMap<>();
                    columnPvStatisticalDay.put("statistical_date", betweenDate);
                    columnPvStatisticalDay.put("total_visitor_count", "0");
                    columnPvStatisticalDay.put("total_visitor_count_tourist", "0");
                    columnPvStatisticalDay.put("total_pv_count", "0");
                    columnPvStatisticalDay.put("total_pv_count_tourist", "0");
                    columnPvStatisticalDay.put("shopping_cart_count", "0");
                    columnPvStatisticalDay.put("shopping_cart_rate", "0.00%");
                    columnPvStatisticalDay.put("sub_product_count", "0");
                    columnPvStatisticalDay.put("sub_product_rate", "0.00%");
                    columnPvStatisticalDay.put("pay_product_count", "0");
                    columnPvStatisticalDay.put("pay_product_rate", "0.00%");
                    columnPvStatisticalDay.put("pay_amount_count", "0");
                    columnPvStatisticalDay.put("new_member_count", "0");
					dataList.add(columnPvStatisticalDay);
				}
			}
            dataList.add(columnPvStatisticalService.selectColumnPvStatisticalDaySum(paramMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        return resMap;
    }

    @RequestMapping("/columnPvStatistical/columnPvStatisticalWeekManager.shtml")
    public ModelAndView columnPvStatisticalWeekManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/columnPvStatistical/columnPvStatisticalWeekList");
        m.addObject("statisticalDateStart", DateUtil.formatDateByFormat(DateUtil.getDateAfter(new Date(), -5*7), "yyyy-MM-dd"));
        m.addObject("statisticalDateEnd", DateUtil.formatDateByFormat(DateUtil.getYesterDayDate(), "yyyy-MM-dd"));
        m.addObject("columnTypeList", DataDicUtil.getStatusList("BU_MEMBER_PV", "COLUMN_TYPE"));
        return m;
    }

    @ResponseBody
    @RequestMapping("/columnPvStatistical/columnPvStatisticalWeekList.shtml")
    public Map<String, Object> columnPvStatisticalWeekList(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Map<String, Object>> dataList = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("statisticalDateStart", request.getParameter("statisticalDateStart"));
            paramMap.put("statisticalDateEnd", request.getParameter("statisticalDateEnd"));
            if(!StringUtils.isEmpty(request.getParameter("columnType")) ) {
                paramMap.put("columnType", request.getParameter("columnType"));
            }
            if(!StringUtils.isEmpty(request.getParameter("columnId")) ) {
                paramMap.put("columnId", request.getParameter("columnId"));
            }
            paramMap.put("orderBy", "statistical_date");
            dataList = columnPvStatisticalService.selectColumnPvStatisticalWeekList(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        return resMap;
    }

    @RequestMapping("/columnPvStatistical/columnPvStatisticalMonthManager.shtml")
    public ModelAndView columnPvStatisticalMonthManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/columnPvStatistical/columnPvStatisticalMonthList");
        m.addObject("statisticalDateStart", DateUtil.formatDateByFormat(DateUtil.getPreDate(new Date(), -4), "yyyy-MM")+"-01");
        m.addObject("statisticalDateEnd", DateUtil.formatDateByFormat(DateUtil.getYesterDayDate(), "yyyy-MM-dd"));
        m.addObject("columnTypeList", DataDicUtil.getStatusList("BU_MEMBER_PV", "COLUMN_TYPE"));
        return m;
    }

    @ResponseBody
    @RequestMapping("/columnPvStatistical/columnPvStatisticalMonthList.shtml")
    public Map<String, Object> columnPvStatisticalMonthList(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("statisticalDateStart", request.getParameter("statisticalDateStart"));
            paramMap.put("statisticalDateEnd", request.getParameter("statisticalDateEnd"));
            if(!StringUtils.isEmpty(request.getParameter("columnType")) ) {
                paramMap.put("columnType", request.getParameter("columnType"));
            }
            if(!StringUtils.isEmpty(request.getParameter("columnId")) ) {
                paramMap.put("columnId", request.getParameter("columnId"));
            }
            paramMap.put("orderBy", "statistical_date");
            List<Map<String, Object>> columnPvStatisticalMonthList = columnPvStatisticalService.selectColumnPvStatisticalMonthList(paramMap);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            List<String> betweenDates = DateUtil.getBetweenDates(sdf.parse(request.getParameter("statisticalDateStart")),
                    sdf.parse(request.getParameter("statisticalDateEnd")),
                    sdf, "month");
            for(String betweenDate : betweenDates ) {
                boolean flag = true;
                for(Map<String, Object> map : columnPvStatisticalMonthList ) {
                    if(betweenDate.equals(map.get("statistical_date_group").toString()) ) {
                        flag = false;
                        dataList.add(map);
                    }
                }
                if(flag) {
                    Map<String, Object> columnPvStatisticalMonth = new HashMap<>();
                    columnPvStatisticalMonth.put("statistical_date_group", betweenDate);
                    columnPvStatisticalMonth.put("total_visitor_count", "0");
                    columnPvStatisticalMonth.put("total_visitor_count_tourist", "0");
                    columnPvStatisticalMonth.put("total_pv_count", "0");
                    columnPvStatisticalMonth.put("total_pv_count_tourist", "0");
                    columnPvStatisticalMonth.put("shopping_cart_count", "0");
                    columnPvStatisticalMonth.put("shopping_cart_rate", "0.00%");
                    columnPvStatisticalMonth.put("sub_product_count", "0");
                    columnPvStatisticalMonth.put("sub_product_rate", "0.00%");
                    columnPvStatisticalMonth.put("pay_product_count", "0");
                    columnPvStatisticalMonth.put("pay_product_rate", "0.00%");
                    columnPvStatisticalMonth.put("pay_amount_count", "0");
                    columnPvStatisticalMonth.put("new_member_count", "0");
                    dataList.add(columnPvStatisticalMonth);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        return resMap;
    }

    @RequestMapping("/columnPvStatistical/columnPvStatisticalColumnTypeManager.shtml")
    public ModelAndView columnPvStatisticalColumnTypeManager(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("/columnPvStatistical/columnPvStatisticalColumnTypeList");
        m.addObject("statisticalDateStart", DateUtil.formatDateByFormat(DateUtil.getPreDate(new Date(), -4), "yyyy-MM")+"-01");
        m.addObject("statisticalDateEnd", DateUtil.formatDateByFormat(DateUtil.getYesterDayDate(), "yyyy-MM-dd"));
        return m;
    }

    @ResponseBody
    @RequestMapping("/columnPvStatistical/columnPvStatisticalColumnTypeList.shtml")
    public Map<String, Object> columnPvStatisticalColumnTypeList(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if(!StringUtils.isEmpty(request.getParameter("statisticalDateStart")) ) {
                paramMap.put("statisticalDateStart", request.getParameter("statisticalDateStart"));
            }
            if(!StringUtils.isEmpty(request.getParameter("statisticalDateEnd")) ) {
                paramMap.put("statisticalDateEnd", request.getParameter("statisticalDateEnd"));
            }
            paramMap.put("orderBy", "column_type");
            List<Map<String, Object>> columnPvStatisticalColumnTypeList = columnPvStatisticalService.selectColumnPvStatisticalColumnTypeList(paramMap);
            List<SysStatus> sysStatusList = DataDicUtil.getStatusList("BU_MEMBER_PV", "COLUMN_TYPE");
            for(SysStatus sysStatus : sysStatusList ) {
                boolean flag = true;
                for(Map<String, Object> map : columnPvStatisticalColumnTypeList ) {
                    if(sysStatus.getStatusDesc().equals(map.get("column_type").toString()) ) {
                        flag = false;
                        dataList.add(map);
                    }
                }
                if(flag) {
                    Map<String, Object> columnPvStatisticalMap = new HashMap<>();
                    columnPvStatisticalMap.put("column_type", sysStatus.getStatusDesc());
                    columnPvStatisticalMap.put("total_visitor_count", "0");
                    columnPvStatisticalMap.put("total_visitor_count_tourist", "0");
                    columnPvStatisticalMap.put("total_pv_count", "0");
                    columnPvStatisticalMap.put("total_pv_count_tourist", "0");
                    columnPvStatisticalMap.put("shopping_cart_count", "0");
                    columnPvStatisticalMap.put("shopping_cart_rate", "0.00%");
                    columnPvStatisticalMap.put("sub_product_count", "0");
                    columnPvStatisticalMap.put("sub_product_rate", "0.00%");
                    columnPvStatisticalMap.put("pay_product_count", "0");
                    columnPvStatisticalMap.put("pay_product_rate", "0.00%");
                    columnPvStatisticalMap.put("pay_amount_count", "0");
                    columnPvStatisticalMap.put("new_member_count", "0");
                    dataList.add(columnPvStatisticalMap);
                }
            }
            dataList.add(columnPvStatisticalService.selectColumnPvStatisticalColumnTypeSum(paramMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("Rows", dataList);
        return resMap;
    }

}
