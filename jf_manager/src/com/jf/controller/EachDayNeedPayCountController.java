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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gzs.common.utils.StringUtil;
import com.jf.entity.OrderDtlCustom;
import com.jf.service.MchtInfoService;
import com.jf.service.OrderDtlService;

@Controller
public class EachDayNeedPayCountController extends BaseController {
	
	@Resource
	private OrderDtlService orderDtlService;
	
	@Resource
	private MchtInfoService mchtInfoService;

	private static final long serialVersionUID = 1L;
	
	/**
	 * 每日应付统计列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/eachDayNeedPayCount/list.shtml")
	public ModelAndView list(HttpServletRequest request) {
		String rtPage = "/eachDayNeedPayCount/list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String payDateEnd = df.format(new Date());
		String payDateBegin = payDateEnd.substring(0,7)+"-01";
		resMap.put("payDateEnd", payDateEnd);
		resMap.put("payDateBegin", payDateBegin);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 每日应付统计列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/eachDayNeedPayCount/data.shtml")
	@ResponseBody
	public Map<String, Object> data(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String payDateBegin = request.getParameter("pay_date_begin");
			String payDateEnd = request.getParameter("pay_date_end");
			String mchtCode = request.getParameter("mchtCode");
			HashMap<String,String> paramMap = new HashMap<String,String>();
			if(!StringUtil.isEmpty(payDateBegin)){
				paramMap.put("payDateBegin",payDateBegin+" 00:00:00");
			}else{
				payDateEnd = df.format(new Date());
				payDateBegin = payDateEnd.substring(0,7)+"-01 00:00:00";
				paramMap.put("payDateBegin",payDateBegin);
			}
			if(!StringUtil.isEmpty(payDateEnd)){
				paramMap.put("payDateEnd",payDateEnd+" 23:59:59");
			}else{
				payDateEnd = df.format(new Date());
				paramMap.put("payDateEnd",payDateEnd);
			}
			if(!StringUtil.isEmpty(mchtCode)){
				paramMap.put("mchtCode",mchtCode);
			}
			List<OrderDtlCustom> orderDtlCustoms = orderDtlService.eachDayNeedPayCount(paramMap);
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
					orderDtlCustom.setTotalSaleAmount(new BigDecimal(0));
					orderDtlCustom.setPopTotalSettleAmount(new BigDecimal(0));
					orderDtlCustom.setPoolTotalSettleAmount(new BigDecimal(0));
					orderDtlCustom.setTotalSettleAmount(new BigDecimal(0));
					orderDtlCustom.setTotalQuantity(0);
					orderDtlCustom.setPopTotalQuantity(0);
					orderDtlCustom.setPoolTotalQuantity(0);
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
			resMap.put("Rows", orderDtlCustoms);
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
}
