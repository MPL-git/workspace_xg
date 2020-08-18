package com.jf.dao;

import com.jf.entity.OrderDtl;
import com.jf.entity.OrderDtlCustom;
import com.jf.entity.OrderDtlCustomExample;
import com.jf.entity.OrderDtlExample;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface OrderDtlCustomMapper{
	public List<OrderDtlCustom>selectByExample(OrderDtlExample OrderDtlExample);
	
	public List<OrderDtlCustom> selectOrderDtlByExample(OrderDtlExample OrderDtlExample);
	
	public Integer countOrderDtlByExample(OrderDtlExample OrderDtlExample);

	public List<OrderDtlCustom> getOrderDtlsBySubOrderId(OrderDtlExample example);

	int querySaleQuantityByExample(OrderDtlExample example);

	public List<Integer> getSubOrderIdsByActivityId(Integer activityId);

	public int countOrderDtlCustomByExample(OrderDtlCustomExample example);

	public List<OrderDtlCustom> eachDaySaleData(HashMap<String, Object> paramMap);

	public List<HashMap<String, Object>> getProductSaleData(HashMap<String, Object> paramMap);

	public List<HashMap<String, Object>> getSingleProductActivitySaleData(HashMap<String, Object> paramMap);

	public List<HashMap<String, Object>> activityAreaSaleData(HashMap<String, Object> paramMap);

	public BigDecimal sumPlatformAndIntegralPreferential(Integer mchtSettleOrderId);

	public Integer countByCombineOrderId(Integer combineOrderId);

	public void updateDeliveryDateAndDeliveryStatus(Map<String, Object> paramMap);
	
	public void updateDeliveryDateAndDeliveryStatusByIds(Map<String, Object> paramMap);

	public List<OrderDtl> getList(int subOrderId);

    public List<OrderDtlCustom> selectOrderDtlsBySubOrderIds(@Param("subOrderIds") ArrayList<Integer> subOrderIds);

    List<OrderDtlCustom> selectByExampleCustom(int mchtSettleOrderId);
}