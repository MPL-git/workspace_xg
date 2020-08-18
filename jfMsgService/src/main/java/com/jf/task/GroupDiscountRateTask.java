package com.jf.task;

import com.jf.common.utils.DateUtil;
import com.jf.service.AndroidChannelGroupDiscountRateService;
import com.jf.service.SpreadActivityGroupDiscountRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Pengl
 * @create 2019-12-06 下午 1:49
 */
public class GroupDiscountRateTask {

	private static Logger logger = LoggerFactory.getLogger(GroupDiscountRateTask.class);

	@Autowired
	private AndroidChannelGroupDiscountRateService androidChannelGroupDiscountRateService;

	@Autowired
	private SpreadActivityGroupDiscountRateService spreadActivityGroupDiscountRateService;

	/**
	 * 渠道优惠率
	 */
	@Scheduled(cron = "0 30 1 * * ?")
	public synchronized void groupDiscountRate() {

		logger.info("安卓渠道优惠率start：" + DateUtil.getStandardDateTime());
		try {
			androidChannelGroupDiscountRateService.androidChannelGroupDiscountRate();
		} catch (Exception e) {
			logger.error("安卓渠道优惠率 Exception:", e);
		}
		logger.info("安卓渠道优惠率end：" + DateUtil.getStandardDateTime());


		logger.info("iOS活动组优惠率start：" + DateUtil.getStandardDateTime());
		try {
			spreadActivityGroupDiscountRateService.spreadActivityGroupDiscountRate();
		} catch (Exception e) {
			logger.error("iOS活动组优惠率 Exception:", e);
		}
		logger.info("iOS活动组优惠率end：" + DateUtil.getStandardDateTime());

	}


}
