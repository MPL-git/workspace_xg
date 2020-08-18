package com.jf.task;

import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
import com.jf.service.MchtInfoService;
import com.jf.service.MchtStatisticalInfoService;

@RegCondition
@Component
public class MchtStatisticalInfoTask {

    private static Logger logger = LoggerFactory.getLogger(MchtStatisticalInfoTask.class);
    
    @Resource
	private MchtInfoService mchtInfoService;
    
    @Resource
	private MchtStatisticalInfoService mchtStatisticalInfoService;

    /**
     * 
     *商家信息统计表每天凌晨两点17分执行一次;
     * 
     */
    @Scheduled(cron="0 17 2 * * ?")
    public synchronized void mchtStatisticalInfo(){
    	    try {
    	    	 logger.info("开始查询插入商家信息统计表:start");
    	    	MchtInfoCustomExample mchtInfoCustomExample=new MchtInfoCustomExample();
    	    	mchtInfoCustomExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
    	    	List<MchtInfoCustom> lisMchtInfoCustoms=mchtInfoService.selectByCustomExample(mchtInfoCustomExample);
    	    	for (MchtInfoCustom mchtInfoCustom : lisMchtInfoCustoms) {
    	    		
    	             mchtStatisticalInfoService.addMchtStatisticalInfo(mchtInfoCustom);
    	    		
				}
    	    	 logger.info("商家信息统计表插入成功:end");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.info(e.getMessage());
				logger.info("开始查询插入商家信息统计表error:end");
			}
 
    }

}
