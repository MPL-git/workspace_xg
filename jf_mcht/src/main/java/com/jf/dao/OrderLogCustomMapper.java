package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.OrderLog;
import com.jf.entity.OrderLogCustom;
import com.jf.entity.OrderLogExample;
@Repository
public interface OrderLogCustomMapper{
	public List<OrderLogCustom>selectByExample(OrderLogExample OrderDtlExample);

	public OrderLog getOrderLogBySubOrderIdAndStatus(Map<String, Object> map);
}