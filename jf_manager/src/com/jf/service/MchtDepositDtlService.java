package com.jf.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DepositOrderMapper;
import com.jf.dao.MchtDepositDtlCustomMapper;
import com.jf.dao.MchtDepositDtlMapper;
import com.jf.dao.MchtDepositMapper;
import com.jf.entity.DepositOrder;
import com.jf.entity.MchtDeposit;
import com.jf.entity.MchtDepositDtl;
import com.jf.entity.MchtDepositDtlCustom;
import com.jf.entity.MchtDepositDtlCustomExample;
import com.jf.entity.MchtDepositDtlExample;

@Service
@Transactional
public class MchtDepositDtlService extends BaseService<MchtDepositDtl,MchtDepositDtlExample> {
	@Autowired
	private MchtDepositDtlMapper mchtDepositDtlMapper;
	
	@Resource
	private MchtDepositDtlCustomMapper mchtDepositDtlCustomMapper;
	
	@Resource
	private DepositOrderMapper depositOrderMapper;
	
	@Resource
	private MchtDepositMapper mchtDepositMapper;
	
	@Autowired
	public void setMchtDepositDtlMapper(MchtDepositDtlMapper mchtDepositDtlMapper) {
		super.setDao(mchtDepositDtlMapper);
		this.mchtDepositDtlMapper = mchtDepositDtlMapper;
	}
	
	public List<MchtDepositDtlCustom> selectMchtDepositDtlList(HashMap<String, Object> paramMap)
	{
		List<MchtDepositDtlCustom> list = mchtDepositDtlCustomMapper.selectMchtDepositDtlList(paramMap);
		return list;
	}
	
	public int mchtDepositDtlListCount(HashMap<String, Object> paramMap)
	{
		return mchtDepositDtlCustomMapper.mchtDepositDtlListCount(paramMap);
	}
	
	public List<MchtDepositDtlCustom> selectVioldateDepositDtlList(HashMap<String, Object> paramMap)
	{
		List<MchtDepositDtlCustom> list = mchtDepositDtlCustomMapper.selectViolateDepositDtlList(paramMap);
		return list;
	}
	
	public int violdateDepositDtlListCount(HashMap<String, Object> paramMap)
	{
		return mchtDepositDtlCustomMapper.violateDepositDtlListCount(paramMap);
	}

	public void saveMchtDepositDtlAndUpdateDepositOrder(
			MchtDepositDtl mchtDepositDtl, DepositOrder depositOrder,MchtDeposit mchtDeposit) {
		this.insertSelective(mchtDepositDtl);
		depositOrderMapper.updateByPrimaryKey(depositOrder);
		mchtDepositMapper.updateByPrimaryKey(mchtDeposit);
	}
	
	public int countByExample(MchtDepositDtlCustomExample example){
		return mchtDepositDtlCustomMapper.countByExample(example);
	}
	
	public List<MchtDepositDtlCustom> selectByExample(MchtDepositDtlCustomExample example){
		return mchtDepositDtlCustomMapper.selectByExample(example);
	}

	public List<MchtDepositDtlCustom> selectMchtDepositDtlCountList(HashMap<String, Object> paramMap) {
		return mchtDepositDtlCustomMapper.selectMchtDepositDtlCountList(paramMap);
	}

	public Integer mchtDepositDtlCountCount(HashMap<String, Object> paramMap) {
		return mchtDepositDtlCustomMapper.mchtDepositDtlCountCount(paramMap);
	}

	public Integer mchtDepositDtlCountEachDayCount(HashMap<String, Object> paramMap) {
		return mchtDepositDtlCustomMapper.mchtDepositDtlCountEachDayCount(paramMap);
	}

	public List<MchtDepositDtlCustom> selectMchtDepositDtlCountEachDayList(HashMap<String, Object> paramMap) {
		return mchtDepositDtlCustomMapper.selectMchtDepositDtlCountEachDayList(paramMap);
	}
}
