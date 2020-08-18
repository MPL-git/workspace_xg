
package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.service.OrderDtlService;
import com.jf.service.SourceNicheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * @ClassName WeightCalculationTask
 * @Description TODO
 * @author chengh
 * @date 2019年7月4日 下午7:46:43
 */
@RegCondition
@Component
public class WeightCalculationTask {

	private static Logger logger = LoggerFactory.getLogger(CustomerOrderServiceTask.class);
	
	@Resource
	private SourceNicheService sourceNicheService;
	
	@Resource
	private OrderDtlService orderDtlService;
	 /**
	  * 
	  * @MethodName weightCalculation
	  * @Description TODO(每15分钟执行一次营销活动权重计算)
	  * @author chengh
	  * @date 2019年7月4日 下午7:47:39
	  */
    @Scheduled(cron="0 0/15 * * * ?")
    public void weightCalculation(){	
    	logger.info("权重计算开始.......");
    	try {
			sourceNicheService.operate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
    	logger.info("权重计算结束.......");
    }
    
    
    
	 /**
	  * 
	  * @MethodName shopWeightCalculation
	  * @Description TODO(每天零点执行一次资源位店铺权重计算)
	  * @author yinrd
	  * @date 2019年7月4日 下午7:47:39
	  */
   @Scheduled(cron="0 0 0 * * ?")
   public void shopWeightCalculation(){	
   	logger.info("权重计算开始.......");
   	try {
			sourceNicheService.shopWeightOperate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
   	logger.info("权重计算结束.......");
   }
}
