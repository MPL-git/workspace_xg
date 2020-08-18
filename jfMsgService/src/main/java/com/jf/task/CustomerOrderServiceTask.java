package com.jf.task;

import com.jf.common.constant.Const;
import com.jf.common.ext.RegCondition;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.InterventionOrderMapper;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.Sms;
import com.jf.service.CustomerServiceOrderService;
import com.jf.service.SmsService;
import com.jf.service.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 售后任务
 *
 * @auther hdl
 */
@RegCondition
@Component
public class CustomerOrderServiceTask {

    private static Logger logger = LoggerFactory.getLogger(CustomerOrderServiceTask.class);

    @Resource
    private CustomerServiceOrderService customerServiceOrderService;
    
    @Resource
    private InterventionOrderMapper interventionOrderMapper;
    
    @Resource
    private SmsService smsService;
	@Autowired
	private CacheService cacheService;

    /**
     * 退款单
     * 客户申请退款48小时后商户还没有操作时，系统自动同意退款
     *
     * 每隔30分钟执行一次
     */

    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void agreeA(){
		if (cacheService.suspend("AUTO_REFUND")) {
			return;
		}
		logger.info("退款单-客户申请退款48小时后商户还没有操作时，系统自动同意退款:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_A);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_A1);
        queryObject.addQuery("updateDateBeforeHours", 48);
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "商家超过48小时未操作，自动同意退款，请耐心等待。";
        for(CustomerServiceOrder customerServiceOrder : list){
            logger.info("退款单-客户申请退款48小时后商户还没有操作时，系统自动同意退款，退款单ID：{}", customerServiceOrder.getId());
            customerServiceOrderService.agreeA(customerServiceOrder, remark);
        }

        logger.info("退款单-客户申请退款48小时后商户还没有操作时，系统自动同意退款:end");
    }

    /**
     * 退货单
     * 客户申请退货48小时后商户还没有操作时，系统自动同意申请
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public void agreeB1(){
		if (cacheService.suspend("AUTO_REFUND")) {
			return;
		}
        logger.info("退货单-客户申请退货48小时后商户还没有操作时，系统自动同意申请:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_B);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_B1);
        queryObject.addQuery("updateDateBeforeHours", 48);
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "商家超过48小时未操作，自动同意退货申请";
        for(CustomerServiceOrder customerServiceOrder : list){
            logger.info("退货单-客户申请退货48小时后商户还没有操作时，系统自动同意申请，退款单ID：{}", customerServiceOrder.getId());
            customerServiceOrderService.agreeB1(customerServiceOrder, remark);
        }

        logger.info("退货单-客户申请退货48小时后商户还没有操作时，系统自动同意申请:end");
    }

    /**
     * 退货单
     * 同意退货申请7*24小时后客户还没有寄件，系统自动撤销退货单
     *
     * 每隔15分钟执行一次
     */
   @Scheduled(cron="0 0/15 * * * ?")
    public void closeB(){
	   if (cacheService.suspend("AUTO_REFUND")) {
		   return;
	   }
        logger.info("退货单-同意退货申请7*24小时后客户还没有寄件，系统自动撤销退货单:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_B);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_B2);
        queryObject.addQuery("updateDateBeforeHours", 7*24);
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "商家同意退货申请7*24小时后客户还没有寄件，系统自动撤销退货单";
        for(CustomerServiceOrder customerServiceOrder : list){
            logger.info("退货单-同意退货申请7*24小时后客户还没有寄件，系统自动撤销退货单，退款单ID：{}", customerServiceOrder.getId());
            customerServiceOrderService.close(customerServiceOrder, remark);
        }

        logger.info("退货单-同意退货申请7*24小时后客户还没有寄件，系统自动撤销退货单:end");
    }

    /**
     * 退货单
     * 客户寄件15*24小时后商户还没有操作时，系统自动同意退款
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void agreeB2(){
		if (cacheService.suspend("AUTO_REFUND")) {
			return;
		}
        logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_B);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_B4);
        queryObject.addQuery("updateDateBeforeHours", 15 * 24);
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "客户寄件15*24小时后商户还没有操作时，系统自动同意退款";
        for(CustomerServiceOrder customerServiceOrder : list){
        	InterventionOrderExample example = new InterventionOrderExample();
            InterventionOrderExample.Criteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andServiceOrderIdEqualTo(customerServiceOrder.getId());
            criteria.andProStatusEqualTo(Const.CUSTOMER_ORDER_PRO_STATUS_B4);
            List<InterventionOrder> interventionOrders = interventionOrderMapper.selectByExample(example);
            if(interventionOrders == null || interventionOrders.size() == 0){
            	logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款，退款单ID：{}", customerServiceOrder.getId());
                customerServiceOrderService.agreeB2(customerServiceOrder, remark);
            }else{
            	InterventionOrder interventionOrder = interventionOrders.get(0);
            	if(interventionOrder.getStatus().equals("8")){//已结案
            		logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款，退款单ID：{}", customerServiceOrder.getId());
                    customerServiceOrderService.agreeB2(customerServiceOrder, remark);
            	}
            }
        }
        logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款:end");
    }


    /**
     * 换货单
     * 客户申请换货48小时后商户还没有操作时，系统自动关闭换货单
     *
     * 每隔15分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public void agreeC(){
		if (cacheService.suspend("AUTO_REFUND")) {
			return;
		}
        logger.info("换货单-客户申请换货48小时后商户还没有操作时，系统自动关闭换货单:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_C);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_C1);
        queryObject.addQuery("updateDateBeforeHours", 48);
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "客户申请换货48小时后商户还没有操作时，系统自动同意换货单";
        for(CustomerServiceOrder customerServiceOrder : list){
            logger.info("换货单-客户申请换货48小时后商户还没有操作时，系统自动同意换货单，换货单ID：{}", customerServiceOrder.getId());
            customerServiceOrderService.agreeC(customerServiceOrder, remark);
        }

        logger.info("换货单-客户申请换货48小时后商户还没有操作时，系统自动同意换货单:end");
    }

    /**
     * 换货单
     * 同意换货申请24*7小时后客户还没有寄件，系统自动撤销换货单
     *
     * 每隔5分钟执行一次
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public void closeC(){
		if (cacheService.suspend("AUTO_REFUND")) {
			return;
		}
        logger.info("换货单-同意换货申请24*7小时后客户还没有寄件，系统自动撤销换货单:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_C);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_C2);
        queryObject.addQuery("updateDateBeforeHours", 24*7);
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "商家同意换货申请24*7小时后客户还没有寄件，系统自动撤销换货单";
        for(CustomerServiceOrder customerServiceOrder : list){
            logger.info("换货单-同意换货申请24*7小时后客户还没有寄件，系统自动撤销换货单，退款单ID：{}", customerServiceOrder.getId());
            customerServiceOrderService.close(customerServiceOrder, remark);
        }

        logger.info("换货单-同意换货申请24*7小时后客户还没有寄件，系统自动撤销换货单:end");
    }
    
    
    /**
     * 换货单
     * 客户寄件15*24小时后商户还没有操作时，系统自动同意退款（即换货改退款）
     *
     * 每隔15分钟执行一次
     * @throws ParseException 
     */
    @Scheduled(cron="0 0/15 * * * ?")
    public synchronized void agreeC4() throws ParseException{
		if (cacheService.suspend("AUTO_REFUND")) {
			return;
		}
        logger.info("换货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款:start");

        QueryObject queryObject = new QueryObject();
        queryObject.addQuery("serviceType", Const.CUSTOMER_ORDER_TYPE_C);
        queryObject.addQuery("status", Const.CUSTOMER_ORDER_STATUS_REFUNDING);
        queryObject.addQuery("proStatus", Const.CUSTOMER_ORDER_PRO_STATUS_C4);
        queryObject.addQuery("updateDateBeforeHours", 15 * 24);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        queryObject.addQuery("createDateGe", sdf.parse("2018-04-18 00:00:00"));
        List<CustomerServiceOrder> list = customerServiceOrderService.findList(queryObject);
        logger.info("扫描到的订单数：{}", list.size());
        String remark = "客户寄件15*24小时后商户还没有操作时，系统自动同意退款";
        for(CustomerServiceOrder customerServiceOrder : list){
        	InterventionOrderExample example = new InterventionOrderExample();
            InterventionOrderExample.Criteria criteria = example.createCriteria();
            criteria.andDelFlagEqualTo("0");
            criteria.andServiceOrderIdEqualTo(customerServiceOrder.getId());
            criteria.andProStatusEqualTo(Const.CUSTOMER_ORDER_PRO_STATUS_C4);
            List<InterventionOrder> interventionOrders = interventionOrderMapper.selectByExample(example);
            if(interventionOrders == null || interventionOrders.size() == 0){
            	logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款，退款单ID：{}", customerServiceOrder.getId());
                customerServiceOrderService.agreeC4(customerServiceOrder, remark);
            }else{
            	InterventionOrder interventionOrder = interventionOrders.get(0);
            	if(interventionOrder.getStatus().equals("8")){//已结案
            		logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款，退款单ID：{}", customerServiceOrder.getId());
                    customerServiceOrderService.agreeC4(customerServiceOrder, remark);
            	}
            }
        }
        logger.info("退货单-客户寄件15*24小时后商户还没有操作时，系统自动同意退款:end");
    }
    
    
    
    
    /**
     * 
     * @throws ParseException
     */
    @Scheduled(cron="0 0 12 * * ?")
    public synchronized void sendMsgB2C2() throws ParseException{
    	logger.info("退货单和换货单在商家同意申请后12小时之后，客户仍未寄件，中午12点发短信提醒用户寄件:start");
	    List<Map<String, Object>> phonesMapList=customerServiceOrderService.selectPhoneWhileB2C2();
	    if(phonesMapList!=null&&phonesMapList.size()>0){
	    	for(Map<String, Object> phonesMap:phonesMapList){
				Sms sms=new Sms();
				sms.setMobile((String)phonesMap.get("receiver_phone"));
				sms.setSmsType("4");
				sms.setBizId((Integer)phonesMap.get("id"));
				sms.setContent("商家已同意您的售后申请，请尽快登录APP，在个人中心“退款/售后”找到相应的售后单填写寄回物流单号。");
				smsService.sendImmediately(sms);
	    	}
	    }
    	logger.info("退货单和换货单在商家同意申请后12小时之后，客户仍未寄件，中午12点发短信提醒用户寄件:end");
    }
    
    
}
