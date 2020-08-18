package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberCouponLogMapper;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.service.cache.CacheService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单任务
 *
 * @auther hdl
 */
@RegCondition
@Component
public class OrderTask {

    private static Logger logger = LoggerFactory.getLogger(OrderTask.class);

    @Resource
    private CombineOrderService combineOrderService;
    @Resource
    private SubOrderService subOrderService;
    @Resource
    private IntegralDtlService integralDtlService;
    @Resource
    private MemberCouponService memberCouponService;
    @Resource
    private MemberCouponLogMapper memberCouponLogMapper;
    @Resource
    private CutPriceOrderService cutPriceOrderService;
    @Resource
    private OrderDtlService orderDtlService;
    @Resource
    private SmsService smsService;
    @Resource
    private OrderCancelMsgSendService orderCancelMsgSendService;
    
    @Autowired
    private CombineDepositOrderService combineDepositOrderService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private SubDepositOrderSerivce subDepositOrderSerivce;
    @Autowired
    private MemberAccountService memberAccountService;
	@Autowired
	private CacheService cacheService;

	@Autowired
	private MemberCollegeStudentCertificationService memberCollegeStudentCertificationService;

	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private keyValueService keyValueService;


    /**
     * 订单下单后24小时未付款取消订单
     *
     * 每隔5分钟执行一次
     */
    @Scheduled(cron="0 0/5 * * * ?")
    public synchronized void close(){
        logger.info("订单下单后24小时未付款取消订单任务:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("status", Const.COMBINE_ORDER_STATUS_NOT_PAID);
        queryObject.addQuery("createDateBeforeMinutes", 4 * 60);
//        queryObject.addQuery("createDateBeforeMinutes", 24*60);
        List<CombineOrder> list = combineOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        for(CombineOrder combineOrder : list){
            logger.info("订单combineOrder_ID：{}超时未付款系统自动关闭", combineOrder.getId());
            
            combineOrderService.close(combineOrder, "超时未支付");
            //超时关闭，积分返还给用户
            integralDtlService.addIntegralDtl(combineOrder);
            //超时关闭，返还优惠券
            memberCouponService.changeStatusByCombineOrder(combineOrder);
            
        }

        logger.info("订单下单后24小时未付款取消订单任务:end");
    }

    /**
     * 订单付款后48小时未发货，生成违规处罚
     *
     * 每隔15分钟执行一次
     * @throws ParseException 
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void deliveryOverDue() throws ParseException{
		if (cacheService.suspend("VIOLATION")) {
			return;
		}
		logger.info("订单付款后48小时未发货，生成超时发货违规处罚任务：start");

        List<SubOrder> list = subOrderService.findListOfDeliveryOverDue();
        logger.info("扫描到的子订单数：{}", list.size());
        if(list!=null){
        	for(SubOrder subOrder : list){
        		logger.info("子订单subOrder_ID：{}付款后48小时未发货，系统自动生成违规处罚", subOrder.getId());
        		subOrderService.deliveryOverDue(subOrder);
        	}
        }
        logger.info("订单付款后48小时未发货，生成违规处罚任务：end");
    }
    
    /**
     * 订单付款后72小时未发货，标记为缺货
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void markOutOfStock(){
		logger.info("标记缺货开始:start");
		if (cacheService.suspend("VIOLATION")) {
			logger.info("标记缺货:end,当前时间暂停缺货处罚");
			return;
		}
    	int count = subOrderService.batchInsertOutOfStock();

		logger.info("标记缺货:end,缺货订单数为{}",count);
    }
    
    
    /**
     * 缺货生成违规单
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="30 0/15 * * * ?")
    public synchronized void createOutOfStockViolate(){
		if (cacheService.suspend("VIOLATION")) {
			return;
		}
    	List<SubOrder> subOrders=subOrderService.selectOutOfStockOrderWithNoViolateOrder();
    	for (SubOrder subOrder:subOrders) {
    		try {
				subOrderService.outOfStockOrderOrderCreateViolateOrder(subOrder);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    }


    /**
     * 订单商家发货之后，15天内，客户未确认收货，批量改为确认收货
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void reciept(){
		if (cacheService.suspend("AUTO_CONFIRM_RECEIVE")) {
			return;
		}
        logger.info("订单商家发货之后，15*24小时内，客户未确认收货，批量改为确认收货任务:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("status", Const.ORDER_STATUS_SHIPPED);
        queryObject.addQuery("deliveryDateBeforDays", 15);
        // TODO: 2017/6/25 考虑退款中的订单不能自动确认收货
        List<SubOrder> list = subOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        for(SubOrder subOrder : list){
            logger.info("订单商家发货之后，15*24小时内，客户未确认收货，系统自动确认收货，subOrderId：{}", subOrder.getId());
            subOrderService.receipt(subOrder);
        }

        logger.info("订单商家发货之后，15*24小时内，客户未确认收货，批量改为确认收货任务:end");
    }
    
    
    
    /**
     * 订单确认收货7天后无售后单的情况下改为完成
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void complateOrder(){
        logger.info("订单确认收货7天后无售后单的情况下批量改为完成:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("status", Const.ORDER_STATUS_RECEIPT);
        queryObject.addQuery("receiptDateLessThan", DateUtil.getDateAfter(new Date(), -7));
        /*queryObject.addQuery("receiptDateLessThan",  DateUtil.getDate());*/
        List<SubOrder> list = subOrderService.findList(queryObject);
        logger.info("扫描到订单确认收货7天后无售后单的订单数：{}", list.size());
        for(SubOrder subOrder : list){
            logger.info("订单确认收货7天后无售后单的情况下改为完成，subOrderId：{}", subOrder.getId());
            subOrderService.complateOrder(subOrder);
        }

        logger.info("订单确认收货7天后无售后单的情况下批量改为完成:end");
    }
    
    
    
    
    
