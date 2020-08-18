package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.IntegralGiveCustomMapper;
import com.jf.dao.IntegralGiveMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.SmsTemplateMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.IntegralGive;
import com.jf.entity.IntegralGiveExample;
import com.jf.entity.IntegralType;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.Sms;
import com.jf.entity.SmsTemplate;

@Service
@Transactional
public class IntegralGiveService extends BaseService<IntegralGive, IntegralGiveExample> {
	
    private static Logger logger = LoggerFactory.getLogger(IntegralGiveService.class);
	
	@Autowired
	private IntegralGiveMapper integralGiveMapper;
	
	@Autowired
	private IntegralGiveCustomMapper integralGiveCustomMapper;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private SmsTemplateMapper smsTemplateMapper;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	
	@Autowired
	private IntegralTypeService integralTypeService;
	
	@Autowired
	private MemberGroupService memberGroupService;
	
	@Autowired
	private GrowthDtlService growthDtlService;
	
	@Autowired
	private IntegralDtlService integralDtlService;
	

	@Autowired
	public void setIntegralGiveMapper(IntegralGiveMapper integralGiveMapper) {
		super.setDao(integralGiveMapper);
		this.integralGiveMapper = integralGiveMapper;
	}


	/**
	 * 积分
	 */
	public void integralGive(IntegralGive integralGive) {
		Integer giveType=integralGive.getType();
		
		Map<String, Object> dataMap=new HashMap<String, Object>();
		dataMap.put("integral", integralGive.getIntegral());
		dataMap.put("orderId", integralGive.getId());
		
		if(giveType==2){//指定会员组
			if(integralGive.getGroupId()==null||"".equals(integralGive.getGroupId())){
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,组Id值为空");
				return;
			}
			String[] groupIdsArray=integralGive.getGroupId().split(",");
			if(groupIdsArray.length>0){
				List<Integer> groupIds=new ArrayList<Integer>();
				for (int i = 0; i < groupIdsArray.length; i++) {
					try {
						groupIds.add(Integer.valueOf(groupIdsArray[i]));
					} catch (NumberFormatException e) {
						continue;
					}
				}
				if(groupIds.size()==0){
					logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,组Id值为空");
					return;
				}
				dataMap.put("memberGroupIds", groupIds);
			}else{
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,组Id值为空");
				return;
			}
		}
		if(giveType==3){//指定会员
			if(integralGive.getGroupId()==null||"".equals(integralGive.getGroupId())){
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,会员Id值为空");
				return;
			}
			String[] memberIdsArray=integralGive.getGroupId().split(",");
			if(memberIdsArray.length>0){
				List<Integer> memberIds=new ArrayList<Integer>();
				for (int i = 0; i < memberIdsArray.length; i++) {
					try {
						memberIds.add(Integer.valueOf(memberIdsArray[i]));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						continue;
					}
				}
				
				if(memberIds.size()==0){
					logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,会员Id值为空");
					return;
				}
				
				dataMap.put("memberIds", memberIds);
			}else{
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,会员Id值为空");
				return;
			}
		}
		if(giveType==4){//指定订单
			if(integralGive.getSubOrderCode()==null||"".equals(integralGive.getSubOrderCode())){
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,子订单号为空");
				return;
			}
			if(integralGive.getGroupId()==null||"".equals(integralGive.getGroupId())){
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,会员Id值为空");
				return;
			}
			String[] memberIdsArray=integralGive.getGroupId().split(",");
			if(memberIdsArray.length>0){
				List<Integer> memberIds=new ArrayList<Integer>();
				for (int i = 0; i < memberIdsArray.length; i++) {
					try {
						memberIds.add(Integer.valueOf(memberIdsArray[i]));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						continue;
					}
				}
				
				if(memberIds.size()==0){
					logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,会员Id值为空");
					return;
				}
				
				dataMap.put("memberIds", memberIds);
			}else{
				logger.error("赠送积分时,Id为"+integralGive.getId()+"的赠送审核表,会员Id值为空");
				return;
			}
		}
		//批量插入积分明细
		integralGiveCustomMapper.batchInsertIntegralDtl(dataMap);
		
		//批量更新账户积分
		integralGiveCustomMapper.batchUpdateMemberAccoutIntegral(dataMap);
		
		//更新积分赠送单状态为已赠送
		IntegralGive integralGive4Update=new IntegralGive();
		integralGive4Update.setId(integralGive.getId());
		integralGive4Update.setAuditStatus("3");
		integralGiveMapper.updateByPrimaryKeySelective(integralGive4Update);
		
		
		//给会员发送短信
		if((giveType==3||giveType==4)&&integralGive.getSmsTemplateId()!=null){
			
			
			List<Integer> memberIds=(List<Integer>)dataMap.get("memberIds");
			
			
			if(memberIds!=null&&memberIds.size()>0){
				MemberInfoExample memberInfoExample=new MemberInfoExample();
				memberInfoExample.createCriteria().andDelFlagEqualTo("0").andIdIn(memberIds);
				List<MemberInfo> memberInfos=memberInfoMapper.selectByExample(memberInfoExample);
				
				//获取短信模版
				SmsTemplate smsTemplate=smsTemplateMapper.selectByPrimaryKey(integralGive.getSmsTemplateId());
				
				BigDecimal money=new BigDecimal(integralGive.getIntegral()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_FLOOR);
				
				String smsContent=smsTemplate.getContent().replace("{1}", integralGive.getIntegral().toString()).replace("{2}", money.toString());
				
				Sms sms;
				for(MemberInfo memberInfo:memberInfos){
					if(!StringUtil.isEmpty(memberInfo.getMobile())){
						try {
							sms=new Sms();
							sms.setMobile(memberInfo.getMobile());
							sms.setSmsType("3");
							sms.setContent(smsContent);
							sms.setMemberId(memberInfo.getId());
							SmsSendThread smsSendThread=new SmsSendThread();
							smsSendThread.setSms(sms);
							smsSendThread.start();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.info("会员"+memberInfo.getId()+"积分赠送后发送短信失败");
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
	}
	
	
	class SmsSendThread extends Thread{
		
		private Sms sms;
		
		public Sms getSms() {
			return sms;
		}
		public void setSms(Sms sms) {
			this.sms = sms;
		}
		@Override
		public void run() {
			try {
				smsService.sendImmediately(sms);
			} catch (Exception e) {
				logger.info("------短信发送失败----------");
				e.printStackTrace();
			}
		}
	}
	
	public void giftIntegral(Integer memberId,BigDecimal totalPayAmount, Integer subOrderId, CombineOrder combineOrder) {
		MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
		Date payDate = combineOrder.getPayDate();
		String payBeginDate = "2018-2-12 00:00:00";
		String payEndDate = "2018-2-22 23:59:59";
		Date begin = DateUtil.getDate(payBeginDate);
		Date end = DateUtil.getDate(payEndDate);
		String remarks = null;
		//购物赠送积分比例
		Integer iChargr = 0;
		//积分比例成长值比例
		Integer gChargr = 0;
		if(memberInfo != null && !memberInfo.getType().equals(Const.MEMBER_INFO_TYPE_TOURIST)){
			//获取赠送积分比例
			IntegralType integralType = integralTypeService.findModel(Integer.valueOf(Const.INTEGRAL_TYPE_SHOPPONG));
			if(integralType != null){
				if(integralType.getIntType().equals("2")){
					iChargr = integralType.getiCharge() == null ? 0 : integralType.getiCharge();
				}
				if(integralType.getGrowType().equals("2")){
					gChargr = integralType.getgCharge() == null ? 0 : integralType.getgCharge();
				}
			}
		}
		//订单交易成功，计算会员剩余积分 = 会员账户积分 +赠送积分(实付金额/100)-使用的积分数量
		MemberAccountExample memberAccountExample = new MemberAccountExample();
		memberAccountExample.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(memberAccountExample);
		if(CollectionUtils.isNotEmpty(memberAccounts)){
			MemberAccount memberAccount = memberAccounts.get(0);
			//会员账户积分
			Integer memberIntegral = memberAccount.getIntegral();
			//赠送积分 
			Integer giveIntegral = 0;
			if(iChargr != 0){
				giveIntegral = totalPayAmount.multiply(new BigDecimal(iChargr)).intValue();
				if(payDate.after(begin) && payDate.before(end)){
					//春节期间双倍赠送
					giveIntegral = giveIntegral * 2;
					remarks = "春节期间双倍赠送";
				}
			}
			//赠送成长值
			Integer giveGrowth = 0;
			if(gChargr != 0){
				giveGrowth = totalPayAmount.multiply(new BigDecimal(gChargr)).intValue();
			}
			Integer integral = memberIntegral + giveIntegral;
			
			//用户成长值 = 用户成长值 + 赠送成长值
			Integer gValue = memberAccount.getgValue();
			if(giveGrowth > 0){
				gValue = gValue+giveGrowth;
				//更改level
				memberGroupService.updateMemberGroup(gValue,memberAccount.getMemberId());
				//插入成长值明细
				growthDtlService.addGrowthDtl(gValue,giveGrowth,memberAccount.getId(),
						memberAccount.getMemberId(),subOrderId,Const.INTEGRAL_TYPE_SHOPPONG);
			}
			if(integral >= 0){
				memberAccount.setIntegral(integral);
			}else{
				memberAccount.setIntegral(0);
			}
			memberAccount.setgValue(gValue);
			memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
			
			// 购物赠送积分
			if (giveIntegral > 0) {
				integralDtlService.addIntegralDtl(memberAccount.getId(), Const.INTEGRAL_TALLY_MODE_INCOME,
						Const.INTEGRAL_TYPE_SHOPPONG, giveIntegral, memberAccount.getIntegral(),
						subOrderId, memberId,remarks,"2");
			}
		}
	}
	public static void main(String[] args) {
		String a="aa";
		String[] b=a.split(",");
		//System.out.println(b.length);
		System.out.println(new BigDecimal(2.56189).setScale(2, BigDecimal.ROUND_CEILING));
		
	}


}
