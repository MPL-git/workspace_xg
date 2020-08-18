package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtDepositCustomMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtDepositService extends BaseService<MchtDeposit,MchtDepositExample> {
	@Autowired
	private MchtDepositMapper dao;
	
	@Autowired
	private MchtDepositCustomMapper mchtDepositCustomMapper;
	
	@Autowired
	public void setMchtDepositMapper(MchtDepositMapper mchtDepositMapper) {
		super.setDao(mchtDepositMapper);
		this.dao = mchtDepositMapper;
	}

	public MchtDeposit getMchtDepositByMchtId(Integer mchtId) {
		return mchtDepositCustomMapper.getMchtDepositByMchtId(mchtId);
	}

}
