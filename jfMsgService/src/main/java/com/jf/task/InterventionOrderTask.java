package com.jf.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.dao.InterventionOrderMapper;
import com.jf.dao.InterventionOrderStatusLogMapper;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.InterventionOrderStatusLog;

/**
 * 介入单任务
 *
 * @auther yjc
 */
@RegCondition
@Component
public class InterventionOrderTask {

    private static Logger logger = LoggerFactory.getLogger(InterventionOrderTask.class);

    @Resource
    private InterventionOrderMapper interventionOrderMapper;
    @Resource
    private InterventionOrderStatusLogMapper interventionOrderStatusLogMapper;


    /**
     * 复审驳回的介入单48小时内未修改变成已结案
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
//    @Scheduled(cron="0 0/1 * * * ?")
    public void accept(){
        logger.info("复审驳回的介入单48小时内未修改变成已结案:start");
        InterventionOrderExample example  = new InterventionOrderExample();
        InterventionOrderExample.Criteria c = example.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andStatusEqualTo("3");//复审驳回
		c.andUpdateDateLessThanOrEqualTo(DateTime.now().minusDays(2).toDate());
        List<InterventionOrder> list = interventionOrderMapper.selectByExample(example);
        logger.info("扫描到的介入单数：{}", list.size());
        for(InterventionOrder interventionOrder : list){
            logger.info("复审驳回的介入单48小时内未修改变成已结案，interventionOrderId：{}", interventionOrder.getId());
            interventionOrder.setStatus("8");
            interventionOrder.setUpdateDate(new Date());
            interventionOrder.setWinType("2");//驳回的算商家为胜方
            InterventionOrderStatusLog interventionOrderStatusLog  = new InterventionOrderStatusLog();
            interventionOrderStatusLog.setCreateDate(new Date());
            interventionOrderStatusLog.setDelFlag("0");
            interventionOrderStatusLog.setInterventionOrderId(interventionOrder.getId());
            interventionOrderStatusLog.setStatus(interventionOrder.getStatus());
            interventionOrderStatusLog.setRemarks("复审驳回的介入单48小时内未修改变成已结案,驳回的算商家为胜方");
            interventionOrderStatusLogMapper.insertSelective(interventionOrderStatusLog);
            interventionOrderMapper.updateByPrimaryKeySelective(interventionOrder);
        }

        logger.info("复审驳回的介入单48小时内未修改变成已结案:end");
    }
}
