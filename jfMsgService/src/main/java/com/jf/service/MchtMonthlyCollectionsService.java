package com.jf.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtMonthlyCollectionsCustomMapper;
import com.jf.dao.MchtMonthlyCollectionsMapper;
import com.jf.dao.MchtSettleOrderCustomMapper;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.MchtMonthlyCollections;
import com.jf.entity.MchtMonthlyCollectionsExample;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PayToMchtLog;

@Service
@Transactional
public class MchtMonthlyCollectionsService extends BaseService<MchtMonthlyCollections, MchtMonthlyCollectionsExample> {
	
	private static Logger logger = LoggerFactory.getLogger(MchtMonthlyCollectionsService.class);
	
	@Autowired
	private MchtMonthlyCollectionsMapper mchtMonthlyCollectionsMapper;
	
	@Autowired
	private MchtMonthlyCollectionsCustomMapper mchtMonthlyCollectionsCustomMapper;
	
	@Autowired
	private MchtSettleOrderCustomMapper mchtSettleOrderCustomMapper;

	@Autowired
	public void setMchtMonthlyCollectionsMapper(MchtMonthlyCollectionsMapper mchtMonthlyCollectionsMapper) {
		super.setDao(mchtMonthlyCollectionsMapper);
		this.mchtMonthlyCollectionsMapper = mchtMonthlyCollectionsMapper;
	}
	
	public void generateMonthlyCollections(String collectionDate){

		
		if(StringUtil.isEmpty(collectionDate)){
			return;
		}
		
		Map<Integer, MchtMonthlyCollections> mchtMonthlyCollectionsMap=new HashMap<Integer, MchtMonthlyCollections>();
		//查出要统计的所有订单明细
		List<OrderDtlCustom> orderDtlList=mchtMonthlyCollectionsCustomMapper.selectNoMonthlyCollectionsOrderDtlList(collectionDate);
		MchtMonthlyCollections mchtMonthlyCollections;
		for(OrderDtlCustom orderDtlCustom:orderDtlList){
			mchtMonthlyCollections=mchtMonthlyCollectionsMap.get(orderDtlCustom.getMchtId());
			if(mchtMonthlyCollections==null){
				mchtMonthlyCollections=new MchtMonthlyCollections();
				mchtMonthlyCollections.setMchtId(orderDtlCustom.getMchtId());
				setMchtMonthlyCollectionsDefaultValues(mchtMonthlyCollections);
				
				
				mchtMonthlyCollectionsMap.put(orderDtlCustom.getMchtId(),mchtMonthlyCollections);
			}
			
			mchtMonthlyCollections.setOrderAmount(mchtMonthlyCollections.getOrderAmount().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getPayAmount())));
			mchtMonthlyCollections.setSettleAmount(mchtMonthlyCollections.getSettleAmount().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getSettleAmount())));
			
			mchtMonthlyCollections.setCollectionIntegralPreferential(mchtMonthlyCollections.getCollectionIntegralPreferential().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getIntegralPreferential())));
			mchtMonthlyCollections.setCollectionMchtPreferential(mchtMonthlyCollections.getCollectionMchtPreferential().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getMchtPreferential())));
			mchtMonthlyCollections.setCollectionPlatformPreferential(mchtMonthlyCollections.getCollectionPlatformPreferential().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getPlatformPreferential())));
			mchtMonthlyCollections.setCollectionProductAmount(mchtMonthlyCollections.getCollectionProductAmount().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity())))));
			mchtMonthlyCollections.setCollectionProductCount(mchtMonthlyCollections.getCollectionProductCount()+(orderDtlCustom.getId()==null?0:(orderDtlCustom.getQuantity())));
			mchtMonthlyCollections.setCollectionCommissionAmount(mchtMonthlyCollections.getCollectionCommissionAmount().add(orderDtlCustom.getId()==null?new BigDecimal(0):(orderDtlCustom.getCommissionAmount()==null?new BigDecimal(0):orderDtlCustom.getCommissionAmount())));
			
		}
		
		//统计所有直赔单
		List<CustomerServiceOrderCustom> customOrderList=mchtMonthlyCollectionsCustomMapper.selectCustomServiceOrderDList(collectionDate);
		for(CustomerServiceOrderCustom customerServiceOrder:customOrderList){
			mchtMonthlyCollections=mchtMonthlyCollectionsMap.get(customerServiceOrder.getMchtId());
			if(mchtMonthlyCollections==null){
				mchtMonthlyCollections=new MchtMonthlyCollections();
				mchtMonthlyCollections.setMchtId(customerServiceOrder.getMchtId());
				setMchtMonthlyCollectionsDefaultValues(mchtMonthlyCollections);
				
				
				mchtMonthlyCollectionsMap.put(customerServiceOrder.getMchtId(),mchtMonthlyCollections);
			}
			mchtMonthlyCollections.setRefundAmount(mchtMonthlyCollections.getRefundAmount().add(customerServiceOrder.getAmount()));
			
			
		}
		
		//统计本期付款金额，根据付款记录表来统计
		List<PayToMchtLog> payToMchtLogList=mchtMonthlyCollectionsCustomMapper.selectPayLogList(collectionDate);
		
		for(PayToMchtLog payToMchtLog:payToMchtLogList){
			mchtMonthlyCollections=mchtMonthlyCollectionsMap.get(payToMchtLog.getMchtId());
			if(mchtMonthlyCollections==null){
				mchtMonthlyCollections=new MchtMonthlyCollections();
				mchtMonthlyCollections.setMchtId(payToMchtLog.getMchtId());
				setMchtMonthlyCollectionsDefaultValues(mchtMonthlyCollections);
				
				mchtMonthlyCollectionsMap.put(payToMchtLog.getMchtId(),mchtMonthlyCollections);
			}
			mchtMonthlyCollections.setPayAmount(mchtMonthlyCollections.getPayAmount().add(payToMchtLog.getPayAmount()));
			
		}
		
		
		
		//统计抵缴保证金
		List<Map<String, Object>> situationDeductionDepositList=mchtSettleOrderCustomMapper.selectSituationDeductionDeposit(collectionDate);
		Map<Integer, BigDecimal> deductiondepositTotalMap=new HashMap<Integer, BigDecimal>();
		if(situationDeductionDepositList!=null&&situationDeductionDepositList.size()>0){
			for(Map<String, Object> deductiondepositTotal:situationDeductionDepositList){
				deductiondepositTotalMap.put((Integer)deductiondepositTotal.get("mchtId"), (BigDecimal)deductiondepositTotal.get("totalDeductionDeposit"));
			}
		}
		
		
		
		
		
		
		
