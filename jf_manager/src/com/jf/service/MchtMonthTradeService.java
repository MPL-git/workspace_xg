package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtMonthTradeCustomMapper;
import com.jf.dao.MchtMonthTradeMapper;
import com.jf.entity.MchtMonthTrade;
import com.jf.entity.MchtMonthTradeCustom;
import com.jf.entity.MchtMonthTradeCustomExample;
import com.jf.entity.MchtMonthTradeExample;

@Service
@Transactional
public class MchtMonthTradeService extends BaseService<MchtMonthTrade, MchtMonthTradeExample> {

	@Autowired
	private MchtMonthTradeMapper mchtMonthTradeMapper;
	
	@Autowired
	private MchtMonthTradeCustomMapper mchtMonthTradeCustomMapper;

	@Autowired
	public void setMchtMonthTradeMapper(MchtMonthTradeMapper mchtMonthTradeMapper) {
		super.setDao(mchtMonthTradeMapper);
		this.mchtMonthTradeMapper = mchtMonthTradeMapper;
	}
	
	public int countByCustomExample(MchtMonthTradeCustomExample example) {
		return mchtMonthTradeCustomMapper.countByCustomExample(example);
	}

    public List<MchtMonthTradeCustom> selectByCustomExample(MchtMonthTradeCustomExample example) {
    	return mchtMonthTradeCustomMapper.selectByCustomExample(example);
    }

    public MchtMonthTradeCustom selectByPrimaryCustomKey(Integer id) {
    	return mchtMonthTradeCustomMapper.selectByPrimaryCustomKey(id);
    }

}
