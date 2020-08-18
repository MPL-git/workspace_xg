package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.CustomerServiceOrderCustom;
import com.jf.entity.CustomerServiceOrderCustomExample;
import com.jf.entity.CustomerServiceOrderExample;
@Repository
public interface CustomerServiceOrderCustomMapper{
	
	List<CustomerServiceOrderCustom> selectByExample(CustomerServiceOrderExample example);
	
	public CustomerServiceOrder selectCustomerServiceOrderBySubOrderIdAndOrderDtlId(Map<String, Object> map);

	public int countByExample(CustomerServiceOrderCustomExample example);

	public int countCustomerServiceOrderByServiceTypeAndProStatusAndStatus(Map<String, Object> map);

	public CustomerServiceOrder getCustomerServiceOrderByOrderDtlId(int orderDtlId);

	public CustomerServiceOrder getDirectCompensationOrder(Map<String,Object> map);

	List<CustomerServiceOrder> getByCombineOrderId(Integer combineOrderId);

}