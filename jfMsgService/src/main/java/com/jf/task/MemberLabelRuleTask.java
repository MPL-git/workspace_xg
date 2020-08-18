package com.jf.task;

import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.entity.MemberLabelRule;
import com.jf.entity.MemberLabelRuleCustomExample;
import com.jf.service.MemberLabelRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@RegCondition
@Component
public class MemberLabelRuleTask {

    private static Logger logger = LoggerFactory.getLogger(MemberLabelRuleTask.class);
    
    @Resource
	private MemberLabelRuleService memberLabelRuleService;
    

    @Scheduled(cron="0 0/30 * * * ?")
    public synchronized void memberLabelRuleTask(){
          logger.info(DateUtil.getStandardDateTime()+"执行标签规则:start");
		
		try {
			 MemberLabelRuleCustomExample memberLabelRuleCustomExample=new MemberLabelRuleCustomExample();
			 MemberLabelRuleCustomExample.MemberLabelRuleCustomCriteria memberLabelRuleCustomCriteria=memberLabelRuleCustomExample.createCriteria();
			 memberLabelRuleCustomCriteria.andDelFlagEqualTo("0");
			 memberLabelRuleCustomCriteria.andMemberlabelStatus();
			 List<MemberLabelRule> memberLabelRules=memberLabelRuleService.selectByExample(memberLabelRuleCustomExample);
			 if (memberLabelRules!=null && memberLabelRules.size()>0) {
				for (MemberLabelRule memberLabelRule : memberLabelRules) {
					 memberLabelRuleService.implementAddmemberLabelRule(memberLabelRule);
					 
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info(DateUtil.getStandardDateTime()+"执行标签规则:end");
 
    }

}
