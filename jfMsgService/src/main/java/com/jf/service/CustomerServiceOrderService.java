package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.base.ServiceException;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.*;
import com.jf.entity.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerServiceOrderService extends BaseService<CustomerServiceOrder, CustomerServiceOrderExample> {

	@Autowired
	private CustomerServiceOrderMapper customerServiceOrderMapper;
	
	@Autowired
	private CustomerServiceOrderCustomMapper customerServiceOrderCustomMapper;

	@Autowired
	private CustomerServiceStatusLogService customerServiceStatusLogService;
	@Autowired
	private CustomerServiceLogService customerServiceLogService;
	@Autowired
	private SysAppMessageService sysAppMessageService;
	
	@Autowired
	private RefundOrderService refundOrderService;
	
	@Autowired
	private SubOrderMapper subOrderMapper;
	
	@Autowired
	private  OrderDtlMapper orderDtlMapper;
	
	@Autowired
	private ProductAfterTempletCustomMapper productAfterTempletCustomMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	
	@Autowired
	private MemberAccountMapper memberAccountMapper;
	
	@Autowired
	private MemberAccountCustomMapper memberAccountCustomMapper;
	
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	
	@Autowired
	private MemberCouponService memberCouponService;
	
	@Autowired
	private SubDepositOrderMapper subDepositOrderMapper;
	
	@Autowired
	private MchtDepositMapper mchtDepositMapper;
	
	@Autowired
	private MchtDepositDtlMapper mchtDepositDtlMapper;
	
	@Autowired
	private CombineDepositOrderMapper combineDepositOrderMapper;

	@Autowired
	private ServiceLogPicService serviceLogPicService;

	@Autowired
	private SubOrderService subOrderService;

	@Autowired
	private CombineOrderService combineOrderService;

	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private SmsBlackMobileService smsBlackMobileService;

	@Autowired
	private SmsWhiteMobileService smsWhiteMobileService; //白名单

	@Autowired
	private SmsService smsService;
	@Autowired
	private MemberAllowanceMapper memberAllowanceMapper;


	@Autowired
	public void setCustomerServiceOrderMapper(CustomerServiceOrderMapper customerServiceOrderMapper) {
		this.setDao(customerServiceOrderMapper);
		this.customerServiceOrderMapper = customerServiceOrderMapper;
	}
	


	/**
	 * 退款单-同意申请
     */
	public void agreeA(CustomerServiceOrder model, String remark) {
		
		
		//如果已经存在退款单则不能重复退款
		CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(model.getOrderDtlId());
		List<CustomerServiceOrder> customerServiceOrders=dao.selectByExample(customerServiceOrderExample);
		List<Integer> customerServiceOrderIds=new ArrayList<Integer>();
		for(CustomerServiceOrder customerServiceOrder:customerServiceOrders){
			customerServiceOrderIds.add(customerServiceOrder.getId());
		}
		RefundOrderExample refundOrderExample=new RefundOrderExample();
		refundOrderExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
		int count=refundOrderService.countByExample(refundOrderExample);
		if(count>0){//如果已经存在退款单则不能重复退款
			return;
		}
		
		//model.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		model.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_A2);
		update(model);
		
		//超时关闭，返还优惠券
		memberCouponService.changeStatusByCustomerServiceOrder(model);
		
		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		//插入一条售后记录
		CustomerServiceLog customerServiceLog=new CustomerServiceLog();
		customerServiceLog.setContent("商家超过48小时未操作，自动同意退款，请耐心等待。");
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType("3");
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);
		
		//插入一条退款记录
		RefundOrder refundOrder=new RefundOrder();
		
		SubOrder subOrder=subOrderMapper.selectByPrimaryKey(model.getSubOrderId());
		refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
		refundOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_A);
		refundOrder.setServiceOrderId(model.getId());
		refundOrder.setRefundAmount(model.getAmount());
		refundOrder.setRefundAgreeDate(new Date());
		refundOrder.setRefundMethod("1");
		refundOrder.setTryTimes(0);
		refundOrder.setStatus("0");
		refundOrder.setCreateDate(new Date());
		refundOrder.setRemarks(remark);
		refundOrder.setDelFlag("0");
		refundOrder.setOrderType("1");
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if(combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6){
			refundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5){
			refundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 3){
			refundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 4){
			refundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
		}
		refundOrderService.insertSelective(refundOrder);
		
		//更新订单明细状态为退款状态
		OrderDtl orderDtl4Update=new OrderDtl();
		orderDtl4Update.setProductStatus("2");
		orderDtl4Update.setId(model.getOrderDtlId());
		orderDtlMapper.updateByPrimaryKeySelective(orderDtl4Update);
		
		
		//返还积分给用户
		OrderDtl orderDtl=orderDtlMapper.selectByPrimaryKey(model.getOrderDtlId());
		
		if(orderDtl.getIntegralPreferential()!=null&&orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0))==1){
			int integral  =orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).intValue();
			
			MemberAccountExample memberAccountExample=new MemberAccountExample();
			memberAccountExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(combineOrder.getMemberId());
			
			List<MemberAccount> memberAccounts=memberAccountMapper.selectByExample(memberAccountExample);
			
			if(memberAccounts!=null&&memberAccounts.size()>0){
				MemberAccount memberAccount=memberAccounts.get(0);
				memberAccountCustomMapper.updateAccountIntegralByPrimaryKey(memberAccount.getId(), integral);
				IntegralDtl integralDtl=new IntegralDtl();
				integralDtl.setAccId(memberAccount.getId());
				integralDtl.setBalance(memberAccount.getIntegral()+integral);
				integralDtl.setCreateDate(new Date());
				integralDtl.setDelFlag("0");
				integralDtl.setIntegral(integral);
				integralDtl.setOrderId(orderDtl.getId());
				integralDtl.setBizType("3");
				integralDtl.setRemarks("订单明细："+orderDtl.getId()+" 退款返还积分");
				integralDtl.setTallyMode("1");
				integralDtl.setType(7);
				integralDtlMapper.insertSelective(integralDtl);
			}
			
		}

		// 返还津贴
		refundAllowance(orderDtl, combineOrder.getMemberId());

		//TODO 定金退款单生成处理
		if(model.getDepositAmount()!=null && model.getDepositAmount().compareTo(new BigDecimal(0))>0){
			RefundOrderExample e = new RefundOrderExample();
			RefundOrderExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andServiceOrderIdEqualTo(model.getId());
			c.andOrderTypeEqualTo("2");//2.预售定金总订单
			List<RefundOrder> ros = refundOrderService.selectByExample(e);
			if(ros!=null && ros.size()>0){//已存在无需在生成
				
			}else{
				Date now = new Date();
				RefundOrder depositRefundOrder = new RefundOrder();
				depositRefundOrder.setDelFlag("0");
				depositRefundOrder.setCreateDate(now);
				depositRefundOrder.setUpdateDate(now);
				depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
				SubDepositOrderExample sdoe = new SubDepositOrderExample();
				sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(model.getOrderDtlId());
				List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
				if(subDepositOrders!=null && subDepositOrders.size()>0){
					Integer combineDepositOrderId = subDepositOrders.get(0).getCombineDepositOrderId();
					depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
					CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
					if(combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6){
						depositRefundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5){
						depositRefundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 3){
						depositRefundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 4){
						depositRefundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
					}
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
		if(model.getReason().equals("A7") && model.getDepositAmount()!=null && model.getDepositAmount().compareTo(new BigDecimal(0))>0){//定金付款时的商家责任。
			SubDepositOrderExample sdoe = new SubDepositOrderExample();
			sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(model.getOrderDtlId());
			List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
			BigDecimal totalDeposit = new BigDecimal(0);
			if(subDepositOrders!=null && subDepositOrders.size()>0){
				for(SubDepositOrder subDepositOrder:subDepositOrders){
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
				memberAccount.setIntegral(memberAccount.getIntegral()+integral);
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


	/**
	 * 退货单-同意申请
	 */
	public void agreeB1(CustomerServiceOrder model, String remark) {
		//model.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		model.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B2);
		update(model);

		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		//获取商品的售后模版
		OrderDtl orderDtl=orderDtlMapper.selectByPrimaryKey(model.getOrderDtlId());
		if(orderDtl!=null){
			Product product = productMapper.selectByPrimaryKey(orderDtl.getProductId());
			ProductAfterTempletCustom productAfterTemplet=productAfterTempletCustomMapper.selectByPrimaryKey(product.getCsTempletId());
			if(productAfterTemplet==null){
				productAfterTemplet=new ProductAfterTempletCustom();
			}
			String content="商家超过48小时未操作，自动同意退货申请,请客户寄件到以下地址：\n收货人："+productAfterTemplet.getRecipient()+"\n联系电话："+productAfterTemplet.getPhone()+"\n地址："+productAfterTemplet.getAreaName()+productAfterTemplet.getAddress();
			
			if(!StringUtil.isEmpty(productAfterTemplet.getRemarks())){
				content=content+"\n注意事项："+productAfterTemplet.getRemarks();
			}
			CustomerServiceLog customerServiceLog=new CustomerServiceLog();
			customerServiceLog.setContent(content);
			customerServiceLog.setCreateDate(new Date());
			customerServiceLog.setDelFlag("0");
			customerServiceLog.setOperatorType("3");
			customerServiceLog.setServiceOrderId(model.getId());
			customerServiceLogService.insertSelective(customerServiceLog);
		}
	}

	/**
	 * 退货单-同意退款
	 */
	public void agreeB2(CustomerServiceOrder model, String remark) {
		
		//如果已经存在退款单则不能重复退款
		CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(model.getOrderDtlId());
		List<CustomerServiceOrder> customerServiceOrders=dao.selectByExample(customerServiceOrderExample);
		List<Integer> customerServiceOrderIds=new ArrayList<Integer>();
		for(CustomerServiceOrder customerServiceOrder:customerServiceOrders){
			customerServiceOrderIds.add(customerServiceOrder.getId());
		}
		RefundOrderExample refundOrderExample=new RefundOrderExample();
		refundOrderExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
		int count=refundOrderService.countByExample(refundOrderExample);
		if(count>0){//如果已经存在退款单则不能重复退款
			return;
		}
		
		
		//model.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		model.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B5);
		update(model);

		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		
		//插入一条售后记录
		CustomerServiceLog customerServiceLog=new CustomerServiceLog();
		customerServiceLog.setContent("商家超过15天未操作，自动同意退款，请耐心等待。");
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType("3");
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);
		
		
		//插入一条退款记录
		RefundOrder refundOrder=new RefundOrder();
		
		SubOrder subOrder=subOrderMapper.selectByPrimaryKey(model.getSubOrderId());
		refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
		refundOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_B);
		refundOrder.setServiceOrderId(model.getId());
		refundOrder.setRefundAmount(model.getAmount());
		refundOrder.setRefundAgreeDate(new Date());
		refundOrder.setRefundMethod("1");
		refundOrder.setTryTimes(0);
		refundOrder.setStatus("0");
		refundOrder.setCreateDate(new Date());
		refundOrder.setRemarks(remark);
		refundOrder.setDelFlag("0");
		refundOrder.setOrderType("1");
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if(combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6){
			refundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5){
			refundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 3){
			refundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 4){
			refundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
		}
		refundOrderService.insertSelective(refundOrder);
		
		
		
		//更新订单明细状态为退款状态
		OrderDtl orderDtl4Update=new OrderDtl();
		orderDtl4Update.setProductStatus("3");
		orderDtl4Update.setId(model.getOrderDtlId());
		orderDtlMapper.updateByPrimaryKeySelective(orderDtl4Update);
		
		
		//返还积分给用户
		OrderDtl orderDtl=orderDtlMapper.selectByPrimaryKey(model.getOrderDtlId());
		
		if(orderDtl.getIntegralPreferential()!=null&&orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0))==1){
			int integral  =orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).intValue();
			
			MemberAccountExample memberAccountExample=new MemberAccountExample();
			memberAccountExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(combineOrder.getMemberId());
			
			List<MemberAccount> memberAccounts=memberAccountMapper.selectByExample(memberAccountExample);
			
			if(memberAccounts!=null&&memberAccounts.size()>0){
				MemberAccount memberAccount=memberAccounts.get(0);
				memberAccountCustomMapper.updateAccountIntegralByPrimaryKey(memberAccount.getId(), integral);
				IntegralDtl integralDtl=new IntegralDtl();
				integralDtl.setAccId(memberAccount.getId());
				integralDtl.setBalance(memberAccount.getIntegral()+integral);
				integralDtl.setCreateDate(new Date());
				integralDtl.setDelFlag("0");
				integralDtl.setIntegral(integral);
				integralDtl.setOrderId(orderDtl.getId());
				integralDtl.setBizType("3");
				integralDtl.setRemarks("订单明细："+orderDtl.getId()+" 退款返还积分");
				integralDtl.setTallyMode("1");
				integralDtl.setType(7);
				integralDtlMapper.insertSelective(integralDtl);
			}
			
		}

		// 返还津贴
		refundAllowance(orderDtl, combineOrder.getMemberId());
		
		if(model.getDepositAmount()!=null && model.getDepositAmount().compareTo(new BigDecimal(0))>0){
			RefundOrderExample e = new RefundOrderExample();
			RefundOrderExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andServiceOrderIdEqualTo(model.getId());
			c.andOrderTypeEqualTo("2");//2.预售定金总订单
			List<RefundOrder> ros = refundOrderService.selectByExample(e);
			if(ros!=null && ros.size()>0){//已存在无需在生成
				
			}else{
				Date now = new Date();
				RefundOrder depositRefundOrder = new RefundOrder();
				depositRefundOrder.setDelFlag("0");
				depositRefundOrder.setCreateDate(now);
				depositRefundOrder.setUpdateDate(now);
				depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
				SubDepositOrderExample sdoe = new SubDepositOrderExample();
				sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(model.getOrderDtlId());
				List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
				if(subDepositOrders!=null && subDepositOrders.size()>0){
					Integer combineDepositOrderId = subDepositOrders.get(0).getCombineDepositOrderId();
					depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
					CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
					if(combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6){
						depositRefundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5){
						depositRefundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 3){
						depositRefundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 4){
						depositRefundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
					}
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
		
		closeSubOrder(model.getSubOrderId());
		
		
	}

	/**
	 * 换货单-换货改退款
	 */
	public void agreeC4(CustomerServiceOrder model, String remark) {
		
		//如果已经存在退款单则不能重复退款
		CustomerServiceOrderExample customerServiceOrderExample=new CustomerServiceOrderExample();
		customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(model.getOrderDtlId());
		List<CustomerServiceOrder> customerServiceOrders=dao.selectByExample(customerServiceOrderExample);
		List<Integer> customerServiceOrderIds=new ArrayList<Integer>();
		for(CustomerServiceOrder customerServiceOrder:customerServiceOrders){
			customerServiceOrderIds.add(customerServiceOrder.getId());
		}
		RefundOrderExample refundOrderExample=new RefundOrderExample();
		refundOrderExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
		int count=refundOrderService.countByExample(refundOrderExample);
		if(count>0){//如果已经存在退款单则不能重复退款
			return;
		}
		
		
		//model.setStatus(Const.CUSTOMER_ORDER_STATUS_REFUNDING);
		model.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B5);
		model.setServiceType(Const.CUSTOMER_ORDER_TYPE_B);
		OrderDtl orderDtl = orderDtlMapper.selectByPrimaryKey(model.getOrderDtlId());
		model.setAmount(orderDtl.getPayAmount());
		update(model);

		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		
		//插入一条售后记录
		CustomerServiceLog customerServiceLog=new CustomerServiceLog();
		customerServiceLog.setContent("商家超过15天未操作， 自动换货改退货退款，请耐心等待。");
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType("3");
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);
		
		
		//插入一条退款记录
		RefundOrder refundOrder=new RefundOrder();
		
		SubOrder subOrder=subOrderMapper.selectByPrimaryKey(model.getSubOrderId());
		refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
		refundOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_B);
		refundOrder.setServiceOrderId(model.getId());
		refundOrder.setRefundAmount(model.getAmount());
		refundOrder.setRefundAgreeDate(new Date());
		refundOrder.setRefundMethod("1");
		refundOrder.setTryTimes(0);
		refundOrder.setStatus("0");
		refundOrder.setCreateDate(new Date());
		refundOrder.setRemarks(remark);
		refundOrder.setDelFlag("0");
		refundOrder.setOrderType("1");
		CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if(combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6){
			refundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5){
			refundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 3){
			refundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
		}else if(combineOrder.getPaymentId() == 4){
			refundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
		}
		refundOrderService.insertSelective(refundOrder);
		
		
		
		//更新订单明细状态为退款状态
		OrderDtl orderDtl4Update=new OrderDtl();
		orderDtl4Update.setProductStatus("3");
		orderDtl4Update.setId(model.getOrderDtlId());
		orderDtlMapper.updateByPrimaryKeySelective(orderDtl4Update);
		
		
		//返还积分给用户
		
		if(orderDtl.getIntegralPreferential()!=null&&orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0))==1){
			int integral  =orderDtl.getIntegralPreferential().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN).intValue();
			
			MemberAccountExample memberAccountExample=new MemberAccountExample();
			memberAccountExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(combineOrder.getMemberId());
			
			List<MemberAccount> memberAccounts=memberAccountMapper.selectByExample(memberAccountExample);
			
			if(memberAccounts!=null&&memberAccounts.size()>0){
				MemberAccount memberAccount=memberAccounts.get(0);
				memberAccountCustomMapper.updateAccountIntegralByPrimaryKey(memberAccount.getId(), integral);
				IntegralDtl integralDtl=new IntegralDtl();
				integralDtl.setAccId(memberAccount.getId());
				integralDtl.setBalance(memberAccount.getIntegral()+integral);
				integralDtl.setCreateDate(new Date());
				integralDtl.setDelFlag("0");
				integralDtl.setIntegral(integral);
				integralDtl.setOrderId(orderDtl.getId());
				integralDtl.setBizType("3");
				integralDtl.setRemarks("订单明细："+orderDtl.getId()+" 退款返还积分");
				integralDtl.setTallyMode("1");
				integralDtl.setType(7);
				integralDtlMapper.insertSelective(integralDtl);
			}
			
		}

		// 返还津贴
		refundAllowance(orderDtl, combineOrder.getMemberId());
		
		if(model.getDepositAmount()!=null && model.getDepositAmount().compareTo(new BigDecimal(0))>0){
			RefundOrderExample e = new RefundOrderExample();
			RefundOrderExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andServiceOrderIdEqualTo(model.getId());
			c.andOrderTypeEqualTo("2");//2.预售定金总订单
			List<RefundOrder> ros = refundOrderService.selectByExample(e);
			if(ros!=null && ros.size()>0){//已存在无需在生成
				
			}else{
				Date now = new Date();
				RefundOrder depositRefundOrder = new RefundOrder();
				depositRefundOrder.setDelFlag("0");
				depositRefundOrder.setCreateDate(now);
				depositRefundOrder.setUpdateDate(now);
				depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
				SubDepositOrderExample sdoe = new SubDepositOrderExample();
				sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(model.getOrderDtlId());
				List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
				if(subDepositOrders!=null && subDepositOrders.size()>0){
					Integer combineDepositOrderId = subDepositOrders.get(0).getCombineDepositOrderId();
					depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
					CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
					if(combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6){
						depositRefundOrder.setRefundCode("ZFB"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5){
						depositRefundOrder.setRefundCode("WX"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 3){
						depositRefundOrder.setRefundCode("YL"+sf.format(new Date())+"T");
					}else if(combineDepositOrder.getPaymentId() == 4){
						depositRefundOrder.setRefundCode("GZH"+sf.format(new Date())+"T");
					}
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
		
		closeSubOrder(model.getSubOrderId());
		
		
	}

	/**
	 * 撤销售后单
	 */
	public void close(CustomerServiceOrder model, String remark) {
		model.setStatus(Const.CUSTOMER_ORDER_STATUS_CANCEL);
		update(model);

		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		//插入一条售后记录
		CustomerServiceLog customerServiceLog=new CustomerServiceLog();
		customerServiceLog.setContent("客户超过7*24小时未操作，自动撤销退货单。");
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType("3");
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);
	}

	/**
	 * 换货单-拒绝申请
	 */
	public void rejectC(CustomerServiceOrder model, String remark) {
		model.setStatus(Const.CUSTOMER_ORDER_STATUS_REJECT);
		update(model);

		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		//插入一条售后记录
		CustomerServiceLog customerServiceLog=new CustomerServiceLog();
		customerServiceLog.setContent("商家超过48小时未操作，自动关闭换货单。");
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType("3");
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);
		
	}
	
	/**
	 * 换货单-同意申请
	 */
	public void agreeC(CustomerServiceOrder model, String remark) {
		model.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_C2);
		update(model);
		
		// 记录售后单状态流水
		customerServiceStatusLogService.save(model.getId(), model.getStatus(), model.getProStatus(), remark);
		
		
		//获取商品的售后模版
		OrderDtl orderDtl=orderDtlMapper.selectByPrimaryKey(model.getOrderDtlId());
		Product product = productMapper.selectByPrimaryKey(orderDtl.getProductId());
		ProductAfterTempletCustom productAfterTemplet=productAfterTempletCustomMapper.selectByPrimaryKey(product.getCsTempletId());
		if(productAfterTemplet==null){
			productAfterTemplet=new ProductAfterTempletCustom();
		}
		String content="商家超过48小时未操作，系统自动同意换货单申请,请客户寄件到以下地址：\n收货人："+productAfterTemplet.getRecipient()+"\n联系电话："+productAfterTemplet.getPhone()+"\n地址："+productAfterTemplet.getAreaName()+productAfterTemplet.getAddress();
		
		if(!StringUtil.isEmpty(productAfterTemplet.getRemarks())){
			content=content+"\n注意事项："+productAfterTemplet.getRemarks();
		}
		
		
		//插入一条售后记录
		CustomerServiceLog customerServiceLog=new CustomerServiceLog();
		customerServiceLog.setContent(content);
		customerServiceLog.setCreateDate(new Date());
		customerServiceLog.setDelFlag("0");
		customerServiceLog.setOperatorType("3");
		customerServiceLog.setServiceOrderId(model.getId());
		customerServiceLogService.insertSelective(customerServiceLog);
		
	}

    /**
     * 退货单-同意退款
     */
    public void agreeB4FromSupplier(CustomerServiceOrderCustom customerServiceOrderCustom) throws ArgException {
        CustomerServiceOrder customerServiceOrder = this.selectByPrimaryKey(customerServiceOrderCustom.getId());

		// 如果当前售后单流程状态非B4 状态非进行中 则不能执行以下操作
		if (!Const.CUSTOMER_ORDER_PRO_STATUS_B4.equals(customerServiceOrder.getProStatus()) || !Const.CUSTOMER_ORDER_STATUS_REFUNDING.equals(customerServiceOrder.getStatus())) {
			throw new ServiceException("该售后单数据错误，请刷新页面");
		}

        //如果已经存在退款单则不能重复退款
        CustomerServiceOrderExample customerServiceOrderExample = new CustomerServiceOrderExample();
        customerServiceOrderExample.createCriteria().andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
        List<CustomerServiceOrder> customerServiceOrders = dao.selectByExample(customerServiceOrderExample);
        List<Integer> customerServiceOrderIds = new ArrayList<Integer>();
        for (CustomerServiceOrder serviceOrder : customerServiceOrders) {
            customerServiceOrderIds.add(serviceOrder.getId());
        }
        RefundOrderExample refundOrderExample = new RefundOrderExample();
        refundOrderExample.createCriteria().andDelFlagEqualTo("0").andServiceOrderIdIn(customerServiceOrderIds).andOrderTypeEqualTo("1");
        int count = refundOrderService.countByExample(refundOrderExample);
        //如果已经存在退款单则不能重复退款
        if (count > 0) {
			throw new ServiceException("该售后单已处理，请勿重复操作");
        }
        Date now = new Date();

        // 状态更改为已完成 商家已同意退款（退货）
		customerServiceOrder.setSupplierRemarks(customerServiceOrderCustom.getSupplierRemarks());
        customerServiceOrder.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B5);
        this.update(customerServiceOrder);

        // 记录售后单状态流水
        CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
        customerServiceStatusLog.setCreateBy(customerServiceOrderCustom.getSupplierUserId());
        customerServiceStatusLog.setCreateDate(now);
        customerServiceStatusLog.setUpdateDate(now);
        customerServiceStatusLog.setDelFlag("0");
        customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
        customerServiceStatusLog.setStatus(Const.CUSTOMER_ORDER_STATUS_SUCCESS);
        customerServiceStatusLog.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B5);
        customerServiceStatusLog.setRemarks(StringUtil.unEscapeHtmlAndIllegal(customerServiceOrderCustom.getContent()));
        customerServiceStatusLogService.insertSelective(customerServiceStatusLog);

        //插入一条售后记录
        CustomerServiceLog customerServiceLog = new CustomerServiceLog();
        customerServiceLog.setCreateBy(customerServiceOrderCustom.getSupplierUserId());
        customerServiceLog.setCreateDate(now);
        customerServiceLog.setUpdateDate(now);
        customerServiceLog.setDelFlag("0");
        customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
        customerServiceLog.setOperatorType("4");// 供应商
        customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(customerServiceOrderCustom.getContent()));
        customerServiceLog.setRemarks(customerServiceOrderCustom.getSupplierRemarks());
        customerServiceLogService.insertSelective(customerServiceLog);

        // 新增售后单图片
        serviceLogPicService.insertSelective(customerServiceOrderCustom.getCustomOrderPics(), now, customerServiceOrderCustom.getSupplierUserId(), customerServiceLog.getId());

        // 插入一条退款记录
        RefundOrder refundOrder = new RefundOrder();

        SubOrder subOrder = subOrderMapper.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
        refundOrder.setCombineOrderId(subOrder.getCombineOrderId());
        refundOrder.setServiceType(Const.CUSTOMER_ORDER_TYPE_B);
        refundOrder.setServiceOrderId(customerServiceOrder.getId());
        refundOrder.setRefundAmount(customerServiceOrder.getAmount());
        refundOrder.setRefundAgreeDate(now);
        refundOrder.setRefundMethod("1");
        refundOrder.setTryTimes(0);
        refundOrder.setStatus("0");
        refundOrder.setCreateDate(new Date());
        refundOrder.setRemarks("供应商同意退货退款");
        refundOrder.setDelFlag("0");
        refundOrder.setOrderType("1");
        CombineOrder combineOrder = combineOrderMapper.selectByPrimaryKey(subOrder.getCombineOrderId());
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        if (combineOrder.getPaymentId() == 1 || combineOrder.getPaymentId() == 6) {
            refundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
        } else if (combineOrder.getPaymentId() == 2 || combineOrder.getPaymentId() == 5) {
            refundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
        } else if (combineOrder.getPaymentId() == 3) {
            refundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
        } else if (combineOrder.getPaymentId() == 4) {
            refundOrder.setRefundCode("GZH" + sf.format(new Date()) + "T");
        }
        refundOrderService.insertSelective(refundOrder);

        //更新订单明细状态为退款状态
        OrderDtl orderDtl4Update = new OrderDtl();
        orderDtl4Update.setProductStatus("3");
        orderDtl4Update.setId(customerServiceOrder.getOrderDtlId());
        orderDtlMapper.updateByPrimaryKeySelective(orderDtl4Update);

        //返还积分给用户
        OrderDtl orderDtl = orderDtlMapper.selectByPrimaryKey(customerServiceOrder.getOrderDtlId());

        if (orderDtl.getIntegralPreferential() != null && orderDtl.getIntegralPreferential().compareTo(new BigDecimal(0)) == 1) {
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

        if (customerServiceOrder.getDepositAmount() != null && customerServiceOrder.getDepositAmount().compareTo(new BigDecimal(0)) > 0) {
            RefundOrderExample e = new RefundOrderExample();
            RefundOrderExample.Criteria c = e.createCriteria();
            c.andDelFlagEqualTo("0");
            c.andServiceOrderIdEqualTo(customerServiceOrder.getId());
            c.andOrderTypeEqualTo("2");//2.预售定金总订单
            List<RefundOrder> ros = refundOrderService.selectByExample(e);
            if (ros != null && ros.size() > 0) {//已存在无需在生成

            } else {
                RefundOrder depositRefundOrder = new RefundOrder();
                depositRefundOrder.setDelFlag("0");
                depositRefundOrder.setCreateDate(now);
                depositRefundOrder.setUpdateDate(now);
                depositRefundOrder.setCombineOrderId(subOrder.getCombineOrderId());
                SubDepositOrderExample sdoe = new SubDepositOrderExample();
                sdoe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(subOrder.getMchtId()).andOrderDtlIdEqualTo(customerServiceOrder.getOrderDtlId());
                List<SubDepositOrder> subDepositOrders = subDepositOrderMapper.selectByExample(sdoe);
                if (subDepositOrders != null && subDepositOrders.size() > 0) {
                    Integer combineDepositOrderId = subDepositOrders.get(0).getCombineDepositOrderId();
                    depositRefundOrder.setCombineDepositOrderId(combineDepositOrderId);
                    CombineDepositOrder combineDepositOrder = combineDepositOrderMapper.selectByPrimaryKey(combineDepositOrderId);
                    if (combineDepositOrder.getPaymentId() == 1 || combineDepositOrder.getPaymentId() == 6) {
                        depositRefundOrder.setRefundCode("ZFB" + sf.format(new Date()) + "T");
                    } else if (combineDepositOrder.getPaymentId() == 2 || combineDepositOrder.getPaymentId() == 5) {
                        depositRefundOrder.setRefundCode("WX" + sf.format(new Date()) + "T");
                    } else if (combineDepositOrder.getPaymentId() == 3) {
                        depositRefundOrder.setRefundCode("YL" + sf.format(new Date()) + "T");
                    } else if (combineDepositOrder.getPaymentId() == 4) {
                        depositRefundOrder.setRefundCode("GZH" + sf.format(new Date()) + "T");
                    }
                }
                depositRefundOrder.setServiceType(customerServiceOrder.getServiceType());
                depositRefundOrder.setServiceOrderId(customerServiceOrder.getId());
                depositRefundOrder.setRefundAmount(customerServiceOrder.getDepositAmount());
                depositRefundOrder.setRefundAgreeDate(new Date());
                depositRefundOrder.setRefundMethod("1");
                depositRefundOrder.setTryTimes(0);
                depositRefundOrder.setStatus("0");
                depositRefundOrder.setOrderType("2");
                refundOrderService.insertSelective(depositRefundOrder);
            }
        }

        closeSubOrder(customerServiceOrder.getSubOrderId());
    }

    /**
     * 供应商端拒绝退货退款
     *
     * @param customerServiceOrderCustom
     * @throws ArgException
     */
    public void refuseB4FromSupplier(CustomerServiceOrderCustom customerServiceOrderCustom) throws ArgException {
        Date now = new Date();
        CustomerServiceOrder customerServiceOrder = this.selectByPrimaryKey(customerServiceOrderCustom.getId());

		// 如果当前售后单流程状态非B4 状态非进行中 则不能执行以下操作
        if (!Const.CUSTOMER_ORDER_PRO_STATUS_B4.equals(customerServiceOrder.getProStatus()) || !Const.CUSTOMER_ORDER_STATUS_REFUNDING.equals(customerServiceOrder.getStatus())) {
            throw new ServiceException("该售后单数据错误，请刷新页面");
        }
        // 更新售后单状态为已拒绝 商家不同意退款（退货）
        customerServiceOrder.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B6);
        customerServiceOrder.setStatus(Const.CUSTOMER_ORDER_STATUS_REJECT);
        customerServiceOrder.setSupplierRemarks(customerServiceOrderCustom.getSupplierRemarks());
        customerServiceOrder.setSupplierRejectReason(customerServiceOrderCustom.getSupplierRejectReason());
        customerServiceOrder.setUpdateDate(now);
        this.updateByPrimaryKeySelective(customerServiceOrder);

        // 记录售后单状态流水
        CustomerServiceStatusLog customerServiceStatusLog = new CustomerServiceStatusLog();
        customerServiceStatusLog.setCreateBy(customerServiceOrderCustom.getSupplierUserId());
        customerServiceStatusLog.setCreateDate(now);
        customerServiceStatusLog.setUpdateDate(now);
        customerServiceStatusLog.setDelFlag("0");
        customerServiceStatusLog.setServiceOrderId(customerServiceOrder.getId());
        customerServiceStatusLog.setStatus(Const.CUSTOMER_ORDER_STATUS_REJECT);
        customerServiceStatusLog.setProStatus(Const.CUSTOMER_ORDER_PRO_STATUS_B6);
        customerServiceStatusLog.setRemarks(StringUtil.unEscapeHtmlAndIllegal(customerServiceOrderCustom.getContent()));
        customerServiceStatusLogService.insertSelective(customerServiceStatusLog);

        //插入一条售后记录
        CustomerServiceLog customerServiceLog = new CustomerServiceLog();
        customerServiceLog.setCreateBy(customerServiceOrderCustom.getSupplierUserId());
        customerServiceLog.setCreateDate(now);
        customerServiceLog.setUpdateDate(now);
        customerServiceLog.setDelFlag("0");
        customerServiceLog.setServiceOrderId(customerServiceOrder.getId());
        customerServiceLog.setOperatorType("4");// 供应商
        customerServiceLog.setContent(StringUtil.unEscapeHtmlAndIllegal(customerServiceOrderCustom.getContent()));
        customerServiceLog.setRemarks(customerServiceOrderCustom.getSupplierRemarks());
        customerServiceLogService.insertSelective(customerServiceLog);

        // 新增售后单图片
        serviceLogPicService.insertSelective(customerServiceOrderCustom.getCustomOrderPics(), now, customerServiceOrderCustom.getSupplierUserId(), customerServiceLog.getId());

        SubOrder subOrder = subOrderService.selectByPrimaryKey(customerServiceOrder.getSubOrderId());
        CombineOrder combineOrder = combineOrderService.selectByPrimaryKey(subOrder.getCombineOrderId());
        SysAppMessage sysAppMessage = new SysAppMessage();
        sysAppMessage.setCreateBy(customerServiceOrderCustom.getSupplierUserId());
        sysAppMessage.setCreateDate(now);
        sysAppMessage.setUpdateDate(now);
        sysAppMessage.setDelFlag("0");
        sysAppMessage.setType(Const.APP_MESSAGE_TYPR_TRAN_MESSAGE);
        sysAppMessage.setExpressNo(subOrder.getExpressNo());
        sysAppMessage.setLinkType(Const.APP_MESSAGE_SERVICE_ORDER);
        sysAppMessage.setLinkId(customerServiceOrder.getId());
        sysAppMessage.setMemberId(combineOrder.getMemberId());
        sysAppMessage.setTitle(Const.APP_MSG_TITLE_REFUND);
        sysAppMessage.setContent("商家已经拒绝了你的退货退款申请");
        sysAppMessageService.insertSelective(sysAppMessage);

		CustomerServiceOrderCustom customForMemberInfo = customerServiceOrderCustomMapper.selectMemberIdAndPhone(customerServiceOrder.getId());
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(customForMemberInfo.getMemberId());
		// 判断该用户是否存在号码 如果不存在 则取订单中的收货人号码
		String mobile = StringUtil.isEmpty(memberInfo.getMobile()) ? customForMemberInfo.getReceiverPhone() : memberInfo.getMobile();
		if (!StringUtil.isMobile(mobile)) {
			throw new ArgException("------------该用户mobile:" + mobile + "不符合规定");
		}
		SmsBlackMobileExample smsMobileExample = new SmsBlackMobileExample();
		smsMobileExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		SmsWhiteMobileExample smsWhiteMobileExample = new SmsWhiteMobileExample(); //白名单号码
		smsWhiteMobileExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		if (smsBlackMobileService.countByExample(smsMobileExample) > 0 && smsWhiteMobileService.countByExample(smsWhiteMobileExample) <=0) {
			throw new ArgException("-------------非法短信用户mobile:" + mobile);
		}
		Sms sms = new Sms();
		sms.setMobile(mobile);
		sms.setSmsType("4");
		sms.setContent("商家不同意{退货退款},具体拒绝理由请尽快登录APP，在个人中心“退款/售后”找到相应的售后单进行查看");
		smsService.sendImmediately(sms);
    }








	public CustomerServiceOrder findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public CustomerServiceOrder save(CustomerServiceOrder model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public CustomerServiceOrder update(CustomerServiceOrder model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		CustomerServiceOrder model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<CustomerServiceOrder> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<CustomerServiceOrder> paginate(QueryObject queryObject) {
		CustomerServiceOrderExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<CustomerServiceOrder> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private CustomerServiceOrderExample builderQuery(QueryObject queryObject) {
		CustomerServiceOrderExample example = new CustomerServiceOrderExample();
		CustomerServiceOrderExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("subOrderId") != null){
			criteria.andSubOrderIdEqualTo(queryObject.getQueryToInt("subOrderId"));
		}
		if(queryObject.getQuery("orderDtlId") != null){
			criteria.andOrderDtlIdEqualTo(queryObject.getQueryToInt("orderDtlId"));
		}
		if(queryObject.getQuery("serviceType") != null){
			criteria.andServiceTypeEqualTo(queryObject.getQueryToStr("serviceType"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("proStatus") != null){
			criteria.andProStatusEqualTo(queryObject.getQueryToStr("proStatus"));
		}
		// 申请超过多少小时
		if(queryObject.getQuery("createDateBeforeHours") != null){
			int createDateBeforeHours = queryObject.getQueryToInt("createDateBeforeHours");
			criteria.andCreateDateLessThan(DateTime.now().minusHours(createDateBeforeHours).toDate());
		}
		// 某一状态时间超过多少小时
		if(queryObject.getQuery("updateDateBeforeHours") != null){
			int updateDateBeforeHours = queryObject.getQueryToInt("updateDateBeforeHours");
			criteria.andUpdateDateLessThan(DateTime.now().minusHours(updateDateBeforeHours).toDate());
		}
		if(queryObject.getQuery("createDateGe") != null){
			criteria.andCreateDateGreaterThanOrEqualTo(queryObject.getQueryToDate("createDateGe"));
		}
		if(queryObject.getQuery("serviceTypeIn") != null){
			List<String> serviceTypeList = queryObject.getQuery("serviceTypeIn");
			criteria.andServiceTypeIn(serviceTypeList);
		}
		if(queryObject.getQuery("statusIn") != null){
			List<String> statusList = queryObject.getQuery("statusIn");
			criteria.andStatusIn(statusList);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
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

	/**
	 * 关闭子定单，判断子订单的所有售后单(直赔单除外)是否都为同意退款状态，是的话要关闭订单	
	 * @param subOrderId
	 */
	public void closeSubOrder(Integer subOrderId) {
		OrderDtlExample orderDtlExample = new OrderDtlExample();
		orderDtlExample.createCriteria().andDelFlagEqualTo("0").andSubOrderIdEqualTo(subOrderId);
		List<OrderDtl> orderDtls = orderDtlMapper.selectByExample(orderDtlExample);
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
			subOrderMapper.updateByPrimaryKeySelective(subOrder4Update);

		}

	}
	
	public List<Map<String, Object>> selectPhoneWhileB2C2(){
		return customerServiceOrderCustomMapper.selectPhoneWhileB2C2();
	}
	
	
	public static void main(String[] args) {
		System.out.println(new BigDecimal(0));
	}



	public List<CustomerServiceOrder> getByCombineOrderId(Integer combineOrderId) {
		return customerServiceOrderCustomMapper.getByCombineOrderId(combineOrderId);
	}

}
