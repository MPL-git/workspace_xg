package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberPvMiddleLog;
import com.jf.entity.MemberPvMiddleLogExample;
import com.jf.service.MemberPvMiddleLogSerivce;
import com.jf.service.MemberPvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FlowAppDataCotroller {

	private static Logger logger = LoggerFactory.getLogger(FlowAppDataCotroller.class);
	
	@Autowired
	private MemberPvService memberPvService;
	
	@Autowired
	private MemberPvMiddleLogSerivce memberPvMiddleLogSerivce;
	
	@ResponseBody
    @RequestMapping("/flowAppDate/flowParseData")
	public synchronized ResponseMsg flowParseData(HttpServletRequest request) {
		logger.info(DateUtil.getStandardDateTime()+"手动调用流量解析与统计:start");
		String batchDate = request.getParameter("batchDate");
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		if(!StringUtil.isEmpty(batchDate) ) {
			responseMsg = flowParseDataCommon(batchDate);
		}else {
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg("手动流量解析与统计错误：请传参batchDate具体日期，格式yyyy-MM-dd。");
		}
		logger.info(DateUtil.getStandardDateTime()+"手动流量解析与统计:end");
		return responseMsg;
	}
	
	@ResponseBody
	@RequestMapping("/flowAppDate/flowParseDataScope")
	public synchronized ResponseMsg flowParseDataScope(HttpServletRequest request) {
		logger.info(DateUtil.getStandardDateTime()+"手动流量解析与统计:start");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			if(!StringUtil.isEmpty(beginDate) && !StringUtil.isEmpty(endDate) ) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				List<String>  dateList = DateUtil.getBetweenDates(sdf.parse(beginDate), sdf.parse(endDate), sdf, "day");
				for (String batchDate : dateList) {
					responseMsg = flowParseDataCommon(batchDate);
				}
			}else {
				responseMsg.setReturnCode(ResponseMsg.ERROR);
				responseMsg.setReturnMsg("手动流量解析与统计错误：请传参beginDate具体开始日期与endDate具体结束日期，格式yyyy-MM-dd。");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info(DateUtil.getStandardDateTime()+"手动流量解析与统计:end");
		return responseMsg;
	}
	
	public ResponseMsg flowParseDataCommon(String batchDate) {
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		//日志记录
		MemberPvMiddleLogExample memberPvMiddleLogExample = new MemberPvMiddleLogExample();
		memberPvMiddleLogExample.createCriteria().andDelFlagEqualTo("0").andBatchDateEqualTo(batchDate);
		memberPvMiddleLogExample.setLimitSize(1);
		List<MemberPvMiddleLog> memberPvMiddleLogList = memberPvMiddleLogSerivce.selectByExample(memberPvMiddleLogExample);
		MemberPvMiddleLog memberPvMiddleLog = null;
		MemberPvMiddleLog parsePvMiddleLog = new MemberPvMiddleLog();
		MemberPvMiddleLog statisticsPvMiddleLog = new MemberPvMiddleLog();
		if(memberPvMiddleLogList != null && memberPvMiddleLogList.size() > 0) {
			memberPvMiddleLog = memberPvMiddleLogList.get(0);
			if(!memberPvMiddleLogList.get(0).getParseType().equals("1")) {
				memberPvMiddleLog.setParseType("0");
				memberPvMiddleLog.setParseStartDate(DateUtil.getDate());
				memberPvMiddleLog.setParseEndDate(null);
				memberPvMiddleLog.setUpdateDate(DateUtil.getDate());
				memberPvMiddleLogSerivce.updateByPrimaryKey(memberPvMiddleLog);
			}
		}else {
			memberPvMiddleLog = new MemberPvMiddleLog();
			memberPvMiddleLog.setBatchDate(batchDate);
			memberPvMiddleLog.setParseType("0");
			memberPvMiddleLog.setParseStartDate(DateUtil.getDate());
			memberPvMiddleLog.setDelFlag("0");
			memberPvMiddleLogSerivce.insertSelective(memberPvMiddleLog);
		}
		parsePvMiddleLog.setId(memberPvMiddleLog.getId());
		statisticsPvMiddleLog.setId(memberPvMiddleLog.getId());
		boolean flag = true;
		if(!memberPvMiddleLog.getParseType().equals("1")) {
			try {
				//数据解析
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date parseDate = sdf.parse(batchDate+" 00:00:00") ;
				
				Date beginDate = DateUtil.getDateAfterAndBeginTime(parseDate, 0);
				Date endDate = DateUtil.getDateAfterHour(beginDate, 1);
				for(int i=0;i<24;i++) {
					if(i!=0){
						beginDate= DateUtil.getDateAfterHour(beginDate, 1);
						endDate= DateUtil.getDateAfterHour(endDate, 1);
					}
					memberPvService.flowParseData(beginDate,endDate);
				}
				parsePvMiddleLog.setParseType("1");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("数据解析错误************>"+e.getMessage());
				flag = false;
				responseMsg.setReturnCode(ResponseMsg.ERROR);
				responseMsg.setReturnMsg("数据解析错误******pvId==========>"+e.getMessage());
				responseMsg.setReturnData(e.getMessage());
				parsePvMiddleLog.setParseType("2");
			}
			parsePvMiddleLog.setParseEndDate(DateUtil.getDate());
			parsePvMiddleLog.setUpdateDate(DateUtil.getDate());
			memberPvMiddleLogSerivce.updateByPrimaryKeySelective(parsePvMiddleLog);
		}
		if(flag && (memberPvMiddleLog.getStatisticsType()==null || !memberPvMiddleLog.getStatisticsType().equals("1"))) {
			statisticsPvMiddleLog.setStatisticsType("0");
			statisticsPvMiddleLog.setStatisticsStartDate(DateUtil.getDate());
			statisticsPvMiddleLog.setUpdateDate(DateUtil.getDate());
			memberPvMiddleLogSerivce.updateByPrimaryKeySelective(statisticsPvMiddleLog);
			try {
				//流量统计
				memberPvService.flowStatisticsData(batchDate, false);
				statisticsPvMiddleLog.setStatisticsType("1");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("流量统计错误************>"+e.getMessage());
				flag = false;
				responseMsg.setReturnCode(ResponseMsg.ERROR);
				responseMsg.setReturnMsg("流量统计错误******pvId==========>"+e.getMessage());
				responseMsg.setReturnData(e.getMessage());
				statisticsPvMiddleLog.setStatisticsType("2");
			}
			statisticsPvMiddleLog.setStatisticsEndDate(DateUtil.getDate());
			statisticsPvMiddleLog.setUpdateDate(DateUtil.getDate());
			memberPvMiddleLogSerivce.updateByPrimaryKeySelective(statisticsPvMiddleLog);
		}
		if(flag) {
			try {
				//间隔7天流量数据统计覆盖
				memberPvService.flowStatisticsData(DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -8), "yyyy-MM-dd"), true);
			} catch (Exception e) {
				e.printStackTrace();
				responseMsg.setReturnCode(ResponseMsg.ERROR);
				responseMsg.setReturnMsg("间隔7天流量数据统计覆盖错误******pvId==========>"+e.getMessage());
				responseMsg.setReturnData(e.getMessage());
			}
		}
		return responseMsg;
	}
	
	
	/*@ResponseBody
	@RequestMapping("/flowAppDate/deletePV")
	public ResponseMsg deletePV(HttpServletRequest request) {
		System.out.println("清除有关流量统计数据==========================>"+DateUtil.getStandardDateTime());
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			memberPvService.deletePV();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(ResponseMsg.ERROR_MSG);
		}
		return responseMsg;
	}*/
	
}
