package com.jf.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.service.IntegralDailyStatisticsService;

@Controller
public class IntegralTaskController {

	@Autowired
    private IntegralDailyStatisticsService integralDailyStatisticsService;
	
	/**
	 * 
	 * @Title integralDailyStatisticTask   
	 * @Description TODO(积分每日汇总)   
	 * @author pengl
	 * @date 2018年4月26日 下午5:18:13
	 */
	@ResponseBody
    @RequestMapping("/integralTask/integralDailyStatisticTask")
	public ResponseMsg integralDailyStatisticTask(HttpServletRequest request) {
		
    	if(StringUtil.isEmpty(request.getParameter("statisticDate"))){
    		return new ResponseMsg(ResponseMsg.ERROR, "请输入日期");
    	}
		
    	integralDailyStatisticsService.dailyStatistic(request.getParameter("statisticDate"));
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	
	/**
	 * 
	 * @Title integralDailyStatisticTask  重新生成所有数据 
	 * @Description TODO(积分每日汇总)   
	 * @author pengl
	 * @date 2018年4月26日 下午5:18:13
	 */
	@ResponseBody
	@RequestMapping("/integralTask/totalCreateIntegralDailyStatisticTask")
	public ResponseMsg totalCreateIntegralDailyStatisticTask(HttpServletRequest request) {
		
		
		Date today=new Date();
		Date statisticDate=DateUtil.getDate("2017-07-01 23:59:59");
		while(statisticDate.getTime()<today.getTime()){
			integralDailyStatisticsService.dailyStatistic(DateUtil.getStandardDate(statisticDate));
			statisticDate=DateUtil.getDateAfter(statisticDate, 1);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
	}
	
	public static void main(String[] args) {
		Date statisticDate=DateUtil.getDate("2017-07-01 23:59:59");
		System.out.println(DateUtil.getStandardDateTime(DateUtil.getDateAfter(statisticDate, 1)));
	}
	 
	 
}