    /**
     * 商家发货72小时后，无快递跟踪信息的标记为虚假发货
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void shamDelivery(){
		if (cacheService.suspend("VIOLATION")) {
			return;
		}
    	logger.info("商家发货72小时后，无快递跟踪信息的标记为虚假发货:start");
    	
    	subOrderService.batchInsertShamDelivery(DateUtil.getDateAfterHour(new Date(), -72));
    	
    	logger.info("商家发货72小时后，无快递跟踪信息的标记为虚假发货:end");
    }
    
    /**
     * 虚假发货生成违规单
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void createShamDeliveryViolate(){
		if (cacheService.suspend("VIOLATION")) {
			return;
		}
    	logger.info("虚假发货生成违规单:start");
    	
    	List<SubOrder> subOrders=subOrderService.selectShamDeliveryOrderWithNoViolateOrder();
    	
    	for (SubOrder subOrder:subOrders) {
    		try {
				subOrderService.shamDeliveryOrderCreateViolateOrder(subOrder);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	logger.info("虚假发货生成违规单:end");
    }
    
    /**
     * 砍价，邀请享免单下单
     */
   @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void submitCutOrder(){
    	logger.info("砍价下单:start");
    	
    	cutPriceOrderService.submitCutOrder();
    	logger.info("砍价下单:end");
    }
    
