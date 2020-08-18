package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.RefundOrderCustomMapper;
import com.jf.dao.RefundOrderMapper;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefundOrderService extends BaseService<RefundOrder,RefundOrderExample> {
	@Autowired
	private RefundOrderMapper dao;
	
	@Autowired
	private RefundOrderCustomMapper refundOrderCustomMapper;
	
	@Autowired
	public void setRefundOrderMapper(RefundOrderMapper refundOrderMapper) {
		super.setDao(refundOrderMapper);
		this.dao = refundOrderMapper;
	}

}
