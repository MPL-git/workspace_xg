package com.jf.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.JgContentCustomMapper;
import com.jf.dao.JgContentMapper;
import com.jf.entity.JgContent;
import com.jf.entity.JgContentCustom;
import com.jf.entity.JgContentExample;
import com.jf.entity.SysAppMessageMember;
import com.jf.task.JpushTask;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月9日 下午5:01:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
public class JgContentService extends BaseService<JgContent, JgContentExample> {
	private static Logger logger = LoggerFactory.getLogger(JgContentService.class);
	
	@Autowired
	private JgContentMapper jgContentMapper;
	@Autowired
	private JgContentCustomMapper jgContentCustomMapper;
	
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private SysAppMessageService sysAppMessageService;
	@Resource
	private SysAppMessageMemberService sysAppMessageMemberService;
	@Resource
	private JgContentService jgContentService;
	@Resource
	private CommonService commonService;
	
	@Autowired
	public void setJgContentMapper(JgContentMapper jgContentMapper) {
		this.setDao(jgContentMapper);
		this.jgContentMapper = jgContentMapper;
	}
	

	public void getPush() {
		Date date = new Date();
		Date begin = DateUtil.getDateAfterAndBeginTime(date, 1);
		Date end = DateUtil.getDateAfterAndEndTime(date, 2);
		List<JgContent> jgContents = new ArrayList<JgContent>();
		try {
			JgContentExample jgExample = new JgContentExample();
			jgExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andTypeEqualTo("1")
			.andSendNumLessThan(2).andSendDateLessThanOrEqualTo(end).andSendDateGreaterThanOrEqualTo(begin);
			jgExample.setLimitStart(0);
			jgExample.setLimitSize(1000);
			jgContents = selectByExample(jgExample);
			Map<String, String> extras = new HashMap<String, String>();

			if(CollectionUtils.isNotEmpty(jgContents)){
				logger.info("扫描到的会员推送数：{}", jgContents.size());
				//推送消息
				try {
					String alert = jgContents.get(0).getContent();
					Integer systemAppMsgId = sysAppMessageService.createAppSystemMsg(Const.APP_MSG_TITLE_COUPON_EXPIRED,alert,Const.APP_MESSAGE_TYPE_SYSTEM_MESSAGE,Const.APP_MESSAGE_NOT_URL);
					List<String> aliasList = new ArrayList<String>();
					for (JgContent jg : jgContents) {
						if(!aliasList.contains(jg.getMemberId().toString())){
							aliasList.add(jg.getMemberId().toString());
						}
					}
					extras.put("source", "3");
					extras.put("name", alert);
					extras.put("activityAreaId", "");
					extras.put("activityId", "");
					extras.put("id", "");
					extras.put("remark1", "");
					extras.put("remark2", "");
					extras.put("remark3", "");
					extras.put("title", "优惠券过期提醒");
					logger.info("推送开始: start");
					JpushTask.sendAlias(alert, aliasList, extras);
					logger.info("推送介绍: end");
					Map<String,Object> map = new HashMap<String,Object>();
					List<Integer> jgIds = new ArrayList<Integer>();
					for (JgContent jg1 : jgContents) {
						jgIds.add(jg1.getId());
					}
					if(CollectionUtils.isNotEmpty(jgIds)){
						map.put("sendNum", 1);
						map.put("status", "1");
						map.put("updateDate", date);
						map.put("jgIds", jgIds);
						batchUpdateJg(map);
					}
					//插入系统消息
					aliasList.clear();
					List<SysAppMessageMember> memberMsgs = new ArrayList<SysAppMessageMember>();
					for (JgContent jg : jgContents) {
						if(!aliasList.contains(jg.getMemberId().toString())){
							SysAppMessageMember sysAppMessageMember = new SysAppMessageMember();
							sysAppMessageMember.setAppMessageId(systemAppMsgId);
							sysAppMessageMember.setMemberId(jg.getMemberId());
							sysAppMessageMember.setCreateDate(date);
							sysAppMessageMember.setDelFlag("0");
							memberMsgs.add(sysAppMessageMember);
						}
					}
					if(CollectionUtils.isNotEmpty(memberMsgs)){
						sysAppMessageService.insertBatchForMemberMsg(memberMsgs);
					}
					getPush();
				} catch (Exception e) {
					e.printStackTrace();
					Map<String,Object> map = new HashMap<String,Object>();
					List<Integer> jgIds = new ArrayList<Integer>();
					for (JgContent jg1 : jgContents) {
						jgIds.add(jg1.getId());
					}
					if(CollectionUtils.isNotEmpty(jgIds)){
						map.put("sendNum", 1);
						map.put("status", "2");
						map.put("updateDate", date);
						map.put("jgIds", jgIds);
						batchUpdateJg(map);
					}
					getPush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void batchUpdateJg(Map<String, Object> map) {
		
		jgContentCustomMapper.batchUpdateJg(map);
	}



	public void pushPointToPoint() {
		String content = "您的《新星计划协议》即将过期，请尽快续签。";
		Integer systemAppMsgId = sysAppMessageService.createAppSystemMsg(Const.APP_MESSAGE_NOVA_PLAN_INDEX,content,"1",Const.APP_MESSAGE_NOVA_PLAN_INDEX);
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("date", "2019-06-11 00:00:00");
		List<JgContentCustom> customs = jgContentCustomMapper.getPushPointToPointList(paramsMap);
		if(CollectionUtils.isNotEmpty(customs)){
			for (JgContentCustom model : customs) {
				Integer id = model.getId();
				String type = model.getType();
				Integer bizId = model.getBizId();
				String proStatus = model.getProStatus();
				BigDecimal amount = model.getAmount();
				String expressNo = model.getExpressNo();
				String expressCompany = model.getExpressCompany();
				Integer memberId = model.getMemberId();
				String subOrderCode = model.getSubOrderCode();
				content = "";
				String title = "";
				String titleStr = "";
				String linkType = "";
				String source = "";
				String linkUrl = "";
				try {
					if("2".equals(type)){
						linkType = Const.APP_MESSAGE_SERVICE_ORDER;
						source = "4";
						if("A2".equalsIgnoreCase(proStatus)){
							content = "商家已经同意了您的退款申请。";
							title = Const.APP_MSG_TITLE_REFUND_APPLY;
						}else if("A3".equalsIgnoreCase(proStatus)){
							content = "商家已经拒绝了您的退款申请，请点击查看更多详情。";
							title = Const.APP_MSG_TITLE_REFUND_APPLY;
						}else if("B2".equalsIgnoreCase(proStatus) || "C2".equalsIgnoreCase(proStatus)){
							content = "商家已经同意了您的退货/换货申请，请尽快寄回商品。";
							title = Const.APP_MSG_TITLE_CUSTOMER_SERVER_APPLY;
						}else if("B3".equalsIgnoreCase(proStatus) || "C3".equalsIgnoreCase(proStatus)){
							content = "商家已经拒绝了您的退货/换货申请，请点击查看更多详情。";
							title = Const.APP_MSG_TITLE_CUSTOMER_SERVER_APPLY;
						}else if("B6".equalsIgnoreCase(proStatus)){
							content = "因商品原因，商家已经拒绝了退款，请点击查看更多详情。";
							title = Const.APP_MSG_TITLE_REFUND;
						}else if("C6".equalsIgnoreCase(proStatus)){
							content = "因商品原因，商家已经拒绝了换货，请点击查看更多详情。";
							title = Const.APP_MSG_TITLE_EXCHANGE_REMIND;
						}else if("C7".equalsIgnoreCase(proStatus)){
							content = "商家同意您换货并已寄件，运单号："+(expressCompany+expressNo)+"，请注意查收。";
							title = Const.APP_MSG_TITLE_EXCHANGE_REMIND;
						}else if("B7".equalsIgnoreCase(proStatus) || "A4".equalsIgnoreCase(proStatus)){
							content = "您的"+amount.setScale(2, BigDecimal.ROUND_DOWN)+"元退款有新的进度，请点击查看更多详情。";
							title = Const.APP_MSG_TITLE_REFUND;
						}
					}else if("3".equals(type)){
						source = "6";
						linkType = Const.APP_MESSAGE_SUB_ORDER;
						content = "您的订单【"+subOrderCode+"】已经发货啦，运单号："+expressCompany+expressNo+"。";
						title = Const.APP_MSG_TITLE_MCHT_SHIPPING;
					}else if("4".equals(type)){
						source = "5";
						linkType = Const.APP_MESSAGE_APPEAL_ORDER;
						content = "您的投诉有新的进度，请点击查看更多详情。";
						title = Const.APP_MSG_TITLE_APPEALY;
					}else if("5".equals(type)){
						source = "7";
						linkUrl = commonService.getCloumnLinkUrl("", "9");
						linkType = Const.APP_MESSAGE_APPEAL_ORDER;
						content = "您的《新星计划协议》即将过期，请尽快续签。";
						title = Const.APP_MSG_TITLE_NOVA_PLAN;
					}
					if(!StringUtil.isBlank(title)){
						titleStr = DataDicUtil.getStatusDesc("SYS_APP_MESSAGE", "TITLE", title);
						if(StringUtil.isBlank(titleStr)){
							titleStr = "";
						}
						
						if("5".equals(type)){
							SysAppMessageMember sysAppMessageMember = new SysAppMessageMember();
							sysAppMessageMember.setAppMessageId(systemAppMsgId);
							sysAppMessageMember.setMemberId(memberId);
							sysAppMessageMember.setCreateDate(new Date());
							sysAppMessageMember.setDelFlag("0");
						}else{
							sysAppMessageService.add("2", title, content, linkType, bizId, memberId, null, "1");
						}
						JgContent jg = new JgContent();
						jg.setType(type);
						jg.setBizId(id);
						jg.setMemberId(memberId);
						jg.setStatus("1");
						jg.setCouponNum(1);
						jg.setContent(content);
						jg.setSendDate(new Date());
						jg.setCreateDate(new Date());
						jg.setDelFlag("0");
						insertSelective(jg);
						
						Map<String, String> extras = new HashMap<String, String>();
						extras.put("id", bizId+"");
						extras.put("source", source);
						extras.put("name", content);
						extras.put("activityAreaId", "");
						extras.put("activityId", "");
						extras.put("remark1", linkUrl);
						extras.put("remark2", "");
						extras.put("remark3", "");
						extras.put("title", titleStr);
						logger.debug("极光点对点推送开始: start");
						JpushTask.sendAlias(content, Arrays.asList(memberId+""), extras);
						logger.debug("极光点对点推送结束: end");
					}
				} catch (Exception e) {
					logger.info("极光点对点推送失败：会员【"+memberId+"】->bizId【"+bizId+"】");
				}
			}
		}
	}

}
