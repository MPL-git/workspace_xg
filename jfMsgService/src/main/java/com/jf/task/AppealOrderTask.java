package com.jf.task;

import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.entity.AppealOrder;
import com.jf.service.AppealOrderService;

/**
 * 订单投诉定时任务
 * @author chenwf
 *
 */
@RegCondition
@Component
public class AppealOrderTask {
	
	private static Logger logger = LoggerFactory.getLogger(AppealOrderTask.class);
	
	@Resource
	private AppealOrderService appealOrderService;
	
	 /**
     * 当投诉的状态为【待客户回复】时，15天后自动将投诉的状态变更为【平台关闭】
     * 1、投诉单是状态必须为：【待客户回复】
	 * 2、时间：取【更新时间】+15天
	 * 每天半夜12点30分执行一次
     */
	@Scheduled(cron = "0 30 0 * * ?")
	public void closeAppealOrder() {
		logger.info("15天后客户未服务，平台关闭投诉单 :start");
		List<AppealOrder> appealOrders = appealOrderService.findCloseAppealOrderList();
		for (AppealOrder appealOrder : appealOrders) {
			try {
				appealOrderService.closeAppealOrder(appealOrder);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
				logger.info("平台关闭投诉单失败：end-->"+appealOrder.getOrderCode());
			}
		}

		logger.info("15天后客户未服务，平台关闭投诉单 :end");
	}
}
