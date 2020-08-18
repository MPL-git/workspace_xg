package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.SmsUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberPvMiddleLog;
import com.jf.entity.MemberPvMiddleLogExample;
import com.jf.entity.Sms;
import com.jf.service.MemberPvMiddleLogSerivce;
import com.jf.service.MemberPvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: FlowAppDataTask
 * @Description: (流量统计定时任务)
 * @author Pengl
 * @date 2019年5月31日 下午2:51:47
 */
@RegCondition
@Component
public class FlowAppDataTask {

	private static Logger logger = LoggerFactory.getLogger(FlowAppDataTask.class);
	
	@Autowired
	private MemberPvService memberPvService;
	
	@Autowired
	private MemberPvMiddleLogSerivce memberPvMiddleLogSerivce;

	/**
	 * 
	 * @MethodName: flowParseDataTask
	 * @Description: (流量解析与统计，每天一次)
	 * @author Pengl
	 * @date 2019年7月3日 下午2:46:00
	 */
	@Scheduled(cron="0 0 2 * * ?")
	public synchronized void flowParseDataTask() {
		logger.info(DateUtil.getStandardDateTime()+"流量解析与统计:start");
		String content = "";
		//日志记录
		MemberPvMiddleLogExample memberPvMiddleLogExample = new MemberPvMiddleLogExample();
		memberPvMiddleLogExample.createCriteria().andDelFlagEqualTo("0")
			.andBatchDateEqualTo(DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -1), "yyyy-MM-dd"));
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
			memberPvMiddleLog.setBatchDate(DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -1), "yyyy-MM-dd"));
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
				Date beginDate =  DateUtil.getDateAfterAndBeginTime(new Date(), -1);
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
				parsePvMiddleLog.setParseType("2");
				content = "您好！【流量数据解析】"+DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -1), "yyyy-MM-dd")+"日期的数据解析报错，请尽快修复BUG！";
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
				memberPvService.flowStatisticsData(DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -1), "yyyy-MM-dd"), false);
				statisticsPvMiddleLog.setStatisticsType("1");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("流量统计错误************>"+e.getMessage());
				flag = false;
				statisticsPvMiddleLog.setStatisticsType("2");
				content = "您好！【流量数据统计】"+DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -1), "yyyy-MM-dd")+"日期的数据统计报错，请尽快修复BUG！";
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
				logger.info("流量统计错误************>"+e.getMessage());
				content = "您好！【间隔7天流量数据统计覆盖】"+DateUtil.getFormatDate(DateUtil.getDateAfter(new Date(), -8), "yyyy-MM-dd")+"日期的数据统计报错，请尽快修复BUG！";
			}
		}
		if(!StringUtil.isEmpty(content)) {
			//报错短信通知
			Sms sms = new Sms();
			sms.setMobile("15006039376");
			sms.setContent(content);
			SmsUtil.sendSms(sms);
			logger.info(DateUtil.getStandardDateTime()+"流量解析与统计:报错短信通知");
		}
		logger.info(DateUtil.getStandardDateTime()+"流量解析与统计:end");
	}
	
	
}
