package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.*;
import com.jf.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberLabelRuleService extends BaseService<MemberLabelRule,MemberLabelRuleExample> {
	
	
	private static final Log logger = LogFactory.getLog(MemberLabelRuleService.class);
	
	@Autowired
	private MemberLabelRuleMapper memberLabelRuleMapper;
	
	@Autowired
	private MemberInfoCustomMapper memberInfoCustomMapper;

	@Autowired
	private MemberLabelRelationMapper memberLabelRelationMapper;
	
	@Autowired
	private MemberLabelRelationCustomMapper memberLabelRelationCustomMapper;
	
	@Autowired
	private MemberLabelMapper memberLabelMapper;
	
	@Autowired
	private MemberLabelGroupRelationMapper memberLabelGroupRelationMapper;
	
	@Autowired
	private MemberLabelGroupMapper memberLabelGroupMapper;
	
	@Autowired
	public void setMemberLabelRuleMapperMapper(MemberLabelRuleMapper memberLabelRuleMapper) {
		super.setDao(memberLabelRuleMapper);
		this.memberLabelRuleMapper = memberLabelRuleMapper;


	}

	
	
	public void implementAddmemberLabelRule(MemberLabelRule rule) {
			logger.info("开始处理符合规则ID:"+rule.getId() + "的用户start");
 		    MemberInfoCustomExample memberInfoCustomExample=new MemberInfoCustomExample();
		    MemberInfoCustomExample.MemberInfoCustomCriteria memberInfoCustomCriteria=memberInfoCustomExample.createCriteria();
		    memberInfoCustomCriteria.andDelFlagEqualTo("0").andStatusEqualTo("A");
		    if (rule.getSignDateBegin()!=null && rule.getSignDateEnd()!=null) {
		    	/*memberInfoCustomCriteria.andTypeEqualTo("1");*/
		    	memberInfoCustomCriteria.andCreateDateBetween(rule.getSignDateBegin(), rule.getSignDateEnd());
			}
		    if (rule.getLastLoginDateBegin()!=null && rule.getLastLoginDateEnd()!=null) {
		    	memberInfoCustomCriteria.andlastLoginDateEquals(rule.getLastLoginDateBegin(),rule.getLastLoginDateEnd());
			}
		    if (rule.getLastExpenseDateBegin()!=null && rule.getLastExpenseDateEnd()!=null) {
		    	memberInfoCustomCriteria.andlastExpenseDateEquals(rule.getLastExpenseDateBegin(), rule.getLastExpenseDateEnd());
			}
		    if (rule.getLoginDateBegin()!=null && rule.getLoginDateEnd()!=null) {
		    	memberInfoCustomCriteria.andloginDateEquals(rule.getLoginDateBegin(),rule.getLoginDateEnd());
			}
		    if (rule.getPayDateBegin()!=null && rule.getPayDateEnd()!=null) {//付款时间，付款金额，付款次数
		    	memberInfoCustomCriteria.andpayAmounEquals(rule.getPayDateBegin(),rule.getPayDateEnd(),
						rule.getPayCountBegin(),rule.getPayCountEnd(),
						rule.getPayAmountBegin(), rule.getPayAmountEnd());
			}
		    
		    if (rule.getPayDateBegin()==null && rule.getPayDateEnd()==null) {
				if (rule.getPayCountBegin()!=null && rule.getPayCountEnd()!=null) {
					memberInfoCustomCriteria.andpayLogCountEquals(rule.getPayCountBegin(),rule.getPayCountEnd());
				}
				if (rule.getPayAmountBegin()!=null && rule.getPayAmountEnd()!=null) {
					memberInfoCustomCriteria.andpayLogAmountEquals(rule.getPayAmountBegin(), rule.getPayAmountEnd());
				}
			}
		    
		    if ((rule.getPayLogCountBegin()!=null && rule.getPayLogCountEnd()!=null) && (rule.getPayCountBegin()==null && rule.getPayCountEnd()==null)) {
		    	memberInfoCustomCriteria.andpayLogCountEquals(rule.getPayLogCountBegin(),rule.getPayLogCountEnd());
			}
		    
		    if ((rule.getPayLogAmountBegin()!=null && rule.getPayLogAmountEnd()!=null) && (rule.getPayAmountBegin()==null && rule.getPayAmountEnd()==null)) {
		    	memberInfoCustomCriteria.andpayLogAmountEquals(rule.getPayLogAmountBegin(), rule.getPayLogAmountEnd());
			}


		    if (rule.getSportTypeCountBegin()!=null && rule.getSportTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getSportTypeCountBegin(),rule.getSportTypeCountEnd(),2);
		    	
			}
		    if (rule.getCostumeTypeCountBegin()!=null && rule.getCostumeTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getCostumeTypeCountBegin(),rule.getCostumeTypeCountEnd(),4);
		    	
			}
		    if (rule.getShoeTypeCountBegin()!=null && rule.getShoeTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getShoeTypeCountBegin(),rule.getShoeTypeCountEnd(),3);
		    	
			}
		    if (rule.getJewelTypeCountBegin()!=null && rule.getJewelTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getJewelTypeCountBegin(),rule.getJewelTypeCountEnd(),430);
		    	
			}
		    if (rule.getLiveTypeCountBegin()!=null && rule.getLiveTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getLiveTypeCountBegin(),rule.getLiveTypeCountEnd(),5);
		    	
			}
		    if (rule.getDigitalTypeCountBegin()!=null && rule.getDigitalTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getDigitalTypeCountBegin(),rule.getDigitalTypeCountEnd(),705);
		    	
			}
		    if (rule.getMakeupTypeCountBegin()!=null && rule.getMakeupTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getMakeupTypeCountBegin(),rule.getMakeupTypeCountEnd(),858);
		    	
			}
		    if (rule.getChildTypeCountBegin()!=null && rule.getChildTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getChildTypeCountBegin(),rule.getChildTypeCountEnd(),954);
		    	
			}
		    if (rule.getFoodTypeCountBegin()!=null && rule.getFoodTypeCountEnd()!=null) {
		    	memberInfoCustomCriteria.andproductTypeCountBeginEquals(rule.getFoodTypeCountBegin(),rule.getFoodTypeCountEnd(),1047);
		    	
			}
		    if (rule.getSvipYear()!=null) {
				memberInfoCustomCriteria.andsvipYearEquals(rule.getSvipYear());
			}
		    if (rule.getReturnGoodsRateMin()!=null && rule.getReturnGoodsRateMax()!=null) {
				memberInfoCustomCriteria.andReturnGoodsRateEquals(rule.getReturnGoodsRateMin(),rule.getReturnGoodsRateMax());
			}
		    if (rule.getRefundRateMin()!=null && rule.getRefundRateMax()!=null) {
				memberInfoCustomCriteria.andRefundRateEquals(rule.getRefundRateMin(),rule.getRefundRateMax());
			}
		    
		    MemberLabelRelationExample memberLabelRelationExample=new MemberLabelRelationExample();
			memberLabelRelationExample.createCriteria().andDelFlagEqualTo("0").andLabelIdEqualTo(rule.getLabelId());
            memberLabelRelationMapper.deleteByExample(memberLabelRelationExample);

		    int count=memberInfoCustomMapper.countByExample(memberInfoCustomExample);
		    logger.info("已获取标签会员总数:"+count);
            List<MemberInfoCustom> memberInfoCustoms;
		    if (count > 10000) {
				int num = (count) % 10000 == 0 ? (count / 10000) : (count / 10000 + 1);	
				logger.info("分批添加次数:"+num);
				for (int i = 1; i <= num; i++) {
                    long startTime = System.currentTimeMillis();
					memberInfoCustomExample.setLimitStart((i-1)*10000);
					memberInfoCustomExample.setLimitSize(10000);
					memberInfoCustoms=memberInfoCustomMapper.selectMemberInfoCustomExample(memberInfoCustomExample);
					if (memberInfoCustoms!=null && memberInfoCustoms.size()>0) {
						memberLabelRelationCustomMapper.insertionMemberLabelRelation(memberInfoCustoms, rule);

					}

                    long endTime = System.currentTimeMillis();
					logger.info("已添加次数:"+num + "用时：[" + (endTime - startTime) + "]毫秒");
				}
				
			}else {
						
				memberInfoCustoms=memberInfoCustomMapper.selectMemberInfoCustomExample(memberInfoCustomExample);
				if (memberInfoCustoms!=null && memberInfoCustoms.size()>0) {
					memberLabelRelationCustomMapper.insertionMemberLabelRelation(memberInfoCustoms, rule);
				}
			
			}
		     
		    MemberLabel memberLabel=memberLabelMapper.selectByPrimaryKey(rule.getLabelId());
			memberLabel.setUpdateDate(new Date());
			memberLabel.setStatus("2");
			memberLabelMapper.updateByPrimaryKeySelective(memberLabel);
			
			MemberLabelGroupRelationExample memberLabelGroupRelationExample=new MemberLabelGroupRelationExample();
			memberLabelGroupRelationExample.createCriteria().andDelFlagEqualTo("0").andLabelIdEqualTo(rule.getLabelId());
			List<MemberLabelGroupRelation> memberLabelGroupRelations=memberLabelGroupRelationMapper.selectByExample(memberLabelGroupRelationExample);
			if (memberLabelGroupRelations.size()>0 && memberLabelGroupRelations!=null) {
				for (MemberLabelGroupRelation memberLabelGroupRelation : memberLabelGroupRelations) {
					 MemberLabelGroup memberLabelGroup=memberLabelGroupMapper.selectByPrimaryKey(memberLabelGroupRelation.getLabelGroupId());
					 memberLabelGroup.setUpdateDate(new Date());
					 memberLabelGroupMapper.updateByPrimaryKey(memberLabelGroup);
					
				}
			}
			

		logger.info("符合规则ID:"+rule.getId() + "的用户处理完毕");
	}
 
	
	
}
