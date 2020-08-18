package com.jf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.StringUtil;
import com.jf.service.MchtMonthTradeService;
import com.jf.service.MchtMonthlyCollectionsService;
import com.jf.service.MchtSettleCompareService;
import com.jf.service.MchtSettleOrderService;

@Controller
public class SettleController {
	
    @Resource
    private MchtSettleOrderService mchtSettleOrderService;
    
    @Resource
    private MchtMonthlyCollectionsService mchtMonthlyCollectionsService;
    
    @Resource
    private MchtSettleCompareService mchtSettleCompareService;
    
    @Resource
    private MchtMonthTradeService mchtMonthTradeService;

	@RequestMapping(value = "/settle/generateSettleSituation")
	@ResponseBody
	public synchronized ResponseMsg generateSettleSituation(HttpServletRequest request) {
		
		if(StringUtil.isEmpty(request.getParameter("settleMonth"))){
			return new ResponseMsg(ResponseMsg.ERROR, "请输入月份");
		}
		
		mchtSettleOrderService.generateSettleSituation(request.getParameter("settleMonth"));
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
    @RequestMapping(value = "/settle/generateMonthlyCollections")
    @ResponseBody
    public synchronized ResponseMsg generateMonthlyCollections(HttpServletRequest request) {
    	
    	if(StringUtil.isEmpty(request.getParameter("settleMonth"))){
    		return new ResponseMsg(ResponseMsg.ERROR, "请输入月份");
    	}
    	
    	mchtMonthlyCollectionsService.generateMonthlyCollections(request.getParameter("settleMonth"));
    	
    	return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }
	
	@RequestMapping(value = "/settle/generateSettleOrder")
	@ResponseBody
	public synchronized ResponseMsg generateSettleOrder(HttpServletRequest request) {
		
		if(StringUtil.isEmpty(request.getParameter("beginDate"))){
			return new ResponseMsg(ResponseMsg.ERROR, "请输入开始日期");
		}
		if(StringUtil.isEmpty(request.getParameter("endDate"))){
			return new ResponseMsg(ResponseMsg.ERROR, "请输入结束日期");
		}
		
		mchtSettleOrderService.generateSettleOrder(request.getParameter("beginDate"), request.getParameter("endDate"));;
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
    @RequestMapping(value = "/settle/generateMchtSettleCompare")
    @ResponseBody
    public synchronized ResponseMsg generateMchtSettleCompare(HttpServletRequest request) {
    	
    	if(StringUtil.isEmpty(request.getParameter("settleMonth"))){
    		return new ResponseMsg(ResponseMsg.ERROR, "请输入月份");
    	}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.SIMPLIFIED_CHINESE);
        Calendar firstCalendar = Calendar.getInstance();  
        try {
			firstCalendar.setTime(sdf.parse(request.getParameter("settleMonth")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        firstCalendar.set(Calendar.DATE, firstCalendar.getActualMinimum(firstCalendar.DATE));
        firstCalendar.set(Calendar.HOUR_OF_DAY, 0);
        firstCalendar.set(Calendar.MINUTE, 0);
        firstCalendar.set(Calendar.SECOND, 0);
        firstCalendar.set(Calendar.MILLISECOND, 0);
        
        
    	Calendar lastCalendar = Calendar.getInstance();  
        try {
        	lastCalendar.setTime(sdf.parse(request.getParameter("settleMonth")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	lastCalendar.set(Calendar.DATE, lastCalendar.getActualMaximum(lastCalendar.DATE));
    	lastCalendar.set(Calendar.HOUR_OF_DAY, 23);
    	lastCalendar.set(Calendar.MINUTE, 59);
    	lastCalendar.set(Calendar.SECOND, 59);
    	lastCalendar.set(Calendar.MILLISECOND, 999);
    	
    	System.out.println(firstCalendar.getTime());
    	System.out.println(lastCalendar.getTime());
    	
    	mchtSettleCompareService.generateMchtSettleCompare(firstCalendar.getTime(),lastCalendar.getTime());
    	
    	return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
    }
	
    /**
     * 
     * @Title generateMchtMonthTrade   
     * @Description TODO(生成每月商家月往来单)   
     * @author pengl
     * @date 2018年4月18日 下午6:04:06
     */
    @ResponseBody
    @RequestMapping("/settle/generateMchtMonthTrade")
	public synchronized ResponseMsg generateMchtMonthTrade(HttpServletRequest request) {
    	try {
    		String tradeMonth = request.getParameter("tradeMonth");
    		if(StringUtil.isEmpty(tradeMonth)){
	    		return new ResponseMsg(ResponseMsg.ERROR, "请输入月份");
	    	}
    		
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  		//获取月的最后一天
	  		Calendar ca = Calendar.getInstance();
	  		ca.setTime(format.parse(tradeMonth));
	  		ca.add(Calendar.MONTH, 1);
	  		ca.set(Calendar.DAY_OF_MONTH, 0);
	  		
	  		String startDate = tradeMonth+"-01 00:00:00";
	  		String endDate = sdf.format(ca.getTime())+" 23:59:59";
			mchtMonthTradeService.generateMchtMonthTrade(tradeMonth, startDate, endDate);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		
	}
	

}
