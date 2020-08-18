package com.jf.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import com.alipay.api.domain.FaceSearchAnonymousUserInfo;
import com.jf.common.ext.RegCondition;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberCouponExample;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.service.SysParamCfgService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Options.Builder;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;
import com.jf.service.*;
import java.util.*;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月13日 下午3:22:51 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@RegCondition
@Component
public class JpushTask {
	
	private static Logger logger = LoggerFactory.getLogger(JpushTask.class);
	
	private static final String appKey = Config.getProperty("appKey");
	private static final String masterSecret = Config.getProperty("masterSecret");
	
	@Resource
	private MemberRemindService memberRemindService;
	@Resource
	private ProductService productService;
	@Resource
	private SysAppMessageService sysAppMessageService;
	@Resource
	private SysAppMessageMemberService sysAppMessageMemberService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private JgContentService jgContentService;
	@Resource
	private SignSendMsgCnfService signSendMsgCnfService;
	@Resource
	private MemberSignInService memberSignInService;
	@Resource
	private MemberExtendService memberExtendService;
	@Resource
	private CommonService commonService;
	
	@Autowired
	private TaskSendMemberService taskSendMemberService;
	
	@Autowired
	private TaskMarketingService taskMarketingService;
	
	@Autowired
	private TaskActivitySelectionService taskActivitySelectionService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Scheduled(cron="0 0/30 * * * ?")
	public void pushAll(){
		try {
			List<MemberRemindCustom> memberReminds = memberRemindService.getRemindList();
			String alert = "";
			if(CollectionUtils.isNotEmpty(memberReminds)){
				logger.info("扫描到的推送数：{}", memberReminds.size());
				Map<String, String> extras = new HashMap<String, String>();
				List<String> aliasList = null;
				Map<String,Object> params = new HashMap<String,Object>();
				for (MemberRemindCustom memberRemindCustom : memberReminds) {
					aliasList = new ArrayList<String>();
					Integer remindId = memberRemindCustom.getRemindId();
					String remindType = memberRemindCustom.getRemindType();
					params.put("remindId", remindId);
					params.put("remindType", remindType);
					List<MemberRemindCustom> list = memberRemindService.getMemberList(params);
					
					String source = "";
					SysAppMessage sysAppMessage = new SysAppMessage();
					SysAppMessageMember sysAppMessageMember = new SysAppMessageMember();
					if(remindType.equals("1")){//品牌
						source = memberRemindCustom.getSource();
						
						if(source.equals("1")){
							alert = memberRemindCustom.getActivityAreaName()+"";
							sysAppMessage.setLinkType(Const.APP_MESSAGE_ACTIVITY_AREA);
							sysAppMessage.setLinkId(memberRemindCustom.getActivityAreaId());
							extras.put("id", memberRemindCustom.getActivityAreaId()+"");
						}else{
							alert = memberRemindCustom.getActivityName()+"";
							sysAppMessage.setLinkType(Const.APP_MESSAGE_ACTIVITY);
							sysAppMessage.setLinkId(memberRemindCustom.getActivityId());
							extras.put("id", memberRemindCustom.getActivityId()+"");
							
						}
						extras.put("source", source);
						extras.put("name", alert);
						extras.put("activityAreaId", memberRemindCustom.getActivityAreaId()+"");
						extras.put("activityId", memberRemindCustom.getActivityId()+"");
					}else{//商品
						alert = memberRemindCustom.getProductName();
						sysAppMessage.setLinkType(Const.APP_MESSAGE_PRODUCT);
						sysAppMessage.setLinkId(remindId);
						extras.put("source", source);
						extras.put("name", alert);
						extras.put("activityAreaId", memberRemindCustom.getActivityAreaId() == null ? "" : memberRemindCustom.getActivityAreaId()+"");
						extras.put("activityId", memberRemindCustom.getActivityId() == null ? "" : memberRemindCustom.getActivityId()+"");
						extras.put("id", remindId+"");
					}
					sysAppMessage.setType(Const.APP_MESSAGE_TYPE_SYSTEM_MESSAGE);
					sysAppMessage.setTitle(Const.APP_MSG_TITLE_SALE_REMIND);
					sysAppMessage.setContent(alert);
					sysAppMessage.setPushFlag("1");
					sysAppMessage.setCreateDate(new Date());
					sysAppMessage.setDelFlag("0");
					sysAppMessageService.insertSelective(sysAppMessage);
					if(CollectionUtils.isNotEmpty(list)){
						for (MemberRemindCustom memberRemindCustom2 : list) {
							//是否接受推送(1是 2否)
							if(memberRemindCustom2.getIsAcceptPush().equals("1")){
								aliasList.add(memberRemindCustom2.getMemberId().toString());
							}
							sysAppMessageMember.setAppMessageId(sysAppMessage.getId());
							sysAppMessageMember.setMemberId(memberRemindCustom2.getMemberId());
							sysAppMessageMember.setCreateDate(new Date());
							sysAppMessageMember.setDelFlag("0");
							sysAppMessageMemberService.insertSelective(sysAppMessageMember);
						}
					}
					
					extras.put("remark1", "");
					extras.put("remark2", "");
					extras.put("remark3", "");
					extras.put("title", "开售提醒");
					//推送消息
					if(!StringUtil.isBlank(alert) && CollectionUtils.isNotEmpty(aliasList)){
						logger.info("推送开始: start");
						sendAlias(alert, aliasList, extras);
						logger.info("推送介绍: end");
					}
				}
			}
			
		} catch (Exception e) {
			logger.info("推送异常",e.getMessage());
			e.printStackTrace();
		}
	}


