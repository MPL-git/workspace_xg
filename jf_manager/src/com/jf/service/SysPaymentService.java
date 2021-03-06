package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysPaymentMapper;
import com.jf.entity.SysPayment;
import com.jf.entity.SysPaymentExample;

@Service
@Transactional
public class SysPaymentService extends BaseService<SysPayment,SysPaymentExample> {
	@Autowired
	private SysPaymentMapper sysPaymentMapper;

	@Autowired
	public void setSysPaymentMapper(SysPaymentMapper sysPaymentMapper) {
		super.setDao(sysPaymentMapper);
		this.sysPaymentMapper = sysPaymentMapper;
	}

}
