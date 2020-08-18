package com.jf.controller;

import com.jf.common.base.ResponseMsg;
import com.jf.common.utils.DateUtil;
import com.jf.entity.MemberLabelRule;
import com.jf.entity.MemberLabelRuleCustomExample;
import com.jf.service.MemberLabelRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MemberLabelController {

	private static Logger logger = LoggerFactory.getLogger(MemberLabelController.class);
	
	@Resource
	private MemberLabelRuleService memberLabelRuleService;
	
	@ResponseBody
    @RequestMapping("/memberLabel/addMemberLabelRule")
    public ResponseMsg memberLabelRuleTask(){
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
		
		return new ResponseMsg(ResponseMsg.SUCCESS,"执行标签成功");
 
    }
	
}
