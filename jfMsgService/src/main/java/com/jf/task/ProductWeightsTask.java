package com.jf.task;

import java.util.Calendar;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.DateUtil;
import com.jf.service.ProductWeightService;
@RegCondition
@Component
public class ProductWeightsTask {
	private static Logger logger = LoggerFactory.getLogger(ProductWeightsTask.class);

	@Autowired
	private ProductWeightService productWeightService;

	/**
	 * 每个月1号凌晨2点更新商品季节权重
	 */
	@Scheduled(cron = "0 0 2 1 * ?")
	public synchronized void seasonWeightTask() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品季节权重:start");
		// 获取当前月
		Calendar calendar = Calendar.getInstance();
		productWeightService.updateProductSeasonWeights(calendar.get(Calendar.MONTH) + 1);
		logger.info(DateUtil.getStandardDateTime() + "刷新商品季节权重:end");
	}
	
	/**
	 * 每天凌晨2:30更新销量权重
	 */
	@Scheduled(cron = "0 30 2 * * ?")
	public synchronized void saleWeightTask() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品销量权重:start");
		productWeightService.updateProductSaleWeight();
		logger.info(DateUtil.getStandardDateTime() + "刷新商品销量权重:end");
	}
	
	/**
	 * 每天凌晨2:45更新销售额权重
	 */
	@Scheduled(cron = "0 45 2 * * ?")
	public synchronized void saleAmountWeightTask() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品销售额权重:start");
		productWeightService.updateProductSaleAmountWeight();
		logger.info(DateUtil.getStandardDateTime() + "刷新商品销售额权重:end");
	}

	
	/**
	 * 每天凌晨3点更新点击量权重
	 */
//	@Scheduled(cron = "0 0 3 * * ?")
	public synchronized void pvWeightTask() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品点击量权重:start");
		productWeightService.updateProductPvWeight();
		logger.info(DateUtil.getStandardDateTime() + "刷新商品点击量权重:end");
	}
	
	
	/**
	 * 每天凌晨3:15更新评价权重
	 */
	@Scheduled(cron = "0 15 3 * * ?")
	public synchronized void updateProductCommentWeight() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品评价权重:start");
		productWeightService.updateProductCommentWeight();
		logger.info(DateUtil.getStandardDateTime() + "刷新商品评价权重:end");
	}
	
	
	/**
	 * 每天凌晨4点更新商品总权重,商品表销量权重
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	public synchronized void totalWeightTask() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品总权重:start");
		productWeightService.updateProductWeightsTotal();
		logger.info(DateUtil.getStandardDateTime() + "刷新商品总权重:end");
	}
	
	
	/**
	 * 每天凌晨4:30更新第三方商品总权重
	 */
	@Scheduled(cron = "0 30 4 * * ?")
	public synchronized void thirdPlatformProductWeightTask() {
		logger.info(DateUtil.getStandardDateTime() + "刷新商品总权重:start");
		productWeightService.updateThirdPlatformProductWeight();
		logger.info(DateUtil.getStandardDateTime() + "刷新商品总权重:end");
	}

}
