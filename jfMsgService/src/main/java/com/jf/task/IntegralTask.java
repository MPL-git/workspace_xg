package com.jf.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.DateUtil;
import com.jf.entity.IntegralGive;
import com.jf.entity.IntegralGiveExample;
import com.jf.service.IntegralDailyStatisticsService;
import com.jf.service.IntegralGiveService;

/**
 *
 *结算单定时任务
 *
 */
@RegCondition
@Component
public class IntegralTask {

    private static Logger logger = LoggerFactory.getLogger(IntegralTask.class);
    
    @Autowired
    private IntegralGiveService integralGiveService;
    
    @Autowired
    private IntegralDailyStatisticsService integralDailyStatisticsService;

    /**
     * 赠送积分
     *
     *
     */
    @Scheduled(cron="0 0/5 * * * ?")
//    @Scheduled(cron="0/30 * * * * ?")
    public synchronized void integralGiveTask(){
        logger.info(DateUtil.getStandardDateTime()+"积分赠送给:start");
        
        //查出所有已审核的积分赠送单
        IntegralGiveExample integralGiveExample=new IntegralGiveExample();
        integralGiveExample.createCriteria().andDelFlagEqualTo("0").andAuditStatusEqualTo("1");
        
        List<IntegralGive> integralGives=integralGiveService.selectByExample(integralGiveExample);
        
        for(IntegralGive integralGive:integralGives){
        	integralGiveService.integralGive(integralGive);
        }

        logger.info(DateUtil.getStandardDateTime()+"积分赠送给:end");
    }
    
    
    /**
     * 每日积分汇总统计
     *
     *
     */
     @Scheduled(cron="0 0 1-3 * * ?")
     /*@Scheduled(cron="0 0/1 * * * ?")*/
    public void integralDailyStatisticTask(){
        logger.info(DateUtil.getStandardDateTime()+"积分每日汇总:start");
        
		Calendar calendar=Calendar.getInstance(); 
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        integralDailyStatisticsService.dailyStatistic(sdf.format(calendar.getTime()));

        logger.info(DateUtil.getStandardDateTime()+"积分每日汇总:end");
    }
    
}
