package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Coupon;
import com.jf.entity.MemberCoupon;
import com.jf.entity.Sms;
import com.jf.entity.SmsExample;
import com.jf.entity.Task;
import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsExample;
import com.jf.service.CouponService;
import com.jf.service.SmsService;
import com.jf.service.TaskSendMemberService;
import com.jf.service.TaskSendSmsService;
import com.jf.service.TaskSmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RegCondition
@Component
public class SmsTask {
    private static Logger logger = LoggerFactory.getLogger(SmsTask.class);
    
    @Autowired
    private SmsService smsService;
    
    @Autowired
    private TaskSendMemberService taskSendMemberService;

  	@Autowired
    private TaskSmsService taskSmsService;

  	@Autowired
    private CouponService couponService;

	@Autowired
	private TaskSendSmsService taskSendSmsService;

    /**
     * 
     * 定时发送违规单赠送积分短信
     * 每天19点至30点每隔10分钟执行一次
     */
    @Scheduled(cron="0 0/10 19-20 * * ?")
    public void updateAccessToken(){
      SmsExample smsExample=new SmsExample();
      smsExample.createCriteria().andDelFlagEqualTo("0").andSendStatusEqualTo("0").andSmsTypeEqualTo("3").andCreateDateGreaterThan(DateUtil.getDateAfter(new Date(), -5));
      List<Sms> smsList=smsService.selectByExample(smsExample);
      if(smsList!=null){
			for (Sms sms : smsList) {
				try {
					smsService.sendImmediately(sms);
				} catch (Exception e) {
					logger.info("短信（id:"+sms.getId()+"）发送异常!");
					e.printStackTrace();
				}
			}
      }
    }
    