    /**
     * 砍价24小时过期
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void getCutExpireOrder(){
    	logger.info("砍价过期:start");
    	cutPriceOrderService.getCutExpireOrder();
    	logger.info("砍价过期:end");
    }
    
    /**
     * 邀请享免单24小时过期
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void getCutInviteExpireOrder(){
    	logger.info("邀请享免单过期:start");
    	cutPriceOrderService.getCutInviteExpireOrder();
    	logger.info("邀请享免单过期:end");
    }
    
    /**
     * 助力大减价过期释放库存
     * 1：任务超时，
     * 2：任务完成
     * 任务领取时间+配置过期时长+24h，超期释放库存
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void getAssistanceExpireOrder(){
    	logger.info("助力大减价过期释放库存:start");
    	cutPriceOrderService.getAssistanceExpireOrder();
    	logger.info("助力大减价过期释放库存:end");
    }
    
    /**
	 * 每天早上10点跑 结算与实收验证、价格与实收验证、价格与结算验证
	 */
	@Scheduled(cron = "0 0 10 * * ?")
	public synchronized void orderDtlTask() {
		logger.info(DateUtil.getStandardDateTime() + "结算与实收验证、价格与实收验证、价格与结算验证:start");
		orderDtlService.checkOrderDtl();
		logger.info(DateUtil.getStandardDateTime() + "结算与实收验证、价格与实收验证、价格与结算验证:end");
	}
	
	/**
	 * 每10分钟跑一次
	 * 待付款状态下单时间已过30分钟时，自动触发短信 提醒 用户去支付 （允许：每30分钟运行一次，在30分到60分时发）
	 */
	@Scheduled(cron="0 0/30 * * * ?")
    public synchronized void sendMsgNoPaidOrder() throws ParseException{
    	logger.info("订单30分钟未支付，发送短信:start");
    	Date currentDate = new Date();
    	Date beginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
    	Date endDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("max", -60);// c+30<=now()<=c+60 才正常发送短信
    	paramsMap.put("min", -30);
    	paramsMap.put("beginDate", beginDate);
    	paramsMap.put("endDate", endDate);
    	List<CombineOrderCustom> combineOrders = combineOrderService.getNoPaidOrderList(paramsMap);
    	if(CollectionUtils.isNotEmpty(combineOrders)){
    		for (CombineOrderCustom order : combineOrders) {
    			Integer memberId = order.getMemberId();
    			Integer combineOrderId = order.getId();
    			Sms sms=new Sms();
    			sms.setMobile(order.getMobile());
    			sms.setSmsType("4");
    			sms.setBizId(combineOrderId);
    			sms.setMemberId(memberId);
    			sms.setContent("您还有订单未支付，订单即将被取消赶紧打开APP进行支付。醒购商城优惠多多 精彩不容错过");
    			smsService.sendImmediately(sms);
    			
    			OrderCancelMsgSend orderCancelMsgSend = new OrderCancelMsgSend();
    			orderCancelMsgSend.setMemberId(memberId);
    			orderCancelMsgSend.setCombineOrderId(combineOrderId);
    			orderCancelMsgSend.setCreateDate(currentDate);
    			orderCancelMsgSend.setDelFlag("0");
    			orderCancelMsgSendService.insertSelective(orderCancelMsgSend);
			}
    	}
    	logger.info("订单30分钟未支付，发送短信:end");
    }
	
	/**
	 * 
	 * @Title sendDepositOrderBegin   
	 * @Description TODO(尾款开始支付钱10分钟短信提示用户支付尾款——五分钟执行一次)   
	 * @author pengl
	 * @date 2018年11月16日 上午10:59:08
	 */
	@Scheduled(cron="0 0/5 * * * ?")
	public synchronized void sendDepositOrderBegin() {
		logger.info("预售商品提前10分钟，发送短信:start");
		Date currentDate = new Date();
    	Date activityBeginTime = DateUtil.addMinute(currentDate, 10);
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("activityBeginTime", activityBeginTime);
    	List<Map<String, Object>> mapList = activityService.getActivityDepositOrderBeginList(paramMap);
    	if(CollectionUtils.isNotEmpty(mapList)) {
    		for(Map<String, Object> map : mapList) {
    			Integer memberId = Integer.parseInt(map.get("member_id").toString());
    			Integer activityId = Integer.parseInt(map.get("activity_id").toString());
    			String mobile = map.get("mobile")==null?null:map.get("mobile").toString();
    			String nick = map.get("nick")==null?"亲":map.get("nick").toString();
    			if(!StringUtil.isEmpty(mobile)) {
    				Sms sms = new Sms();
    				sms.setMobile(mobile);
    				sms.setSmsType("4");
    				sms.setBizId(activityId);
    				sms.setMemberId(memberId);
    				sms.setContent("亲爱的"+nick+"，您预购的商品，可支付尾款请及时前往醒购进行支付。");
    				smsService.sendImmediately(sms);
    				
    				OrderCancelMsgSend orderCancelMsgSend = new OrderCancelMsgSend();
    				orderCancelMsgSend.setMemberId(memberId);
    				orderCancelMsgSend.setCombineOrderId(activityId);
    				orderCancelMsgSend.setMsgSendType("1");
    				orderCancelMsgSend.setCreateDate(currentDate);
    				orderCancelMsgSend.setDelFlag("0");
    				orderCancelMsgSendService.insertSelective(orderCancelMsgSend);
    			}
    		}
    	}
    	logger.info("预售商品提前10分钟，发送短信:end");
	}
	
