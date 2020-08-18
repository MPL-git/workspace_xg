package com.jf.task;


import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.service.SourceNicheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@RegCondition
@Component
public class SourceNicheTask {
	private static Logger logger = LoggerFactory.getLogger(SourceNicheTask.class);
	
	@Resource
	private SourceNicheService sourceNicheService;

	
	/**
	 * 每天十点驳回审核未通过的商品
	 */
	@Scheduled(cron = "0 0 10 * *  ?")
	public synchronized void sourceNicheTask() {
		logger.info(DateUtil.getStandardDateTime() + "驳回未审核商品:start");
		sourceNicheService.UpdateSourceNicheAuditStatus();
		logger.info(DateUtil.getStandardDateTime() + "驳回未审核商品:end");
	}


	/**
	 * 有好货上线时间超过90天的活动下线
	 */
	@Scheduled(cron="0 0/15 * * * ?")
	public void sourceNicheTimingOfTheShelves() {
		logger.info(DateUtil.getStandardDateTime() + "有好货上线时间超过90天的活动下线:start");
		sourceNicheService.sourceNicheTimingOfTheShelves();
		logger.info(DateUtil.getStandardDateTime() + "有好货上线时间超过90天的活动下线:end");
	}

	/**
	 * 每天添加每个类目权重前300的商品到bu_source_niche
	 * 执行时间：每天凌晨4:20分
	 */
	@Scheduled(cron="0 20 4 * * ?")
	public synchronized void sourceNicheProductTask() {
		logger.info(DateUtil.getStandardDateTime() + "非手动添加商品:start");
		sourceNicheService.addSourceNicheProduct();
		logger.info(DateUtil.getStandardDateTime() + "非手动添加商品:end");
	}

	/**
	 * 不满足规则的积分转盘商品进行回收
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void sourceNicheTurntableProductTask() {
		logger.info(DateUtil.getStandardDateTime() + "不满足规则的积分转盘商品进行回收:start");
		sourceNicheService.recycleTurntableProduct();
		logger.info(DateUtil.getStandardDateTime() + "不满足规则的积分转盘商品进行回收:end");
	}

}
