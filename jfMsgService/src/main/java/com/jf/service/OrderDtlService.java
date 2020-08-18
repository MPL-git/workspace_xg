package com.jf.service;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.exception.BizException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.DateUtil;
import com.jf.dao.OrderDtlCustomMapper;
import com.jf.dao.OrderDtlMapper;
import com.jf.entity.*;
import com.jf.entity.pay.WxRefundResuletModel;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class OrderDtlService extends BaseService<OrderDtl, OrderDtlExample> {
	private static Logger logger = LoggerFactory.getLogger(OrderDtlService.class);

	@Autowired
	private OrderDtlMapper orderDtlMapper;
	
	@Autowired
	private OrderDtlCustomMapper orderDtlCustomMapper;
	
	@Autowired
	private ProductItemService productItemService;
	@Autowired
	private CombineOrderService combineOrderService;
	@Autowired
	private IntegralGiveService integralGiveService;
	@Autowired
	private IntegralDtlService integralDtlService;
    @Resource
    private SubOrderService subOrderService;
    @Resource
    private OrderDtlService orderDtlService;
    @Resource
    private RefundOrderService refundOrderService;
    @Resource
	private CommonService commonService;
    @Resource
	private CustomerServiceOrderService customerServiceOrderService;
    @Resource
    private SingleProductActivityService singleProductActivityService;
    @Resource
    private SmsService smsService;
    @Resource
    private MchtInfoService mchtInfoService;
    @Resource
    private SysAppMessageService sysAppMessageService;
    @Resource
    private SysParamCfgService sysParamCfgService;
    @Resource
    private MemberAccountService memberAccountService;
    @Resource
    private MemberAccountDtlService memberAccountDtlService;
	
	@Autowired
	public void setOrderDtlMapper(OrderDtlMapper orderDtlMapper) {
		this.setDao(orderDtlMapper);
		this.orderDtlMapper = orderDtlMapper;
	}
	
	/**
	 * 订单完成
	 * @param customerServiceOrderAmount 
	 *
	 * @param subOrderId the subOrderId
	 * @param combineOrderId 
	 */
	public void complateBySubOrderId(SubOrder subOrder, BigDecimal customerServiceOrderAmount) {
		Integer subOrderId = subOrder.getId();
		QueryObject combineOrderObject = new QueryObject();
		combineOrderObject.addQuery("status", Const.COMBINE_ORDER_STATUS_PAID);
		combineOrderObject.addQuery("id", subOrder.getCombineOrderId());
		List<CombineOrder> combineOrders = combineOrderService.findList(combineOrderObject);
		BigDecimal payAmount = new BigDecimal("0");
		CombineOrder combineOrder = null;
		if (CollectionUtils.isNotEmpty(combineOrders)) {
			combineOrder = combineOrders.get(0);
		}else{
			logger.info("总订单状态未处于确认收货状态"+subOrder.getCombineOrderId());
			return;
		}
		
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("subOrderId", subOrder.getId());
		List<OrderDtl> list = findList(queryObject);
		Date now =new Date();
		for(OrderDtl orderDtl : list){
			if(orderDtl.getProductStatus() == null){
				orderDtl.setProductStatus(Const.ORDER_PRODUCT_STATUS_SUCCESS);
				orderDtl.setProductStatusDate(now);//订单明细商品完成时间
				update(orderDtl);
				payAmount = payAmount.add(orderDtl.getPayAmount());
			}
		}
		payAmount = payAmount.subtract(customerServiceOrderAmount);
		if(payAmount.intValue() > 0){
			//订单完成赠送积分
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(subOrder.getMchtId());
			// 特殊商家不赠送积分
			if(!commonService.listSpecMchtCode().contains(mchtInfo.getMchtCode())){
				IntegralDtlExample integralDtlExample = new IntegralDtlExample();
				integralDtlExample.createCriteria().andOrderIdEqualTo(subOrderId)
						.andBizTypeEqualTo("2").andDelFlagEqualTo("0").andTypeEqualTo(1)
						.andTallyModeEqualTo(Const.INTEGRAL_TALLY_MODE_INCOME);
				List<IntegralDtl> integralDtls = integralDtlService.selectByExample(integralDtlExample);
				if(CollectionUtils.isEmpty(integralDtls)){
					integralGiveService.giftIntegral(combineOrder.getMemberId(),payAmount,subOrderId,combineOrder);
				}
				//发送交易消息
				String appContent = "您的订单"+subOrder.getSubOrderCode()+"已经完成，恭喜您获得"+payAmount.intValue()+"积分";
				sysAppMessageService.add(Const.APP_MESSAGE_TYPR_TRAN_MESSAGE,Const.APP_MSG_TITLE_ORDER_COMPLATE,appContent,Const.APP_MESSAGE_INTEGRAL_MALL,subOrderId,subOrder.getCreateBy(),1,"0");
			}

		}
	}

	public OrderDtl findById(int id){
		return dao.selectByPrimaryKey(id);
	}

	public OrderDtl save(OrderDtl model){
		model.setCreateDate(new Date());
		dao.insertSelective(model);
		return model;
	}

	public OrderDtl update(OrderDtl model){
		model.setUpdateDate(new Date());
		dao.updateByPrimaryKeySelective(model);
		return model;
	}

	public void delete(int id){
		OrderDtl model = findById(id);
		if (model == null || model.getDelFlag().equals(Const.FLAG_TRUE)) {
			throw new BizException("ID为[" + id + "]的数据不存在");
		}
		model.setDelFlag(Const.FLAG_TRUE);
		update(model);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<OrderDtl> findList(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<OrderDtl> paginate(QueryObject queryObject) {
		OrderDtlExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<OrderDtl> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private OrderDtlExample builderQuery(QueryObject queryObject) {
		OrderDtlExample example = new OrderDtlExample();
		OrderDtlExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("subOrderId") != null){
			criteria.andSubOrderIdEqualTo(queryObject.getQueryToInt("subOrderId"));
		}
		if(queryObject.getQuery("isGive") != null){
			criteria.andIsGiveNotEqualTo(queryObject.getQueryToStr("isGive"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public List<OrderDtlCustom> getOrderInfoAB(Map<String, Integer> params) {
		
		return orderDtlCustomMapper.getOrderInfoAB(params);
	}

	public List<OrderDtlCustom> getOrderInfoD(Map<String, Integer> params) {
		
		return orderDtlCustomMapper.getOrderInfoD(params);
	}

	public void updateSku(SubOrder subOrder) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("subOrderId", subOrder.getId());
		List<OrderDtl> orderDtls = findList(queryObject);
		if(CollectionUtils.isNotEmpty(orderDtls)){
			for (OrderDtl orderDtl : orderDtls) {
				if(orderDtl.getProductItemId() == 0){
					continue;
				}
				productItemService.updateProductSkuByItemId(orderDtl);
				//必须先解冻库存再执行商品最小价格的更新
				productItemService.updateMinProductItemPrice(orderDtl.getProductId());
			}
		}

	}


	public void updateRefundSuccessInfo(WxRefundResuletModel wxRefundResuletModel, AlipayTradeRefundResponse response, RefundOrder refundOrder,
			String type) {
		Date date = new Date();
		refundOrder.setStatus("1");
		if(type.equals("1")){
			refundOrder.setWxRefundId(wxRefundResuletModel.getRefund_id());
		}else if(type.equals("2")){
			refundOrder.setZfbRefundId(response.getTradeNo());
		}
		refundOrder.setSuccessDate(date);
		refundOrder.setUpdateDate(date);
		refundOrderService.updateByPrimaryKeySelective(refundOrder);
		//1修改售后单据状态 2 新增售后流水表数据
		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.findById(refundOrder.getServiceOrderId());
		customerServiceOrderService.updateRefundSuccess(customerServiceOrder,"退款成功");
		if ("A".equals(customerServiceOrder.getServiceType())||"B".equals(customerServiceOrder.getServiceType())){
			OrderDtl orderDtl = findById(customerServiceOrder.getOrderDtlId());
			orderDtl.setRefundDate(refundOrder.getSuccessDate());
			update(orderDtl);
			SubOrderExample subOrderExample = new SubOrderExample();
			subOrderExample.createCriteria().andDelFlagEqualTo("0")
					.andIdEqualTo(customerServiceOrder.getSubOrderId()).andAuditStatusIn(Arrays.asList("2","4"));
			SubOrder subOrder = new SubOrder();
			subOrder.setStatus(Const.ORDER_STATUS_CLOSE);
			subOrderService.updateByExampleSelective(subOrder, subOrderExample);
		}
		
	}


	public Integer countByCombineOrderId(Integer combineOrderId) {
		return orderDtlCustomMapper.countByCombineOrderId(combineOrderId);
	}


	public void checkOrderDtl(){
		HashMap<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = sdf.format(new Date())+" 10:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String beginDate = df.format(DateUtil.getDateAfterAndBeginTime(new Date(), -1));
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		Integer count = orderDtlCustomMapper.getErrorOrderDtlCount(map);
		if(count>0){
			SysParamCfgExample e = new SysParamCfgExample();
			e.createCriteria().andParamCodeEqualTo("ORDER_DTL_ERROR_ALERT_PHONE");//订单商品明细异常提醒手机号
			List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(e);
			for(SysParamCfg sysParamCfg:sysParamCfgList){
				Sms sms=new Sms();
				sms.setMobile(sysParamCfg.getParamValue());
				sms.setSmsType("4");
				sms.setContent("订单商品明细共有"+count+"条异常，请及时排查。");
				smsService.sendImmediately(sms);
			}
		}
	}

	public List<CombineDepositOrderCustom> getDepositRefundOrderInfo(Map<String, Integer> depositParams) {
		
		return orderDtlCustomMapper.getDepositRefundOrderInfo(depositParams);
	}
	
	
	//拉新分润订单
	public void distributionOrder(String beginDate, String endDate) {
		
        List<OrderDtl> lisOrderDtls=orderDtlCustomMapper.selectDistributionOrderDtlList(beginDate, endDate);
        if (lisOrderDtls!=null && lisOrderDtls.size()>0) {
			for (OrderDtl orderDtl : lisOrderDtls) {
				 if (orderDtl.getDistributionMemberId()!=null) {
					 MemberAccount memberAccount=memberAccountService.selectMemberIdKey(orderDtl.getDistributionMemberId());
					 memberAccount.setProfitInviteBalance(memberAccount.getProfitInviteBalance()==null?new BigDecimal(0).add(orderDtl.getDistributionAmount()==null?new BigDecimal(0):(orderDtl.getDistributionAmount())):memberAccount.getProfitInviteBalance().add(orderDtl.getDistributionAmount()==null?new BigDecimal(0):(orderDtl.getDistributionAmount())));
					 memberAccount.setUpdateDate(new Date());
					 memberAccount.setUpdateBy(orderDtl.getDistributionMemberId());
					 memberAccountService.updateByPrimaryKeySelective(memberAccount);
					 
					
					 MemberAccountDtl memberAccountDtl=new MemberAccountDtl();
					 memberAccountDtl.setAccId(memberAccount.getId());
					 memberAccountDtl.setTallyMode("1");
					 memberAccountDtl.setAmount(orderDtl.getDistributionAmount());
					 memberAccountDtl.setBalance(memberAccount.getProfitInviteBalance());
					 memberAccountDtl.setBizType("9");
					 memberAccountDtl.setBizId(orderDtl.getId());
					 memberAccountDtl.setCreateDate(new Date());
					 memberAccountDtl.setCreateBy(memberAccount.getMemberId());
					 memberAccountDtlService.insertSelective(memberAccountDtl);
					 
					 
					 OrderDtl odDtl=orderDtlService.selectByPrimaryKey(memberAccountDtl.getBizId());
					 odDtl.setDistributionSettleStatus("1");
					 odDtl.setDistributionSettleDate(new Date());
					 odDtl.setUpdateDate(new Date());
					 orderDtlService.updateByPrimaryKey(odDtl);
					 
				}
			}
		}

	
   }

	public boolean existsUnDeliveryDtl(Integer subOrderId) {
		return orderDtlCustomMapper.countUnDeliveryDtl(subOrderId) > 0;
	}

}
