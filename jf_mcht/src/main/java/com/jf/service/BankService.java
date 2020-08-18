package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.BankMapper;
import com.jf.entity.Bank;
import com.jf.entity.BankExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService extends BaseService<Bank,BankExample> {
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	public void setBankMapper(BankMapper bankMapper) {
		super.setDao(bankMapper);
		this.bankMapper = bankMapper;
	}
}
