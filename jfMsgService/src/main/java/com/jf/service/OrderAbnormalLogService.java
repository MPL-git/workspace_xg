package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderAbnormalLogMapper;
import com.jf.entity.OrderAbnormalLog;
import com.jf.entity.OrderAbnormalLogExample;

@Service
@Transactional
public class OrderAbnormalLogService extends BaseService<OrderAbnormalLog, OrderAbnormalLogExample> {
	@Autowired
	private OrderAbnormalLogMapper orderAbnormalLogMapper;

	@Autowired
	public void setOrderAbnormalLogMapper(OrderAbnormalLogMapper orderAbnormalLogMapper) {
		this.setDao(orderAbnormalLogMapper);
		this.orderAbnormalLogMapper = orderAbnormalLogMapper;
	}
	
}
