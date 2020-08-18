package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.OrderLogMapper;
import com.jf.entity.OrderLog;
import com.jf.entity.OrderLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月13日 上午11:04:47 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class OrderLogService extends BaseService<OrderLog, OrderLogExample> {
	
	@Autowired
	private OrderLogMapper orderLogMapper;

	@Autowired
	public void setOrderLogMapper(OrderLogMapper orderLogMapper) {
		this.setDao(orderLogMapper);
		this.orderLogMapper = orderLogMapper;
	}

	public void insertOrderLog(Integer subOrderId, String status, Integer memberId) {
		OrderLog orderLog = new OrderLog();
		orderLog.setSubOrderId(subOrderId);
		orderLog.setStatus(status);
		orderLog.setCreateDate(new Date());
		orderLog.setCreateBy(memberId);
		orderLog.setDelFlag("0");
		orderLogMapper.insertSelective(orderLog);
	}
	
}
