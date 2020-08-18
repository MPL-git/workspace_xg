package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderLogCustomMapper;
import com.jf.dao.OrderLogMapper;
import com.jf.entity.OrderLog;
import com.jf.entity.OrderLogCustom;
import com.jf.entity.OrderLogExample;

@Service
@Transactional
public class OrderLogService extends BaseService<OrderLog,OrderLogExample> {
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private OrderLogCustomMapper orderLogCustomMapper;
	
	@Autowired
	public void setOrderLogMapper(OrderLogMapper orderLogMapper) {
		super.setDao(orderLogMapper);
		this.orderLogMapper = orderLogMapper;
	}
	
	public int countOrderLogCustomByExample(OrderLogExample example){
		return orderLogCustomMapper.countByExample(example);
	}
    public List<OrderLogCustom> selectOrderLogCustomByExample(OrderLogExample example){
    	return orderLogCustomMapper.selectByExample(example);
    }
    public OrderLogCustom selectOrderLogCustomByPrimaryKey(Integer id){
    	return orderLogCustomMapper.selectByPrimaryKey(id);
    }
}
