package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.service.PropertyRightAppealService;
import com.jf.service.PropertyRightComplainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: 知识产权任务
 * @Author: zhen.li
 * @Date: 2018/12/26 17:12
 */
@RegCondition
@Component
public class PropertyRightTask {

    private static Logger logger = LoggerFactory.getLogger(PropertyRightTask.class);

    @Resource
    private PropertyRightAppealService appealService;

    @Resource
    private PropertyRightComplainService complainService;


    /**
     * 知识产权投诉超期未声明，更改投诉单状态任务
     * 更改投诉单状态为完成
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public synchronized void updateOverComplainDueStatus(){
        logger.info("知识产权投诉---超期未声明---，更改投诉单状态任务:start");

		appealService.updateOverComplainDueStatus();

        logger.info("知识产权投诉---超期未声明---，更改投诉单状态任务:end");
    }

	/**
	 * 知识产权投诉超期未起诉，更改投诉单状态任务
	 * 更改投诉单状态为撤销
	 */
	@Scheduled(cron="0 0/5 * * * ?")
	public synchronized void updateOverProsecutionDueStatus(){
		logger.info("知识产权投诉---超期未起诉---，更改投诉单状态任务:start");

		complainService.updateOverProsecutionDueStatus();

		logger.info("知识产权投诉---超期未起诉---，更改投诉单状态任务:end");
	}


}
