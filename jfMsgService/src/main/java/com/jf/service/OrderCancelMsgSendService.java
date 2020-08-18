package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.OrderCancelMsgSendMapper;
import com.jf.entity.OrderCancelMsgSend;
import com.jf.entity.OrderCancelMsgSendExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年9月4日 下午4:45:57 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class OrderCancelMsgSendService extends BaseService<OrderCancelMsgSend, OrderCancelMsgSendExample> {
	@Autowired
	private OrderCancelMsgSendMapper orderCancelMsgSendMapper;

	@Autowired
	public void setOrderCancelMsgSendMapper(OrderCancelMsgSendMapper orderCancelMsgSendMapper) {
		this.setDao(orderCancelMsgSendMapper);
		this.orderCancelMsgSendMapper = orderCancelMsgSendMapper;
	}
	
	
}
