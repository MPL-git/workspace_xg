package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.entity.AppLoginDistinctLog;
import com.jf.entity.AppLoginDistinctLogExample;
import com.jf.service.AppLoginDistinctLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2019-11-21 下午 4:08
 */
@RegCondition
@Component
public class AppLoginDistinctLogTask {

	private static Logger logger = LoggerFactory.getLogger(AppLoginDistinctLogTask.class);

	@Autowired
	private AppLoginDistinctLogService appLoginDistinctLogService;

	/**
	 * 留存统计
	 */
	@Scheduled(cron = "0 30 4 * * ?")
	public synchronized void insertAppLoginDistinctLog() {

		logger.info("留存统计start：" + DateUtil.getStandardDateTime());

		try {
			Date createDateBegin = null;
			Date createDateEnd = DateUtil.getDateAfterAndEndTime(new Date(), -1);
			AppLoginDistinctLogExample appLoginDistinctLogExample = new AppLoginDistinctLogExample();
			appLoginDistinctLogExample.createCriteria().andDelFlagEqualTo("0");
			appLoginDistinctLogExample.setOrderByClause(" id desc");
			appLoginDistinctLogExample.setLimitStart(0);
			appLoginDistinctLogExample.setLimitSize(1);
			List<AppLoginDistinctLog> appLoginDistinctLogList = appLoginDistinctLogService.selectByExample(appLoginDistinctLogExample);
			if(appLoginDistinctLogList != null && appLoginDistinctLogList.size() > 0 ) {
				createDateBegin = DateUtil.getDateAfterAndBeginTime(appLoginDistinctLogList.get(0).getDistinctDate(), 1);
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("createDateBegin", createDateBegin);
			paramMap.put("createDateEnd", createDateEnd);
			appLoginDistinctLogService.insertSelect(paramMap);
		} catch (Exception e) {
			logger.error("留存统计 Exception:", e);
		}

		logger.info("留存统计end：" + DateUtil.getStandardDateTime());

	}

}