//		//统计所有退款，换货单,实退金额
//		List<CustomerServiceOrderCustom> customServiceOrderABList=mchtMonthlyCollectionsCustomMapper.selectCustomServiceOrderABList(collectionDate);
//		for(CustomerServiceOrderCustom customerServiceOrder:customServiceOrderABList){
//			mchtMonthlyCollections=mchtMonthlyCollectionsMap.get(customerServiceOrder.getMchtId());
//			if(mchtMonthlyCollections==null){
//				mchtMonthlyCollections=new MchtMonthlyCollections();
//				mchtMonthlyCollections.setMchtId(customerServiceOrder.getMchtId());
//				setMchtMonthlyCollectionsDefaultValues(mchtMonthlyCollections);
//				
//				
//				mchtMonthlyCollectionsMap.put(customerServiceOrder.getMchtId(),mchtMonthlyCollections);
//			}
//			mchtMonthlyCollections.setReturnAmount(mchtMonthlyCollections.getReturnAmount().add(customerServiceOrder.getAmount()));
//			
//			
//		}
		
		
		
		//统计产生退货，退款单的订单明细，且状态为已退款
		List<OrderDtlCustom> orderDtlListAB=mchtMonthlyCollectionsCustomMapper.selectOrderDtlList4CustomServiceOrderAB(collectionDate);
		for(OrderDtlCustom orderDtlCustom:orderDtlListAB){
			mchtMonthlyCollections=mchtMonthlyCollectionsMap.get(orderDtlCustom.getMchtId());
			if(mchtMonthlyCollections==null){
				mchtMonthlyCollections=new MchtMonthlyCollections();
				mchtMonthlyCollections.setMchtId(orderDtlCustom.getMchtId());
				setMchtMonthlyCollectionsDefaultValues(mchtMonthlyCollections);
				
				
				mchtMonthlyCollectionsMap.put(orderDtlCustom.getMchtId(),mchtMonthlyCollections);
			}
			
			mchtMonthlyCollections.setReturnOrderAmount(mchtMonthlyCollections.getReturnOrderAmount().add(orderDtlCustom.getSettleAmount()));
			mchtMonthlyCollections.setRefundProductAmount(mchtMonthlyCollections.getRefundProductAmount().add(orderDtlCustom.getSalePrice().multiply(new BigDecimal(orderDtlCustom.getQuantity()))));
			mchtMonthlyCollections.setRefundProductCount(mchtMonthlyCollections.getRefundProductCount()+orderDtlCustom.getQuantity());
			mchtMonthlyCollections.setRefundMchtPreferential(mchtMonthlyCollections.getRefundMchtPreferential().add(orderDtlCustom.getMchtPreferential()));
			mchtMonthlyCollections.setRefundPlatformPreferential(mchtMonthlyCollections.getRefundPlatformPreferential().add(orderDtlCustom.getPlatformPreferential()));
			mchtMonthlyCollections.setRefundIntegralPreferential(mchtMonthlyCollections.getRefundIntegralPreferential().add(orderDtlCustom.getIntegralPreferential()));
			mchtMonthlyCollections.setRefundCommissionAmount(mchtMonthlyCollections.getRefundCommissionAmount().add(orderDtlCustom.getCommissionAmount()==null?new BigDecimal(0):orderDtlCustom.getCommissionAmount()));
			mchtMonthlyCollections.setReturnAmount(mchtMonthlyCollections.getReturnAmount().add(orderDtlCustom.getPayAmount()));
		}
		
		
		
		//生成每月收款情况记录
		for(Integer mchtId:mchtMonthlyCollectionsMap.keySet()){
			mchtMonthlyCollections=mchtMonthlyCollectionsMap.get(mchtId);
			mchtMonthlyCollections.setCollectionDate(collectionDate);

			//抵缴保证金
			mchtMonthlyCollections.setDeductionDepositTotal(deductiondepositTotalMap.get(mchtId)==null?new BigDecimal(0):deductiondepositTotalMap.get(mchtId));
			
			//查询期初未付金额=上期末的未付金额      商家入驻的第一个月 a=0
			Calendar calendar=Calendar.getInstance(); 
			calendar.set(Integer.valueOf(collectionDate.substring(0, 4)),Integer.valueOf(collectionDate.substring(5, 7))-1,1);
			calendar.add(Calendar.MONTH, -1);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			
			MchtMonthlyCollectionsExample mchtMonthlyCollectionsExample=new MchtMonthlyCollectionsExample();
			mchtMonthlyCollectionsExample.createCriteria().andMchtIdEqualTo(mchtId).andDelFlagEqualTo("0").andCollectionDateEqualTo(sdf.format(calendar.getTime()));
			List<MchtMonthlyCollections> lastMchtMonthlyCollections =this.selectByExample(mchtMonthlyCollectionsExample);
			if(lastMchtMonthlyCollections==null||lastMchtMonthlyCollections.size()==0){
				mchtMonthlyCollections.setBeginUnpay(new BigDecimal(0));
			}else{
				mchtMonthlyCollections.setBeginUnpay(lastMchtMonthlyCollections.get(0).getEndUnpay());
			}
			mchtMonthlyCollections.setNeedPayAmount(mchtMonthlyCollections.getSettleAmount().subtract(mchtMonthlyCollections.getReturnOrderAmount()).subtract(mchtMonthlyCollections.getRefundAmount()));
			
			
			mchtMonthlyCollections.setEndUnpay(mchtMonthlyCollections.getBeginUnpay().add(mchtMonthlyCollections.getSettleAmount()).subtract(mchtMonthlyCollections.getRefundAmount()).subtract(mchtMonthlyCollections.getReturnOrderAmount()).subtract(mchtMonthlyCollections.getPayAmount()).subtract(mchtMonthlyCollections.getDeductionDepositTotal()));
			
			
			mchtMonthlyCollections.setDiscount(new BigDecimal(0));
			mchtMonthlyCollections.setDiscountedEndNeedPay(mchtMonthlyCollections.getEndUnpay());
			
			mchtMonthlyCollections.setCreateDate(new Date());
			mchtMonthlyCollections.setDelFlag("0");
			
			this.insertSelective(mchtMonthlyCollections);
		}

		
		
		
		
	
		
	}

	private void setMchtMonthlyCollectionsDefaultValues(MchtMonthlyCollections mchtMonthlyCollections) {
		mchtMonthlyCollections.setBeginUnpay(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionCommissionAmount(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionMchtPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionIntegralPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionPlatformPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionPlatformPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionProductAmount(new BigDecimal(0));
		mchtMonthlyCollections.setCollectionProductCount(0);
		mchtMonthlyCollections.setCreateDate(new Date());
		mchtMonthlyCollections.setDeductionDepositTotal(new BigDecimal(0));
		mchtMonthlyCollections.setDelFlag("0");
		mchtMonthlyCollections.setDiscount(new BigDecimal(0));
		mchtMonthlyCollections.setDiscountedEndNeedPay(new BigDecimal(0));
		mchtMonthlyCollections.setEndUnpay(new BigDecimal(0));
		mchtMonthlyCollections.setNeedPayAmount(new BigDecimal(0));
		mchtMonthlyCollections.setOrderAmount(new BigDecimal(0));
		mchtMonthlyCollections.setPayAmount(new BigDecimal(0));
		
		mchtMonthlyCollections.setRefundAmount(new BigDecimal(0));
		mchtMonthlyCollections.setRefundCommissionAmount(new BigDecimal(0));
		mchtMonthlyCollections.setRefundIntegralPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setRefundMchtPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setRefundPlatformPreferential(new BigDecimal(0));
		mchtMonthlyCollections.setRefundProductAmount(new BigDecimal(0));
		mchtMonthlyCollections.setRefundProductCount(0);
		mchtMonthlyCollections.setReturnAmount(new BigDecimal(0));
		mchtMonthlyCollections.setReturnOrderAmount(new BigDecimal(0));
		
		mchtMonthlyCollections.setSettleAmount(new BigDecimal(0));
		
	}
}
