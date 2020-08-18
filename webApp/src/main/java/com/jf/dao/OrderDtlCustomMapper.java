package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderDtlCustom;
@Repository
public interface OrderDtlCustomMapper{

	Integer getOrderDtlCount(Map<String, Object> params);

	List<OrderDtlCustom> getOrderDtlList(Map<String, Object> params);

	List<OrderDtlCustom> getOrderCode(Map<String, Object> params);

	List<OrderDtlCustom> getOrderDtlInfoList(Map<String, Object> params);

	List<OrderDtlCustom> getCustomerServiceList(Map<String, Object> params);

	Integer getCustomerServiceCount(Map<String, Object> params);

	List<OrderDtlCustom> getProductInfoById(Integer orderDtlId);

	List<OrderDtlCustom> getOrderDtlInfoById(Integer combineOrderId);

	List<OrderDtlCustom> getOrderProductInfoList(Map<String, Object> params);

	List<OrderDtlCustom> findMchtAddress(Integer orderDtlId);
	
	Integer getPayQuantity(Map<String, Integer> qMap);

	Integer getAlreadyPaidCount(Map<String, Object> params);

	List<OrderDtlCustom> getSubMemberHistoryOrderList(Map<String, Object> paramMap);

	Integer getCollegeStudentSubOrderId(Integer memberId);

}