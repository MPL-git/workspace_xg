package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WithdrawOrderStatusLogCustomMapper;
import com.jf.dao.WithdrawOrderStatusLogMapper;
import com.jf.entity.WithdrawOrderStatusLog;
import com.jf.entity.WithdrawOrderStatusLogExample;

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
	private WithdrawOrderStatusLogCustomMapper withdrawOrderStatusLogCustomMapper;

	@Autowired
	public void setWithdrawOrderStatusLogMapper(WithdrawOrderStatusLogMapper withdrawOrderStatusLogMapper) {
		this.setDao(withdrawOrderStatusLogMapper);
		this.withdrawOrderStatusLogMapper = withdrawOrderStatusLogMapper;
	}

	public void batchInsert(List<WithdrawOrderStatusLog> withdrawOrderStatusLogs) {
		withdrawOrderStatusLogCustomMapper.batchInsert(withdrawOrderStatusLogs);
	}
	
	
}
