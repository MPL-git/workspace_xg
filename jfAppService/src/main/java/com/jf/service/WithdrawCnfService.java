package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.WithdrawCnfMapper;
import com.jf.entity.WithdrawCnf;
import com.jf.entity.WithdrawCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:25:56 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WithdrawCnfService extends BaseService<WithdrawCnf, WithdrawCnfExample> {
	@Autowired
	private WithdrawCnfMapper withdrawCnfMapper;

	@Autowired
	public void setWithdrawCnfMapper(WithdrawCnfMapper withdrawCnfMapper) {
		this.setDao(withdrawCnfMapper);
		this.withdrawCnfMapper = withdrawCnfMapper;
	}
	
	
}
