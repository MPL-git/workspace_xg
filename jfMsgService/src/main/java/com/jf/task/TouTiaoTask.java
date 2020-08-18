package com.jf.task;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.service.ToutiaoAdInfoService;
import com.jf.service.ToutiaoAdvertiserInfoService;
import com.jf.service.ToutiaoCampaignInfoService;

/**
 *
 *头条推广定时任务
 *
 */
@RegCondition
@Component
public class TouTiaoTask {

    private static Logger logger = LoggerFactory.getLogger(TouTiaoTask.class);
    
    @Autowired
    private ToutiaoAdvertiserInfoService toutiaoAdvertiserInfoService;
    
    @Autowired
    private ToutiaoAdInfoService toutiaoAdInfoService;
    
    @Autowired
    private ToutiaoCampaignInfoService toutiaoCampaignInfoService;
    

    /**
     * 
     * @Title refreshTokenTask   
     * @Description TODO(每天凌晨1点，凌晨5点   刷新Token)   
     * @author pengl
     * @date 2018年8月15日 下午4:23:09
     */
    @Scheduled(cron="0 0-10/3 1,5 * * ?")
    public synchronized void refreshTokenTask(){
        logger.info(DateUtil.getStandardDateTime()+"头条推广刷新token:start");
        
        ResponseMsg responseMsg = toutiaoAdvertiserInfoService.refreshToken(null);
		System.out.println("头条推广刷新token======>"+responseMsg.getReturnCode()+"------"+responseMsg.getReturnMsg());

        logger.info(DateUtil.getStandardDateTime()+"头条推广刷新token:end");
    }
    
    /**
     * 
     * @Title advertiserInfoList   
     * @Description TODO(头条推广广告主信息)   
     * @author pengl
     * @date 2018年8月15日 下午6:59:14
     *//*
    @Scheduled(cron="0 0 1,13 * * ?")
    public synchronized void advertiserInfoList(){
        logger.info(DateUtil.getStandardDateTime()+"头条推广广告主信息:start");
        
        ResponseMsg responseMsg = toutiaoAdvertiserInfoService.advertiserInfoList();
        System.out.println("头条推广广告主信息======>"+responseMsg.getReturnCode()+"------"+responseMsg.getReturnMsg());
        
        logger.info(DateUtil.getStandardDateTime()+"头条推广广告主信息:end");
    }
    
    *//**
     * 
     * @Title campaignGetList   
     * @Description TODO(头条推广获取广告组（新）)   
     * @author pengl
     * @date 2018年8月16日 下午12:53:34
     *//*
    @Scheduled(cron="0 0 * * *  ?")
    public synchronized void campaignGetList(){
        logger.info(DateUtil.getStandardDateTime()+"头条推广获取广告组（新）:start");
        
        ResponseMsg responseMsg = toutiaoAdvertiserInfoService.campaignGetList();
        System.out.println("头条推广获取广告组（新）======>"+responseMsg.getReturnCode()+"------"+responseMsg.getReturnMsg());
        
        logger.info(DateUtil.getStandardDateTime()+"头条推广获取广告组（新）:end");
    }
    
    *//**
     * 
     * @Title adGetList   
     * @Description TODO(头条推广获取广告计划（新）)   
     * @author pengl
     * @date 2018年8月16日 下午2:27:14
     *//*
    @Scheduled(cron="0 0 * * *  ?")
    public synchronized void adGetList(){
    	logger.info(DateUtil.getStandardDateTime()+"头条推广获取广告计划（新）:start");
    	
    	ResponseMsg responseMsg = toutiaoAdvertiserInfoService.adGetList();
    	System.out.println("头条推广获取广告计划（新）======>"+responseMsg.getReturnCode()+"------"+responseMsg.getReturnMsg());
    	
    	logger.info(DateUtil.getStandardDateTime()+"头条推广获取广告计划（新）:end");
    }*/
    
    
}
