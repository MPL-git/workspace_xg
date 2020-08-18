package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.OrderViewlogCustomMapper;
import com.jf.dao.OrderViewlogMapper;
import com.jf.entity.OrderViewlog;
import com.jf.entity.OrderViewlogCustom;
import com.jf.entity.OrderViewlogCustomExample;
import com.jf.entity.OrderViewlogExample;

@Service
public class OrderViewlogService extends BaseService<OrderViewlog, OrderViewlogExample> {

	@Autowired
	private OrderViewlogMapper orderViewlogMapper;
	
	@Autowired
	private OrderViewlogCustomMapper orderViewlogCustomMapper;
	
	@Autowired
	public void setOrderViewlogMapper(OrderViewlogMapper orderViewlogMapper) {
		super.setDao(orderViewlogMapper);
		this.orderViewlogMapper = orderViewlogMapper;
	}
	
	public Integer orderViewlogExamplecountByExample(OrderViewlogCustomExample example) {
		return orderViewlogCustomMapper.orderViewlogExamplecountByExample(example);
	}

	public List<OrderViewlogCustom> orderViewlogCustomselectByExample(OrderViewlogCustomExample example) {
		return orderViewlogCustomMapper.orderViewlogCustomselectByExample(example);
	}
	
	public OrderViewlogCustom orderViewlogCustomselectByPrimaryKey(Integer id) {
		return orderViewlogCustomMapper.orderViewlogCustomselectByPrimaryKey(id);
	}
	
}
