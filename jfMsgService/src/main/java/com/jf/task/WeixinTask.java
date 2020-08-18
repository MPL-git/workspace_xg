package com.jf.task;

import com.jf.common.enumerate.RuntimeEnv;
import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.WeixinUtil;
@RegCondition(level = RuntimeEnv.PROD)
@Component
public class WeixinTask {
    private static Logger logger = LoggerFactory.getLogger(WeixinTask.class);
    
    
    /**
     * 
     * 更新微信access_token
     * 每隔5分钟执行一次
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public void updateAccessToken(){
        logger.info("更新微信公众号access_token:start");
        WeixinUtil.getAccessToken();
        logger.info("更新微信公众号aaccess_token:end");
    }
    
    /**
     * 
     * 更新小程序access_token
     * 每隔5分钟执行一次
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public void updateXcxAccessToken(){
    	logger.info("更新微信小程序access_token:start");
    	WeixinUtil.getXcxAccessToken();
    	logger.info("更新微信小程序access_token:end");
    }
    
    /**
     * 
     * 更新微信access_token
     * 每隔5分钟执行一次
     */
    @Scheduled(cron="30 0/5 * * * ?")
    public void getJsapiTicket(){
    	logger.info("更新微信JsapiTicket:start");
    	WeixinUtil.getJsapiTicket();
    	logger.info("更新微信JsapiTicket:end");
    }
	
}
