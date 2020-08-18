package com.jf.task;

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
import com.jf.dao.CommentMapper;
import com.jf.entity.SubOrder;
import com.jf.service.SubOrderService;

/**
 * 评论任务
 *
 * @auther yjc
 */
@RegCondition
@Component
public class CommentTask {

    private static Logger logger = LoggerFactory.getLogger(CommentTask.class);

    @Resource
    private SubOrderService subOrderService;
    @Resource
    private CommentMapper commentMapper;


    /**
     * 确认收货15天后未评价的子订单自动评价,且改变子订单的评价状态
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
//    @Scheduled(cron="0 0/1 * * * ?")
    public void reciept(){
        logger.info("确认收货15天后未评价的子订单自动评价 :start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("statusIn", Arrays.asList("3,6".split(",")));//已收货,完成（2019年5月9日09:44:53添加的完成状态）
        queryObject.addQuery("isComment", "0");//未评价
        queryObject.addQuery("receiptDateLessThan", DateUtil.getDateAfter(new Date(), -15));//当前时间大于等于收货时间+15天
        
        List<SubOrder> list = subOrderService.findList(queryObject);
        logger.info("扫描到的未评价子订单单数：{}", list.size());
        for(SubOrder subOrder : list){
            logger.debug("确认收货15天后未评价的子订单，修改每个子订单的评价状态，并生成默认好评，subOrderId：{}", subOrder.getId());
            subOrderService.autoComment(subOrder);
        }

        logger.info("确认收货15天后未评价的子订单自动评价 :end");
    }
}
