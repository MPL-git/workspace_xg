package com.jf.dao;

import com.jf.entity.MchtInfoCustom;
import com.jf.entity.OrderDtlCustom;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

	List<OrderDtlCustom> getCombineOrderData(Map<String, Object> map);

	List<OrderDtlCustom> getUnComplateOrderNum(Map<String, Object> paramsMap);

	BigDecimal getSvipSaveAmount(Integer memberId);

	List<OrderDtlCustom> getSvipSaveAmountOrderList(Map<String, Object> paramMap);

	List<OrderDtlCustom> getSubMemberHistoryOrderList(Map<String, Object> paramMap);

	List<MchtInfoCustom> countSaleByMchtIdList(Map<String, Object> paramMap);

    Integer countByCombineOrderId(Integer combineOrderId);

}