	/**
	 * 
	 * @Title cancelSubDepositOrder   
	 * @Description TODO(预售定金订单，保留30分钟，30分钟后超时订单状态变为定金未付取消，并释放库存——10分钟执行一次)   
	 * @author pengl
	 * @date 2018年11月16日 上午11:26:08
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void cancelSubDepositOrder() {
		logger.info("预售商品定金30分钟未付款，自动取消:start");
		Date currentDate = new Date();
    	Date createDate = DateUtil.addMinute(currentDate, -30);
    	SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
    	subDepositOrderExample.createCriteria().andDelFlagEqualTo("0")
    		.andStatusEqualTo("1").andCreateDateLessThanOrEqualTo(createDate);
    	List<SubDepositOrder> subDepositOrderList = subDepositOrderSerivce.selectByExample(subDepositOrderExample);
		for(SubDepositOrder subDepositOrder : subDepositOrderList) {
			subDepositOrderSerivce.updateSubDepositOrder(subDepositOrder);
		}
    	logger.info("预售商品定金30分钟未付款，自动取消:end");
	}
	
	/**
	 * 
	 * @Title closeSubDepositOrder   
	 * @Description TODO(活动结束，关闭预售定金订单——5分钟执行一次)   
	 * @author pengl
	 * @date 2018年11月16日 下午1:09:46
	 */
	@Scheduled(cron="0 0/5 * * * ?")
	public synchronized void closeSubDepositOrder() {
		logger.info("预售商品定金活动结束，定金未付尾款取消:start");
		Date date = new Date();
		Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("activityEndTime", date);
    	List<Map<String, Object>> mapList = activityService.getActivityDepositOrderCloseList(paramMap);
    	if(CollectionUtils.isNotEmpty(mapList)) {
    		subDepositOrderSerivce.closeSubDepositOrder(mapList);
    	}
		logger.info("预售商品定金活动结束，定金未付尾款取消:end");
	}
	
	/**
     * (拉新分润订单)
     * 每周一凌晨02:00执行上一周的分润;
     *
     */
	@Scheduled(cron="0 0 2 ? * MON")
    public synchronized void distributionOrderTask(){
        logger.info(DateUtil.getStandardDateTime()+"开始跑分润订单:start");
        Map<String, String> lastWeekDate=DateUtil.getLastWeekDate();
        
        orderDtlService.distributionOrder(lastWeekDate.get("lastMonday"),lastWeekDate.get("lastSunday"));

        logger.info(DateUtil.getStandardDateTime()+"结束分润订单:end");
    }


