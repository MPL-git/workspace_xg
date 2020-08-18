package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineDepositOrderCustom;
import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.SourceNiche;
@Repository
public interface OrderDtlCustomMapper{

	List<OrderDtlCustom> getOrderInfoAB(Map<String, Integer> params);

	List<OrderDtlCustom> getOrderInfoD(Map<String, Integer> params);

	Integer countByCombineOrderId(Integer combineOrderId);
	
	Integer getErrorOrderDtlCount(HashMap<String,Object> map);

	List<CombineDepositOrderCustom> getDepositRefundOrderInfo(Map<String, Integer> params);
	
	List<OrderDtl> selectDistributionOrderDtlList(@Param("beginDate") String beginDate,@Param("endDate") String endDate);

	/**
	 * @MethodName selectSalesVolume
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月5日 下午1:32:03
	 */
	List<OrderDtlCustom> selectSalesVolume(List<SourceNiche> list);
	
	
	/**
	 * @MethodName findSumMoneyAndCustomer
	 * @Description TODO
	 * @author yinrd
	 * @date 2019年8月9日 下午1:32:03
	 */
	List<OrderDtlCustom> findSumMoneyAndCustomer(List<Integer> list);

    int countUnDeliveryDtl(@Param("subOrderId") Integer subOrderId);

}