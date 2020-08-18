package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderPreferentialInfoCustomMapper;
import com.jf.dao.OrderPreferentialInfoMapper;
import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoCustom;
import com.jf.entity.OrderPreferentialInfoExample;

@Service
@Transactional
public class OrderPreferentialInfoService extends BaseService<OrderPreferentialInfo,OrderPreferentialInfoExample> {
	@Autowired
	private OrderPreferentialInfoMapper orderPreferentialInfoMapper;
	@Autowired
	private OrderPreferentialInfoCustomMapper orderPreferentialInfoCustomMapper;
	
	@Autowired
	public void setOrderPreferentialInfoMapper(OrderPreferentialInfoMapper orderPreferentialInfoMapper) {
		super.setDao(orderPreferentialInfoMapper);
		this.orderPreferentialInfoMapper = orderPreferentialInfoMapper;
	}
	
	public int countOrderPreferentialInfoCustomByExample(OrderPreferentialInfoExample example){
		return orderPreferentialInfoCustomMapper.countByExample(example);
	}
    public List<OrderPreferentialInfoCustom> selectOrderPreferentialInfoCustomByExample(OrderPreferentialInfoExample example){
    	return orderPreferentialInfoCustomMapper.selectByExample(example);
    }
    public OrderPreferentialInfoCustom selectOrderPreferentialInfoCustomByPrimaryKey(Integer id){
    	return orderPreferentialInfoCustomMapper.selectByPrimaryKey(id);
    }
    public List<OrderPreferentialInfoCustom> selectOrderPreferentialInfoCustomByCombineOrder(Integer combineOrderId){
    	return orderPreferentialInfoCustomMapper.selectByCombineOrder(combineOrderId);
    }
    public List<OrderPreferentialInfoCustom> selectOrderPreferentialInfoCustomBySubOrder(Integer subOrderId){
    	return orderPreferentialInfoCustomMapper.selectBySubOrder(subOrderId);
    }
}
