package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DepositOrderCustomMapper;
import com.jf.dao.DepositOrderMapper;
import com.jf.entity.DepositOrder;
import com.jf.entity.DepositOrderCustom;
import com.jf.entity.DepositOrderCustomExample;
import com.jf.entity.DepositOrderExample;

@Service
@Transactional
public class DepositOrderService extends BaseService<DepositOrder,DepositOrderExample> {
	@Autowired
	private DepositOrderMapper dao;
	
	@Autowired
	private DepositOrderCustomMapper depositOrderCustomMapper;
	
	@Autowired
	public void setDepositOrderMapper(DepositOrderMapper depositOrderMapper) {
		super.setDao(depositOrderMapper);
		this.dao = depositOrderMapper;
	}
	
	public int countDepositOrderCustomByExample(DepositOrderCustomExample example){
		return depositOrderCustomMapper.countByExample(example);
	}
	
	public List<DepositOrderCustom> selectDepositOrderCustomByExample(DepositOrderCustomExample example) {
		return depositOrderCustomMapper.selectByExample(example);
	}
	
	
}
