package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSettleCompareCustomMapper;
import com.jf.dao.MchtSettleCompareMapper;
import com.jf.entity.MchtSettleCompare;
import com.jf.entity.MchtSettleCompareCustom;
import com.jf.entity.MchtSettleCompareCustomExample;
import com.jf.entity.MchtSettleCompareExample;

@Transactional
@Service
public class MchtSettleCompareService extends BaseService<MchtSettleCompare, MchtSettleCompareExample> {

	@Autowired
	private MchtSettleCompareCustomMapper mchtSettleCompareCustomMapper;
	
	@Autowired
	private MchtSettleCompareMapper mchtSettleCompareMapper;
	
	
	 public int countByCustomExample(MchtSettleCompareCustomExample example) {
		 return mchtSettleCompareCustomMapper.countByCustomExample(example);
	 }

	 public List<MchtSettleCompareCustom> selectByCustomExample(MchtSettleCompareCustomExample example) {
		 return mchtSettleCompareCustomMapper.selectByCustomExample(example);
	 }
	
}
