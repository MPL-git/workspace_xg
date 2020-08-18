package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SubDepositOrderCustomMapper;
import com.jf.dao.SubDepositOrderMapper;
import com.jf.entity.SubDepositOrder;
import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderCustomExample;
import com.jf.entity.SubDepositOrderExample;

@Service
@Transactional
public class SubDepositOrderService extends BaseService<SubDepositOrder,SubDepositOrderExample> {
	@Autowired
	private SubDepositOrderMapper dao;
	
	@Autowired
	private SubDepositOrderCustomMapper subDepositOrderCustomMapper;
	
	
	
	@Autowired
	public void setSubOrderMapper(SubDepositOrderMapper subDepositOrderMapper) {
		super.setDao(subDepositOrderMapper);
		this.dao = subDepositOrderMapper;
	}

	public int countSubDepositOrderCustomByExample(SubDepositOrderCustomExample example) {
		return subDepositOrderCustomMapper.countByExample(example);
	}

	public List<SubDepositOrderCustom> selectSubDepositOrderCustomByExample(SubDepositOrderCustomExample example) {
		return subDepositOrderCustomMapper.selectByExample(example);
	}

	public List<String> getBrandNamesByMchtId(Integer mchtId) {
		return subDepositOrderCustomMapper.getBrandNamesByMchtId(mchtId);
	}

	
}
