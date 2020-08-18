package com.jf.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoExample;
import com.jf.service.ThirdPlatformProductInfoService;

@RegCondition
@Component
public class ThirdPlatformProductInfoTask {
    private static Logger logger = LoggerFactory.getLogger(ThirdPlatformProductInfoTask.class);
    
    @Resource
    private ThirdPlatformProductInfoService thirdPlatformProductInfoService;
    
    
    /**
     * 淘宝客商品自动上架
     *
     * 每隔15分钟执行一次
     * @throws ParseException 
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void autoUp() throws ParseException{
        logger.info("淘宝客商品自动上架：start");
        Date now = new Date();
        ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
        thirdPlatformProductInfo.setStatus("1");
        thirdPlatformProductInfo.setUpdateDate(now);
        ThirdPlatformProductInfoExample e = new ThirdPlatformProductInfoExample();
        e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andAutoUpDateLessThanOrEqualTo(now).andAutoDownDateGreaterThanOrEqualTo(now);
        List<ThirdPlatformProductInfo> list = thirdPlatformProductInfoService.selectByExample(e);
        logger.info("扫描到的商品数：{}", list.size());
        List<Integer> productIds = new ArrayList<Integer>();
        for(ThirdPlatformProductInfo item:list){
        	productIds.add(item.getProductId());
        }
        thirdPlatformProductInfoService.autoUp(thirdPlatformProductInfo,e,productIds);
        logger.info("淘宝客商品自动上架：end");
    }
    
    /**
     * 淘宝客商品自动下架
     *
     * 每隔15分钟执行一次
     * @throws ParseException 
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void autoDown() throws ParseException{
    	logger.info("淘宝客商品自动下架：start");
    	Date now = new Date();
    	ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
    	thirdPlatformProductInfo.setStatus("0");
    	thirdPlatformProductInfo.setUpdateDate(now);
    	ThirdPlatformProductInfoExample e = new ThirdPlatformProductInfoExample();
    	e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andAutoDownDateLessThanOrEqualTo(now);
    	List<ThirdPlatformProductInfo> list = thirdPlatformProductInfoService.selectByExample(e);
    	logger.info("扫描到的商品数：{}", list.size());
    	List<Integer> productIds = new ArrayList<Integer>();
    	for(ThirdPlatformProductInfo item:list){
    		productIds.add(item.getProductId());
    	}
    	thirdPlatformProductInfoService.autoDown(thirdPlatformProductInfo,e,productIds);
    	logger.info("淘宝客商品自动下架：end");
    }
    
    /**
     * 淘宝客商品优惠券过期的自动下架
     *
     * 每隔15分钟执行一次
     * @throws ParseException 
     */
//    @Scheduled(cron="0 0/15 * * * ?")
//    public synchronized void autoDownByCoupon() throws ParseException{
//    	logger.info("淘宝客商品优惠券过期的商品自动下架：start");
//    	Date now = new Date();
//    	ThirdPlatformProductInfo thirdPlatformProductInfo = new ThirdPlatformProductInfo();
//    	thirdPlatformProductInfo.setStatus("0");
//    	thirdPlatformProductInfo.setUpdateDate(now);
//    	ThirdPlatformProductInfoExample e = new ThirdPlatformProductInfoExample();
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andCouponEndTimeLessThan(sdf.format(now));
//    	List<ThirdPlatformProductInfo> list = thirdPlatformProductInfoService.selectByExample(e);
//    	logger.info("扫描到的商品数：{}", list.size());
//    	List<Integer> productIds = new ArrayList<Integer>();
//    	for(ThirdPlatformProductInfo item:list){
//    		productIds.add(item.getProductId());
//    	}
//    	thirdPlatformProductInfoService.autoDown(thirdPlatformProductInfo,e,productIds);
//    	logger.info("淘宝客商品优惠券过期的商品自动下架：end");
//    }
    
}
