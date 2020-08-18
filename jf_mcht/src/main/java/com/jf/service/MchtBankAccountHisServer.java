package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBankAccountHisCustomMapper;
import com.jf.dao.MchtBankAccountHisMapper;
import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtBankAccountHisCustom;
import com.jf.entity.MchtBankAccountHisExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtBankAccountHisServer extends BaseService<MchtBankAccountHis, MchtBankAccountHisExample> {
	@Autowired
	private MchtBankAccountHisMapper mchtBankAccountHisMapper;
	
	@Autowired
	private MchtBankAccountHisCustomMapper mchtBankAccountHisCustomMapper;
	
	@Autowired
	public void setMchtBankAccountHisMapper(MchtBankAccountHisMapper mchtBankAccountHisMapper) {
		super.setDao(mchtBankAccountHisMapper);
		this.mchtBankAccountHisMapper = mchtBankAccountHisMapper;
	}
	
	
	public List<MchtBankAccountHisCustom> selectMchtBankAccountHisCustomByExample(MchtBankAccountHisExample example){
		return mchtBankAccountHisCustomMapper.selectByExample(example);
	}
	public MchtBankAccountHisCustom selectMchtBankAccountHisCustomByPrimaryKey(Integer id){
		return mchtBankAccountHisCustomMapper.selectByPrimaryKey(id);
	}
}
