package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBankAccountHisMapper;
import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtBankAccountHisExample;

@Service
@Transactional
public class MchtBankAccountHisService extends BaseService<MchtBankAccountHis, MchtBankAccountHisExample> {
	@Autowired
	private MchtBankAccountHisMapper mchtBankAccountHisMapper;

	@Autowired
	public void setMchtBankAccountHisMapper(MchtBankAccountHisMapper mchtBankAccountHisMapper) {
		super.setDao(mchtBankAccountHisMapper);
		this.mchtBankAccountHisMapper = mchtBankAccountHisMapper;
	}
}
