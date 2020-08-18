package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderProductSnapshotMapper;
import com.jf.entity.OrderProductSnapshot;
import com.jf.entity.OrderProductSnapshotExample;

@Service
@Transactional
public class OrderProductSnapshotService extends BaseService<OrderProductSnapshot, OrderProductSnapshotExample> {
	@Autowired
	private OrderProductSnapshotMapper orderProductSnapshotMapper;
	@Autowired
	public void setOrderProductSnapshotMapper(OrderProductSnapshotMapper orderProductSnapshotMapper) {
		this.setDao(orderProductSnapshotMapper);
		this.orderProductSnapshotMapper = orderProductSnapshotMapper;
	}
	
	
}
