package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CombineDepositOrderCustomMapper;
import com.jf.dao.CombineDepositOrderMapper;
import com.jf.entity.CombineDepositOrder;
import com.jf.entity.CombineDepositOrderExample;

@Service
@Transactional
public class CombineDepositOrderService extends BaseService<CombineDepositOrder,CombineDepositOrderExample> {
	@Autowired
	private CombineDepositOrderMapper combineDepositOrderMapper;
	@Autowired
	private CombineDepositOrderCustomMapper combineDepositOrderCustomMapper;
	@Autowired
	public void setCombineDepositOrderMapper(CombineDepositOrderMapper combineDepositOrderMapper) {
		super.setDao(combineDepositOrderMapper);
		this.combineDepositOrderMapper = combineDepositOrderMapper;
	}
	public List<Map<String, Object>> combineDepositOrderCountEachDayList(HashMap<String, Object> paramMap) {
		return combineDepositOrderCustomMapper.combineDepositOrderCountEachDayList(paramMap);
	}
	
}
