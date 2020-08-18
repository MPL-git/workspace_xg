package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.BankBranchMapper;
import com.jf.entity.BankBranch;
import com.jf.entity.BankBranchExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BankBranchService extends BaseService<BankBranch, BankBranchExample> {
	@Autowired
	private BankBranchMapper bankBranchMapper;
	@Autowired
	public void setBankBranchMapper(BankBranchMapper bankBranchMapper) {
		this.setDao(bankBranchMapper);
		this.bankBranchMapper = bankBranchMapper;
	}
	public List<Map<String, Object>> getBrandBranchInfos(Integer bankId, Integer areaId) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<BankBranch> bankBranchs = findModeByBankId(bankId,areaId);
		if(CollectionUtils.isNotEmpty(bankBranchs)){
			for (BankBranch bankBranch : bankBranchs) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("id", bankBranch.getId());
				dataMap.put("name", bankBranch.getBranchName());
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	private List<BankBranch> findModeByBankId(Integer bankId, Integer areaId) {
		BankBranchExample bankBranchExample = new BankBranchExample();
		bankBranchExample.createCriteria().andBankIdEqualTo(bankId).andAreaIdEqualTo(areaId).andDelFlagEqualTo("0");
		bankBranchExample.setOrderByClause("id");
		return selectByExample(bankBranchExample);
	}
	
	
}