	/**
	 *
	 *(上传认证信息10分钟后未下单，发短信通知用户);
	 *
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void memberCollegeStudentCertificationTask(){
		logger.info(DateUtil.getStandardDateTime()+"上传认证信息10分钟后未下单，发短信通知用户:start");
		Date currentDate = new Date();
		Date commitDate = DateUtil.addMinute(currentDate, -10);
		MemberCollegeStudentCertificationExample memberCollegeStudentCertificationExample=new MemberCollegeStudentCertificationExample();
		memberCollegeStudentCertificationExample.createCriteria().andDelFlagEqualTo("0").andCommitDateLessThanOrEqualTo(commitDate);
		List<MemberCollegeStudentCertification> memberCollegeStudentCertifications=memberCollegeStudentCertificationService.selectByExample(memberCollegeStudentCertificationExample);
		if (memberCollegeStudentCertifications!=null && memberCollegeStudentCertifications.size()>0){
			for (MemberCollegeStudentCertification memberCollegeStudentCertification:memberCollegeStudentCertifications){
				memberCollegeStudentCertificationService.memberCollegeStudentCertificationData(memberCollegeStudentCertification);
			}
		}
		logger.info(DateUtil.getStandardDateTime()+"上传认证信息10分钟后未下单，发短信通知用户:end");

	}


	/**
	 *
	 *(用户付款10分钟后未认证，发短信通知用户);
	 *
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public synchronized void memberCollegeStudentCertificationPaymentTask(){
		logger.info(DateUtil.getStandardDateTime()+"用户付款10分钟后未认证，发短信通知用户:start");
		Date currentDate = new Date();
		Date payDate = DateUtil.addMinute(currentDate, -10);
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("payDate", payDate);
        List<CombineOrderCustom> combineOrders=combineOrderService.getMemberList(paramsMap);
        if (combineOrders!=null && combineOrders.size()>0){
        	for (CombineOrder combineOrder:combineOrders){
        		 MemberCollegeStudentCertificationExample memberCollegeStudentCertificationExample=new MemberCollegeStudentCertificationExample();
        		 memberCollegeStudentCertificationExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(combineOrder.getMemberId());
        		 List<MemberCollegeStudentCertification> memberCollegeStudentCertifications=memberCollegeStudentCertificationService.selectByExample(memberCollegeStudentCertificationExample);
        		    if (memberCollegeStudentCertifications.size()<=0) {
						MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(combineOrder.getMemberId());
						if (memberInfo.getMobile()!=null && !memberInfo.getMobile().equals("")) {
							KeyValueExample keyValueExample = new KeyValueExample();
							keyValueExample.createCriteria().andTypeCodeEqualTo("smsNotice2").andMKeyEqualTo(memberInfo.getMobile()).andDelFlagEqualTo("0");
							List<KeyValue> keyValues = keyValueService.selectByExample(keyValueExample);
							if (keyValues.size() <= 0) {
								Sms sms = new Sms();
								sms.setMobile(memberInfo.getMobile());
								sms.setSmsType("4");
								sms.setBizId(combineOrder.getId());//母订单ID
								sms.setMemberId(memberInfo.getId());
								sms.setContent("您参与的大学生领防疫套装活动未完成，请尽快完成认证，超时将自动退款 https://dwz.cn/dPoesWht 退订回T");
								smsService.sendImmediately(sms);
								KeyValue keyValue = new KeyValue();
								keyValue.setTypeCode("smsNotice2");//类型标识:用户付款10分钟后未认证
								keyValue.setmKey(memberInfo.getMobile());
								keyValue.setmValue(combineOrder.getId().toString());//母订单ID
								keyValue.setReservedInt(0);
								keyValue.setCreateDate(currentDate);
								keyValue.setDelFlag("0");
								keyValueService.insert(keyValue);
							}
						}
					}
			}
		}

		logger.info(DateUtil.getStandardDateTime()+"用户付款10分钟后未认证，发短信通知用户:end");

	}

	/**
	 * 子订单自动批量审核任务执行：
	 * 将付款时间超过10分钟子订单 批量待审 改为 通过
	 * 每10分钟执行一次
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void bulkUpdateSubOrderAuditStatus() {
		logger.info("子订单自动批量审核任务执行：start");
		subOrderService.bulkUpdateSubOrderAuditStatus();
		logger.info("子订单自动批量审核任务执行：start");
	}
}
