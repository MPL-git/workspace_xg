package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.AppLoginDistinctLog;
import com.jf.entity.AppLoginDistinctLogExample;
import com.jf.service.AppLoginDistinctLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2019-11-21 下午 5:13
 */
@RegCondition
@Controller
public class AppLoginDistinctLogController {

	private static Logger logger = LoggerFactory.getLogger(AppLoginDistinctLogController.class);

	@Autowired
	private AppLoginDistinctLogService appLoginDistinctLogService;

	@ResponseBody
	@RequestMapping("/appLoginDistinctLog/insertAppLoginDistinctLog")
	public synchronized ResponseMsg insertAppLoginDistinctLog(HttpServletRequest request) {

		logger.info(DateUtil.getStandardDateTime()+"留存统计:start");

		String batchDate = request.getParameter("batchDate");
		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG+"！若指定结束日期，请传参batchDate具体日期，格式yyyy-MM-dd。");
		try {
			Date createDateBegin = null;
			Date createDateEnd = DateUtil.getDateAfterAndEndTime(new Date(), -1);
			if(!StringUtil.isEmpty(batchDate) ) {
				createDateEnd = DateUtil.getDateAfterAndEndTime(DateUtil.getDate(batchDate, "yyyy-MM-dd"), -1);
			}
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
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(ResponseMsg.ERROR_MSG);
			logger.error("留存统计 Excetion:", e);
		}

		logger.info(DateUtil.getStandardDateTime()+"留存统计:end");

		return responseMsg;
	}

}
