package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.WithdrawOrderStatusLogMapper;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WithdrawOrderStatusLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:28:41 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WithdrawOrderStatusLogService extends BaseService<WithdrawOrderStatusLog, WithdrawOrderStatusLogExample> {
	@Autowired
	private WithdrawOrderStatusLogMapper withdrawOrderStatusLogMapper;

	@Autowired
	public void setWithdrawOrderStatusLogMapper(WithdrawOrderStatusLogMapper withdrawOrderStatusLogMapper) {
		this.setDao(withdrawOrderStatusLogMapper);
		this.withdrawOrderStatusLogMapper = withdrawOrderStatusLogMapper;
	}

	public void addLog(Integer withdrawOrderId, String status, Integer memberId) {
		WithdrawOrderStatusLog log = new WithdrawOrderStatusLog();
		log.setWithdrawOrderId(withdrawOrderId);
		log.setStatus(status);
		log.setCreateBy(memberId);
		log.setCreateDate(new Date());
		log.setDelFlag("0");
		insertSelective(log);
	}
	
	
}