    /**
     * 
     * @MethodName: sendTaskSms
     * @Description: (营销中心玄武短信任务推送)
     * @author Pengl
     * @date 2019年8月23日 上午9:54:55
     */
    @Scheduled(cron="0 2/3 * * * ?")
    public synchronized void sendTaskSms() {
		
		logger.info("营销中心玄武短信任务推送start：" + DateUtil.getStandardDateTime());
		
		try {
			int sendSms = 0;
			Map<String, Object> paramMap = new HashMap<>();
			List<Map<String, Object>> taskMapList = taskSendMemberService.sendSmsTaskListByChannel(Const.TASK_XW_SMS_CHANNEL);
			List<Map<String, Object>> mapList;
			List<List<Map<String, Object>>> resultList;
			for(Map<String, Object> taskMap : taskMapList ) {
				if(taskMap.get("send_count") == null || StringUtil.isEmpty(taskMap.get("send_count").toString()) ) {
					continue;
				}
				boolean giveCouponFlag = "1".equals(taskMap.get("is_give_coupon").toString());
				MemberCoupon memberCoupon = new MemberCoupon();
				if (giveCouponFlag) {
					memberCoupon = this.getMemberCouponTemplate(Integer.parseInt(taskMap.get("coupon_id").toString()));
				}

				logger.info("定时任务taskId=====>：" + taskMap.get("id"));

				paramMap.put("sendChannel", Const.TASK_XW_SMS_CHANNEL);
				paramMap.put("taskId", taskMap.get("id").toString());
				paramMap.put("limitSize", Integer.parseInt(taskMap.get("send_count").toString()));
				mapList = taskSendMemberService.sendSmsSendListByChannel(paramMap);
				resultList = new ArrayList<List<Map<String, Object>>>();

				logger.info("定时任务task任务发总送量=====>：" + mapList.size());

				if(mapList != null && mapList.size() > 0 ) {
					int sendCount = Integer.parseInt(taskMap.get("send_count").toString());
					sendCount = sendCount>(Const.MAX_SEND_SMS-sendSms)?(Const.MAX_SEND_SMS-sendSms):sendCount;
					int len = Const.MAX_TASK_SEND_PAGE;
					int size = mapList.size()>sendCount?sendCount:mapList.size();
					int count = (size + len - 1) / len;
					for (int i = 0; i < count; i++) {
						List<Map<String, Object>> subList = mapList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
						resultList.add(subList);
					}
					for(List<Map<String, Object>> resultMapList : resultList) {
						//赠送优惠券
						if (giveCouponFlag) {
							couponService.batchGiveCoupontoMember(resultMapList, memberCoupon);
						}
						taskSendMemberService.sendTaskSmsList(resultMapList);
					}
					Task task = new Task();
					task.setId(Integer.parseInt(taskMap.get("id").toString()));
					task.setLastSendDate(new Date());
					task.setUpdateDate(new Date());
					taskSendMemberService.updateTaskKeySelective(task);
					sendSms += size;
				}

				if(Integer.parseInt(taskMap.get("send_count").toString()) > mapList.size()) {
					TaskSmsExample taskSmsExample = new TaskSmsExample();
					taskSmsExample.createCriteria().andDelFlagEqualTo("0")
							.andSendStatusEqualTo("0")
							.andTaskIdEqualTo(Integer.parseInt(taskMap.get("id").toString()));
					TaskSms taskSms = new TaskSms();
					taskSms.setSendStatus("1");
					taskSms.setUpdateDate(new Date());
					taskSmsService.updateByExampleSelective(taskSms, taskSmsExample);
				}

				logger.info("定时任务task:{}本次任务发送完毕", taskMap.get("id"));

				if(sendSms >= Const.MAX_SEND_SMS ) {
					break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("营销中心玄武短信任务推送end：" + DateUtil.getStandardDateTime());
		
	}


    /**
     *
     * @MethodName: sendTaskXYSmsList
     * @Description: (营销中心上海歆阳短信任务推送)
     * @author Pengl
     * @date 2019年8月23日 上午9:54:55
     */
    @Scheduled(cron="0 2/3 * * * ?")
    public synchronized void sendTaskXYSmsList() {

		logger.info("营销中心上海歆阳短信任务推送start：" + DateUtil.getStandardDateTime());

		try {
			int sendSms = 0;
			Map<String, Object> paramMap = new HashMap<>();
			List<Map<String, Object>> taskMapList = taskSendMemberService.sendSmsTaskListByChannel(Const.TASK_SHXY_SMS_CHANNEL);
			List<Map<String, Object>> mapList;
			List<List<Map<String, Object>>> resultList;
			for(Map<String, Object> taskMap : taskMapList ) {
				if(taskMap.get("send_count") == null || StringUtil.isEmpty(taskMap.get("send_count").toString()) ) {
					continue;
				}
				boolean giveCouponFlag = "1".equals(taskMap.get("is_give_coupon").toString());
				MemberCoupon memberCoupon = new MemberCoupon();
				if (giveCouponFlag) {
					memberCoupon = this.getMemberCouponTemplate(Integer.parseInt(taskMap.get("coupon_id").toString()));
				}

				logger.info("定时任务taskId=====>：" + taskMap.get("id"));

				paramMap.put("sendChannel", Const.TASK_SHXY_SMS_CHANNEL);
				paramMap.put("taskId", taskMap.get("id").toString());
				paramMap.put("limitSize", Integer.parseInt(taskMap.get("send_count").toString()));
				mapList = taskSendMemberService.sendSmsSendListByChannel(paramMap);
				resultList = new ArrayList<List<Map<String, Object>>>();

				logger.info("定时任务task任务发总送量=====>：" + mapList.size());


				if(mapList != null && mapList.size() > 0 ) {
					int sendCount = Integer.parseInt(taskMap.get("send_count").toString());
					sendCount = sendCount>(Const.MAX_SEND_SMS-sendSms)?(Const.MAX_SEND_SMS-sendSms):sendCount;
					int len = Const.MAX_TASK_SEND_PAGE;
					int size = mapList.size()>sendCount?sendCount:mapList.size();
					int count = (size + len - 1) / len;
					for (int i = 0; i < count; i++) {
						List<Map<String, Object>> subList = mapList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
						resultList.add(subList);
					}
					for(List<Map<String, Object>> resultMapList : resultList) {
						//赠送优惠券
						if (giveCouponFlag) {
							couponService.batchGiveCoupontoMember(resultMapList, memberCoupon);
						}
						taskSendMemberService.sendTaskXYSmsList(resultMapList);
					}
					Task task = new Task();
					task.setId(Integer.parseInt(taskMap.get("id").toString()));
					task.setLastSendDate(new Date());
					task.setUpdateDate(new Date());
					taskSendMemberService.updateTaskKeySelective(task);
					sendSms += size;
				}

				if(Integer.parseInt(taskMap.get("send_count").toString()) > mapList.size()) {
					TaskSmsExample taskSmsExample = new TaskSmsExample();
					taskSmsExample.createCriteria().andDelFlagEqualTo("0")
							.andSendStatusEqualTo("0")
							.andTaskIdEqualTo(Integer.parseInt(taskMap.get("id").toString()));
					TaskSms taskSms = new TaskSms();
					taskSms.setSendStatus("1");
					taskSms.setUpdateDate(new Date());
					taskSmsService.updateByExampleSelective(taskSms, taskSmsExample);
				}

				logger.info("定时任务task:{}本次任务发送完毕", taskMap.get("id"));

				if(sendSms >= Const.MAX_SEND_SMS ) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("营销中心上海歆阳短信任务推送end：" + DateUtil.getStandardDateTime());

	}

	/**
	 *
	 * @MethodName: sendTaskMwSmsList
	 * @Description: (营销中心梦网短信任务推送)
	 * @author XDD
	 * @date 2020-06-02 16:59:55
	 */
	@Scheduled(cron="0 1/3 * * * ?")
	public synchronized void sendTaskMwSmsList() {

		logger.info("营销中心梦网短信任务推送start：" + DateUtil.getStandardDateTime());

		try {
			int sendSms = 0;
			Map<String, Object> paramMap = new HashMap<>();
			List<Map<String, Object>> taskMapList = taskSendMemberService.sendSmsTaskListByChannel(Const.TASK_MW_SMS_CHANNEL);
			List<Map<String, Object>> mapList;
			List<List<Map<String, Object>>> resultList;
			for(Map<String, Object> taskMap : taskMapList ) {
				if(taskMap.get("send_count") == null || StringUtil.isEmpty(taskMap.get("send_count").toString()) ) {
					continue;
				}
				boolean giveCouponFlag = "1".equals(taskMap.get("is_give_coupon").toString());
				MemberCoupon memberCoupon = new MemberCoupon();
				if (giveCouponFlag) {
					memberCoupon = this.getMemberCouponTemplate(Integer.parseInt(taskMap.get("coupon_id").toString()));
				}
				String seqNum = taskMap.get("id").toString()+"-"+taskMap.get("send_type").toString();

				logger.info("定时任务taskId=====>：" + taskMap.get("id"));

				paramMap.put("sendChannel", Const.TASK_MW_SMS_CHANNEL);
				paramMap.put("taskId", taskMap.get("id").toString());
				paramMap.put("limitSize", Integer.parseInt(taskMap.get("send_count").toString()));
				mapList = taskSendMemberService.sendSmsSendListByChannel(paramMap);
				resultList = new ArrayList<List<Map<String, Object>>>();

				logger.info("定时任务task任务发总送量=====>：" + mapList.size());


				if(mapList != null && mapList.size() > 0 ) {
					int sendCount = Integer.parseInt(taskMap.get("send_count").toString());
					sendCount = sendCount>(Const.MAX_SEND_SMS-sendSms)?(Const.MAX_SEND_SMS-sendSms):sendCount;
					int len = Const.MW_MAX_TASK_SEND_PAGE;
					int size = mapList.size()>sendCount?sendCount:mapList.size();
					int count = (size + len - 1) / len;
					for (int i = 0; i < count; i++) {
						List<Map<String, Object>> subList = mapList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
						resultList.add(subList);
					}
					for(List<Map<String, Object>> resultMapList : resultList) {
						//赠送优惠券
						if (giveCouponFlag) {
							couponService.batchGiveCoupontoMember(resultMapList, memberCoupon);
						}
						taskSendSmsService.sendTaskMwSmsList(resultMapList, seqNum);
					}
					Task task = new Task();
					task.setId(Integer.parseInt(taskMap.get("id").toString()));
					task.setLastSendDate(new Date());
					task.setUpdateDate(new Date());
					taskSendMemberService.updateTaskKeySelective(task);
					sendSms += size;
				}

				if(Integer.parseInt(taskMap.get("send_count").toString()) > mapList.size()) {
					TaskSmsExample taskSmsExample = new TaskSmsExample();
					taskSmsExample.createCriteria().andDelFlagEqualTo("0")
							.andSendStatusEqualTo("0")
							.andTaskIdEqualTo(Integer.parseInt(taskMap.get("id").toString()));
					TaskSms taskSms = new TaskSms();
					taskSms.setSendStatus("1");
					taskSms.setUpdateDate(new Date());
					taskSmsService.updateByExampleSelective(taskSms, taskSmsExample);
				}

				logger.info("定时任务task:{}本次任务发送完毕", taskMap.get("id"));

				if(sendSms >= Const.MAX_SEND_SMS ) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("营销中心梦网短信任务推送end：" + DateUtil.getStandardDateTime());

	}

	/**
	 * 营销短信优惠券模板
	 * @param couponId
	 * @return
	 */
	private MemberCoupon getMemberCouponTemplate(Integer couponId){
		Coupon coupon = couponService.selectByPrimaryKey(couponId);
		Date date = new Date();
		MemberCoupon memberCoupon = new MemberCoupon();
		memberCoupon.setCouponId(couponId);
		memberCoupon.setRecDate(date);
		//有效期类型（1绝对时间 2相对时间）
		if("1".equals(coupon.getExpiryType())){
			memberCoupon.setExpiryBeginDate(coupon.getExpiryBeginDate());
			memberCoupon.setExpiryEndDate(coupon.getExpiryEndDate());
		} else if ("2".equals(coupon.getExpiryType())) {
			memberCoupon.setExpiryBeginDate(date);
			memberCoupon.setExpiryEndDate(DateUtil.addDay(date, coupon.getExpiryDays()));
		}
		memberCoupon.setStatus("0");
		memberCoupon.setReceiveType("9");//营销短信赠送
		memberCoupon.setCreateDate(new Date());
		memberCoupon.setDelFlag("0");
		return memberCoupon;
	}
}
