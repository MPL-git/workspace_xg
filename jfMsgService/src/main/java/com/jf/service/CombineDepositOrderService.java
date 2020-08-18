package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CombineDepositOrderMapper;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineDepositOrderExample;

@Service
@Transactional
public class CombineDepositOrderService extends BaseService<CombineDepositOrder, CombineDepositOrderExample> {

	@Autowired
	private CombineDepositOrderMapper combineDepositOrderMapper;
	
	@Autowired
	public void setCombineDepositOrderMapper(CombineDepositOrderMapper combineDepositOrderMapper) {
		super.setDao(combineDepositOrderMapper);
		this.combineDepositOrderMapper = combineDepositOrderMapper;
	}
	
	
}
