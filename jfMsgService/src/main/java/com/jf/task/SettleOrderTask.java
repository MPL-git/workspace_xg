package com.jf.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.utils.DateUtil;
import com.jf.service.MchtMonthTradeService;
import com.jf.service.MchtMonthlyCollectionsService;
import com.jf.service.MchtSettleCompareService;
import com.jf.service.MchtSettleOrderService;

/**
 *
 *结算单定时任务
 *
 */
@RegCondition
@Component
public class SettleOrderTask {

    private static Logger logger = LoggerFactory.getLogger(SettleOrderTask.class);
    
    @Resource
    private MchtSettleOrderService mchtSettleOrderService;

    @Resource
    private MchtMonthlyCollectionsService mchtMonthlyCollectionsService;
    
    @Resource
    private MchtSettleCompareService mchtSettleCompareService;
    
    @Resource
    private MchtMonthTradeService mchtMonthTradeService;

    /**
     * 每周一   1-3点执行一次，共执行3次，生成商家对账单
     *
     *
     */
	@Scheduled(cron="0 0/1 * * * ?")
    public synchronized void generateSettleOrderTask(){
        logger.info(DateUtil.getStandardDateTime()+"生成结算单:start");
        Map<String, String> lastWeekDate=DateUtil.getLastWeekDate();
        
        mchtSettleOrderService.generateSettleOrder(lastWeekDate.get("lastMonday"),lastWeekDate.get("lastSunday"));

        logger.info(DateUtil.getStandardDateTime()+"生成结算单:end");
    }
    
    /**
     * 每月1号 1-3点执行一次，共执行3次 生成结算情况表
     *
     *
     */
    @Scheduled(cron="0 0 1-3 1 * ?")
    public synchronized void generateSettleSituation(){
    	logger.info(DateUtil.getStandardDateTime()+"生成结算情况单:start");
    	Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
    	
    	mchtSettleOrderService.generateSettleSituation(sdf.format(calendar.getTime()));
    	
    	logger.info(DateUtil.getStandardDateTime()+"生成结算情况单:end");
    }
    
    
    /**
     * 每月1号 1-3点执行一次，共执行3次  生成收款情况单
     *
     *
     */ 
  @Scheduled(cron="0 0 1-3 1 * ?")
    public synchronized void generateMonthlyCollections(){
    	logger.info(DateUtil.getStandardDateTime()+"生成每月收款单:start");
    	Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
    	
		mchtMonthlyCollectionsService.generateMonthlyCollections(sdf.format(calendar.getTime()));
    	
    	logger.info(DateUtil.getStandardDateTime()+"生成每月收款单:end");
    }
  
  	/**
  	 * 
  	 * @Title generateMchtMonthTrade   
  	 * @Description TODO(每月1号 1-3点执行一次，共执行3次  生成每月商家月往来单)   
  	 * @author pengl
  	 * @date 2018年4月16日 下午5:35:32
  	 */
//  	@Scheduled(cron="0 0/1 * * * ?")
  	@Scheduled(cron="0 0 1-3 1 * ?")
  	public synchronized void generateMchtMonthTrade() {
  		logger.info(DateUtil.getStandardDateTime()+"生成每月商家月往来单:start");
  		
  		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  		//获取前月
  		Calendar calendar = Calendar.getInstance();
  		calendar.add(Calendar.MONTH, -1);
  		//获取前月的最后一天
  		Calendar ca = Calendar.getInstance();
  		ca.set(Calendar.DAY_OF_MONTH, 0);
  		
  		String tradeMonth = format.format(calendar.getTime());
  		String startDate = tradeMonth+"-01 00:00:00";
  		String endDate = sdf.format(ca.getTime())+" 23:59:59";
  		mchtMonthTradeService.generateMchtMonthTrade(tradeMonth, startDate, endDate);
  		
  		logger.info(DateUtil.getStandardDateTime()+"生成每月商家月往来单:end");
  	}
    
    
    /**
     * 结算单14*24小时商家未确认，自动确认
     *
     *
     */
//    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void confirmSettleOrderTask(){
    	logger.info(DateUtil.getStandardDateTime()+"自动确认结算但:start");
    	Map<String, String> lastWeekDate=DateUtil.getLastWeekDate();
    	
    	int count=mchtSettleOrderService.autoConfirmSettleOrder();
    	
    	logger.info(DateUtil.getStandardDateTime()+"自动确认了"+count+"个结算单"+" :end");
    }
    
    
    /**
     * 每月1号 1-3点执行一次，共执行3次  生成结算对比单
     *
     *
     */ 
  @Scheduled(cron="0 0 1-3 1 * ?")
  public synchronized void generateMchtSettleCompare(){
    	logger.info(DateUtil.getStandardDateTime()+"生成结算对比单:start");
    	
		mchtSettleCompareService.generateMchtSettleCompare(DateUtil.getFirstMonthDate(-1),DateUtil.getLastMonthDate(-1));
    	
    	logger.info(DateUtil.getStandardDateTime()+"生成结算对比单:end");
    }
    
    
  
  public static void main(String[] args) {
	
	  System.out.println(DateUtil.getFirstMonthDate(-3));
	  System.out.println(DateUtil.getLastMonthDate(-1));
   
}
    
   
  
    
}
