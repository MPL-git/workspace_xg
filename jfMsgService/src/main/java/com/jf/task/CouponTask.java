package com.jf.task;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.DateUtil;
import com.jf.service.CouponService;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName: CouponTask
 * @Description: (优惠券定时任务)
 * @author Pengl
 * @date 2019年7月11日 上午11:40:22
 */
@RegCondition
@Component
public class CouponTask {

	private static Logger logger = LoggerFactory.getLogger(CouponTask.class);
	
	@Autowired
	private CouponService couponService;

	/**
	 * 增加优惠数量和领取时间定时任务
	 * 每天15:45，23:45各执行一次，其中15:45只增加活动类型为领券秒杀的优惠券
	 *
	 */
	@Scheduled(cron="0 46 15,23 * * ?")
	public synchronized void addGrantQuantityTask() {
		logger.info(DateUtil.getStandardDateTime()+"自动追加优惠券发行量:start");
		
		try {
			//添加优惠券
			Calendar calendar= Calendar.getInstance();
			int currentHour=calendar.get(Calendar.HOUR_OF_DAY);
			if (currentHour>=23){
				int updateCount = couponService.addGrantQuantity(null);
                logger.info(DateUtil.getStandardDateTime()+"更新{}条优惠券",updateCount);
			}else{
                int updateCount = couponService.addGrantQuantity("2");
                logger.info(DateUtil.getStandardDateTime()+"更新{}条秒杀优惠券",updateCount);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info(DateUtil.getStandardDateTime()+"自动追加优惠券发行量:end");
	}

	
}
