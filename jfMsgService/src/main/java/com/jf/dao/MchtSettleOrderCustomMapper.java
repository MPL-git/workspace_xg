package com.jf.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtSettleSituation;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.PayToMchtLog;
@Repository
public interface MchtSettleOrderCustomMapper{
	List<OrderDtlCustom> selectNoSettleOrderDtlList(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	List<CustomerServiceOrderCustom> selectNoSettleCustomOrderList(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	List<MchtInfo> selectNoSettleMchtInfo(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	List<OrderDtlCustom> selectNoSituationCustomOrderDtlList(@Param("settleDate") String settleDate);
	List<CustomerServiceOrderCustom> selectNoSituationCustomOrderList(@Param("settleDate") String settleDate);
	List<PayToMchtLog> selectNoSituationPayLogList(@Param("settleDate") String settleDate);
	MchtSettleSituation selectSituationByMchtIdAndDate(@Param("mchtId") Integer mchtId,@Param("settleDate") String settleDate);
	List<Map<String, Object>> selectSituationDepositTotal(@Param("payDate") String payDate);
	List<Map<String, Object>> selectSituationDeductionDeposit(@Param("payDate") String payDate);
	List<OrderDtlCustom> selectNoSituationReturnOrderDtlList(@Param("settleDate") String settleDate);
	
	/**
	 * 
	 * @param settleDate
	 * @param dateType 1当期退款  ， 2跨期退款
	 * @return
	 */
	List<OrderDtlCustom> selectNoSituationCustomOrderListAB(@Param("settleDate") String settleDate,@Param("dateType")String dateType);
	
	/**
	 * 统计本月可计算 
	 * @param settleDate
	 * @return
	 */
	List<Map<String, Object>> selectCurrentMonthSettleAmount(@Param("settleDate") String settleDate);
	
	/**
	 *  统计跨月可计算 
	 * @param settleDate
	 * @return
	 */
	List<Map<String, Object>> selectAcrossMonthSettleAmount(@Param("settleDate") String settleDate);
	
	
	
	List<MchtInfo> selectNoSettleSituationMcht(@Param("settleDate") String settleDate);
	
//	根据订单明细id统计预售定金结算金额
	List<Map<String, Object>> selectTotalDepositSettleAmountByOrderDtlIds(@Param("orderDtlIds") List<Integer> orderDtlIds);
	
	
	List<Map<String, Object>> selectNoSettleSubDepositOrderList(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	
}