	@Scheduled(cron="0 13 10 * * ?")
	public void useCouponReminder(){
		try {
			int everySendNum = 100000;
			//获取所有新人优惠券数据
			SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
			sysParamCfgExample.createCriteria().andParamCodeIn(Arrays.asList(new String[]{"APP_EXPIRED_COUPON_IDS","APP_COUPON_ID_BY_RED"}));
			List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(sysParamCfgExample);
			//获取所有优惠券ID
			List<Integer> couponIdList = new ArrayList<>();
			for (SysParamCfg sysParamCfg:sysParamCfgList) {
				if(StringUtils.equals(sysParamCfg.getParamCode(),"APP_EXPIRED_COUPON_IDS")){
					String[] split = sysParamCfg.getParamValue().split(",");
					for (String couponId : split){
						couponIdList.add(Integer.parseInt(couponId));
					}
				}else {
					couponIdList.add(Integer.parseInt(sysParamCfg.getParamValue()));
				}
			}
			List<String> aliasList= new ArrayList<String>();
			List<Integer> intAliasList = new ArrayList<>();
			//查询5天之前领取新人优惠券未使用的用户
			boolean hasMoreRecord = true;
			while (hasMoreRecord){
				intAliasList = memberCouponService.selectByExampleCustom(couponIdList,everySendNum);
				for (Integer memberId : intAliasList) {
					aliasList.add(String.valueOf(memberId));
				}
				Map<String, String> extras = new HashMap<String, String>();
				String alert = "小主，您的500元优惠券已经在你账户等候多时了，赶紧来使用吧！";
				if(CollectionUtils.isNotEmpty(intAliasList)) {
					logger.info("新人优惠券数据极光推送，扫描到的推送用户：{}", intAliasList.size());
					SysAppMessage sysAppMessage = new SysAppMessage();
					SysAppMessageMember sysAppMessageMember = new SysAppMessageMember();
					for (Integer memberId : intAliasList) {
						//app消息记录表
						sysAppMessage.setLinkType(Const.APP_MESSAGE_NOT_URL);
						sysAppMessage.setLinkId(0);
						sysAppMessage.setType(Const.APP_MESSAGE_TYPE_SYSTEM_MESSAGE);
						sysAppMessage.setTitle(Const.APP_MSG_TITLE_NOT_USED_FIVE_HUNDRED);
						sysAppMessage.setContent(alert);
						sysAppMessage.setPushFlag("1");
						sysAppMessage.setCreateDate(new Date());
						sysAppMessage.setDelFlag("0");
						sysAppMessageService.insertSelective(sysAppMessage);

						//app系统消息会员表
						sysAppMessageMember.setAppMessageId(sysAppMessage.getId());
						sysAppMessageMember.setMemberId(memberId);
						sysAppMessageMember.setCreateDate(new Date());
						sysAppMessageMember.setDelFlag("0");
						sysAppMessageMemberService.insertSelective(sysAppMessageMember);
					}
					extras.put("source", "3");
					extras.put("id", "");
					extras.put("activityAreaId", "");
					extras.put("remark1", "");
					extras.put("activityId", "");
					extras.put("remark2", "");
					extras.put("remark3", "");
					extras.put("title", "新人500元优惠券未使用提醒");
					extras.put("name", "新人500元优惠券未使用提醒");
					logger.info("新人优惠券数据极光推送: start");
					JpushTask.sendPlatformAndAlias(alert, aliasList, extras, true, Const.PLATFORM_ALL, null);
					logger.info("新人优惠券数据极光推送: end");
					//推送完对发送消息提醒的用户做更新
					memberExtendService.updateCouponRemindStatus(intAliasList);
				}
				aliasList.clear();
				hasMoreRecord = intAliasList.size()==everySendNum;
			}
		}catch (Exception e){
			logger.info("新人优惠券数据极光推送，推送异常",e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 每天11点定时推送优惠券过期给会员
	 * 0 0 11 * * ?
	 * 方法描述 ：
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月11日 下午1:51:33 
	 * @version
	 */
	@Scheduled(cron="0 8 11 * * ?")
	public void pushMemberCouponInfo(){
		logger.info("推送会员优惠券过期提醒: start");
		jgContentService.getPush();
		logger.info("推送会员优惠券过期提醒: end");
	}
	
	
	
	
	/**
	 * 
	 * 方法描述 ：每天晚上3点定时获取会员优惠券过期推送记录
	 * 0 0 3 * * ?
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月11日 下午1:48:31 
	 * @version
	 */
	@Scheduled(cron="0 0 3 * * ?")
	public void getMemberCouponInfoForJG(){
		try {
			List<JgContentCustom> memberCouponCustoms = memberCouponService.getMemberCouponInfoForJG();
			Date date = new Date();
			Date sendDate = DateUtil.addDay(date, 1);
			List<JgContent> list = new ArrayList<JgContent>();
			if(CollectionUtils.isNotEmpty(memberCouponCustoms)){
				logger.info("扫描到的会员优惠券推送数：{}", memberCouponCustoms.size());
				for (JgContentCustom memberCouponCustom : memberCouponCustoms) {
					Integer memberId = memberCouponCustom.getMemberId();
					Integer couponNum = memberCouponCustom.getCouponNum();
					if(memberId != null){
						JgContent jg = new JgContent();
						jg.setType("1");
						jg.setMemberId(memberId);
						jg.setCouponNum(couponNum);
						jg.setContent("您有优惠券即将过期，请尽快使用，优惠多多不容错过哦！");
						jg.setSendDate(sendDate);
						jg.setCreateDate(date);
						jg.setSendDate(sendDate);
						list.add(jg);
					}
				}
				if(CollectionUtils.isNotEmpty(list)){
					memberCouponService.insertBatchJg(list);
				}
			}
			
		} catch (Exception e) {
			logger.info("获取会员优惠券过期推送记录异常",e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 方法描述 ：签到推送）——每天10：30点推送
	 * @author  chenwf: 
	 * @date 创建时间：2019年6月11日 下午1:09:47 
	 * @version
	 */
	@Scheduled(cron="0 33 10 * * ?")
	//@Scheduled(cron="0 0/5 9-19 * * ?")
	public synchronized void findSignInRemindMemberPush(){
		logger.info("签到提醒推送: start");
		memberExtendService.findSignInRemindMember(0,1000);
		logger.info("签到提醒推送: end");
	}
	
	
	/**
	 * 点对点推送数据给用户
	 * 0 0 11 * * ?
	 * 方法描述 ：
	 * @author  chenwf: 
	 * @date 创建时间：2018年2月11日 下午1:51:33 
	 * @version
	 */
	@Scheduled(cron="0 0/5 * * * ?")
	public synchronized void pushPointToPoint(){
		jgContentService.pushPointToPoint();
	}
	
	/**
	 * 
	 * @MethodName: sendTaskMarketing
	 * @Description: (营销中心极光任务推送)
	 * @author Pengl
	 * @date 2019年8月23日 上午9:54:45
	 */
	@Scheduled(cron="0 2/5 * * * ?")
	public synchronized void sendTaskMarketing() {

		logger.info("营销中心极光任务推送start：" + DateUtil.getStandardDateTime());

		int startNum = 1;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", "1");
		paramMap.put("status", "4");
		List<Task> taskIdList = taskSendMemberService.sendTaskList(paramMap);
		if(taskIdList.size() > 0){
			try {
				for(Task task : taskIdList) {
					TaskMarketingExample taskMarketingExample = new TaskMarketingExample();
					taskMarketingExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(task.getId());
					List<TaskMarketing> taskMarketingList = taskMarketingService.selectByExample(taskMarketingExample);
					if(taskMarketingList != null && taskMarketingList.size() > 0 ) {
						startNum = taskSendMemberService.sendTaskMarketing(task, taskMarketingList.get(0), 0, 1000, startNum);
						if(startNum > Const.MAX_PAGE) {
							return;
						}
					}
				}

				logger.info("营销中心极光任务推送end：" + DateUtil.getStandardDateTime());

				if(startNum <= Const.MAX_PAGE) {
					sendTaskActivitySelection(startNum);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			sendTaskActivitySelection(startNum);
		}

	}

	/**
	 *
	 * @MethodName: sendTaskMarketing
	 * @Description: (活动精选极光任务推送)
	 * @author XDD
	 * @date 2019年8月26日
	 */
	private synchronized void sendTaskActivitySelection(Integer pushNumber) {

		logger.info("活动精选极光任务推送start：" + DateUtil.getStandardDateTime());

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("type", "2");
			paramMap.put("status", "3");
			List<Task> taskIdList = taskSendMemberService.sendTaskList(paramMap);
			for(Task task : taskIdList) {
				TaskActivitySelectionExample taskActivitySelectionExample = new TaskActivitySelectionExample();
				taskActivitySelectionExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(task.getId());
				List<TaskActivitySelection> taskActivitySelectionList = taskActivitySelectionService.selectByExample(taskActivitySelectionExample);
				if(taskActivitySelectionList != null && taskActivitySelectionList.size() > 0 ) {
					//创建APP消息记录表实体
					TaskActivitySelection taskActivitySelection = taskActivitySelectionList.get(0);
					SysAppMessage sysAppMessage = new SysAppMessage();
					sysAppMessage.setType("3");
					sysAppMessage.setTitle(task.getName());
					sysAppMessage.setContent(task.getContent());
					sysAppMessage.setPic(taskActivitySelection.getCoverPic());
					sysAppMessage.setLinkType("11");//活动任务精选
					sysAppMessage.setLinkId(taskActivitySelection.getId());
					sysAppMessage.setCreateBy(task.getCreateBy());
					sysAppMessage.setCreateDate(new Date());
					sysAppMessage.setDelFlag("0");
					//是否插入SysAppMessage的状态
					Boolean flag = true;
					pushNumber = taskSendMemberService.sendTaskActivitySelection(task, taskActivitySelection, sysAppMessage, flag, pushNumber,0, 1000);
					if (pushNumber > Const.MAX_PAGE ){
						return;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("活动精选极光任务推送end：" + DateUtil.getStandardDateTime());

	}
	
	
	public static void sendAlias(String alert,List<String> aliasList,Map<String, String> extras){
		JPushClient jPushClient = new JPushClient(masterSecret, appKey);
		PushPayload pushPayload = allPlatformAndAlias(alert,aliasList, extras);
		try {
			PushResult result = jPushClient.sendPush(pushPayload);
			logger.info("推送响应",result);
		} catch (APIConnectionException e) {
			logger.info("推送异常",e);
		} catch (APIRequestException e) {
			logger.info("推送异常",e);
			logger.info("Error response from JPush server. Should review and fix it. "+e);
			logger.info("HTTP Status: "+e.getMessage());
			logger.info("Error Code: "+e.getErrorCode());
			logger.info("Error Message: "+e.getErrorMessage());
			logger.info("Msg ID: "+e.getMsgId());
		}
	}
	
	private static PushPayload allPlatformAndAlias(String alert,List<String> aliasList,Map<String, String> extras){
		String title = "";
		if(extras.containsKey("title")){
			title = extras.get("title");
		}
		String oppoAction = "com.xgbuy.xg.activities.OppoReceiveActivity_";
		String huweiActivity = "com.xgbuy.xg.activities.HuaweiReceiveActivity_";
		IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody(title, null, alert).build();
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(aliasList))
				.setNotification(
							Notification
								.newBuilder()
								.addPlatformNotification(AndroidNotification.newBuilder().setUriAction(oppoAction).setUriActivity(huweiActivity).setTitle(title).setAlert(alert).addExtras(extras).build())
								.addPlatformNotification(IosNotification.newBuilder().setAlert(iosAlert).addExtras(extras).build())
								.build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build())
				.build();
	}
	
	public static PushResult sendPlatformAndAlias(String alert, List<String> aliasList, Map<String, String> extras, boolean apnsProduction, String platform, Integer bigPushDuration){
		JPushClient jPushClient = new JPushClient(masterSecret, appKey);
		PushPayload pushPayload = platformAndAlias(alert, aliasList, extras, apnsProduction, platform, bigPushDuration);
		PushResult result = null;
		try {
			result = jPushClient.sendPush(pushPayload);
			logger.info("推送响应",result);
		} catch (APIConnectionException e) {
			logger.info("推送异常",e);
		} catch (APIRequestException e) {
			logger.info("推送异常",e);
			logger.info("Error response from JPush server. Should review and fix it. "+e);
			logger.info("HTTP Status: "+e.getMessage());
			logger.info("Error Code: "+e.getErrorCode());
			logger.info("Error Message: "+e.getErrorMessage());
			logger.info("Msg ID: "+e.getMsgId());
		}
		return result;
	}
	
	private static PushPayload platformAndAlias(String alert, List<String> aliasList, Map<String, String> extras, boolean apnsProduction, String platform, Integer bigPushDuration){
		String title = "";
		if(extras.containsKey("title")){
			title = extras.get("title");
		}
		String oppoAction = "com.xgbuy.xg.activities.OppoReceiveActivity_";
		String huweiActivity = "com.xgbuy.xg.activities.HuaweiReceiveActivity_";
		IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody(title, null, alert).build();
		cn.jpush.api.push.model.notification.Notification.Builder notificationBuilder = Notification.newBuilder();
		Builder optionsBuilder = Options.newBuilder().setApnsProduction(apnsProduction);
		if(!StringUtil.isEmpty(platform) && "android".equals(platform) ) {
			notificationBuilder.addPlatformNotification(AndroidNotification.newBuilder().setUriAction(oppoAction).setUriActivity(huweiActivity).setTitle(title).setAlert(alert).addExtras(extras).build());
		}else if(!StringUtil.isEmpty(platform) && "ios".equals(platform) ) {
			if(StringUtil.isEmpty(extras.get("source")) && StringUtil.isEmpty(extras.get("id")) ) {
				notificationBuilder.addPlatformNotification(IosNotification.newBuilder().setAlert(iosAlert).build());
			}else{
				notificationBuilder.addPlatformNotification(IosNotification.newBuilder().setAlert(iosAlert).addExtras(extras).build());
			}
		}else {
			notificationBuilder.addPlatformNotification(AndroidNotification.newBuilder().setUriAction(oppoAction).setUriActivity(huweiActivity).setTitle(title).setAlert(alert).addExtras(extras).build())
					.addPlatformNotification(IosNotification.newBuilder().setAlert(iosAlert).addExtras(extras).build());
		}
		if(bigPushDuration != null ) {
			optionsBuilder.setBigPushDuration(bigPushDuration);
		}
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(aliasList))
				.setNotification(notificationBuilder.build())
				.setOptions(optionsBuilder.build())
				.build();
	}
	
	
	
}
