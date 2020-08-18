package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.OrderPreferentialInfoCustomMapper;
import com.jf.dao.OrderPreferentialInfoMapper;
import com.jf.entity.OrderPreferentialInfo;
import com.jf.entity.OrderPreferentialInfoCustom;
import com.jf.entity.OrderPreferentialInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderPreferentialInfoService extends BaseService<OrderPreferentialInfo,OrderPreferentialInfoExample> {
	@Autowired
	private OrderPreferentialInfoMapper dao;
	
	@Autowired
	private OrderPreferentialInfoCustomMapper orderPreferentialInfoCustomMapper;
	
	@Autowired
	public void setOrderPreferentialInfoMapper(OrderPreferentialInfoMapper orderPreferentialInfoMapper) {
		super.setDao(orderPreferentialInfoMapper);
		this.dao = orderPreferentialInfoMapper;
	}

	public List<OrderPreferentialInfo> getOrderPreferentialInfosByOrderDtlId(Integer orderDtlId) {
		return orderPreferentialInfoCustomMapper.getOrderPreferentialInfosByOrderDtlId(orderDtlId);
	}

	public List<OrderPreferentialInfoCustom> getOrderPreferentialInfosByorderDtlIds(List<Integer> orderDtlIds) {
		return orderPreferentialInfoCustomMapper.getOrderPreferentialInfosByorderDtlIds(orderDtlIds);
	}
}
