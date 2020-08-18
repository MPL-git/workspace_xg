package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CombineDepositOrderMapper;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.CustomerServiceOrderMapper;
import com.jf.dao.IntegralDtlMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.dao.MemberAccountCustomMapper;
import com.jf.dao.MemberAccountMapper;
import com.jf.dao.MemberAllowanceMapper;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineOrder;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.CustomerServiceLog;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderExample;
import com.jf.entity.CustomerServicePic;
import com.jf.entity.CustomerServicePicExample;
import com.jf.entity.IntegralDtl;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositExample;
import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;
import com.jf.entity.MemberAllowance;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlExtend;
import com.jf.entity.OrderDtlExample;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderExample;
import com.jf.entity.SubOrder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月4日 上午10:47:49 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomerServiceOrderService extends BaseService<CustomerServiceOrder, CustomerServiceOrderExample> {
	
	@Autowired
	private CustomerServiceOrderMapper customerServiceOrderMapper;
	@Resource
	private CustomerServicePicService customerServicePicService;
	
	@Resource
	private CustomerServiceStatusLogService customerServiceStatusLogService;
	
	@Resource
	private CustomerServiceLogService customerServiceLogService;
	
	@Resource
	private ServiceLogPicService serviceLogPicService;
	@Resource
	private PlatformMsgService platformMsgService;
	@Resource
	private MsgTplService msgTplService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private SysAppMessageService sysAppMessageService;
	@Resource
	private InterventionOrderService interventionOrderService;
	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private SubDepositOrderService subDepositOrderService;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	@Autowired
	private RefundOrderService refundOrderService;
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	@Autowired
	private MemberAllowanceMapper memberAllowanceMapper;
	@Autowired
	private CombineDepositOrderMapper combineDepositOrderMapper;
	@Autowired
	private MchtDepositMapper mchtDepositMapper;
	@Autowired
	private MchtDepositDtlMapper mchtDepositDtlMapper;

	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	@Autowired
	private OrderDtlExtendService orderDtlExtendService;

	@Autowired
	public void setCustomerServiceOrderMapper(CustomerServiceOrderMapper customerServiceOrderMapper) {
		this.setDao(customerServiceOrderMapper);
		this.customerServiceOrderMapper = customerServiceOrderMapper;
	}
	public List<CustomerServiceOrder> findList(Integer orderDtlId, Integer memberId, String serviceType) {
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
					.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(serviceType);
		}else if(serviceType.equals("BC")){
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_B);
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_C);
		}else if(serviceType.equals("AB")){
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_A)
			.andStatusEqualTo(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
			customerServiceOrderExample.or().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId)).andServiceTypeEqualTo(Const.CUSTOMER_ORDER_TYPE_B)
			.andStatusEqualTo(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
		}else{
			customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0")
			.andCreateByEqualTo(Integer.valueOf(memberId));
		}
		customerServiceOrderExample.setOrderByClause("id desc");
		return customerServiceOrderMapper.selectByExample(customerServiceOrderExample);
	}
	public List<CustomerServiceOrder> findListBySubOrderIdAndStatus(Integer subOrderId,
			String status) {
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0")
		.andStatusEqualTo(status);
		return customerServiceOrderMapper.selectByExample(customerServiceOrderExample);
	}
	
	public int findListBySubOrderIdAndStatusCount(Integer subOrderId, List<String> csServiceList) {
		CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andSubOrderIdEqualTo(subOrderId).andDelFlagEqualTo("0").andStatusIn(csServiceList);
		return customerServiceOrderMapper.countByExample(customerServiceOrderExample);
	}
	
	public CustomerServiceOrder addCustomerService(Integer memberId, OrderDtl orderDtl, String serviceType,
			String contactPhone, String reason, String reasonStr, String remarks, Integer quantity, String pic, String isAllowMchtModify) throws ArgException, IllegalAccessException, InvocationTargetException{
		
		Integer subOrderId = orderDtl.getSubOrderId();
		Integer orderDtlId = orderDtl.getId();
		Integer buyQuantity = orderDtl.getQuantity();//购买的数量
		BigDecimal deductAmount = new BigDecimal("0");//预售金可抵扣金额
		BigDecimal deposit = new BigDecimal("0");//定金
		BigDecimal refundDeposit = new BigDecimal("0");//定金
		Date date = new Date();
		//判断是否已经存在进行中的售后单
		CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
		List<String> serviceOrderStatus=new ArrayList<String>();
		serviceOrderStatus.add(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
		serviceOrderStatus.add(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdEqualTo(orderDtlId).andStatusIn(serviceOrderStatus);
		int count=customerServiceOrderMapper.countByExample(customerServiceOrderExample);
		if(count>0){
			throw new ArgException("您已经申请过售后,请勿重新提交。");
		}
		//查找出所有尾款已付的定金
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_TAIL_PAID).andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0");
		List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(subDepositOrderExample);
		if(CollectionUtils.isNotEmpty(subDepositOrders)){
			for (SubDepositOrder subDepositOrder : subDepositOrders) {
				deductAmount = deductAmount.add(subDepositOrder.getDeductAmount());
				deposit = deposit.add(subDepositOrder.getDeposit());
			}
		}
		//拆单，申请售后的数量 < 购买的数量(产品确认退款也需退定金)
		Integer subDepositOrderSize = subDepositOrders.size();
		Integer needDepositQuantity = subDepositOrderSize -(buyQuantity-quantity);
		if(needDepositQuantity > 0){
			refundDeposit = deposit.multiply(new BigDecimal(needDepositQuantity+"")).divide(new BigDecimal(subDepositOrders.size()+""));
		}
		if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			if(quantity < buyQuantity){
				orderDtl = orderDtlService.splitOrderDtl(orderDtl,quantity,memberId,deductAmount,subDepositOrders,serviceType);
			}
		}else{
			if(!reason.equals("A7")){
				refundDeposit = new BigDecimal("0");
			}
		}

		SubOrder subOrder = subOrderService.findListById(subOrderId);
		CustomerServiceOrder customerServiceOrder;
		//1售后单表
		customerServiceOrder = new CustomerServiceOrder();
		customerServiceOrder.setSubOrderId(subOrderId);
		customerServiceOrder.setOrderDtlId(orderDtlId);
		customerServiceOrder.setServiceType(serviceType);
		customerServiceOrder.setOrderCode(serviceType + CommonUtil.getOrderCode());
		customerServiceOrder.setProStatus(serviceType+"1");
		customerServiceOrder.setContactPhone(contactPhone);
		customerServiceOrder.setReason(reason);
		customerServiceOrder.setQuantity(quantity);
		customerServiceOrder.setAmount(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C)?new BigDecimal("0"):orderDtl.getPayAmount());
		customerServiceOrder.setDepositAmount(refundDeposit);
		customerServiceOrder.setRemarks(remarks);
		customerServiceOrder.setCreateBy(memberId);
		customerServiceOrder.setCreateDate(date);
		customerServiceOrder.setUpdateDate(date);
		customerServiceOrder.setIsAllowMchtModify(isAllowMchtModify);
		//售后状态 0.进行中 1已完成 2已拒绝 3已撤销
		customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		customerServiceOrder.setDelFlag("0");
		
		saveModel(customerServiceOrder);
		
		//2插入售后单状态流水日志表
		customerServiceStatusLogService.add(customerServiceOrder.getId(),Const.CUSTOMER_ORDER_STATUS_REFUNDING,customerServiceOrder.getProStatus(),memberId,remarks,date);
		
		//3售后记录表
		CustomerServiceLog customerServiceLog = customerServiceLogService.add(customerServiceOrder, reasonStr, date);

		// 自动同意退款： 退款单 且 待发货状态 且 审核状态为批量待审、人工待审 且 退款理由不为：商家责任A7
		if (serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)
				&& "1".equals(subOrder.getStatus())
				&& ("1".equals(subOrder.getAuditStatus()) || "2".equals(subOrder.getAuditStatus()))
				&& !reason.equals("A7")) {

			agreeRefund(customerServiceOrder, date);
		}
		
		//插入图片
		if(!StringUtil.isBlank(pic)){
			String[] picList = pic.split(",");
			for (String str : picList) {
				str = StringUtil.replace(str,"xgbuy.cc");
				
				//4售后单图片表
				customerServicePicService.add(customerServiceOrder.getId(),str,memberId,date,remarks);
				//5售后记录图片表
				serviceLogPicService.add(customerServiceLog.getId(),str,memberId,date,remarks);
			}
		}
		//站内信息
		String title = "";
		String tplType = "";
		String msgType = "";
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			title = Const.PLATFORM_MSG_TITLE_A1;
			tplType = Const.MSG_TLP_TYPE_A1;
			msgType = Const.PLATFORM_MSG_TYPE_A1;
		}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B)){
			title = Const.PLATFORM_MSG_TITLE_A20;
			tplType = Const.MSG_TLP_TYPE_A20;
			msgType = Const.PLATFORM_MSG_TYPE_A2;
		}else{
			title = Const.PLATFORM_MSG_TITLE_A30;
			tplType = Const.MSG_TLP_TYPE_A30;
			msgType = Const.PLATFORM_MSG_TYPE_A3;
		}
		String content = msgTplService.findMsgContent(tplType,customerServiceOrder.getOrderCode(),customerServiceOrder.getId(),subOrder.getSubOrderCode(),subOrder.getId(),customerServiceOrder.getAmount().add(refundDeposit));
		platformMsgService.add(subOrder.getMchtId(),msgType,title,content,customerServiceOrder.getId(),Const.PLATFORM_MSG_STATUS_0);
		return customerServiceOrder;
	}

	private void agreeRefund(CustomerServiceOrder model, Date date) {
		model.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_A2);
		update(model);

		//超时关闭，返还优惠券
		memberCouponService.changeStatusByCustomerServiceOrder(model);

		String remark = "未审核状态下用户申请退款，系统自动同意退款";
		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);

		//插入一条售后记录
		CustomerServiceLog customerServiceLog = new CustomerServiceLog();
		customerServiceLog.setContent("退款中，请耐心等待");
		customerServiceLog.setCreateDate(date);
		customerServiceLog.setDelFlag(StateConst.FALSE);
		customerServiceLog.setOperatorType("3"); //系统
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);

		SubOrder subOrder = subOrderService.selectByPrimaryKey(model.getSubOrderId());
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());

		//插入一条退款记录
		RefundOrder refundOrder = new RefundOrder();
		refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
		refundOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_A);
		refundOrder.setServiceOrderId(model.getId());
		refundOrder.setRefundAmount(model.getAmount());
		refundOrder.setRefundAgreeDate(date);
		refundOrder.setRefundMethod("1"); //原路返回
		refundOrder.setTryTimes(0);
		refundOrder.setStatus("0"); //未退
		refundOrder.setCreateDate(date);
		refundOrder.setRemarks(remark);
		refundOrder.setDelFlag(StateConst.FALSE);
		refundOrder.setOrderType("1"); //总订单
		refundOrder.setRefundCode(getRefundCode(combineOrder.getPaymentId(), date));
		refundOrderService.insertSelective(refundOrder);

		//更新订单明细状态为退款状态
		OrderDtl orderDtl4Update=new OrderDtl();
		orderDtl4Update.setProductStatus("2");
		orderDtl4Update.setId(model.getOrderDtlId());
		orderDtlService.updateByPrimaryKeySelective(orderDtl4Update);


		//返还积分给用户
		OrderDtl orderDtl = orderDtlService.selectByPrimaryKey(model.getOrderDtlId());
		if (orderDtl.getIntegralPreferential() != null && orderDtl.getIntegralPreferential().compareTo(BigDecimal.ZERO) > 0) {
			int integral = orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).intValue();

			MemberAccountExample memberAccountExample = new MemberAccountExample();
			memberAccountExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(combineOrder.getMemberId());
			List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(memberAccountExample);
			if (memberAccounts != null && memberAccounts.size() > 0) {
				MemberAccount memberAccount = memberAccounts.get(0);
				memberAccountCustomMapper.updateAccountIntegralByPrimaryKey(memberAccount.getId(), integral);
				IntegralDtl integralDtl = new IntegralDtl();
				integralDtl.setAccId(memberAccount.getId());
				integralDtl.setBalance(memberAccount.getIntegral() + integral);
				integralDtl.setCreateDate(new Date());
				integralDtl.setDelFlag("0");
				integralDtl.setIntegral(integral);
				integralDtl.setOrderId(orderDtl.getId());
				integralDtl.setBizType("3");
				integralDtl.setRemarks("订单明细：" + orderDtl.getId() + " 退款返还积分");
				integralDtl.setTallyMode("1");
				integralDtl.setType(7);
				integralDtlMapper.insertSelective(integralDtl);
			}
		}

		// 返还津贴
		refundAllowance(orderDtl, combineOrder.getMemberId());

		// 定金退款单生成处理
		if (model.getDepositAmount() != null && model.getDepositAmount().compareTo(BigDecimal.ZERO) > 0) {
			RefundOrderExample e = new RefundOrderExample();
			RefundOrderExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andServiceOrderIdEqualTo(model.getId());
			c.andOrderTypeEqualTo("2");//2.预售定金总订单
			List<RefundOrder> ros = refundOrderService.selectByExample(e);
			if (CollectionUtil.isEmpty(ros)) {//已存在无需在生成
				RefundOrder depositRefundOrder = new RefundOrder();
				depositRefundOrder.setDelFlag(StateConst.FALSE);
				depositRefundOrder.setCreateDate(date);
				depositRefundOrder.setUpdateDate(date);
				depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
				SubDepositOrderExample sdoe = new SubDepositOrderExample();
				sdoe.createCriteria().andDelFlagEqualTo(StateConst.FALSE).andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(model.getOrderDtlId());
				List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(sdoe);
				if (subDepositOrders != null && subDepositOrders.size() > 0) {
					Integer combineDepositOrderId = subDepositOrders.get(0).getCombineDepositOrderId();
					depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
					CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
					depositRefundOrder.setRefundCode(getRefundCode(combineDepositOrder.getPaymentId(), date));
				}
				depositRefundOrder.setServiceType(model.getServiceType());
				depositRefundOrder.setServiceOrderId(model.getId());
				depositRefundOrder.setRefundAmount(model.getDepositAmount());
				depositRefundOrder.setRefundAgreeDate(new Date());
				depositRefundOrder.setRefundMethod("1");
				depositRefundOrder.setTryTimes(0);
				depositRefundOrder.setStatus("0");
				depositRefundOrder.setOrderType("2");
				refundOrderService.insertSelective(depositRefundOrder);
			}
		}

		//定金，商家责任，扣除保证金（保证金金额为定金金额），送用户积分
		if (model.getReason().equals("A7") && model.getDepositAmount() != null && model.getDepositAmount().compareTo(BigDecimal.ZERO) > 0) {//定金付款时的商家责任。
			SubDepositOrderExample sdoe = new SubDepositOrderExample();
			sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(model.getOrderDtlId());
			List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(sdoe);
			BigDecimal totalDeposit = BigDecimal.ZERO;
			if (subDepositOrders != null && subDepositOrders.size() > 0) {
				for (SubDepositOrder subDepositOrder : subDepositOrders) {
					totalDeposit.add(subDepositOrder.getDeposit());
				}
				MchtDepositExample mde = new MchtDepositExample();
				mde.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId());
				List<MchtDeposit> mchtDeposits = mchtDepositMapper.selectByExample(mde);
				MchtDeposit mchtDeposit = mchtDeposits.get(0);
				mchtDeposit.setUnpayAmt(mchtDeposit.getUnpayAmt().add(totalDeposit));
				mchtDeposit.setPayAmt(mchtDeposit.getPayAmt().subtract(totalDeposit));
				mchtDeposit.setUpdateDate(new Date());
				mchtDepositMapper.updateByPrimaryKeySelective(mchtDeposit);

				MchtDepositDtl mdd = new MchtDepositDtl();
				mdd.setDepositId(mchtDeposit.getId());
				mdd.setTxnType("E");//E.违规扣款
				mdd.setTypeSub("E4");//售后
				mdd.setTxnAmt(totalDeposit.negate());
				mdd.setPayAmt(mchtDeposit.getPayAmt());
				mdd.setBizType("2");
				mdd.setRemarks("【售后】预售违规");
				mdd.setCreateDate(new Date());
				mdd.setDelFlag("0");
				mchtDepositDtlMapper.insertSelective(mdd);

				MemberAccountExample mae = new MemberAccountExample();
				MemberAccountExample.Criteria c = mae.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andMemberIdEqualTo(subDepositOrders.get(0).getMemberId());
				List<MemberAccount> memberAccounts = memberAccountMapper.selectByExample(mae);
				Integer integral = Integer.parseInt(totalDeposit.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString());
				MemberAccount memberAccount = memberAccounts.get(0);
				memberAccount.setIntegral(memberAccount.getIntegral() + integral);
				memberAccount.setUpdateDate(new Date());
				memberAccountMapper.updateByPrimaryKeySelective(memberAccount);

				IntegralDtl integralDtl = new IntegralDtl();
				integralDtl.setAccId(memberAccount.getId());
				integralDtl.setTallyMode("1");
				integralDtl.setType(7);
				integralDtl.setIntegral(integral);
				integralDtl.setBalance(memberAccount.getIntegral());
				integralDtl.setOrderId(orderDtl.getSubOrderId());
				integralDtl.setCreateDate(new Date());
				integralDtl.setDelFlag("0");
				integralDtlMapper.insertSelective(integralDtl);
			}
		}

		//关闭子订单
		closeSubOrder(model.getSubOrderId());
	}

	private String getRefundCode(Integer paymentId, Date date) {
		String dateStr = DateUtil.getFormatDate(date, "yyyyMMdd");
		if(paymentId == 1 || paymentId == 6){
			return "ZFB" + dateStr + "T";
		}else if(paymentId == 2 || paymentId == 5){
			return"WX" + dateStr + "T";
		}else if(paymentId == 3){
			return"YL" + dateStr + "T";
		}else if(paymentId == 4){
			return"GZH" + dateStr + "T";
		}
		return null;
	}

	/**
	 * 关闭子定单，判断子订单的所有售后单(直赔单除外)是否都为同意退款状态，是的话要关闭订单
	 */
	public void closeSubOrder(Integer subOrderId) {
		OrderDtlExample orderDtlExample = new OrderDtlExample();
		orderDtlExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
		List<OrderDtl> orderDtls = orderDtlService.selectByExample(orderDtlExample);
		if (orderDtls != null && orderDtls.size() > 0) {
			for (OrderDtl orderDtl : orderDtls) {
				CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
				customerServiceOrderExample.createCriteria().andDelFlagEqualTo("0").andOrderDtlIdEqualTo(orderDtl.getId());
				List<CustomerServiceOrder> customerServiceOrders = customerServiceOrderMapper.selectByExample(customerServiceOrderExample);
				if (customerServiceOrders == null || customerServiceOrders.size() == 0) {// 存在无售后的子订单，则不能关闭售后
					return;
				}

				boolean has_A2_A4_B5_B7_status = false;//判断此明细售后单是否已经同意退款，不是的话不能关闭
				for (CustomerServiceOrder customerServiceOrder : customerServiceOrders) {// 同一个明细可能多个售后单，（申请退款单后被拒绝，在申请退货时存在此情况）
					String proStatus = customerServiceOrder.getProStatus();
					if (Const.CUSTOMER_ORDER_PRO_STATUS_A2.equals(proStatus) || Const.CUSTOMER_ORDER_PRO_STATUS_A4.equals(proStatus) || Const.CUSTOMER_ORDER_PRO_STATUS_B5.equals(proStatus) || Const.CUSTOMER_ORDER_PRO_STATUS_B7.equals(proStatus)) {
						has_A2_A4_B5_B7_status = true;
						break;
					}
				}
				if (!has_A2_A4_B5_B7_status) {
					return;
				}
			}

			SubOrder subOrder4Update = new SubOrder();
			subOrder4Update.setUpdateDate(new Date());
			subOrder4Update.setId(subOrderId);
			subOrder4Update.setStatus("5");
			subOrder4Update.setCloseDate(new Date());
			subOrderService.updateByPrimaryKeySelective(subOrder4Update);
		}

	}

	private void refundAllowance(OrderDtl orderDtl, Integer memberId) {
		if (orderDtl.getAllowance() != null && orderDtl.getAllowance().compareTo(BigDecimal.ZERO) > 0) {
			MemberAllowance memberAllowance = new MemberAllowance();
			memberAllowance.setMemberId(memberId);
			memberAllowance.setSource("2");
			memberAllowance.setAllowanceAmount(orderDtl.getAllowance().setScale(0, BigDecimal.ROUND_DOWN));
			memberAllowance.setCreateDate(new Date());
			memberAllowance.setDelFlag("0");
			memberAllowanceMapper.insert(memberAllowance);
		}
	}


	public void saveModel(CustomerServiceOrder customerServiceOrder) {
		
		customerServiceOrderMapper.insertSelective(customerServiceOrder);
	}
	public void updateCustomerService(Integer memberId, Integer serviceOrderId, String serviceType, String contactPhone,
			String reason, String reasonStr, String remarks, Integer quantity, String pic, SubOrder subOrder, OrderDtl orderDtl, String isAllowMchtModify) throws IllegalAccessException, InvocationTargetException {
		Date date = new Date();
		String proStatus = serviceType+"1";
		String subStatus = subOrder.getStatus();
		Integer buyQuantity = orderDtl.getQuantity();//购买的数量
		BigDecimal deductAmount = new BigDecimal("0");//预售金可抵扣金额
		BigDecimal deposit = new BigDecimal("0");//定金
		BigDecimal refundDeposit = new BigDecimal("0");//定金
		//1售后单表
		CustomerServiceOrder customerServiceOrder = customerServiceOrderMapper.selectByPrimaryKey(serviceOrderId);
		String status = customerServiceOrder.getStatus();
		String proS = customerServiceOrder.getProStatus();
		String sType = customerServiceOrder.getServiceType();
		if(status.equals(Const.CUSTOMER_ORDER_STATUS_SUCCESS)){
			//换货放过去 && 订单未确认收货
			if(!sType.equals("C") && !subStatus.equals(Const.ORDER_STATUS_SUCCESS)){
				throw new ArgException("不能重复申请售后");
			}
		}
		if (status.equals(Const.CUSTOMER_ORDER_STATUS_REFUNDING)) {
			if (!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_A1) && sType.equals("A")) {
				throw new ArgException("不能重复申请售后");
			} else if ((!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B1)
					&& !proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B3)
					&& !proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B6)) && sType.equals("B")) {
				throw new ArgException("不能重复申请售后");
			} else if ((!proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C1)
					&& !proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C3)
					&& !proS.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C6)) && sType.equals("C")) {
				throw new ArgException("不能重复申请售后");
			} else if (sType.equals("D")) {
				throw new ArgException("不能重复申请售后");
			}
		}
		//存在最新介入单且未结案的不能修改售后单据 WRH
		InterventionOrderExample interventionOrderExample = new InterventionOrderExample();
		interventionOrderExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andStatusNotEqualTo("8").andDelFlagEqualTo("0");
		List<InterventionOrder> interventionOrders = interventionOrderService.selectByExample(interventionOrderExample);
		if(CollectionUtils.isNotEmpty(interventionOrders)){
			throw new ArgException("您申请的平台介入未完成，完成后才能进行相关操作。");
		}
		//查找是否有交定金
		SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
		subDepositOrderExample.createCriteria().andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_TAIL_PAID).andOrderDtlIdEqualTo(orderDtl.getId()).andDelFlagEqualTo("0");
		List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(subDepositOrderExample);
		if(CollectionUtils.isNotEmpty(subDepositOrders)){
			for (SubDepositOrder subDepositOrder : subDepositOrders) {
				deductAmount = deductAmount.add(subDepositOrder.getDeductAmount());
				deposit = deposit.add(subDepositOrder.getDeposit());
			}
		}
		//拆单，申请售后的数量 < 购买的数量
		if(!serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			Integer needDepositQuantity = subDepositOrders.size()-(buyQuantity-quantity);
			if(needDepositQuantity > 0){
				refundDeposit = deposit.multiply(new BigDecimal(needDepositQuantity+"")).divide(new BigDecimal(subDepositOrders.size()+""));
			}
			if(quantity < buyQuantity){
				orderDtl = orderDtlService.splitOrderDtl(orderDtl,quantity,memberId,deductAmount,subDepositOrders,serviceType);
			}
		}
		BigDecimal amount = orderDtl.getPayAmount();
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_C)){
			//取订单明细的实付金额
			amount = new BigDecimal("0");
		}
		String orderCode = customerServiceOrder.getOrderCode();
		orderCode = serviceType+orderCode.substring(1);
		customerServiceOrder.setId(serviceOrderId);
		customerServiceOrder.setServiceType(serviceType);
		customerServiceOrder.setOrderCode(orderCode);
		customerServiceOrder.setContactPhone(contactPhone);
		customerServiceOrder.setReason(reason);
		customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		customerServiceOrder.setIsAllowMchtModify(isAllowMchtModify);
		customerServiceOrder.setProStatus(proStatus);
		customerServiceOrder.setQuantity(quantity);
		customerServiceOrder.setAmount(amount);
		customerServiceOrder.setDepositAmount(refundDeposit);
		customerServiceOrder.setRemarks(remarks);
		customerServiceOrder.setUpdateBy(memberId);
		customerServiceOrder.setUpdateDate(date);
		customerServiceOrderMapper.updateByPrimaryKeySelective(customerServiceOrder);
		
		//2插入售后单状态流水日志表
		customerServiceStatusLogService.add(customerServiceOrder.getId(),Const.CUSTOMER_ORDER_STATUS_REFUNDING, 
				proStatus, memberId, remarks, date);
		
		//3售后记录表
		CustomerServiceLog customerServiceLog = customerServiceLogService.add(customerServiceOrder, reasonStr, date);
		
		//把售后单图片设置为删除标志1
		CustomerServicePicExample customerServicePicExampleUpdateAll = new CustomerServicePicExample();
		customerServicePicExampleUpdateAll.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andCreateByEqualTo(memberId);
		CustomerServicePic customerServicePicUpdateAll = new CustomerServicePic();
		customerServicePicUpdateAll.setDelFlag("1");
		customerServicePicService.updateByExampleSelective(customerServicePicUpdateAll, customerServicePicExampleUpdateAll);
		//售后记录图片设置为删除标志1
		ServiceLogPicExample serviceLogPicExampleUpdateAll = new ServiceLogPicExample();
		serviceLogPicExampleUpdateAll.createCriteria().andServiceLogIdEqualTo(customerServiceLog.getId()).andCreateByEqualTo(memberId);
		ServiceLogPic serviceLogPicUpdateAll = new ServiceLogPic();
		serviceLogPicUpdateAll.setDelFlag("1");
		serviceLogPicService.updateByExampleSelective(serviceLogPicUpdateAll, serviceLogPicExampleUpdateAll);
		
		if(!StringUtil.isBlank(pic)){
			String[] picList = pic.split(",");
			for (String customPic : picList) {
				customPic = StringUtil.replace(customPic,"xgbuy.cc");
				
				//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
				CustomerServicePicExample customerServicePicExampleUpdate = new CustomerServicePicExample();
				customerServicePicExampleUpdate.createCriteria().andPicEqualTo(customPic).andServiceOrderIdEqualTo(serviceOrderId).andCreateByEqualTo(memberId);
				CustomerServicePic customerServicePicUpdate = new CustomerServicePic();
				customerServicePicUpdate.setDelFlag("0");
				int updateCustomerServicePicCount = customerServicePicService.updateByExampleSelective(customerServicePicUpdate, customerServicePicExampleUpdate);
				if(updateCustomerServicePicCount > 0){
					continue;
				}
				//不存在则做插入操作
				customerServicePicService.add(serviceOrderId, customPic, memberId, date, remarks);
				
			}
			
			for (String str : picList) {
				str = StringUtil.replace(str,"xgbuy.cc");
				//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
				ServiceLogPicExample serviceLogPicExampleUpdate = new ServiceLogPicExample();
				serviceLogPicExampleUpdate.createCriteria().andPicEqualTo(str).andServiceLogIdEqualTo(customerServiceLog.getId()).andCreateByEqualTo(memberId);
				ServiceLogPic serviceLogPicUpdate = new ServiceLogPic();
				serviceLogPicUpdate.setDelFlag("0");
				int updateServiceLogPicCount = serviceLogPicService.updateByExampleSelective(serviceLogPicUpdate, serviceLogPicExampleUpdate);
				if(updateServiceLogPicCount > 0){
					continue;
				}
				//不存在则做插入操作
				serviceLogPicService.add(customerServiceLog.getId(), str, memberId, date, remarks);
				
			}
			
		}
		
		//站内信息
		String title = "";
		String tplType = "";
		String msgType = "";
		if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_A)){
			title = Const.PLATFORM_MSG_TITLE_A1;
			tplType = Const.MSG_TLP_TYPE_A1;
			msgType = Const.PLATFORM_MSG_TYPE_A1;
		}else if(serviceType.equals(Const.CUSTOMER_ORDER_TYPE_B)){
			title = Const.PLATFORM_MSG_TITLE_A20;
			tplType = Const.MSG_TLP_TYPE_A20;
			msgType = Const.PLATFORM_MSG_TYPE_A2;
		}else{
			title = Const.PLATFORM_MSG_TITLE_A30;
			tplType = Const.MSG_TLP_TYPE_A30;
			msgType = Const.PLATFORM_MSG_TYPE_A3;
		}
		String content = msgTplService.findMsgContent(tplType,customerServiceOrder.getOrderCode(),customerServiceOrder.getId(),subOrder.getSubOrderCode(),subOrder.getId(),customerServiceOrder.getAmount().add(refundDeposit));
		platformMsgService.add(subOrder.getMchtId(),msgType,title,content,customerServiceOrder.getId(),Const.PLATFORM_MSG_STATUS_0);
		
	}
	public CustomerServiceOrder findListById(Integer serviceOrderId) {
		
		return customerServiceOrderMapper.selectByPrimaryKey(serviceOrderId);
	}
	
	public List<CustomerServiceOrder> findListQuery(QueryObject queryObject) {
		return customerServiceOrderMapper.selectByExample(builderQuery(queryObject));
	}

	public CustomerServiceOrderExample builderQuery(QueryObject queryObject) {
		CustomerServiceOrderExample example = new CustomerServiceOrderExample();
		CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("statusList") != null){
			List<String> statusList = queryObject.getQuery("statusList");
			criteria.andStatusIn(statusList);
		}
		if(queryObject.getQuery("orderDtlId") != null){
			criteria.andOrderDtlIdEqualTo(queryObject.getQueryToInt("orderDtlId"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}
	/**
	 * 
	 * 方法描述 ：退款成功
	 * @author  chenwf: 
	 * @date 创建时间：2017年7月14日 上午9:21:24 
	 * @version
	 * @param model
	 * @param remark 
	 */
	public void updateRefundSuccess(CustomerServiceOrder model, String remark) {
		String serviceType = model.getServiceType();
		String proStatus = "";
		String content = "退款操作完成，金额将原路返回到客户支付账号上，实际到账时间以在线支付工具和银行结算时间为准。请耐心等待。同时售后完成。";
		// 记录售后单状态流水
		String appContent = "你的"+model.getAmount()+"元有退款进度有更新";
		if(serviceType.equals("A")){
			proStatus = serviceType+"4";
			sysAppMessageService.add(Const.APP_MESSAGE_TYPR_TRAN_MESSAGE,Const.APP_MSG_TITLE_REFUND_SPEED_REMIND,appContent,Const.APP_MESSAGE_SERVICE_ORDER,model.getId(),model.getCreateBy(),1,"0");
		}else if (serviceType.equals("B")) {
			proStatus = serviceType+"7";
			sysAppMessageService.add(Const.APP_MESSAGE_TYPR_TRAN_MESSAGE,Const.APP_MSG_TITLE_REFUND_SPEED_REMIND,appContent,Const.APP_MESSAGE_SERVICE_ORDER,model.getId(),model.getCreateBy(),1,"0");
		}else if (serviceType.equals("D")) {
			proStatus = serviceType+"2";
		}
		
		model.setStatus(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
		model.setProStatus(proStatus);
		model.setRemarks("退款成功");
		update(model);
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), proStatus, remark);
		customerServiceLogService.save(model.getId(),Const.OPERATOR_TYPE_SYSTEM,1,content,remark);
	}
	
	public CustomerServiceOrder update(CustomerServiceOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}
	public Map<String, BigDecimal> getCustomerServiceAmount(Integer orderDtlId, Integer refundQuantity, OrderDtl orderDtl,String serviceType,OrderDtlExtend orderDtlExtend) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		if(orderDtl == null){
			orderDtl = orderDtlService.selectByPrimaryKey(orderDtlId);
		}
		if (orderDtlExtend == null) {
			orderDtlExtend = orderDtlExtendService.getByOrderDtlId(orderDtlId, orderDtl.getCreateBy());
		}
		//销售单价
		BigDecimal freight = orderDtl.getFreight();
		//销售单价
		BigDecimal salePrice = orderDtl.getSalePrice();
		//购买的数量
		Integer buyQuantity = orderDtl.getQuantity();
		BigDecimal popCommissionRate = orderDtl.getPopCommissionRate();
		//平台优惠
		BigDecimal platformPreferential = orderDtl.getPlatformPreferential(); 
		//商家优惠  1：商家优惠金额要先扣除预售金优惠金额
		BigDecimal mchtPreferential = orderDtl.getMchtPreferential();
		//津贴使用
		BigDecimal allowance = orderDtl.getAllowance();
		//积分优惠
		BigDecimal integralPreferential = orderDtl.getIntegralPreferential();
		//分润金额
		BigDecimal distributionAmount = orderDtl.getDistributionAmount();
		//自营运费
		BigDecimal manageSelfFreight = orderDtlExtend.getManageSelfFreight();
		//查找出所有尾款已付的定金
		BigDecimal zero = new BigDecimal("0");
		BigDecimal deductAmount = zero;
		BigDecimal deposit = zero;
		BigDecimal refundDeposit = zero;
		Integer subDepositOrderSize = 0;
		if(!"A".equals(serviceType)){
			SubDepositOrderExample subDepositOrderExample = new SubDepositOrderExample();
			subDepositOrderExample.createCriteria().andStatusEqualTo(Const.SUB_DEPOSIT_STATUS_TAIL_PAID).andOrderDtlIdEqualTo(orderDtlId).andDelFlagEqualTo("0");
			List<SubDepositOrder> subDepositOrders = subDepositOrderService.selectByExample(subDepositOrderExample);
			subDepositOrderSize = subDepositOrders.size();
			if(CollectionUtils.isNotEmpty(subDepositOrders)){
				for (SubDepositOrder subDepositOrder : subDepositOrders) {
					deductAmount = deductAmount.add(subDepositOrder.getDeductAmount());
					deposit = deposit.add(subDepositOrder.getDeposit());
				}
			}
		}
		BigDecimal payToatlAmount = salePrice.multiply(new BigDecimal(buyQuantity+"")).subtract(deductAmount);
		BigDecimal refundAmout = salePrice.multiply(new BigDecimal(refundQuantity+""));
		BigDecimal needRefundDepositAmout = zero;
		Integer needDepositQuantity = subDepositOrderSize-(buyQuantity-refundQuantity);//获取需要用到定金的数量
		if(needDepositQuantity > 0){
			needRefundDepositAmout = deductAmount.multiply(new BigDecimal(needDepositQuantity+"")).divide(new BigDecimal(subDepositOrderSize+""));
			refundDeposit = deposit.multiply(new BigDecimal(needDepositQuantity+"")).divide(new BigDecimal(subDepositOrderSize+""));
		}
		
		refundAmout = refundAmout.subtract(needRefundDepositAmout);
		BigDecimal rate = refundAmout.divide(payToatlAmount,8,BigDecimal.ROUND_DOWN);
		BigDecimal oldMchtPreferential = (mchtPreferential.subtract(deductAmount)).multiply(rate).add(needRefundDepositAmout).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal oldAllowance = allowance.multiply(rate).setScale(0, BigDecimal.ROUND_DOWN);
		BigDecimal oldPlatformPreferential = platformPreferential.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal oldIntegralPreferential = integralPreferential.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal oldFreight = freight.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal oldPayAmount = (salePrice.multiply(new BigDecimal(refundQuantity+""))).add(oldFreight).subtract(oldPlatformPreferential.add(oldMchtPreferential).add(oldIntegralPreferential)).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal oldDistributionAmount = distributionAmount.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal oldManageSelfFreight = manageSelfFreight.multiply(rate).setScale(2, BigDecimal.ROUND_DOWN);
		BigDecimal amount = refundDeposit.add(oldPayAmount);

		BigDecimal oldSettleAmount = BigDecimal.ZERO;
		BigDecimal oldCommissionAmount = BigDecimal.ZERO;
		SubOrder subOrder = subOrderService.selectByPrimaryKey(orderDtl.getSubOrderId());
		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(subOrder.getMchtId());
		if (Const.MCHT_TYPE_POP.equals(subOrder.getMchtType())) {
			oldSettleAmount = (salePrice.multiply(new BigDecimal(refundQuantity)).subtract(oldMchtPreferential).add(oldFreight)).multiply(new BigDecimal("1").subtract(popCommissionRate)).setScale(2, BigDecimal.ROUND_DOWN);
			oldCommissionAmount = (salePrice.multiply(new BigDecimal(refundQuantity)).subtract(oldMchtPreferential).add(oldFreight)).multiply(popCommissionRate).setScale(2, BigDecimal.ROUND_DOWN);
		} else {
			if (StateConst.FALSE.equals(mchtInfo.getIsManageSelf())) {
				//开放spop：
				//结算金额 = 结算价 * 数量 + 运费
				oldSettleAmount = orderDtl.getSettlePrice().multiply(new BigDecimal(refundQuantity)).add(oldFreight).setScale(2, BigDecimal.ROUND_DOWN);
				//服务费 = （醒购价 - 结算价）* 数量
				oldCommissionAmount = orderDtl.getSalePrice().subtract(orderDtl.getSettlePrice()).multiply(new BigDecimal(refundQuantity));
			} else {
				//自营spop：
				//结算金额 = 结算价 * 数量 - 商家优惠 + 自营运费
				oldSettleAmount = orderDtl.getSettlePrice().multiply(new BigDecimal(refundQuantity))
						.subtract(oldMchtPreferential).add(oldFreight).setScale(2, BigDecimal.ROUND_DOWN);
				//服务费 = （醒购价 - 结算价）* 数量 + 订单运费 - 自营运费
				oldCommissionAmount = orderDtl.getSalePrice().subtract(orderDtl.getSettlePrice()).multiply(new BigDecimal(refundQuantity))
						.add(oldFreight).subtract(oldManageSelfFreight);
			}

		}
		map.put("oldPayAmount", oldPayAmount);
		map.put("oldPlatformPreferential", oldPlatformPreferential);
		map.put("oldMchtPreferential", oldMchtPreferential);
		map.put("oldIntegralPreferential", oldIntegralPreferential);
		map.put("oldAllowance", oldAllowance);
		map.put("oldFreight", oldFreight);
		map.put("oldCommissionAmount", oldCommissionAmount);
		map.put("oldSettleAmount", oldSettleAmount);
		map.put("oldDistributionAmount", oldDistributionAmount);
		map.put("oldManageSelfFreight", oldManageSelfFreight);
		map.put("amount", amount);
		return map;
	}

}
