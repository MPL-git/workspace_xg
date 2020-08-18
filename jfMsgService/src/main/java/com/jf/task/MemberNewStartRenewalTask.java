
package com.jf.task;


import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.service.MemberInfoService;

/**
 * 
 * @ClassName MemberNewStartRenewalTask
 * @Description TODO
 * @author chengh
 * @date 2019年7月18日 上午9:22:44
 */
@RegCondition
@Component
public class MemberNewStartRenewalTask {

	private static Logger logger = LoggerFactory.getLogger(CustomerOrderServiceTask.class);
	
	@Resource
	private MemberInfoService memberInfoService;
	/**
	 * 
	 * @MethodName newStartRenewal
	 * @Description TODO(每天计算出now()-1天的店长会员，执行续签操作)
	 * @author chengh
	 * @date 2019年7月18日 上午9:25:11
	 */
    @Scheduled(cron="0 0 2 * * ?")
    public void newStartRenewal(){	
    	logger.info("续签操作开始.......");
    	memberInfoService.operate();
    	logger.info("续签操作结束.......");
    }
}
