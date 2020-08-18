package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PayToMchtLog;
@Repository
public interface MchtMonthlyCollectionsCustomMapper{
	List<OrderDtlCustom> selectNoMonthlyCollectionsOrderDtlList(@Param("collectionDate") String collectionDate);
	
	//查询产生退货，退款单的订单明细，且状态为已退款
	List<OrderDtlCustom> selectOrderDtlList4CustomServiceOrderAB(@Param("collectionDate") String collectionDate);
	
	/**
	 * 查询未生成收款情况单的直赔单
	 * @param collectionDate
	 * @return
	 */
	List<CustomerServiceOrderCustom> selectCustomServiceOrderDList(@Param("collectionDate") String collectionDate);
	
	
	/**
	 * 查询未生成收款情况单的退款，退货单
	 * @param collectionDate
	 * @return
	 */
//	List<CustomerServiceOrderCustom> selectCustomServiceOrderABList(@Param("collectionDate") String collectionDate);
	
	
	/**
	 * 
	 * 查询商家收款情况单
	 * 
	 */
	MchtSettleSituation selectSituationByMchtIdAndDate(@Param("mchtId") Integer mchtId,@Param("settleDate") String settleDate);
	
	/**
	 * 查询付款记录
	 * @param settleDate
	 * @return
	 */
	List<PayToMchtLog> selectPayLogList(@Param("collectionDate") String collectionDate);
	
}