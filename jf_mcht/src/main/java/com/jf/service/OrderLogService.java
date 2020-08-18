package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.OrderLogCustomMapper;
import com.jf.dao.OrderLogMapper;
import com.jf.entity.OrderLog;
import com.jf.entity.OrderLogCustom;
import com.jf.entity.OrderLogCustomExample;
import com.jf.entity.OrderLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderLogService extends BaseService<OrderLog,OrderLogExample> {
	@Autowired
	private OrderLogMapper dao;
	
	@Autowired
	private OrderLogCustomMapper orderLogCustomMapper;
	
	@Autowired
	public void setOrderLogMapper(OrderLogMapper orderLogMapper) {
		super.setDao(orderLogMapper);
		this.dao = orderLogMapper;
	}

	public List<OrderLogCustom> selectOrderLogCustomByExample(OrderLogCustomExample example) {
		return orderLogCustomMapper.selectByExample(example);
	}
	
	public OrderLog getOrderLogBySubOrderIdAndStatus(Integer subOrderId,String status){
		Map map = new HashMap<>();
		map.put("subOrderId", subOrderId);
		map.put("status", status);
		return orderLogCustomMapper.getOrderLogBySubOrderIdAndStatus(map);
	}
}
