package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoCustom;
import com.jf.entity.OrderPreferentialInfoExample;
@Repository
public interface OrderPreferentialInfoCustomMapper{
	public List<OrderPreferentialInfoCustom>selectByExample(OrderPreferentialInfoExample orderPreferentialInfoExample);

	public List<OrderPreferentialInfo> getOrderPreferentialInfosByOrderDtlId(Integer orderDtlId);

	public List<OrderPreferentialInfoCustom> getOrderPreferentialInfosByorderDtlIds(List<Integer> orderDtlIds);
	
}