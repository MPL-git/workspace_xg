package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BankMapper;
import com.jf.entity.Bank;
import com.jf.entity.BankExample;

@Service
@Transactional
public class BankService extends BaseService<Bank, BankExample> {
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	public void setBankMapper(BankMapper bankMapper) {
		this.setDao(bankMapper);
		this.bankMapper = bankMapper;
	}
	public List<Map<String, Object>> getBankInfo() {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Bank> banks = findModes();
		if(CollectionUtils.isNotEmpty(banks)){
			for (Bank bank : banks) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("id", bank.getId());
				dataMap.put("name", bank.getName());
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	private List<Bank> findModes() {
		BankExample bankExample = new BankExample();
		bankExample.setOrderByClause("sort");
		return selectByExample(bankExample);
	}
	
	
}
