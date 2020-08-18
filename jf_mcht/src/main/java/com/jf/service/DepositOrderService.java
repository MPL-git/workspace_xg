package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DepositOrderCustomMapper;
import com.jf.dao.DepositOrderMapper;
import com.jf.entity.DepositOrder;
import com.jf.entity.DepositOrderCustom;
import com.jf.entity.DepositOrderCustomExample;
import com.jf.entity.DepositOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepositOrderService extends BaseService<DepositOrder,DepositOrderExample> {
	@Autowired
	private DepositOrderMapper dao;
	
	@Autowired
	private DepositOrderCustomMapper depositOrderCustomMapper;
	
	@Autowired
	private MchtDepositDtlService mchtDepositDtlService;
	
	@Autowired
	private MchtDepositService mchtDepositService;
	
	@Autowired
	public void setDepositOrderMapper(DepositOrderMapper depositOrderMapper) {
		super.setDao(depositOrderMapper);
		this.dao = depositOrderMapper;
	}

	public List<DepositOrderCustom> selectDepositOrderCustomByExample(DepositOrderCustomExample example) {
		return depositOrderCustomMapper.selectByExample(example);
	}

}
