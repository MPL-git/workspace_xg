package com.jf.task;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.DateUtil;
import com.jf.service.SpreadNameService;

/**
 *
 *推广活动定时任务
 *
 */
@RegCondition
@Component
public class SpreadTask {

    private static Logger logger = LoggerFactory.getLogger(SpreadTask.class);
    
    @Resource
    private SpreadNameService spreadNameService;

    /**
     * 抽取推广活动名称
     *
     *
     */
    @Scheduled(cron="0 0 0/1 * * ?")
    public synchronized void extrackSpreadName(){
        logger.info(DateUtil.getStandardDateTime()+"抽取推广活动名称:start");
        
        int count=spreadNameService.extrackSpreadName();

        logger.info(DateUtil.getStandardDateTime()+"抽取推广活动名称:"+count+"条");
        logger.info(DateUtil.getStandardDateTime()+"抽取推广活动名称:end");
    }
    
    
}
