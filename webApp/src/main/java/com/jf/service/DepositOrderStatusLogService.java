package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DepositOrderStatusLogMapper;
import com.jf.entity.DepositOrderStatusLog;
import com.jf.entity.DepositOrderStatusLogExample;

@Service
@Transactional
public class DepositOrderStatusLogService extends BaseService<DepositOrderStatusLog, DepositOrderStatusLogExample> {

	@Autowired
	private DepositOrderStatusLogMapper depositOrderStatusLogMapper;
	
	@Autowired
	public void setDepositOrderStatusLogMapper(DepositOrderStatusLogMapper depositOrderStatusLogMapper) {
		super.setDao(depositOrderStatusLogMapper);
		this.depositOrderStatusLogMapper = depositOrderStatusLogMapper;
	}

	public void addLog(Integer subDepositOrderId, String status, Integer orderDtlId, Integer memberId) {
		DepositOrderStatusLog log = new DepositOrderStatusLog();
		log.setSubDepositOrderId(subDepositOrderId);
		log.setStatus(status);
		log.setOrderDtlId(orderDtlId);
		log.setCreateBy(memberId);
		add(log);
	}

	private void add(DepositOrderStatusLog log) {
		Date date = new Date();
		log.setCreateDate(date);
		log.setDelFlag("0");
		insertSelective(log);
	}
	
	
}
