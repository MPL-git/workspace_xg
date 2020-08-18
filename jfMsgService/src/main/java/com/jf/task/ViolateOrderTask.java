package com.jf.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DateUtil;
import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderCustomExample;
import com.jf.entity.ViolateOrderExample;
import com.jf.service.CombineOrderService;
import com.jf.service.IntegralDtlService;
import com.jf.service.SubOrderService;
import com.jf.service.ViolateOrderService;

/**
 * 违规单任务
 *
 * @auther yjc
 */
@RegCondition
@Component
public class ViolateOrderTask {

    private static Logger logger = LoggerFactory.getLogger(ViolateOrderTask.class);

    @Resource
    private CombineOrderService combineOrderService;
    @Resource
    private SubOrderService subOrderService;
    @Resource
    private IntegralDtlService integralDtlService;
    @Resource
    private ViolateOrderService violateOrderService;


    /**
     * 超过违规单的申诉截至时间，修改违规单的状态
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
////    @Scheduled(cron="0 0/1 * * * ?")
    public synchronized void reciept(){
        logger.info("超过违规单的申诉截至时间，修改违规单的状态:start");

        QueryObject queryObject = new QueryObject();
        List<String> statusList = new ArrayList<String>();
        statusList.add("1");//未申诉
        statusList.add("3");//申诉中
        queryObject.addQuery("statusIn", statusList);
        queryObject.addQuery("complainEndDateIsNotNull", true);//申诉截至时间不为空
        queryObject.addQuery("auditStatus", "1");//审核通过
        queryObject.addQuery("complainEndDateBeforeNow", true);//申诉截至时间小于当前时间

        List<ViolateOrder> list = violateOrderService.findList(queryObject);
        logger.info("扫描到的违规单数：{}", list.size());
        for(ViolateOrder violateOrder : list){
            logger.info("超过违规单的申诉截至时间，修改每个违规单的状态，violateOrderId：{}", violateOrder.getId());
            violateOrderService.updateStatus(violateOrder);
        }

        logger.info("超过违规单的申诉截至时间，修改违规单的状态:end");
    }
    
    
    /**
     * 商家保证金不足未给用户赠送积分的，在商家保证金重新缴纳后，给用户赠送积分
     *
     * 每隔1小时执行一次
     */
    @Scheduled(cron="0 0 0/1 * * ?")
    public synchronized void giveMemberIntegral(){
        logger.info("商家保证金重新缴纳后，给用户赠送积分:start");
        Date now=new Date();
        ViolateOrderExample violateOrderExample=new ViolateOrderExample();
        violateOrderExample.createCriteria().andDelFlagEqualTo("0").andJifenStatusEqualTo("3").andCreateDateBetween(DateUtil.getDateAfter(now, -30),now);
        List<ViolateOrder> violateOrders=violateOrderService.selectByExample(violateOrderExample);
        for(ViolateOrder violateOrder:violateOrders){
        	violateOrderService.dealWithIntegral(violateOrder);
        }
        logger.info("商家保证金重新缴纳后，给用户赠送积分:end");
    }
    
    /**
     * 虚假发货产生的违规单96小时（4天）后自动将未审核状态改为通过
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void shamDeliveryOrderAudit(){
        logger.info("虚假发货产生的违规单96小时（4天）后自动将未审核状态改为通过:start");
        Date limitDate = DateUtil.getDateAfter(new Date(), -4);
        ViolateOrderCustomExample e = new ViolateOrderCustomExample();
        ViolateOrderCustomExample.ViolateOrderCustomCriteria c = e.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andIsShamDeliveryOrder();
        c.andAuditStatusEqualTo("0");
        c.andCreateDateLessThanOrEqualTo(limitDate);
        List<ViolateOrder> list = violateOrderService.selectByCustomExample(e);
        logger.info("扫描到虚假发货产生的违规单数：{}", list.size());
        for(ViolateOrder violateOrder : list){
            logger.info("虚假发货产生的违规单96小时（4天）后自动将未审核状态改为通过，violateOrderId：{}", violateOrder.getId());
            try {
				violateOrderService.shamDeliveryOrderAudit(violateOrder);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
        }
        logger.info("虚假发货产生的违规单96小时（4天）后自动将未审核状态改为通过:end");
    }
    
    /**
     * 违规单状态in（超期未申诉、申诉失败、不可申诉）
     * 且违规单审核状态为通过
     * 且违规单状态时间（statusDate）+48小时<=当前时间（now）
     * 且复审状态不等于3(线下申诉成功)
     * 发放积分给用户且需要发送短信提醒（STORY #1430）
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
//    @Scheduled(cron="0 0/1 * * * ?")
    public synchronized void sendIntegralToMember(){
        logger.info("发放积分给用户且需要发送短信提醒（STORY #1430）:start");
        Date limitDate = DateUtil.getDateAfter(new Date(), -2);
        ViolateOrderCustomExample e = new ViolateOrderCustomExample();
        ViolateOrderCustomExample.ViolateOrderCustomCriteria c = e.createCriteria();
        c.andDelFlagEqualTo("0");
        c.andStatusIn(Arrays.asList("4,5,2".split(",")));//超期未申诉、申诉失败、不可申诉
        c.andAuditStatusEqualTo("1");
        c.andStatusDateLessThanOrEqualTo(limitDate);
        c.andAgainAuditStatusNotEqualTo("3");
        List<ViolateOrder> list = violateOrderService.selectByCustomExample(e);
        logger.info("扫描到需要赠送给会员积分的违规单数：{}", list.size());
        for(ViolateOrder violateOrder : list){
            try {
            	violateOrderService.dealWithIntegral(violateOrder);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        logger.info("发放积分给用户且需要发送短信提醒（STORY #1430）:end");
    }
    
    /**
     * 判定商家违规后：48小时补偿积分给用户
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void violateCompensateMemberIntegral(){
        logger.info("违规48小时后补偿用户积分:start");
        ViolateOrderExample orderExample = new ViolateOrderExample();
        orderExample.createCriteria().andDelFlagEqualTo("0").andStatusIn(Arrays.asList("2","4","5")).andAuditStatusEqualTo("1")
        .andJifenStatusEqualTo("0").andStatusDateGreaterThan(DateUtil.addHour(new Date(), -48)).andAgainAuditStatusIn(Arrays.asList("0","1"));
       
        List<ViolateOrder> list = violateOrderService.selectByExample(orderExample);
        logger.info("扫描到违规补偿单数：{}", list.size());
        for(ViolateOrder violateOrder : list){
            try {
            	if(violateOrder.getJifenIntegral()!=null && violateOrder.getJifenIntegral().equals(0)){
            		violateOrder.setJifenIntegral(1);
            	}
				violateOrderService.dealWithIntegral(violateOrder);
				violateOrderService.savePlatFormMsg(violateOrder);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        logger.info("违规48小时后补偿用户积分::end");
    }
}
