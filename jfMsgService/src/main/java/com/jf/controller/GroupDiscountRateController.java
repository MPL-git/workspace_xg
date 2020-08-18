package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.service.AndroidChannelGroupDiscountRateService;
import com.jf.service.SpreadActivityGroupDiscountRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pengl
 * @create 2019-12-06 上午 11:02
 */
@RegCondition
@Controller
public class GroupDiscountRateController {

	private static Logger logger = LoggerFactory.getLogger(GroupDiscountRateController.class);

	@Autowired
	private AndroidChannelGroupDiscountRateService androidChannelGroupDiscountRateService;

	@Autowired
	private SpreadActivityGroupDiscountRateService spreadActivityGroupDiscountRateService;

	@ResponseBody
	@RequestMapping("/groupDiscountRate/androidChannelGroupDiscountRate")
	public synchronized ResponseMsg androidChannelGroupDiscountRate(HttpServletRequest request) {

		logger.info(DateUtil.getStandardDateTime()+"安卓渠道优惠率:start");

		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			androidChannelGroupDiscountRateService.androidChannelGroupDiscountRate();
		} catch (Exception e) {
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(ResponseMsg.ERROR_MSG);
			logger.error("安卓渠道优惠率 Excetion:", e);
		}

		logger.info(DateUtil.getStandardDateTime()+"安卓渠道优惠率:end");

		return responseMsg;
	}

	@ResponseBody
	@RequestMapping("/groupDiscountRate/spreadActivityGroupDiscountRate")
	public synchronized ResponseMsg spreadActivityGroupDiscountRate(HttpServletRequest request) {

		logger.info(DateUtil.getStandardDateTime()+"iOS活动组优惠率:start");

		ResponseMsg responseMsg = new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		try {
			spreadActivityGroupDiscountRateService.spreadActivityGroupDiscountRate();
		} catch (Exception e) {
			responseMsg.setReturnCode(ResponseMsg.ERROR);
			responseMsg.setReturnMsg(ResponseMsg.ERROR_MSG);
			logger.error("iOS活动组优惠率 Excetion:", e);
		}

		logger.info(DateUtil.getStandardDateTime()+"iOS活动组优惠率:end");

		return responseMsg;
	}

}
