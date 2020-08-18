package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtDepositDtlCustomMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositDtlCustom;
import com.jf.entity.MchtDepositDtlCustomExample;
import com.jf.entity.MchtDepositDtlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtDepositDtlService extends BaseService<MchtDepositDtl,MchtDepositDtlExample> {
	@Autowired
	private MchtDepositDtlMapper dao;
	
	@Autowired
	private MchtDepositDtlCustomMapper mchtDepositDtlCustomMapper;
	
	@Autowired
	public void setMchtDepositDtlMapper(MchtDepositDtlMapper mchtDepositDtlMapper) {
		super.setDao(mchtDepositDtlMapper);
		this.dao = mchtDepositDtlMapper;
	}
	
	public List<MchtDepositDtlCustom> selectMchtDepositDtlCustomByExample(MchtDepositDtlCustomExample example){
		return mchtDepositDtlCustomMapper.selectByExample(example);
	}
	
	public int countMchtDepositDtlCustomByExample(MchtDepositDtlCustomExample example){
		return mchtDepositDtlCustomMapper.countByExample(example);
	}
}
